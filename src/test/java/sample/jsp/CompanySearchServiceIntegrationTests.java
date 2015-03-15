package sample.jsp;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.malipio.eniro.search.EniroSearchExampleApplication;
import com.github.malipio.eniro.search.domain.BasicSearchRequestBuilder;
import com.github.malipio.eniro.search.domain.BasicSearchResponse;
import com.github.malipio.eniro.search.service.CompanySearchService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void shouldCallEniro() {
		ResponseEntity<BasicSearchResponse> response = searchService.basicSearchSync(new BasicSearchRequestBuilder()
			.withSearchWord("advokaat")
			);
		
		assertThat(response, notNullValue());
	}

}
