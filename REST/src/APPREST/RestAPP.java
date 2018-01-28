package APPREST;

import java.util.HashSet;
import java.util.Set;


import javax.ws.rs.*;
import javax.ws.rs.core.Application;

@ApplicationPath("API")
public class RestAPP extends Application {
	public Set <Class<?>> getClasses(){
		
		Set <Class<?>> lista_clase=new HashSet<Class<?>>();
		lista_clase.add(Actors.class);
		return lista_clase;
		
	}
	
	
	
	
	
	

}
