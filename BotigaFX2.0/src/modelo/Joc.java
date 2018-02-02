package modelo;

import java.time.LocalDate;

public final class Joc extends Producto{
	
	//Parametros de clase
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int edad_minima;
	private int id_proveedor;
	
	//Constructores
	public Joc(String id, String nom, double preu, int stock,LocalDate fecha_inicio,LocalDate fecha_final,int edad_minima, int id_proveedor) {
		super(id, nom, preu, stock,fecha_inicio,fecha_final);
		this.edad_minima=edad_minima;
		this.id_proveedor=id_proveedor;
		
	}
	
	
	public Joc() {
		this("","",0.0,0,null,null,0,0);
		
	}

	
	
	//Getters And Setters
	public int getEdad_minima() {
		return edad_minima;
	}

	public void setEdad_minima(int edad_minima) {
		this.edad_minima = edad_minima;
	}

	public int getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	//Metodo que imprime los parametros de la clase
	@Override
	public void imprimir() {
		System.out.println("---------------------------------------------");
		System.out.println("Joc");
		super.imprimir();
		System.out.println("Edad Minima "+edad_minima+" ID Proveedor: "+id_proveedor);
		System.out.println("---------------------------------------------");
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
}
