package com.lxisoft.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lxisoft.domain.Media;
import com.lxisoft.domain.NewsFeed;
import com.lxisoft.repository.NewsFeedRepository;
import com.lxisoft.service.MediaService;
import com.lxisoft.service.NewsFeedService;
import com.lxisoft.service.dto.MediaDTO;
import com.lxisoft.service.dto.NewsFeedDTO;
import com.lxisoft.service.mapper.NewsFeedMapper;

/**
 * Service Implementation for managing NewsFeed.
 */
@Service
@Transactional
public class NewsFeedServiceImpl implements NewsFeedService {

	private final Logger log = LoggerFactory.getLogger(NewsFeedServiceImpl.class);

	private final NewsFeedRepository newsFeedRepository;

	@Autowired
	private MediaService mediaService;

	private final NewsFeedMapper newsFeedMapper;

	public NewsFeedServiceImpl(NewsFeedRepository newsFeedRepository, NewsFeedMapper newsFeedMapper) {
		this.newsFeedRepository = newsFeedRepository;
		this.newsFeedMapper = newsFeedMapper;
	}

	/**
	 * Save a newsFeed.
	 *
	 * @param newsFeedDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public NewsFeedDTO save(NewsFeedDTO newsFeedDTO) {
		log.debug("Request to save NewsFeed : {}", newsFeedDTO);
		NewsFeed newsFeed = newsFeedMapper.toEntity(newsFeedDTO);
		newsFeed = newsFeedRepository.save(newsFeed);
		return newsFeedMapper.toDto(newsFeed);
	}

	/**
	 * Get all the newsFeeds.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<NewsFeedDTO> findAll(Pageable pageable) {
		log.debug("Request to get all NewsFeeds");
		return newsFeedRepository.findAll(pageable).map(newsFeedMapper::toDto);
	}

	/**
	 * Get one newsFeed by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<NewsFeedDTO> findOne(Long id) {
		log.debug("Request to get NewsFeed : {}", id);
		return newsFeedRepository.findById(id).map(newsFeedMapper::toDto);
	}

	/**
	 * Delete the newsFeed by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete NewsFeed : {}", id);
		newsFeedRepository.deleteById(id);
	}

	/**
	 * Get all the newsFeeds.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */

	@Override
	@Transactional(readOnly = true)
	public Page<NewsFeedDTO> findAllNewsFeeds(Pageable pageable) {
		log.debug("Request to get all NewsFeeds");

		Page<NewsFeed> newsFeedsPage = newsFeedRepository.findAll(pageable);
		List<NewsFeed> newsFeedsList = newsFeedsPage.getContent();
		Page<NewsFeedDTO> newsFeedsDtoPage = newsFeedsPage.map(newsFeedMapper::toDto);
		List<NewsFeedDTO> newsFeedsDtoList = newsFeedsDtoPage.getContent();

		// populating urls of newsfeed from returned domain objects to
		// corresponding dtos

		for (NewsFeed newsFeed : newsFeedsList) {
			Set<Media> medias = newsFeed.getAttachments();
			String url;
			Set<String> urls = new TreeSet<String>();
			for (Media m : medias) {
				url = m.getUrl();
				urls.add(url);
			}
			for (NewsFeedDTO newsFeedDto : newsFeedsDtoList) {
				if (newsFeed.getId() == newsFeedDto.getId()) {
					newsFeedDto.setAttachedFilesUrls(urls);
				}
			}
		}

		// Calculating and populating the postedBefore time for each newsFeed
		// dtos

		for (NewsFeedDTO newsFeedDto : newsFeedsDtoList) {
			Instant instant = Instant.now();
			Date newsfeedtime = null;
			if (newsFeedDto.getDate() != null) {
				newsfeedtime = Date.from(newsFeedDto.getDate());
			}
			Date current = Date.from(instant);
			long diffInSecond = 0l;
			if (newsfeedtime != null) {
				diffInSecond = (current.getTime() - newsfeedtime.getTime()) / 1000l;
			}
			long postedBefore = 0l;
			if (diffInSecond < 60l) {
				newsFeedDto.setPostedBefore(diffInSecond + " seconds ago");
			} else if (diffInSecond < 3600l) {
				postedBefore = diffInSecond / 60l;
				newsFeedDto.setPostedBefore(postedBefore + " minutes ago");
			} else if (diffInSecond < 86400l) {
				postedBefore = diffInSecond / 3600l;
				newsFeedDto.setPostedBefore(postedBefore + " hours ago");
			} else if (diffInSecond < 2592000l) {
				postedBefore = diffInSecond / 86400l;
				newsFeedDto.setPostedBefore(postedBefore + " days ago");
			} else if (diffInSecond < 31104000l) {
				postedBefore = diffInSecond / 2592000l;
				newsFeedDto.setPostedBefore(postedBefore + " months ago");
			} else {
				postedBefore = diffInSecond / 31104000l;
				newsFeedDto.setPostedBefore(postedBefore + " years ago");
			}

		}
		Page<NewsFeedDTO> newsFeedDtos = new PageImpl<NewsFeedDTO>(newsFeedsDtoList, pageable, newsFeedsDtoList.size());
		return newsFeedDtos;
	}

	/**
	 * Save a newsFeed and corresponding supported medias.
	 *
	 * @param newsFeedDTO
	 *            the entity to save
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException
	 * 
	 * @return the persisted entity
	 */
	@Override
	public NewsFeedDTO saveNewsFeed(NewsFeedDTO newsFeedDTO)
			throws URISyntaxException, IllegalStateException, IOException {
		log.debug("Request to save news feed");
		// TODO Auto-generated method stub
		if (newsFeedDTO.getDateInString() != null) {
			String parseDate = newsFeedDTO.getDateInString().replace(" ", "T").concat("Z");
			Instant dateInstant = Instant.parse(parseDate);
			newsFeedDTO.setDate(dateInstant);
		}
		NewsFeedDTO savedNewsFeedDto = save(newsFeedDTO);
		log.debug("Request to see news feed saved");
		MultipartFile[] attachedFiles = newsFeedDTO.getAttachedFiles();
		for (MultipartFile file : attachedFiles) {
			MediaDTO mediaDTO = new MediaDTO();
			mediaDTO.setFile(file);
			mediaDTO.setNewsFeedId(savedNewsFeedDto.getId());
			mediaService.save(mediaDTO);
			log.debug("Request news feed media saved");
		}
		return savedNewsFeedDto;
	}
}
