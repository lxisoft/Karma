package com.lxisoft.service;

import com.lxisoft.service.dto.MediaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.Optional;

/**
 * Service Interface for managing Media.
 */
public interface MediaService {

    /**
     * Save a media.
     *
     * @param mediaDTO the entity to save
     * @return the persisted entity
     * @throws IOException 
     */
    MediaDTO save(MediaDTO mediaDTO) throws IOException;

    /**
     * Get all the media.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MediaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" media.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MediaDTO> findOne(Long id);

    /**
     * Delete the "id" media.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * Get the "id" media by fileName.
     *
     * @param fileName the fileName of the entity
     * @return the entity
     */
    Optional<MediaDTO> findByFileName(String fileName);
    
    /**
     * Get all the media.
     *
     * @param needId of the media
     * @return the list of entities
     */
    Page<MediaDTO> findAllUrlByNeedId(Long needId,Pageable pageable);

    /**
     * Get all the media.
     *
     * @param helpId of the media
     * @return the list of entities
     */
	Page<MediaDTO> findAllUrlByHelpId(Long helpId, Pageable pageable);
}
