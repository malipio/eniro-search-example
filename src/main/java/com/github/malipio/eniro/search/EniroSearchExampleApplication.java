/*
 * Copyright 2012-2013 the original author or authors.
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

package com.github.malipio.eniro.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.AsyncRestTemplate;

import com.github.malipio.eniro.search.service.CompanySearchService;

@SpringBootApplication
public class EniroSearchExampleApplication extends SpringBootServletInitializer {

	@Bean
	public CompanySearchService searchService() {
		return new CompanySearchService();
	}
	
	@Bean 
	public AsyncRestTemplate asyncRestTemplate() {
		return new AsyncRestTemplate();
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EniroSearchExampleApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EniroSearchExampleApplication.class, args);
	}
	
	

}