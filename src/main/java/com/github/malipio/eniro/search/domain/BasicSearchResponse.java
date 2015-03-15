package com.github.malipio.eniro.search.domain;

import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

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
			
			@GeneratePojoBuilder
			public CompanyInfo(String companyName, String orgNumber,
					String companyText) {
				super();
				this.companyName = companyName;
				this.orgNumber = orgNumber;
				this.companyText = companyText;
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
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((eniroId == null) ? 0 : eniroId.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Advert other = (Advert) obj;
			if (eniroId == null) {
				if (other.eniroId != null)
					return false;
			} else if (!eniroId.equals(other.eniroId))
				return false;
			return true;
		}
		
		@GeneratePojoBuilder
		public Advert(String eniroId, CompanyInfo companyInfo, String homepage,
				String facebook, String companyReview, String infoPageLink) {
			super();
			this.eniroId = eniroId;
			this.companyInfo = companyInfo;
			this.homepage = homepage;
			this.facebook = facebook;
			this.companyReview = companyReview;
			this.infoPageLink = infoPageLink;
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

	@GeneratePojoBuilder
	public BasicSearchResponse(String title, String query, long totalHits,
			long totalCount, long startIndex, short itemsPerPage,
			List<Advert> adverts) {
		super();
		this.title = title;
		this.query = query;
		this.totalHits = totalHits;
		this.totalCount = totalCount;
		this.startIndex = startIndex;
		this.itemsPerPage = itemsPerPage;
		this.adverts = adverts;
	}
	
	
	
}
