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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.codahale.metrics.annotation.Timed;
import com.lxisoft.client.karma.api.CategoryResourceApi;
import com.lxisoft.client.karma.model.CategoryDTO;
import com.lxisoft.client.karma.model.NeedDTO;

/**
 * TODO Provide a detailed description here
 * 
 * @author Sarangi Balu sarangibalu, sarangibalu.a@lxisoft.com
 */
@Controller
public class CategoryController {
	
	private final Logger log = LoggerFactory.getLogger(CategoryController.class);

	private static final String ENTITY_NAME = "karmaCategory";

	@Autowired
	CategoryResourceApi categoryResourceApi;

	/**
	 * POST /categories : Create a new category.
	 *
	 * @param categoryDTO
	 *            the categoryDTO to create
	 * @return the String value
	 */
	@PostMapping("/categories")
	@Timed
	public String createCategory(@ModelAttribute CategoryDTO categoryDTO, Model model) throws URISyntaxException {
		log.debug("REST request to save Category : {}", categoryDTO);		
		CategoryDTO category = categoryResourceApi.createCategoryUsingPOST(categoryDTO).getBody();
		model.addAttribute("category", category);
		return "category";

	}

	/**
	 * GET /categories : get all the categories.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the string value
	 */
	@GetMapping("/categories")
	@Timed
	public String getAllCategories(Pageable pageable, Model model) {
		log.debug("request to get a page of Categories");
		
		//List<CategoryDTO> categories = categoryResourceApi.getAllCategoriesUsingGET(offset, page, pageNumber, pageSize, paged, size, sort, sortSorted, sortUnsorted, unpaged);
		
		List<CategoryDTO> categories = categoryResourceApi.getAllCategoriesUsingGET(null, null, null, null, null, null, null, null, null, null).getBody();
		
		model.addAttribute("categories", categories);
		NeedDTO needDTO = new NeedDTO();
		needDTO.setCategoryList(new ArrayList<>(Arrays.asList(new CategoryDTO[] { new CategoryDTO() })));
		model.addAttribute("need", needDTO);
		return "post-help-request";

	}

	/**
	 * GET /categories/:id : get the "id" category.
	 *
	 * @param id
	 *            the id of the categoryDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/categories/{id}")
	@Timed
	public String getCategory(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get Category : {}", id);
		CategoryDTO categoryDTO = categoryResourceApi.getCategoryUsingGET(id).getBody();
		model.addAttribute("categoryById", categoryDTO);
		return "categoryById";
	}

}
