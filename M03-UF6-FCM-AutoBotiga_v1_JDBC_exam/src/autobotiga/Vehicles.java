package autobotiga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Vehicles implements Persistent {


	private Connection conexionBD;

	public Vehicles(Connection conexionBD) {
		this.conexionBD = conexionBD;	
	}

	@Override
	public boolean guardar(Vehicle vehicle) {
		try {
			String sql = "";
			PreparedStatement stmt = null;

			if (this.buscar(vehicle.getMatricula()) == null){
				if (vehicle instanceof Cotxe){
					sql = "INSERT INTO cotxes VALUES(?,?,?,?,?,?,?,?,?)";
					stmt = conexionBD.prepareStatement(sql);
					int i = 1;
					stmt.setString(i++, vehicle.getMatricula());
					stmt.setString(i++, vehicle.getMarca());
					stmt.setString(i++, vehicle.getModel());
					stmt.setString(i++, vehicle.getVersio());
					stmt.setDouble(i++, vehicle.getEmisionsCO2());
					stmt.setDouble(i++, vehicle.getPreu());
					stmt.setDouble(i++, 0);
					stmt.setInt(i++, Integer.valueOf(((Cotxe)vehicle).getPortes()));
					stmt.setInt(i++, Integer.valueOf(((Cotxe)vehicle).getPlaces()));
				}else{
					sql = "INSERT INTO motos VALUES(?,?,?,?,?,?,?,?,?)";
					stmt = conexionBD.prepareStatement(sql);
					int i = 1;
					stmt.setString(i++, vehicle.getMatricula());
					stmt.setString(i++, vehicle.getMarca());
					stmt.setString(i++, vehicle.getModel());
					stmt.setString(i++, vehicle.getVersio());
					stmt.setDouble(i++, vehicle.getEmisionsCO2());
					stmt.setDouble(i++, vehicle.getPreu());
					stmt.setDouble(i++, 0);
					stmt.setString(i++, ((Moto)vehicle).getCategoria());
					stmt.setInt(i++, Integer.valueOf(((Moto)vehicle).getCilindrada()));

				}
			} else{
				if (vehicle instanceof Cotxe){
					sql = "UPDATE cotxes SET marca=?,model=?,versio=?,emisionsco2=?,preu=?,portes=?,places=? WHERE matricula = ?";
					stmt = conexionBD.prepareStatement(sql);
					int i = 1;
					stmt.setString(i++, vehicle.getMarca());
					stmt.setString(i++, vehicle.getModel());
					stmt.setString(i++, vehicle.getVersio());
					stmt.setDouble(i++, vehicle.getEmisionsCO2());
					stmt.setDouble(i++, vehicle.getPreu());
					stmt.setInt(i++, Integer.valueOf(((Cotxe)vehicle).getPortes()));
					stmt.setInt(i++, Integer.valueOf(((Cotxe)vehicle).getPlaces()));
					stmt.setString(i++, vehicle.getMatricula());
				}else{
					sql = "UPDATE motos SET marca=?,model=?,versio=?,emisionsco2=?,preu=?,categoria=?,cilindrada=? WHERE matricula = ?";
					stmt = conexionBD.prepareStatement(sql);
					int i = 1;
					stmt.setString(i++, vehicle.getMarca());
					stmt.setString(i++, vehicle.getModel());
					stmt.setString(i++, vehicle.getVersio());
					stmt.setDouble(i++, vehicle.getEmisionsCO2());
					stmt.setDouble(i++, vehicle.getPreu());
					stmt.setString(i++, ((Moto)vehicle).getCategoria());
					stmt.setInt(i++, Integer.valueOf(((Moto)vehicle).getCilindrada()));
					stmt.setString(i++, vehicle.getMatricula());
				}
			}
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Vehicle buscar(String matricula) {
		if (matricula == null || matricula.equals("")){
			return null;
		}

		Vehicle v = null;
		try {
			//https://www.postgresql.org/docs/9.5/static/ddl-inherit.html
			PreparedStatement stmt = conexionBD.prepareStatement("SELECT v.tableoid::regclass as regclass,* "
					+ "FROM vehicles v "
					+ "LEFT OUTER JOIN cotxes c ON c.matricula = v.matricula "
					+ "LEFT OUTER JOIN motos m ON m.matricula = v.matricula "
					+ "WHERE v.matricula = ?");
			stmt.setString(1, matricula);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				if (result.getString("regclass").equals("cotxes")){
					v = new Cotxe(result.getString("matricula"), result.getString("marca"), result.getString("model"),result.getString("versio"),result.getInt("emisionsco2"), result.getLong("preu"),result.getInt("portes"),result.getInt("places"));
				}else{

					v = new Moto(result.getString("matricula"), result.getString("marca"), result.getString("model"),result.getString("versio"),result.getInt("emisionsco2"), result.getLong("preu"),result.getString("categoria"),result.getInt("cilindrada"));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return v;
	}

	@Override
	public boolean eliminarFCM(String matricula) {
		try {
			PreparedStatement sta=conexionBD.prepareStatement("DELETE FROM vehicles WHERE matricula=?");
			sta.setString(1, matricula);
			if(sta.executeUpdate()!=2)return true;
			else return false;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
		
		
	}

	@Override
	public void imprimirTots() {

		System.out.println("-------------------");
		System.out.println("Tots els vehicles");
		System.out.println("-------------------");

		try {
			PreparedStatement stmt = conexionBD.prepareStatement("SELECT v.tableoid::regclass as regclass,* "
					+ "FROM vehicles v "
					+ "LEFT OUTER JOIN cotxes c ON c.matricula = v.matricula "
					+ "LEFT OUTER JOIN motos m ON m.matricula = v.matricula");
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				Vehicle v = null;
				if (result.getString("regclass").equals("cotxes")){
					v = new Cotxe(result.getString("matricula"), result.getString("marca"), result.getString("model"),result.getString("versio"),result.getInt("emisionsco2"), result.getLong("preu"),result.getInt("portes"),result.getInt("places"));
				}else{

					v = new Moto(result.getString("matricula"), result.getString("marca"), result.getString("model"),result.getString("versio"),result.getInt("emisionsco2"), result.getLong("preu"),result.getString("categoria"),result.getInt("cilindrada"));
				}
				v.imprimir();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void imprimirPerMarcaFMC(String marca) {
		try {
			
			//No me hace bien la consulta XD lo intente de todas formas, ahora tengo curiosisdad
			PreparedStatement sta=conexionBD.prepareStatement("SELECT v.tableoid::regclass as regclass,* "
					+ "FROM vehicles v "
					+ "LEFT OUTER JOIN cotxes c ON c.matricula = v.matricula and v.marca=?"
					+ "LEFT OUTER JOIN motos m ON m.matricula = v.matricula and v.marca=?");
			sta.setString(1, marca);
			sta.setString(2, marca);
			ResultSet result= sta.executeQuery();
			while(result.next()) {
				Vehicle v = null;
				if (result.getString("regclass").equals("cotxes")){
					v = new Cotxe(result.getString("matricula"), result.getString("marca"), result.getString("model"),result.getString("versio"),result.getInt("emisionsco2"), result.getLong("preu"),result.getInt("portes"),result.getInt("places"));
				}else{

					v = new Moto(result.getString("matricula"), result.getString("marca"), result.getString("model"),result.getString("versio"),result.getInt("emisionsco2"), result.getLong("preu"),result.getString("categoria"),result.getInt("cilindrada"));
				}
				v.imprimir();
				
				
				
	
				
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	public void actualitzarPreuFinalFMC() {
		//TODO
	}
}
