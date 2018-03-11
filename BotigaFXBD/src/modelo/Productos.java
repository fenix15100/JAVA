package modelo;
import modelo.Producto;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;


public class Productos {
	
	private Connection conexionBD;
	private TreeMap<String, Producto> lista_productos=new TreeMap<String,Producto>();

	
	public Productos(Connection conexionBD) {
		this.conexionBD=conexionBD;
	}


	
	//Metodo que recibe un producto y lo introduce en la estructura de datos
	public void addProducto(Producto productemp) throws SQLException {
		
		
		
		//Inserto el JOC
		
		if(productemp instanceof Joc ) {
			
			Joc joc=(Joc)productemp;
			PreparedStatement sta=conexionBD.prepareStatement("INSERT INTO jocs (idproducte,nom,preu,edat,idproveidor,stock,fecha_inicio,fecha_final,tipo) VALUES(?,?,?,?,?,?,?,?,?)");
			sta.setString(1,joc.getId());
			sta.setString(2, joc.getNom());
			sta.setDouble(3, joc.getPreu());
			sta.setInt(4, joc.getEdad_minima());
			sta.setInt(5, joc.getId_proveedor());
			sta.setInt(6, joc.getStock());
			sta.setDate(7, java.sql.Date.valueOf(joc.getFecha_inicio()));
			sta.setDate(8, java.sql.Date.valueOf(joc.getFecha_final()));
			sta.setString(9, String.valueOf('J'));
			
			sta.executeUpdate();
			
			//Inserto el Pack	
		}else if(productemp instanceof Pack) {
			
			Pack pack=(Pack)productemp;
			PreparedStatement sta=conexionBD.prepareStatement("INSERT INTO packs1 (idproducte,nom,preu,porc_dto,idproveidor,jocs,stock,fecha_inicio,fecha_final,tipo) VALUES(?,?,?,?,?,?,?,?,?,?)");
			sta.setString(1,pack.getId());
			sta.setString(2, pack.getNom());
			sta.setDouble(3, pack.getPreu());
			sta.setDouble(4, pack.getDescuento());
			sta.setInt(5,pack.getId_proveedor());
			
			//Convierto el treset a array String y luego hago un array generica tipo Integer para
			//meterla en el stament.
			//https://stackoverflow.com/questions/17842211/how-to-use-an-arraylist-as-a-prepared-statement-parameter
			
			String[] lista_juegos_array=pack.getListaJuegos().toArray(new String[pack.getListaJuegos().size()]);
			Array lista = conexionBD.createArrayOf("INTEGER", lista_juegos_array);
			sta.setArray(6,lista );
			sta.setInt(7, pack.getStock());
			sta.setDate(8, java.sql.Date.valueOf(pack.getFecha_inicio()));
			sta.setDate(9, java.sql.Date.valueOf(pack.getFecha_final()));
			sta.setString(10, String.valueOf('P'));
			
			sta.executeUpdate();
		}
		
		
		
	}
	
	
	
	//Metodo que recibe una ID y comprueba que exista en la estructura de datos.
	//Si es asi devuelve el Producto
	public Producto searchProducto(String id) {
		//Implementar
		return null;
		
		
		
		
		
	}
	
	
	//Recibe un Producto previamente modificado por la clase BotigaProductes y sobreescribe en la
	//Estructura de datos el Producto
	public void updateProducto(Producto productemp) {
		
		//IMplementar
		
		
	}
	
	//Recibe un ID y los busca en la estructura de datos, si existe borra el producto
	public boolean deleteProducto(String id){
		
		//implementar
		return true;
		
		
	}
	
	
	
	
	public void closeDB() throws SQLException {
		if(conexionBD!=null) conexionBD.close();
		
	}
			

}
