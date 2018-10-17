/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lxisoft.web;

import java.net.URISyntaxException;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.ApprovalStatusService;
import com.lxisoft.service.CategoryService;
import com.lxisoft.service.NeedService;
import com.lxisoft.service.UserCheckService;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.CategoryDTO;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.service.dto.UserCheckDTO;
import com.lxisoft.web.rest.errors.BadRequestAlertException;

/**
 * TODO Provide a detailed description here
 * 
 * @author Sarangi Balu sarangibalu, sarangibalu.a@lxisoft.com
 */
@Controller
public class NeedController {

	private final Logger log = LoggerFactory.getLogger(NeedController.class);

	private static final String ENTITY_NAME = "karmaNeed";

	private NeedService needService;

	@Autowired
	ApprovalStatusService approvalStatusService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserCheckService userCheckService;

	public NeedController(NeedService needService) {
		this.needService = needService;
	}

	/**
	 * POST /needs : Create a new need.
	 *
	 * @param needDTO
	 *            the needDTO to create
	 * @return the string value
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/needs")
	@Timed
	public String createNeed(@ModelAttribute NeedDTO needDTO, Model model) throws URISyntaxException {
		log.debug(" request to save Need : {}", needDTO);

		Set<CategoryDTO> categorySet = new HashSet<CategoryDTO>();

		if (needDTO.getId() != null) {
			throw new BadRequestAlertException("A new need cannot already have an ID", ENTITY_NAME, "idexists");
		}

		if (needDTO.getApprovalStatusId() == null) {

			Optional<ApprovalStatusDTO> approvalStatus = approvalStatusService.findByStatus("pending");

			long id = approvalStatus.get().getId();
			log.debug("***************{}" + id);
			needDTO.setApprovalStatusId(approvalStatus.get().getId());
		}

		needDTO.setCategories(new HashSet<CategoryDTO>(needDTO.getCategoryList()));

		Set<CategoryDTO> categories = needDTO.getCategories();

		for (CategoryDTO category : categories) {
			Long id = category.getId();
			CategoryDTO categoryDTO = categoryService.findOne(id).orElse(null);
			categorySet.add(categoryDTO);
		}

		needDTO.setCategories(categorySet);

		// parsing string to ISO_INSTANT format
		String parsedDate = needDTO.getDateInString().replaceAll(" ", "T").concat("Z");

		// creates a date instance of type instant from a string
		needDTO.setDate(Instant.parse(parsedDate));

		NeedDTO need = needService.save(needDTO);
		model.addAttribute("need", need);
		return "help-post-result";

	}

	/**
	 * PUT /needs : Updates an existing need.
	 *
	 * @param needDTO
	 *            the needDTO to update
	 * @return the string value, or with status 400 (Bad Request) if the needDTO
	 *         is not valid, or with status 500 (Internal Server Error) if the
	 *         needDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/needs")
	@Timed
	public String updateNeed(@ModelAttribute NeedDTO needDTO, Model model) throws URISyntaxException {
		log.debug("request to update Need : {}", needDTO);
		if (needDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Set<CategoryDTO> categorySet = new HashSet<CategoryDTO>();
		needDTO.setCategories(new HashSet<CategoryDTO>(needDTO.getCategoryList()));
		Set<CategoryDTO> categories = needDTO.getCategories();

		for (CategoryDTO category : categories) {
			Long id = category.getId();
			CategoryDTO categoryDTO = categoryService.findOne(id).orElse(null);
			categorySet.add(categoryDTO);
		}

		needDTO.setCategories(categorySet);
		NeedDTO needDto = needService.save(needDTO);
		model.addAttribute("need", needDto);
		model.addAttribute("message", approvalStatusService.findOne(needDTO.getApprovalStatusId()).orElse(null));
		return "approve-decline";
	}

	/**
	 * GET /needs : get all the needs.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */
	@GetMapping("/needs")
	@Timed
	public String getAllNeeds(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload, Model model) {
		log.debug("request to get a page of Needs");
		Page<NeedDTO> page;
		if (eagerload) {
			page = needService.findAllWithEagerRelationships(pageable);
		} else {
			page = needService.findAll(pageable);
		}
		List<NeedDTO> needs = page.getContent();
		
		for(NeedDTO need:needs){
			
			List<String> fileNameList=need.getFileNameList();
			//Page<MediaDTO> media=mediaService.findAllUrlByNeedId(need.getId(), pageable);
			
			log.info("*********need");
			
			//List<MediaDTO> mediaDtoList=(List<MediaDTO>) mediaService.findAllUrlByNeedId(need.getId(), pageable);
	
			Page<MediaDTO> mediaList=mediaService.findAllUrlByNeedId(need.getId(), pageable);
			
			List<MediaDTO> mediaDtoList=mediaList.getContent();
			
			for(MediaDTO media:mediaDtoList){
				
				String mediaUrl=media.getUrl();
				fileNameList.add(mediaUrl);
				log.info("*********media url{}",mediaUrl);
			
			}
		}
		
		model.addAttribute("needs", needs);
		return "home";

	}

	/**
	 * GET /needs : get all the needs by approvalStatus.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */

	@GetMapping("/home/{approvalStatus}")
	@Timed
	public String getAllNeedsByApprovedStatus(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "approvalStatus") String approvalStatus, Model model) {
		log.debug("request to get a page of Needs");
		Page<NeedDTO> page;
		if (eagerload) {
			page = needService.findAllWithEagerRelationships(pageable);
		} else {
			page = needService.findAllNeedsByApprovedStatus(pageable, approvalStatus);
		}
		List<NeedDTO> needs = page.getContent();
		
		int count=0;
		
		for(NeedDTO need:needs)
		{
			Page<UserCheckDTO> userCheckDTOs=userCheckService.findAllUserChecksByCheckedNeedId(pageable,need.getId());
			List<UserCheckDTO> userCheckDTOList = userCheckDTOs.getContent();
			
			for(UserCheckDTO userChecks:userCheckDTOList)
			{
				if(userChecks.getVoteType()=="postive")
				{
					count=count+1;
				}
			}
			
			need.setPercentageOfGenuineness(new Long((count/userCheckDTOList.size())*100));
		}
		
		model.addAttribute("needs", needs);
		if (approvalStatus.equals("approved"))
			return "home";
		else if (approvalStatus.equals("pending"))
			return "pending-requests";
		else
			return "home";
	}

	/**
	 * GET /needs/:id : get the "id" need.
	 *
	 * @param id
	 *            the id of the needDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/needs/statuses/{id}")
	@Timed
	public String getNeedWithStatuses(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get Need : {}", id);
		Pageable pageable = PageRequest.of(0, 20);

		NeedDTO needDTO = needService.findOne(id).orElse(null);

		model.addAttribute("need", needDTO);
		model.addAttribute("approvalStatuses", approvalStatusService.findAll(pageable).getContent());

		return "pending-need";
	}

	/**
	 * GET /needs/:id : get the "id" need.
	 *
	 * @param id
	 *            the id of the needDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/needs/{id}")
	@Timed
	public String getNeed(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get Need : {}", id);

		NeedDTO needDTO = needService.findOne(id).orElse(null);

		model.addAttribute("need", needDTO);

		return "need";
	}

}
