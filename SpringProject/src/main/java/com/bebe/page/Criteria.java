package com.bebe.page;

public class Criteria {
	private int page, perPageNum,StartPage;

	Criteria(){
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public void setStartPage(int startPage) {
		StartPage = startPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	public int getStartPage(){
		return (page-1)*perPageNum;
	}
}
