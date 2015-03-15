package sample.jsp;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.malipio.eniro.search.EniroSearchExampleApplication;
import com.github.malipio.eniro.search.domain.BasicSearchResponse.Advert;
import com.github.malipio.eniro.search.service.CompanyMultiSearchWithFilterService;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EniroSearchExampleApplication.class)
public class CompanyMultiSearchWithFilterServiceIntegrationTests {

	@Autowired
	private CompanyMultiSearchWithFilterService multiSearchService;
	
	@Test
	public void shouldCallEniro() {
		
		List<Advert> adverts = multiSearchService.multiSearchWithFilter(
				Arrays.asList("advokat", "hotel", "ikea"), Pattern.compile(""));
		
		assertThat(adverts, notNullValue());
	}

}
