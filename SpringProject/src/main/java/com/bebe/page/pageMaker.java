package com.bebe.page;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;

import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class pageMaker {
	Criteria cri = new Criteria();
	int startPage, endPage, displayPageNum = 10, totalPage;
	boolean prev, next;
	
	public String makeSearch(int page) {
		UriComponents uri = UriComponentsBuilder.newInstance()
		.queryParam("page", page)
		.queryParam("perPageNum", cri.getPerPageNum())
		.queryParam("searchType", ((Search)cri).getSearchType())
		.queryParam("keyword", encoding(((Search)cri).getKeyword())).build();
		return uri.toString();
	}
	
	public String makeQuery(int page) {
		UriComponents uri = UriComponentsBuilder.newInstance().queryParam("page", page)
		.queryParam("perPageNum", cri.getPerPageNum()).build();
		return uri.toString();
	}
	
	private String encoding(String str) {
		if(str==null||str.trim().length()==0)
			return "";
		try {
			return URLEncoder.encode(str, "UTF-8");
		}catch (Exception e) {
			return "";
		}
	}
	public void calc() {
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		startPage = (endPage-displayPageNum)+1;
		int tempEndPage = (int)(Math.ceil(totalPage/(double)cri.getPerPageNum()));
		if(endPage > tempEndPage)
			endPage = tempEndPage;
		prev = startPage==1?false:true;
		next = endPage*cri.getPerPageNum()>=totalPage?false:true;
	}
	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		calc();
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
}