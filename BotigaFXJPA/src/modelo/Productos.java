package modelo;
import modelo.Producto;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;




public class Productos {
	
	private EntityManager em;
	
	public Productos(EntityManager em) {
		this.em=em;
	}


	
	//Metodo que recibe un producto y lo introduce en la estructura de datos INSERT UPDATE
	public boolean addProducto(Producto productemp){
		
		
		EntityTransaction tx = em.getTransaction(); 
		tx.begin();
		
		if(em.find(Producto.class, productemp.getId()) != null){
			em.merge(productemp);
			tx.commit();
			
		}else{
			em.persist(productemp);
			tx.commit();
			
		}

		
		
		return true;
		
		
		
		
		
	}
	
	
	
	//Metodo que recibe una ID y comprueba que exista en la estructura de datos.
	//Si es asi devuelve el Producto
	
	public Producto searchProducto(String id) {
		
		return em.find(Producto.class, id);	
				
			
	}
	
	
	//Recibe un ID y los busca en la estructura de datos, si existe borra el producto
	public boolean deleteProducto(String id){
		
		EntityTransaction tx = em.getTransaction(); 
		tx.begin();
		em.remove(em.find(Producto.class, id));
		em.flush();
		tx.commit();
		return true;
		
		
		
	}
	
	
	
	
	public void closeDB() {
		if(em!=null) em.close();
		
	}
			

}
