package com.example.DTO;
import java.util.ArrayList;
import java.util.List;

import model.Article;

public class ArticleDto {
	
	private long id;

	private String abstract_;

	private String keyWords;

	private String pdfurl;

	private String title;

	private MagazineDto magazine;

	private AppuserDto appuser;
	
	private String authorEmail;
	
	public ArticleDto() { }
	
	public ArticleDto(Article article) {
		
		this.setId(article.getId());
		this.setTitle(article.getTitle());
		this.setAbstract_(article.getAbstract_());
		this.setKeyWords(article.getKeywords());
		this.setPdfurl(article.getPdfurl());
		this.setAppuser(new AppuserDto(article.getAppuser()));
		this.setMagazine(new MagazineDto(article.getMagazine()));
			
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAbstract_() {
		return abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getPdfurl() {
		return pdfurl;
	}

	public void setPdfurl(String pdfurl) {
		this.pdfurl = pdfurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MagazineDto getMagazine() {
		return magazine;
	}

	public void setMagazine(MagazineDto magazine) {
		this.magazine = magazine;
	}

	public AppuserDto getAppuser() {
		return appuser;
	}

	public void setAppuser(AppuserDto appuser) {
		this.appuser = appuser;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}


	
}
