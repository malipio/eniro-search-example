package com.github.malipio.eniro.search;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import static org.mockito.Mockito.*;

import com.github.malipio.eniro.search.domain.AdvertBuilder;
import com.github.malipio.eniro.search.domain.BasicSearchRequestBuilder;
import com.github.malipio.eniro.search.domain.BasicSearchResponse;
import com.github.malipio.eniro.search.domain.BasicSearchResponse.Advert;
import com.github.malipio.eniro.search.domain.BasicSearchResponseBuilder;
import com.github.malipio.eniro.search.domain.CompanyInfoBuilder;
import com.github.malipio.eniro.search.service.CompanyMultiSearchWithFilterService;
import com.github.malipio.eniro.search.service.CompanySearchService;

/**
 *
 */
public class CompanyMultiSearchWithFilterServiceTests {

	private CompanyMultiSearchWithFilterService multiSearchService;
	
	private CompanySearchService searchService;
	
	@Before
	public void setupService() {
		multiSearchService = new CompanyMultiSearchWithFilterService();
		this.searchService = mock(CompanySearchService.class);
		multiSearchService.setSearchService(searchService);
	}
	
	private static ListenableFuture<ResponseEntity<BasicSearchResponse>> asFuture(BasicSearchResponse response, HttpStatus statusCode) {
		
		return new AsyncResult<>(new ResponseEntity<>(response, statusCode)); 
	}
	
	private static ListenableFuture<ResponseEntity<BasicSearchResponse>> ok(BasicSearchResponse response) {
		return asFuture(response, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldAggregateWithNoDuplicatesResults() {
		
		when(searchService.basicSearchAsync(Mockito.any(BasicSearchRequestBuilder.class)))
			.thenReturn(
					ok(new BasicSearchResponseBuilder()
						.withAdverts(Arrays.asList(new AdvertBuilder()
							.withEniroId("1")
							.withCompanyInfo(new CompanyInfoBuilder()
								.withCompanyName("A")
								.build())
							.build()))
						.build()),
						ok(new BasicSearchResponseBuilder()
						.withAdverts(Arrays.asList(new AdvertBuilder()
							.withEniroId("2")
							.withCompanyInfo(new CompanyInfoBuilder()
								.withCompanyName("B")
								.build())
							.build()))
						.build()),
						ok(new BasicSearchResponseBuilder()
						.withAdverts(Arrays.asList(new AdvertBuilder()
							.withEniroId("3")
							.withCompanyInfo(new CompanyInfoBuilder()
								.withCompanyName("C")
								.build())
							.build()))
						.build()),
						ok(new BasicSearchResponseBuilder()
						.withAdverts(Arrays.asList(new AdvertBuilder()
							.withEniroId("1")
							.withCompanyInfo(new CompanyInfoBuilder()
								.withCompanyName("A")
								.build())
							.build()))
						.build())
					);
				
		List<Advert> adverts = multiSearchService.multiSearchWithFilter(
				Arrays.asList("a", "b", "c", "d"), Pattern.compile("."));
		
		assertThat(adverts, notNullValue());
		assertThat(adverts.size(), is(3));
		assertThat(adverts.stream().map(ad -> ad.getEniroId())
				.collect(Collectors.toList()), hasItems("1", "2", "3"));
		assertThat(adverts.stream().map(ad -> ad.getCompanyInfo().getCompanyName())
				.collect(Collectors.toList()), hasItems("A", "B", "C"));
	}
	
	@Test
	public void shouldFilterResults() {
		
		when(searchService.basicSearchAsync(Mockito.any(BasicSearchRequestBuilder.class)))
			.thenReturn(
					ok(new BasicSearchResponseBuilder()
						.withAdverts(Arrays.asList(new AdvertBuilder()
							.withEniroId("1")
							.withCompanyInfo(new CompanyInfoBuilder()
								.withCompanyName("A")
								.build())
							.build(),
							new AdvertBuilder()
							.withEniroId("2")
							.withCompanyInfo(new CompanyInfoBuilder()
								.withCompanyName("B")
								.build())
							.build(),
							new AdvertBuilder()
							.withEniroId("3")
							.withCompanyInfo(new CompanyInfoBuilder()
								.withCompanyName("C")
								.build())
							.build())).build()));
				
		List<Advert> adverts = multiSearchService.multiSearchWithFilter(
				Arrays.asList("a"), Pattern.compile("A"));
		
		assertThat(adverts, notNullValue());
		assertThat(adverts.size(), is(1));
		assertThat(adverts.get(0).getCompanyInfo().getCompanyName(), is("A"));
	}

}
