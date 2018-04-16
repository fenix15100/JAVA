package modelFCM;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity

public class ComandaVendaFCM {
	
	@Id
	private int idcomanda;
	
	@Column(columnDefinition = "DATE")
	private LocalDate data_entrega;
	
	@OneToOne
	private ClientFCM client;
	private double importe;
	
	@OneToMany
	private List<ComandaVendaLineaFCM> linies;
	public int getIdcomanda() {
		return idcomanda;
	}
	public void setIdcomanda(int idcomanda) {
		this.idcomanda = idcomanda;
	}
	public LocalDate getData_entrega() {
		return data_entrega;
	}
	public void setData_entrega(LocalDate data_entrega) {
		this.data_entrega = data_entrega;
	}
	public ClientFCM getClient() {
		return client;
	}
	public void setClient(ClientFCM client) {
		this.client = client;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public List<ComandaVendaLineaFCM> getLinies() {
		return linies;
	}
	public void setLinies(List<ComandaVendaLineaFCM> linies) {
		this.linies = linies;
	}
	public ComandaVendaFCM(int idcomanda, LocalDate data_entrega, ClientFCM client, double importe,
			List<ComandaVendaLineaFCM> linies) {
		super();
		this.idcomanda = idcomanda;
		this.data_entrega = data_entrega;
		this.client = client;
		this.importe = importe;
		this.linies = linies;
	}
	public ComandaVendaFCM() {
		super();
		this.idcomanda =0;
		this.data_entrega = null;
		this.client = null;
		this.importe = 0.0;
		this.linies = null;
	}
	
	
	
	

}
