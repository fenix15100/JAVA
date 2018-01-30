package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Clase per gestionar la persistència de les dades de les persones
 * @author manuel
 *
 */
public class Personas {
	
    private TreeMap<Integer, Persona> personas = new TreeMap<Integer,Persona>();
    
    public boolean save(Persona persona){
    	
    	if (personas.containsKey(persona.getId())){	
    		personas.replace(persona.getId(), persona);
    		return true;
    	} else{
    		personas.put(persona.getId(), persona);
    		return true;
    	}
    }
    
    public boolean delete(Integer id){
    	
    	if (personas.containsKey(id)){
    		personas.remove(id);
    		return true;
    	}
    	    	
    	return false;
    }
    
    public Persona find(Integer id){
    	
    	if (id == null || id.equals("")){
    		return null;
    	}
    	
    	return personas.get(id);
    }
    
    public void saveAll(){
    	
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("personas.dat"));
			oos.writeObject(personas);
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
	@SuppressWarnings("unchecked")
	public void openAll(){
    	
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personas.dat"));
			personas = (TreeMap<Integer, Persona>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void showAll(){
    	
		System.out.println("-------------------");
		System.out.println("Todos los personas");
		System.out.println("-------------------");

    	for (Iterator<Persona> iterator = personas.values().iterator(); iterator.hasNext();) {
    		//polimorfisme classes
    		Persona product = (Persona) iterator.next();	
			product.imprimir();
		}
    }
	
}

