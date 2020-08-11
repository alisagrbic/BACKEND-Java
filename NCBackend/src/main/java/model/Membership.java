package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the membership database table.
 * 
 */
@Entity
@NamedQuery(name="Membership.findAll", query="SELECT m FROM Membership m")
public class Membership implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@ManyToOne
	private Appuser appuser;

	@ManyToOne
	private Magazine magazine;
	

	public Membership() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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