package com.lxisoft.service.impl;

import com.lxisoft.service.MediaService;
import com.lxisoft.domain.Media;
import com.lxisoft.repository.MediaRepository;
import com.lxisoft.service.dto.MediaDTO;
import com.lxisoft.service.mapper.MediaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

/**
 * Service Implementation for managing Media.
 */
@Service
@Transactional
public class MediaServiceImpl implements MediaService {

    private final Logger log = LoggerFactory.getLogger(MediaServiceImpl.class);

    private final MediaRepository mediaRepository;

    private final MediaMapper mediaMapper;
    
    @Value("${upload.path}")
    private String path;
    

    public MediaServiceImpl(MediaRepository mediaRepository, MediaMapper mediaMapper) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
    }

    /**
     * Save a media.
     *
     * @param mediaDTO the entity to save
     * @return the persisted entity
     * @throws IOException 
     */
    @Override
    public MediaDTO save(MediaDTO mediaDTO) throws IOException {
        log.debug("Request to save Media : {}", mediaDTO);
        
        
        mediaDTO.setFileName(mediaDTO.getFile().getOriginalFilename());	
        
        mediaDTO.setExtension(mediaDTO.getFile().getContentType());
       
        log.info("*******{}",mediaDTO.getFile().getContentType());
        
      
        
        if (!mediaDTO.getFile().isEmpty()) {

        	
            String fileName = mediaDTO.getFile().getOriginalFilename();
            InputStream is = mediaDTO.getFile().getInputStream();
            
            mediaDTO.setUrl(path+fileName);
          
           //mediaDTO.setUrl(newFile.getAbsolutePath());
            log.info("*************{}",fileName);
            
            log.info("*************path.get{}",Paths.get(fileName));
            
            log.info("**************getAbsolutePath:",Paths.get(fileName));
            
            Files.copy(is, Paths.get(path+fileName),
                   StandardCopyOption.REPLACE_EXISTING);
            
            
        } 

        Media media = mediaMapper.toEntity(mediaDTO);
        media = mediaRepository.save(media);
        return mediaMapper.toDto(media);
    }

    /**
     * Get all the media.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MediaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Media");
        return mediaRepository.findAll(pageable)
            .map(mediaMapper::toDto);
    }


    /**
     * Get one media by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MediaDTO> findOne(Long id) {
        log.debug("Request to get Media : {}", id);
        return mediaRepository.findById(id)
            .map(mediaMapper::toDto);
    }

    /**
     * Delete the media by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Media : {}", id);
        mediaRepository.deleteById(id);
    }
    
    /**
     * Get one media by id.
     *
     * @param fileName the fileName of the entity
     * @return the entity
     */
    public Optional<MediaDTO> findByFileName(String fileName){
    	 log.debug("Request to get Media : {}", fileName);
         return mediaRepository.findByFileName(fileName)
             .map(mediaMapper::toDto);
    }


    /**
     * Get all the media by needId.
     *
     * @param needId of the media
     * @return the list of entities
     */
	@Override
	public Page<MediaDTO> findAllUrlByNeedId(Long needId,Pageable pageable) {
		// TODO Auto-generated method stub
		return mediaRepository.findAllUrlByNeedId(needId,pageable)
				.map(mediaMapper::toDto);
	}

}
