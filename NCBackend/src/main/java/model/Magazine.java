package model;

import java.io.Serializable;
import javax.persistence.*;

import model.Appuser;

import java.util.List;
import java.util.Set;


/**
 * The persistent class for the magazine database table.
 * 
 */
@Entity
@NamedQuery(name="Magazine.findAll", query="SELECT m FROM Magazine m")
public class Magazine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String description;
	
	private boolean isopenaccess;

	private String title;

	//bi-directional many-to-one association to Article
	@OneToMany(mappedBy="magazine")
	private List<Article> articles;
	
	@OneToMany(mappedBy="magazine")
	private List<Membership> memberships;

	//bi-directional many-to-many association to Scientificfield
	@ManyToMany(mappedBy="magazines")
	private List<Scientificfield> scientificfields;
	
	@ManyToOne
	private Appuser appuser;

	public Magazine() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsopenaccess() {
		return this.isopenaccess;
	}

	public void setIsopenaccess(boolean isopenaccess) {
		this.isopenaccess = isopenaccess;
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

	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public List<Membership> getMemberships() {
		return this.memberships;
	}

	public void setMembershipes(List<Membership> memberships) {
		this.memberships = memberships;
	}

	public Article addArticle(Article article) {
		getArticles().add(article);
		article.setMagazine(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setMagazine(null);

		return article;
	}
	
	public Membership addMembership(Membership membership) {
		getMemberships().add(membership);
		membership.setMagazine(this);

		return membership;
	}

	public Membership removeMembership(Membership membership) {
		getArticles().remove(membership);
		membership.setMagazine(null);

		return membership;
	}

	public List<Scientificfield> getScientificfields() {
		return this.scientificfields;
	}

	public void setScientificfields(List<Scientificfield> scientificfields) {
		this.scientificfields = scientificfields;
	}

}