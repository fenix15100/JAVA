package controlador;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import modelo.Productos;

public class CargaStockController extends Application {

	
	@FXML
	private Button btnchosse;
	 	
	@FXML
	private Label label;

    @FXML
	private TextArea console; 
	
    private Connection conexionBD;
    
    private Productos dao=null;
 
  	public CargaStockController() {
  		super();
  		
  		try{
  			//Carregar el controlador per la BD PostgreSQL
  			Class.forName("org.postgresql.Driver");

  			//Establir la connexió amb la BD
  			String urlBaseDades = "jdbc:postgresql://192.168.123.31/botiga";
  			String usuari = "postgres";
  			String contrasenya = "Destino20$";
  			conexionBD = DriverManager.getConnection(urlBaseDades , usuari, contrasenya);
  			if(conexionBD!=null) {
  				System.out.println("Conexion DB establecida");
  			}

  		} catch (Exception e) {
  			System.out.println(e.getMessage());
  		}
  		
  		
  	}
    
    
    
    
    
    
	@Override
	public void start(Stage escenarioPrincipal) throws Exception {
		
		
				FXMLLoader loaderview = new FXMLLoader(getClass().getResource("/vista/CargaStockView.fxml"));
				
				//Instanacio el DAO
				dao=new Productos(conexionBD);
				
				//Creo una Scena a partir de la vista
				Scene escenaPrincipal = new Scene(loaderview.load());
				
				//Asigno la escena al Escenario principal y abro el telon
				escenarioPrincipal.setScene(escenaPrincipal);
				escenarioPrincipal.setTitle("Carrega de Stock");
				escenarioPrincipal.show();
				
				
				
	}
	
	
	 @FXML
	 public void cargar(ActionEvent event) {
		 
		 OutputStream out = new OutputStream() {
		        @Override
		        public void write(int b) throws IOException {
		            appendText(String.valueOf((char) b));
		        }
		    };
		    
		 System.setOut(new PrintStream(out, true));
		 
		
		 
		 
		 
		    
		    
		    
		    
		    
		    
		  
		 
	}
	 
	 
	 
	 
	 public void appendText(String str) {
		    Platform.runLater(() -> console.appendText(str));
		}
	
	
	
	
	
	
}