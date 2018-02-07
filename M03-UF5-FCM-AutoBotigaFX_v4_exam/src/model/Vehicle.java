package model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Vehicle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String marca;
	private String model;
	private String versio;
	private int emisionsCO2;
	private double preu;
	private final int IVA = 21;
	private LocalDate data_matriculacio;

	public Vehicle() {
		this.matricula = "";
		this.marca = "";
		this.model = "";
		this.versio = "";
		this.emisionsCO2 = 0;
		this.preu = 0;
		this.data_matriculacio=null;
	}
	
	public Vehicle(String matricula, String marca, String model, String versio, int emisionsCO2, double preu,LocalDate data_matriculacio) {
		this.matricula = matricula;
		this.marca = marca;
		this.model = model;
		this.versio = versio;
		this.emisionsCO2 = emisionsCO2;
		this.preu = preu;
		this.data_matriculacio=data_matriculacio;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVersio() {
		return versio;
	}

	public void setVersio(String versio) {
		this.versio = versio;
	}

	public int getEmisionsCO2() {
		return emisionsCO2;
	}

	public void setEmisionsCO2(int emisionsCO2) {
		this.emisionsCO2 = emisionsCO2;
	}

	public double getPreu() {
		return preu;
	}

	public void setPreu(double preu) {
		this.preu = preu;
	}

	public int getIVA() {
		return IVA;
	}

	public double getImpostMatriculacio(){
		
		double impost=0.0;
		if (emisionsCO2 < 80){
			impost=0.0;
		}else if(emisionsCO2 > 100 && emisionsCO2 < 120){
			impost=4.75;
		}else if(emisionsCO2 > 120 && emisionsCO2 < 140){
			impost=9.75;
		}else if(emisionsCO2 > 140){
			impost=14.75;
		}
		
		return impost;
	}
	
	public void imprimir(){
		System.out.println("matricula: " + matricula);
		System.out.println("marca: " + marca);
		System.out.println("model: " + model);
		System.out.println("versio: " + versio);
		System.out.println("emisions CO2: " + Integer.toString(emisionsCO2));
		System.out.println("preu: " + Double.toString(preu));
		System.out.println("impost matriculació: " + getImpostMatriculacio() + "%");
		System.out.println("impost matriculació: " + getImpostMatriculacio() + "%");
	}
	
	public void setDataMatriculacio(LocalDate data_matriculacio) {
		this.data_matriculacio = data_matriculacio;
	
	}

	public LocalDate getDataMatriculacio() {
		return data_matriculacio;
		
	}
}
