package controlador;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Locale.Category;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modelo.Productos;


public class IniciBotonsController extends Application {
	//Cargo la traduccion en el controlador
	private Locale localitzacioDisplay = Locale.getDefault(Category.DISPLAY);
	private ResourceBundle texts = ResourceBundle.getBundle("vista.Texts", localitzacioDisplay);
	
	

	
	@FXML
    private BorderPane root;

    

	@Override
	public void start(Stage escenarioPrincipal) throws Exception {
		
		
		
		//Cargo la vista asociada al controlador Principal
		FXMLLoader loaderview = new FXMLLoader(getClass().getResource("/vista/IniciBotonsView.fxml"));
		
		//Cargo la traducion a la vista
		loaderview.setResources(texts);
		
		//Creo una Scena a partir de la vista
		Scene escenaPrincipal = new Scene(loaderview.load());
		
		//Asigno la escena al Escenario principal y abro el telon
		escenarioPrincipal.setScene(escenaPrincipal);
		escenarioPrincipal.setTitle(texts.getString("Inicio.title.botiga"));
		escenarioPrincipal.show();
		
		

	}
	
	
	 @FXML
	 public void OnActionbtnProductos(ActionEvent event) throws IOException {
		 
		
	
		 //Cargo en memoria la vista ProductosView y la cargo dentro de un Panel 
			FXMLLoader loaderview = new FXMLLoader(getClass().getResource("/vista/ProductosView.fxml"));
			
			SplitPane Panel=(SplitPane)loaderview.load();
	
			root.setCenter(Panel);
			
		 

	 }

	 @FXML
	 public void OnMenubtnSalir(ActionEvent event) throws IOException {
		
		 Platform.exit();

	 }
}
