package com.github.malipio.eniro.search.itests;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.malipio.eniro.search.EniroSearchExampleApplication;
import com.github.malipio.eniro.search.domain.BasicSearchRequestBuilder;
import com.github.malipio.eniro.search.domain.BasicSearchResponse;
import com.github.malipio.eniro.search.service.CompanySearchService;


/**
 * Basic integration tests for JSP application.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EniroSearchExampleApplication.class)
public class CompanySearchServiceIntegrationTests {

	@Autowired
	private CompanySearchService searchService;
	
	@Test
	public void shouldCallEniroApiAndSearchForAdvokaat() {
		ResponseEntity<BasicSearchResponse> response = searchService.basicSearchSync(new BasicSearchRequestBuilder()
			.withSearchWord("advokaat")
			);
		
		assertThat(response, notNullValue());
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), notNullValue());
		assertThat(response.getBody().getTotalCount(), greaterThan(25L));
		assertThat(response.getBody().getAdverts().size(), is(25));
	}

}
