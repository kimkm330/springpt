package com.docmall.util;

public class SearchCriteria extends Criteria
{
	// 글 검색기능 구현을 위한 변수 설정부분
	private String searchType; 	//title,content,writer.....
	private String keyword;		//검색어
	
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + ", toString()=" + super.toString()
				+ "]";
	}
}


