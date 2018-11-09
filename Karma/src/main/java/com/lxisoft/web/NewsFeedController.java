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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.MediaService;
import com.lxisoft.service.NewsFeedService;
import com.lxisoft.service.dto.NewsFeedDTO;
import com.lxisoft.web.rest.HelpResource;

@Controller
public class NewsFeedController {

	private final Logger log = LoggerFactory.getLogger(HelpResource.class);

	private static final String ENTITY_NAME = "karmaNewsFeed";

	@Autowired
	NewsFeedService newsFeedService;

	@Autowired
	MediaService mediaService;

	@GetMapping("/postnewsfeed")
	public String showPostNewsFeed(Model model) {
		// model.addAttribute("car", new Car());
		return "post-newsfeed";
	}

	@PostMapping("/postnewsfeed")
	@Timed
	public String postNewsFeed(@ModelAttribute NewsFeedDTO newsFeedDTO, @RequestParam MultipartFile[] attachedFiles,
			Model model) throws URISyntaxException, IllegalStateException, IOException {
		newsFeedDTO.setAttachedFiles(attachedFiles);
		NewsFeedDTO newsFeedDto = newsFeedService.saveNewsFeed(newsFeedDTO);
		return "post-newsfeed";
	}

	@GetMapping("/allNewsFeeds")
	@Timed
	public String getAllNewsFeeds(Pageable pageable, Model model) {
		log.debug("REST request to get a page of Books");
		Page<NewsFeedDTO> newsFeedDtoList = newsFeedService.findAllNewsFeeds(pageable);
		model.addAttribute("newsFeedList", newsFeedDtoList);
		return "show-newsfeed-demo";
	}

}
