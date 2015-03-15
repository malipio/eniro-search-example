package com.github.malipio.eniro.search.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import com.github.malipio.eniro.search.domain.validator.RegExpPattern;

@Entity
public class SearchObject {
	
	@GeneratedValue
	@Id
	long id;
	
	@NotEmpty(message = "search words are required.")
	@Column(nullable=false)
	private String searchWords = "advokat, hotel, ikea";
	
	@NotEmpty(message = "regexp filter is required.")
	@RegExpPattern
	@Column(nullable=false)
	private String regexpFilter = ".";
	
	@Column(nullable=false)
	private Date searchDate;
	
	public String getSearchWords() {
		return searchWords;
	}
	public void setSearchWords(String searchWords) {
		this.searchWords = searchWords;
	}
	public String getRegexpFilter() {
		return regexpFilter;
	}
	public void setRegexpFilter(String regexpFilter) {
		this.regexpFilter = regexpFilter;
	}
	
	public Date getSearchDate() {
		return searchDate;
	}
	
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	public List<String> getSearchWordsAsList() {
		return Arrays.asList(getSearchWords().split(",\\s*"));
	}
	
	public Pattern getRegexpFilterAsPattern() {
		return Pattern.compile(getRegexpFilter());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
