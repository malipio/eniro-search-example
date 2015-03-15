package com.github.malipio.eniro.search.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.malipio.eniro.search.domain.BasicSearchRequestBuilder;
import com.github.malipio.eniro.search.domain.BasicSearchResponse;

@Component
public class CompanySearchService {

	private static final Logger log = LoggerFactory.getLogger(CompanySearchService.class);
	
	@Value("${application.eniro.url:http://api.eniro.com/cs/search/basic}")
	private String apiUrl;
	
	@Value("${application.eniro.profile:pmalinow}")
	private String profile;
	
	@Value("${application.eniro.key:5097889399396675686}")
	private String key; 
	
	@Value("${application.eniro.version:1.1.3}")
	private String version;
	
	@Value("${application.eniro.country:se}")
	private String country;
	
	@Autowired
	private AsyncRestTemplate rest;
	
	public ListenableFuture<ResponseEntity<BasicSearchResponse>> basicSearchAsync(BasicSearchRequestBuilder request) {
		request.withProfile(profile).withKey(key).withVersion(version).withCountry(country);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl);
		for(Map.Entry<String, String> entry : request.build().entrySet())
			uriBuilder.queryParam(entry.getKey(), entry.getValue());
		log.info("request is {}", uriBuilder.build().toUriString());
		ListenableFuture<ResponseEntity<BasicSearchResponse>> responseFuture = rest.getForEntity(uriBuilder.build().toUri(), BasicSearchResponse.class);
		responseFuture.addCallback( r -> log.info("SUCCESS with response: {}",r), 
				r -> log.info("FAILURE with response: {}",r));
		return responseFuture;
	}
	
	public ResponseEntity<BasicSearchResponse> basicSearchSync(BasicSearchRequestBuilder request) {
		try {
			return this.basicSearchAsync(request).get();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
