package modelFCM;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity

public class ClientFCM extends PersonaFCM {
	
	private boolean enviar_publicitat;
	private List<AdrecaFCM> adreces;
	public boolean isEnviar_publicitat() {
		return enviar_publicitat;
	}
	public void setEnviar_publicitat(boolean enviar_publicitat) {
		this.enviar_publicitat = enviar_publicitat;
	}
	public List<AdrecaFCM> getAdreces() {
		return adreces;
	}
	public void setAdreces(List<AdrecaFCM> adreces) {
		this.adreces = adreces;
	}
	public ClientFCM(int idpersona, String nom, String dni, Set<String> telefons, boolean enviar_publicitat,
			List<AdrecaFCM> adreces) {
		super(idpersona, nom, dni, telefons);
		this.enviar_publicitat = enviar_publicitat;
		this.adreces = adreces;
	}

	public ClientFCM() {
		super(0, null, null, null);
		this.enviar_publicitat=false;
		this.adreces=null;
		
	}
	
	
	

}
