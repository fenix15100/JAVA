package modelFCM;

import java.util.List;



import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity

public class BotigaFCM {
	@Id
	private int idbotiga;
	
	private String nom;
	@OneToOne
	@JoinColumn(name="idadreça")
	private AdrecaFCM adreca;
	
	private int num_treballadors;
	
	@OneToMany
	private List<ArticleFCM> articles;
	@OneToMany
	private List<ComandaVendaFCM> comandesVenda;

	public int getIdbotiga() {
		return idbotiga;
	}

	public void setIdbotiga(int idbotiga) {
		this.idbotiga = idbotiga;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public AdrecaFCM getAdreca() {
		return adreca;
	}

	public void setAdreca(AdrecaFCM adreca) {
		this.adreca = adreca;
	}

	public int getNum_treballadors() {
		return num_treballadors;
	}

	public void setNum_treballadors(int num_treballadors) {
		this.num_treballadors = num_treballadors;
	}

	public List<ArticleFCM> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleFCM> articles) {
		this.articles = articles;
	}

	public List<ComandaVendaFCM> getComandesVenda() {
		return comandesVenda;
	}

	public void setComandesVenda(List<ComandaVendaFCM> comandesVenda) {
		this.comandesVenda = comandesVenda;
	}

	public BotigaFCM(int idbotiga, String nom, AdrecaFCM adreca, int num_treballadors, List<ArticleFCM> articles,
			List<ComandaVendaFCM> comandesVenda) {
		super();
		this.idbotiga = idbotiga;
		this.nom = nom;
		this.adreca = adreca;
		this.num_treballadors = num_treballadors;
		this.articles = articles;
		this.comandesVenda = comandesVenda;
	}

	public BotigaFCM() {
		super();
		this.idbotiga =0;
		this.nom = null;
		this.adreca = null;
		this.num_treballadors=0;
		this.articles = null;
		this.comandesVenda =null;
	}
	
	
	
	

}
