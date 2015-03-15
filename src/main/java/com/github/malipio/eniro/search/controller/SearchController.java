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

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.malipio.eniro.search.domain.BasicSearchResponse.Advert;
import com.github.malipio.eniro.search.domain.SearchObject;
import com.github.malipio.eniro.search.service.CompanyMultiSearchWithFilterService;

@Controller
public class SearchController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";
	
	@Autowired
	private CompanyMultiSearchWithFilterService multiSearchService;

	@RequestMapping("/")
	public String beforeSearch(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		model.put("searchObject", new SearchObject());
		return "welcome";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("searchObject") SearchObject searchObject, ModelMap model) {
		
		List<Advert> adverts = multiSearchService.multiSearchWithFilter(
				Arrays.asList(searchObject.getSearchWords().split(",\\s*")),
				Pattern.compile(searchObject.getRegexpFilter()));
		model.put("adverts", adverts);
		return "results";
	}
	
	@RequestMapping("/results")
	public String displayResults() {
		return "results";
	}

}
