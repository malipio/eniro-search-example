/*
 * Copyright 2012-2014 the original author or authors.
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

package com.github.malipio.eniro.search.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.malipio.eniro.search.domain.BasicSearchResponse.Advert;
import com.github.malipio.eniro.search.domain.SearchObject;
import com.github.malipio.eniro.search.service.CompanyMultiSearchWithFilterService;
import com.github.malipio.eniro.search.service.SearchObjectRepository;

@Controller
public class SearchController {

	@Autowired
	private CompanyMultiSearchWithFilterService multiSearchService;
	
	@Autowired
	private SearchObjectRepository searchObjectRepo;

	
	@RequestMapping(value="/", method = {RequestMethod.GET})
	public String search(ModelMap model) {
		model.put("searchObjectHistory", searchObjectRepo.findAll());
		model.putIfAbsent("searchObject", new SearchObject());
		
		return "welcome";
	}
	
	@RequestMapping(value="/", method = {RequestMethod.POST})
	@Transactional
	public String search(@ModelAttribute("searchObject") SearchObject searchObject, ModelMap model) {
		searchObject.setSearchDate(new Date());
		searchObjectRepo.save(searchObject);
		List<Advert> adverts = multiSearchService.multiSearchWithFilter(
				searchObject.getSearchWordsAsList(),
				searchObject.getRegexpFilterAsPattern());
		model.put("adverts", adverts);
		
		return this.search(model);
	}
	
}
