package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the scientificfield database table.
 * 
 */
@Entity
@NamedQuery(name="Scientificfield.findAll", query="SELECT s FROM Scientificfield s")
public class Scientificfield implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-many association to Appuser
	@ManyToMany
	@JoinTable(
		name="appuser_scifield"
		, joinColumns={
			@JoinColumn(name="scientificfield_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="appuser_id")
			}
		)
	private List<Appuser> appusers;

	//bi-directional many-to-many association to Magazine
	@ManyToMany
	@JoinTable(
		name="magazine_scifield"
		, joinColumns={
			@JoinColumn(name="scientificfield_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="magazine_id")
			}
		)
	private List<Magazine> magazines;

	public Scientificfield() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Appuser> getAppusers() {
		return this.appusers;
	}

	public void setAppusers(List<Appuser> appusers) {
		this.appusers = appusers;
	}

	public List<Magazine> getMagazines() {
		return this.magazines;
	}

	public void setMagazines(List<Magazine> magazines) {
		this.magazines = magazines;
	}

}