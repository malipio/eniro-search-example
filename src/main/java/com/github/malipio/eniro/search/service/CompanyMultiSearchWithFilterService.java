package com.github.malipio.eniro.search.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.github.malipio.eniro.search.domain.BasicSearchRequestBuilder;
import com.github.malipio.eniro.search.domain.BasicSearchResponse;
import com.github.malipio.eniro.search.domain.BasicSearchResponse.Advert;

@Component
public class CompanyMultiSearchWithFilterService {
	private static final Logger log = LoggerFactory.getLogger(CompanyMultiSearchWithFilterService.class);
	
	private static class AdvertRegExpFilter implements Predicate<Advert> {

		private final Pattern filterRegexp;
		public AdvertRegExpFilter(Pattern filterRegexp) {
			this.filterRegexp = filterRegexp;
		}
		
		@Override
		public boolean test(Advert t) {
			
			String fieldsToCheck[] = {t.getCompanyReview(), t.getCompanyInfo().getCompanyName(), 
					t.getCompanyInfo().getCompanyText(), t.getCompanyInfo().getOrgNumber()};
			
			return Arrays.stream(fieldsToCheck).filter(s -> s != null).anyMatch(filterRegexp.asPredicate());
		}
	}
	
	@Autowired
	private CompanySearchService searchService;
	
	public List<Advert> multiSearchWithFilter(List<String> searchWords, Pattern filterRegexp) {
		log.info("invoked with searchWords={} and filter={}",searchWords, filterRegexp);
		List<ListenableFuture<ResponseEntity<BasicSearchResponse>>> futures = searchWords.stream()
			.map(word -> searchService.basicSearchAsync(
				new BasicSearchRequestBuilder().withSearchWord(word))).collect(Collectors.toList());
		// it could be one parallel stream
		return futures.parallelStream().map(f -> {
			try {
				return Optional.of(f.get());
			} catch (Exception e) {
				return Optional.<ResponseEntity<BasicSearchResponse>> empty();
			}
		}).filter(opt -> opt.isPresent()).map(opt -> opt.get())
				.filter(entity -> entity.getStatusCode().equals(HttpStatus.OK))
				.flatMap(entity -> entity.getBody().getAdverts().stream())
				.distinct() // according to equals on Advert class
				.filter(new AdvertRegExpFilter(filterRegexp))
				.collect(Collectors.toList());
		
	}

	public CompanySearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(CompanySearchService searchService) {
		this.searchService = searchService;
	}
}
