package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the appuser database table.
 * 
 */
@Entity
@NamedQuery(name="Appuser.findAll", query="SELECT a FROM Appuser a")
public class Appuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String city;

	private String country;

	private String email;

	private String name;

	private String password;

	private String role;
	
	private String surname;
	
	private String username;
	

	@OneToMany(mappedBy="appuser")
	private List<Magazine> magazines;
	
	@OneToMany(mappedBy="appuser")
	private List<Membership> memberships;

	//bi-directional many-to-many association to Scientificfield
	@ManyToMany(mappedBy="appusers")
	private List<Scientificfield> scientificfields;

	//bi-directional many-to-one association to Article
	@OneToMany(mappedBy="appuser")
	private List<Article> articles;

	public Appuser() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public List<Magazine> getMagazines() {
		return this.magazines;
	}

	public void setMagazines(List<Magazine> magazines) {
		this.magazines = magazines;
	}
	
	public List<Membership> getMemberships() {
		return this.memberships;
	}

	public void setMembership(List<Membership> memberships) {
		this.memberships = memberships;
	}
	
	public Membership addMembership(Membership membership) {
		getMemberships().add(membership);
		membership.setAppuser(this);

		return membership;
	}

	public Membership removeMembership(Membership membership) {
		getArticles().remove(membership);
		membership.setAppuser(null);

		return membership;
	}

	public List<Scientificfield> getScientificfields() {
		return this.scientificfields;
	}

	public void setScientificfields(List<Scientificfield> scientificfields) {
		this.scientificfields = scientificfields;
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Article addArticle(Article article) {
		getArticles().add(article);
		article.setAppuser(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setAppuser(null);

		return article;
	}

}