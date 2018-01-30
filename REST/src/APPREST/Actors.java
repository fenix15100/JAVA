package APPREST;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;

import com.sun.appserv.jdbc.DataSource;

import modelos.Actor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Path("/Actors")
public class Actors {
	
	@GET
	@Path("/")
	@Produces("application/json")
	
	public String showAll(@QueryParam("first_name_v") String first_name_v, @QueryParam("last_name_v") 
		   String last_name_v,@QueryParam("skip") int skip,@QueryParam("take") int take ) throws NamingException, SQLException {
		
		InitialContext ctx=new InitialContext();
		DataSource ds=(DataSource)ctx.lookup("jdbc/driver");
		Connection conn=ds.getConnection();
		Statement sta=null;
		ResultSet rs=null;
		ArrayList<Actor> actores=new ArrayList<Actor>();
		Gson serializer=new GsonBuilder().setPrettyPrinting().create();
		
		String sql="SELECT * FROM actor WHERE actor_id>="+skip;
		
		
		if(first_name_v==null&&last_name_v==null) {
		
				
			
		}else if (first_name_v!=null&&last_name_v==null) {
			
			sql=sql+" AND first_name LIKE '"+first_name_v+"'";
			
			
		}else if (first_name_v==null&&last_name_v!=null) {
			sql=sql+" AND last_name LIKE '"+last_name_v+"'";
			
				
			
		}else if(first_name_v!=null&&last_name_v!=null){
			sql=sql+" AND last_name LIKE '"+last_name_v+"' AND first_name LIKE '"+first_name_v+"'";
			
		}
		if(take>0) {
			
			sql=sql+" LIMIT "+take;
		}
		
		sta=conn.createStatement();
		rs=sta.executeQuery(sql);
		
		while(rs.next()) {
			int id=rs.getInt("actor_id");
			String first_name=rs.getString("first_name");
			String last_name=rs.getString("last_name");
			
			Actor actor=new Actor(id,first_name,last_name);
			actores.add(actor);
			
		}
		
		conn.close();
		
		
		
		return serializer.toJson(actores);
		
		
		
	}
	
	

	@GET
	@Path("/{actor_id:.*}")
	@Produces("application/json")
	
	public String showActor(@PathParam("actor_id") int actor_id) throws SQLException, NamingException {
		
		Gson serializer=new GsonBuilder().setPrettyPrinting().create();
		
		
		InitialContext ctx=new InitialContext();
		DataSource ds=(DataSource)ctx.lookup("jdbc/driver");
		Connection conn=ds.getConnection();
		PreparedStatement stat=conn.prepareStatement("SELECT * FROM actor WHERE actor_id=?");
		
		stat.setInt(1, actor_id);
		
		ResultSet rs=stat.executeQuery();
		
		
		if(rs.next()) {
			int id=rs.getInt("actor_id");
			String first_name=rs.getString("first_name");
			String last_name=rs.getString("last_name");
			
			Actor actor=new Actor(id,first_name,last_name);
			
			conn.close();
			return serializer.toJson(actor);
			
		}else {
			conn.close();
			return "Actor "+actor_id+" Not Found in Database";
				
		}
		
		
		
		
		
	}
	
	
	
	@PUT
	@Path("/{actor_id:.*}")
	@Produces("application/json")
	@Consumes("application/json")
	
	public String updateActor(@PathParam("actor_id") int actor_id,String body) throws NamingException, SQLException{
		
		
		Gson serializer=new GsonBuilder().setPrettyPrinting().create();
		
		Actor actor=serializer.fromJson(body,Actor.class);
		
		InitialContext ctx=new InitialContext();
		DataSource ds=(DataSource)ctx.lookup("jdbc/driver");
		Connection conn=ds.getConnection();
		PreparedStatement sta=conn.prepareStatement("UPDATE actor SET first_name=?,last_name=? WHERE actor_id=?");
		
		sta.setString(1, actor.firstname);
		sta.setString(2, actor.lastName);
		sta.setInt(3, actor_id);
		
		if(sta.executeUpdate()==1) {
			
			sta=conn.prepareStatement("SELECT * FROM actor WHERE actor_id=?");
			sta.setInt(1, actor_id);
			ResultSet rs =sta.executeQuery();
			
			while(rs.next()) {
				int id=rs.getInt("actor_id");
				String first_name=rs.getString("first_name");
				String last_name=rs.getString("last_name");
				
				actor=new Actor(id,first_name,last_name);
			}
				
			conn.close();
			return serializer.toJson(actor);
		
			
		}else {
			conn.close();
			return "No se puede actualizar";
		}
		
	

	}
	
	
	@DELETE
	@Path("/{actor_id:.*}")
	@Produces("text/plain")
	
	public String deleteActor(@PathParam("actor_id") int actor_id) throws NamingException, SQLException {
		
		InitialContext ctx=new InitialContext();
		DataSource ds=(DataSource)ctx.lookup("jdbc/driver");
		Connection conn=ds.getConnection();
		PreparedStatement sta=conn.prepareStatement("DELETE FROM film_actor WHERE actor_id=?");
		sta.setInt(1, actor_id);
		sta.executeUpdate();
		
		sta=conn.prepareStatement("DELETE FROM actor WHERE actor_id=?");
		sta.setInt(1, actor_id);
		if(sta.executeUpdate()==1) {
			conn.close();
			return "Se ha borrado el actor de la base de datos";
		}
		else {
			conn.close();
			return "No se ha podido borrar el registro";
		}
		
		
		
		
		
	}
	
	
	@POST
	@Path("/")
	@Produces("application/json")
	@Consumes("application/json")
	
	public String createActor(String body) throws SQLException, NamingException {
		
		Gson serializer=new GsonBuilder().setPrettyPrinting().create();
		
		Actor actor=serializer.fromJson(body,Actor.class);
		
		
		InitialContext ctx=new InitialContext();
		DataSource ds=(DataSource)ctx.lookup("jdbc/driver");
		Connection conn=ds.getConnection();
		PreparedStatement sta=conn.prepareStatement("INSERT INTO actor (first_name,last_name) VALUES(?,?)");
		sta.setString(1, actor.firstname);
		sta.setString(2, actor.lastName);
		
		if(sta.executeUpdate()==1){
			sta=conn.prepareStatement("SELECT * FROM actor WHERE actor_id=max(actor_id)");
			ResultSet rs =sta.executeQuery();

			while(rs.next()){

				int id=rs.getInt("actor_id");
				String first_name=rs.getString("first_name");
				String last_name=rs.getString("last_name");
				
				actor=new Actor(id,first_name,last_name);

			}
			conn.close();
			return serializer.toJson(actor);


		}else{
			conn.close();
			return "No se ha podido insertar el registro";

		}
		
		
		
		
		
	}
	
	
	
	
}
