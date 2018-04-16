package modelFCM;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class AdrecaFCM {
	@Id
	@GeneratedValue
	private int idadreca;
	
	private String poblacio;
	
	private String provinca;
	
	private String cp;
	
	private String domicili;

	public int getIdadreca() {
		return idadreca;
	}

	public void setIdadreca(int idadreca) {
		this.idadreca = idadreca;
	}

	public String getPoblacio() {
		return poblacio;
	}

	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}

	public String getProvinca() {
		return provinca;
	}

	public void setProvinca(String provinca) {
		this.provinca = provinca;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDomicili() {
		return domicili;
	}

	public void setDomicili(String domicili) {
		this.domicili = domicili;
	}

	public AdrecaFCM(int idadreca, String poblacio, String provinca, String cp, String domicili) {
		super();
		this.idadreca = idadreca;
		this.poblacio = poblacio;
		this.provinca = provinca;
		this.cp = cp;
		this.domicili = domicili;
	}

	public AdrecaFCM() {
		super();
		this.idadreca =0;
		this.poblacio = null;
		this.provinca = null;
		this.cp = null;
		this.domicili = null;
	}
	
	
	
	

}
