package com.docmall.domain;

import java.util.Arrays;
import java.util.Date;

public class BoardVO {
	
	private Integer BD_NUM;
	private String BD_TITLE;
	private String BD_CONTENT;
	private String BD_WRITER;
	private Date BD_DATE;
	private int BD_VIEWCNT;
	private int BD_REPLYCNT;
	
	private String[] files;
	
		
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public int getBD_REPLYCNT() {
		return BD_REPLYCNT;
	}
	public void setBD_REPLYCNT(int BD_REPLYCNT) {
		this.BD_REPLYCNT = BD_REPLYCNT;
	}
	public Integer getBD_NUM() {
		return BD_NUM;
	}
	public void setBD_NUM(Integer BD_NUM) {
		this.BD_NUM = BD_NUM;
	}
	public String getBD_TITLE() {
		return BD_TITLE;
	}
	public void setBD_TITLE(String BD_TITLE) {
		this.BD_TITLE = BD_TITLE;
	}
	public String getBD_CONTENT() {
		return BD_CONTENT;
	}
	public void setBD_CONTENT(String BD_CONTENT) {
		this.BD_CONTENT = BD_CONTENT;
	}
	public String getBD_WRITER() {
		return BD_WRITER;
	}
	public void setBD_WRITER(String BD_WRITER) {
		this.BD_WRITER = BD_WRITER;
	}
	public Date getBD_DATE() {
		return BD_DATE;
	}
	public void setBD_DATE(Date BD_DATE) {
		this.BD_DATE = BD_DATE;
	}
	public int getBD_VIEWCNT() {
		return BD_VIEWCNT;
	}
	public void setBD_VIEWCNT(int BD_VIEWCNT) {
		this.BD_VIEWCNT = BD_VIEWCNT;
	}
	@Override
	public String toString() {
		return "BoardVO [BD_NUM=" + BD_NUM + ", BD_TITLE=" + BD_TITLE + ", BD_CONTENT="
				+ BD_CONTENT + ", BD_WRITER=" + BD_WRITER + ", BD_DATE=" + BD_DATE
				+ ", BD_VIEWCNT=" + BD_VIEWCNT + ", BD_REPLYCNT=" + BD_REPLYCNT
				+ ", files=" + Arrays.toString(files) + "]";
	}

}
