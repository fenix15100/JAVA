package modelFCM;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity

@Inheritance(strategy = InheritanceType.JOINED)
public class PersonaFCM {
	@Id
	private int idpersona;
	private String nom;
	private String dni;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Set<String> telefons;
	public int getIdpersona() {
		return idpersona;
	}
	public void setIdpersona(int idpersona) {
		this.idpersona = idpersona;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Set<String> getTelefons() {
		return telefons;
	}
	public void setTelefons(Set<String> telefons) {
		this.telefons = telefons;
	}
	public PersonaFCM(int idpersona, String nom, String dni, Set<String> telefons) {
		super();
		this.idpersona = idpersona;
		this.nom = nom;
		this.dni = dni;
		this.telefons = telefons;
	}
	public PersonaFCM() {
		super();
		this.idpersona = 0;
		this.nom = null;
		this.dni = null;
		this.telefons = null;
	}
	
	

}
