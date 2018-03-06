package modelo;
import modelo.Producto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.TreeMap;


public class Productos {
	
	private Connection conexionBD;
	private TreeMap<String, Producto> lista_productos=new TreeMap<String,Producto>();
	private ObjectInputStream reader=null;
	private ObjectOutputStream writer=null;
	
	

	public Productos(Connection conexionBD) {
		this.conexionBD=conexionBD;
	}


	
	//Metodo que recibe un producto y lo introduce en la estructura de datos
	public boolean addProducto(Producto productemp) {
		
		if(lista_productos.containsKey(productemp.getId())){
			
			return false;
			
			
		}else {
			
			lista_productos.put(productemp.getId(),productemp);
			System.out.println("Producto "+productemp.getId()+" Introducido correctamente");
			return true;	
		}
		
		
		
	}
	
	
	
	//Metodo que recibe una ID y comprueba que exista en la estructura de datos.
	//Si es asi devuelve el Producto
	public Producto searchProducto(String id) {
		
		if(!lista_productos.containsKey(id)) {
			
			return null;
			
		}else {
			return lista_productos.get(id);
		}
	
		
		
		
		
	}
	
	
	//Recibe un Producto previamente modificado por la clase BotigaProductes y sobreescribe en la
	//Estructura de datos el Producto
	public void updateProducto(Producto productemp) {
		
		lista_productos.put(productemp.getId(),productemp);
		System.out.println("Producto "+productemp.getId()+" modificado correctamente");
		
		
	}
	
	//Recibe un ID y los busca en la estructura de datos, si existe borra el producto
	public boolean deleteProducto(String id){
		
		if(searchProducto(id)==null) {
			return false;
			
		}else {
			lista_productos.remove(id);
			return true;
			
		}
		
		
	}
	
	
	//Recorre toda la estructura de datos obteniendo los productos, para mas tarde imprimir sus datos
	//Con los metodos imprimir() de cada producto
	public void showAll() {
		
		for(Producto p:lista_productos.values()) {
			p.imprimir();
			
			
		}
		
		
	}
			

}
