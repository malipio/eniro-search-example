package com.github.malipio.eniro.search.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BasicSearchResponse {

	private String title;
	private String query;
	private long totalHits;
	private long totalCount;
	private long startIndex;
	private short itemsPerPage;

	public static class Advert {
		private String eniroId;
		
		public static class CompanyInfo {
			private String companyName;
			private String orgNumber;
			private String companyText;
			public String getCompanyName() {
				return companyName;
			}
			public String getOrgNumber() {
				return orgNumber;
			}
			public String getCompanyText() {
				return companyText;
			}
			@Override
			public String toString() {
				StringBuilder builder = new StringBuilder();
				builder.append("CompanyInfo [companyName=");
				builder.append(companyName);
				builder.append(", orgNumber=");
				builder.append(orgNumber);
				builder.append(", companyText=");
				builder.append(companyText);
				builder.append("]");
				return builder.toString();
			}
		}
		
		private CompanyInfo companyInfo;
		
		public static class Address {
			
		}
//		private Address address;
//		private String location;
//		private String phoneNumbers;
		private String homepage;
		private String facebook;
		private String companyReview;
		private String infoPageLink;
		
		public String getEniroId() {
			return eniroId;
		}
		public CompanyInfo getCompanyInfo() {
			return companyInfo;
		}
		public String getHomepage() {
			return homepage;
		}
		public String getFacebook() {
			return facebook;
		}
		public String getCompanyReview() {
			return companyReview;
		}
		public String getInfoPageLink() {
			return infoPageLink;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Advert [eniroId=");
			builder.append(eniroId);
			builder.append(", companyInfo=");
			builder.append(companyInfo);
			builder.append(", homepage=");
			builder.append(homepage);
			builder.append(", facebook=");
			builder.append(facebook);
			builder.append(", companyReview=");
			builder.append(companyReview);
			builder.append(", infoPageLink=");
			builder.append(infoPageLink);
			builder.append("]");
			return builder.toString();
		}
		
	}

	private List<Advert> adverts;

	public String getTitle() {
		return title;
	}

	public String getQuery() {
		return query;
	}

	public long getTotalHits() {
		return totalHits;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public long getStartIndex() {
		return startIndex;
	}

	public short getItemsPerPage() {
		return itemsPerPage;
	}

	public List<Advert> getAdverts() {
		return adverts;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BasicSearchResponse [title=");
		builder.append(title);
		builder.append(", query=");
		builder.append(query);
		builder.append(", totalHits=");
		builder.append(totalHits);
		builder.append(", totalCount=");
		builder.append(totalCount);
		builder.append(", startIndex=");
		builder.append(startIndex);
		builder.append(", itemsPerPage=");
		builder.append(itemsPerPage);
		builder.append(", adverts=");
		builder.append(adverts);
		builder.append("]");
		return builder.toString();
	}
	
	
}
