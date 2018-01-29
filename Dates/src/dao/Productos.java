package dao;
import bo.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Productos {
	
	//Parametros clase
		//Estructura de datos tipo TreeMap donde almacenaremos los productos (Joc y Pack)
			//<ID,Producto>
	private TreeMap<String, Producto> lista_productos=new TreeMap<String,Producto>();
	private ObjectInputStream reader=null;
	private ObjectOutputStream writer=null;
	
	
	///Comparadores
	
	static final Comparator<Producto> precioComparator = new Comparator<Producto>(){
        public int compare(Producto p1, Producto p2){
            return Double.valueOf(p1.getPreu()).compareTo(Double.valueOf(p2.getPreu())); //valueOf transforma el dato primitivo double a la clase Double 
        }
    };
    static final Comparator<Producto> nombreComparator = new Comparator<Producto>(){
        public int compare(Producto p1, Producto p2){
            return p1.getNom().compareTo(p2.getNom());
        }
    };
    
    static final Comparator<Producto> stockComparator = new Comparator<Producto>(){
        public int compare(Producto p1, Producto p2){
            return Integer.valueOf(p1.getStock()).compareTo(Integer.valueOf(p2.getStock()));
        }
    };
	
	
	
	

	@SuppressWarnings("unchecked")
	public void loadData() throws IOException {
		try {
			reader=new ObjectInputStream(new FileInputStream("Productos.dat"));
			
			lista_productos=(TreeMap<String, Producto>)reader.readObject();
				
				
			
			
		} catch (FileNotFoundException e) {
			
			System.out.println(e.getMessage());
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			
			System.out.println(e.getMessage());
		}finally {
			if(reader!=null) {
				reader.close();
			}
		}
		
	}
	
	
	public void saveData() throws IOException {
		try {
			writer=new ObjectOutputStream(new FileOutputStream("Productos.dat"));
			
			writer.writeObject(lista_productos);
		} catch (FileNotFoundException e) {
			
			System.out.println(e.getMessage());
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
		}finally {
			if(writer!=null) {
				writer.close();
			}
		}
		
	}
	//Metodo que recibe un producto y lo introduce en la estructura de datos
	public boolean addProducto(Producto productemp) {
		
		if(lista_productos.containsKey(productemp.getId())){
			
			return false;
			
			
		}else {
			
			lista_productos.put(productemp.getId(),productemp);
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
	
	
	public void OrderBy(String campo) {
		
		List<Producto> listaProductosOrdenar = lista_productos.values().stream().collect(Collectors.toList()); 
        
        
        switch(campo){
            case "precio":
               Collections.sort(listaProductosOrdenar, precioComparator);
                for(Producto p :listaProductosOrdenar){
                   p.imprimir();
                }
                break;
            case "nombre":
                Collections.sort(listaProductosOrdenar, nombreComparator);
                 for(Producto p :listaProductosOrdenar){
                   p.imprimir();
                }
                break;
            case "stock":
                Collections.sort(listaProductosOrdenar, stockComparator);
                 for(Producto p :listaProductosOrdenar){
                   p.imprimir();
                }
                break;
        }
		
		
		
	}
	
	
	public void imprimirDescatalogados(LocalDate fecha) {
		
	
		for(Producto p:lista_productos.values()) {
			
			if(p.getFecha_final().compareTo(fecha)<0) {
				p.imprimir();
				
				Period periodo=Period.between(p.getFecha_final(),fecha);
				System.out.println("El producto fue descatalogado hace "+periodo.getDays()+" Dias");
			}
			else continue;
			
			
			
			
			
		}
		
		
		
		
		
		
	}
	
	
	
	

}
