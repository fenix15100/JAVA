package bo;

import java.io.Serializable;

import vc.StockInsuficientException;

public abstract class Producto implements Serializable {
	
	 //Parametros de clase
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nom;
	private double preu;
	private int stock;
	
	//Contructores
	
	public Producto() {
		this("","",0.0,0);
			
	}
	public Producto(String id, String nom, double preu, int stock) {
		this.id = id;
		this.nom = nom;
		this.preu = preu;
		this.stock=stock;
	}
	
	
	//Getters and Setters
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPreu() {
		return preu;
	}
	public void setPreu(double preu) {
		this.preu = preu;
	}
	
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	public void ponStock(int stock) {
		
		this.stock+=stock;
		
	}
	
	
	
	public void quitarStock(int stock) throws StockInsuficientException {
		if(this.stock<stock) {
			StockInsuficientException error= new StockInsuficientException("No hay suficiente Stock");
			throw error;
		}else {
			this.stock-=stock;
		}
		
	}
	//Metodo que compara el nombre del producto instanciado con otro producto pasado por parametro
	

	
	public boolean equals(Producto producto) {
		return this.getNom().equals(producto.getNom());
		
		
	}
	
	
	
	
	
	//Imprime los parametros de la clase productos.
	public void imprimir() {
		
		System.out.println("ID: "+id+" Nombre: "+nom+" Precio: "+preu+"Stock: "+stock);
	}
	
	
	
	

}
