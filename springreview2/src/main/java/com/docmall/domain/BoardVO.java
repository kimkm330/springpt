package com.docmall.domain;

import java.util.Date;

public class BoardVO {
// BNO, TITLE, CONTENT, WRITER, REGDATE, VIEWCNT
	private int		bno;
	private String		title;
	private	String		bd_content;
	private	String		writer;
	private	Date		regdate;
	private	int			viewcnt;
	
	public int getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBD_CONTENT() {
		return bd_content;
	}
	public void setBD_CONTENT(String content) {
		this.bd_content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", bd_content=" + bd_content + ", writer=" + writer + ", regdate="
				+ regdate + ", viewcnt=" + viewcnt + "]";
	}
	
	
}
