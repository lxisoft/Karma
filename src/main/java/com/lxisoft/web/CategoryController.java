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
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.CategoryService;
import com.lxisoft.service.dto.CategoryDTO;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.web.rest.errors.BadRequestAlertException;

/**
 * TODO Provide a detailed description here
 * 
 * @author Sarangi Balu sarangibalu, sarangibalu.a@lxisoft.com
 */
@Controller
public class CategoryController {
	private final Logger log = LoggerFactory.getLogger(CategoryController.class);

	private static final String ENTITY_NAME = "karmaCategory";

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

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
		if (categoryDTO.getId() != null) {
			throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
		}
		CategoryDTO category = categoryService.save(categoryDTO);
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
		Page<CategoryDTO> page = categoryService.findAll(pageable);
		List<CategoryDTO> categories = page.getContent();
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
		Optional<CategoryDTO> categoryDTO = categoryService.findOne(id);
		model.addAttribute("categoryById", categoryDTO);
		return "categoryById";
	}

}
