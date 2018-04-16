package modelFCM;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity


public class ArticleFCM {
	@Id
	private int idarticle;
	private String nom;
	private double preu_venda;
	private int stock;
	public int getIdarticle() {
		return idarticle;
	}
	public void setIdarticle(int idarticle) {
		this.idarticle = idarticle;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPreu_venda() {
		return preu_venda;
	}
	public void setPreu_venda(double preu_venda) {
		this.preu_venda = preu_venda;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public ArticleFCM(int idarticle, String nom, double preu_venda, int stock) {
		super();
		this.idarticle = idarticle;
		this.nom = nom;
		this.preu_venda = preu_venda;
		this.stock = stock;
	}
	public ArticleFCM() {
		super();
		this.idarticle = 0;
		this.nom = null;
		this.preu_venda = 0.0;
		this.stock = 0;
	}
	
	
	

}
