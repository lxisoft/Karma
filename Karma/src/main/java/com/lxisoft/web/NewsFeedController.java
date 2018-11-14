package com.lxisoft.web;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.MediaService;
import com.lxisoft.service.NewsFeedService;
import com.lxisoft.service.dto.NewsFeedDTO;
import com.lxisoft.web.rest.errors.BadRequestAlertException;

/**
 * TODO Provide a detailed description here
 * 
 * @author Muhammed Ruhail, muhammed.ruhail@lxisoft.com
 */

@Controller
public class NewsFeedController {

	private final Logger log = LoggerFactory.getLogger(NewsFeedController.class);

	private static final String ENTITY_NAME = "karmaNewsFeed";

	@Autowired
	NewsFeedService newsFeedService;

	@Autowired
	MediaService mediaService;

	/**
	 * GET /postnewsfeed : to create model.
	 *
	 * @param model
	 *            the form model
	 * @return the string value
	 */

	@GetMapping("/postnewsfeed")
	public String showPostNewsFeed(Model model) {
		// model.addAttribute("car", new Car());
		return "post-newsfeed";
	}

	/**
	 * POST /postnewsfeed : Create a new need.
	 *
	 * @param newsFeedDTO
	 *            the newsFeedDTO to create
	 * @return the string value
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException
	 * @throws IllegalStateException
	 */

	@PostMapping("/postnewsfeed")
	@Timed
	public String postNewsFeed(@ModelAttribute NewsFeedDTO newsFeedDTO, @RequestParam MultipartFile[] attachedFiles,
			Model model) throws URISyntaxException, IllegalStateException, IOException {
		newsFeedDTO.setAttachedFiles(attachedFiles);
		NewsFeedDTO newsFeedDto = newsFeedService.saveNewsFeed(newsFeedDTO);
		model.addAttribute("newsFeed", newsFeedDto);
		return "post-newsfeed";
	}

	/**
	 * GET /allNewsFeeds : get all the newsfeeds.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the string value
	 */

	@GetMapping("/allNewsFeeds")
	@Timed
	public String getAllNewsFeeds(Pageable pageable, Model model) {
		log.debug("REST request to get newsFeeds");
		Page<NewsFeedDTO> newsFeedDtoList = newsFeedService.findAllNewsFeeds(pageable);
		model.addAttribute("newsFeedList", newsFeedDtoList);
		return "show-newsfeed-demo";
	}

	/**
	 * POST /postnewsfeed : Create a new need.
	 *
	 * @param newsFeedDTO
	 *            the newsFeedDTO to create
	 * @return the string value
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException
	 * @throws IllegalStateException
	 */

	@PutMapping("/updatenewsfeed")
	@Timed
	public String updateNewsFeed(@ModelAttribute NewsFeedDTO newsFeedDTO, @RequestParam MultipartFile[] attachedFiles,
			Model model) throws URISyntaxException, IllegalStateException, IOException {
		if (newsFeedDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		newsFeedDTO.setAttachedFiles(attachedFiles);
		NewsFeedDTO newsFeedDto = newsFeedService.saveNewsFeed(newsFeedDTO);
		model.addAttribute("newsFeed", newsFeedDto);
		return "post-newsfeed";
	}

}
