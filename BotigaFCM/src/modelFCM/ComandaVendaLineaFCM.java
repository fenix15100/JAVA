package modelFCM;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class ComandaVendaLineaFCM {
	
	@Id
	private int idlinea;
	@OneToOne
	@JoinColumn(name="idarticle")
	private ArticleFCM article;
	private String descripcio;
	private double preu;
	private int quantitat;
	private double importe;
	public int getIdlinea() {
		return idlinea;
	}
	public void setIdlinea(int idlinea) {
		this.idlinea = idlinea;
	}
	public ArticleFCM getArticle() {
		return article;
	}
	public void setArticle(ArticleFCM article) {
		this.article = article;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public double getPreu() {
		return preu;
	}
	public void setPreu(double preu) {
		this.preu = preu;
	}
	public int getQuantitat() {
		return quantitat;
	}
	public void setQuantitat(int quantitat) {
		this.quantitat = quantitat;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public ComandaVendaLineaFCM(int idlinea, ArticleFCM article, String descripcio, double preu, int quantitat,
			double importe) {
		super();
		this.idlinea = idlinea;
		this.article = article;
		this.descripcio = descripcio;
		this.preu = preu;
		this.quantitat = quantitat;
		this.importe = importe;
	}
	public ComandaVendaLineaFCM() {
		super();
		this.idlinea = 0;
		this.article = null;
		this.descripcio = null;
		this.preu = 0.0;
		this.quantitat = 0;
		this.importe = 0.0;
	}
	
	

}
