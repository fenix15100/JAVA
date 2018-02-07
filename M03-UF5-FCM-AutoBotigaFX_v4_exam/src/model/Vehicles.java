package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;

import model.*;

public class Vehicles implements Persistent {

	private TreeMap<String, Vehicle> vehicles = new TreeMap<String, Vehicle>();
	private ObjectInputStream reader = null;
	private ObjectOutputStream writer = null;

	@SuppressWarnings("unchecked")
	public void openAll() throws FileNotFoundException {
		try {
			System.out.println("Cargando datos");
			reader = new ObjectInputStream(new FileInputStream("vehicles.dat"));

			vehicles = (TreeMap<String, Vehicle>) reader.readObject();

		
		} catch (IOException e) {

			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}
	
	
	public void saveAll() {
		try {
			
			System.out.println("Guardando Datos");
			writer=new ObjectOutputStream(new FileOutputStream("vehicles.dat"));
			
			writer.writeObject(vehicles);
		
		
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
		}finally {
			if(writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
	}
	
	
	
	

	@Override
	public void guardar(Vehicle vehicle) {
		vehicles.put(vehicle.getMatricula(), vehicle);
	}

	@Override
	public Vehicle buscar(String matricula) {
		return vehicles.get(matricula);

	}

	@Override
	public boolean eliminar(String matricula) {
		if (vehicles.containsKey(matricula)) {
			vehicles.remove(matricula);
			return true;
		}

		return false;
	}

	@Override
	public void imprimirTots() {
		for (Vehicle vehicle : vehicles.values()) {
			vehicle.imprimir();
		}
	}


	@Override
	public ArrayList<Vehicle> toArrayList() {
		
		ArrayList<Vehicle> values=new ArrayList<Vehicle>(vehicles.values());
		return values;
	}
}
