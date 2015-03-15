package com.github.malipio.eniro.search.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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

	@RequestMapping(value="/enirotest", method = {RequestMethod.GET})
	public String search(ModelMap model) {
		model.put("searchObjectHistory", searchObjectRepo.findAll());
		model.putIfAbsent("searchObject", new SearchObject());
		
		return "search";
	}
	
	@RequestMapping(value="/enirotest", method = {RequestMethod.POST})
	@Transactional
	public String search(@Valid SearchObject searchObject, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return this.search(model);
		}
		searchObject.setSearchDate(new Date());
		searchObjectRepo.save(searchObject);
		List<Advert> adverts = multiSearchService.multiSearchWithFilter(
				searchObject.getSearchWordsAsList(),
				searchObject.getRegexpFilterAsPattern());
		model.put("adverts", adverts);
		
		return this.search(model);
	}
	
}
