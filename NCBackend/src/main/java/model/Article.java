package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the article database table.
 * 
 */
@Entity
@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String abstract_;
	
	private String pdfurl;

	private String title;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	private Appuser appuser;

	//bi-directional many-to-one association to Magazine
	@ManyToOne
	private Magazine magazine;
	
	@Column(name="keywords")
	private String keywords;

	public Article() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPdfurl() {
		return this.pdfurl;
	}

	public void setPdfurl(String pdfurl) {
		this.pdfurl = pdfurl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Appuser getAppuser() {
		return this.appuser;
	}

	public void setAppuser(Appuser appuser) {
		this.appuser = appuser;
	}

	public Magazine getMagazine() {
		return this.magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

}