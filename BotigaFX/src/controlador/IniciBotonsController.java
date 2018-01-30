package controlador;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Locale.Category;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class IniciBotonsController extends Application {
	
	@FXML
    private AnchorPane root;

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnSalir;

	@Override
	public void start(Stage escenarioPrincipal) throws Exception {
		
		//Cargo la vista asociada al controlador Principal
		FXMLLoader loaderview = new FXMLLoader(getClass().getResource("/vista/IniciBotonsView.fxml"));
		
		//Cargo la traduccion en el controlador
		Locale localitzacioDisplay = Locale.getDefault(Category.DISPLAY);
		ResourceBundle texts = ResourceBundle.getBundle("vista.Texts", localitzacioDisplay);
		
		//Cargo la traducion a la vista
		loaderview.setResources(texts);
		
		//Creo una Scena a partir de la vista
		Scene escenaPrincipal = new Scene(loaderview.load());
		
		//Asigno la escena al Escenario principal y abro el telon
		escenarioPrincipal.setScene(escenaPrincipal);
		escenarioPrincipal.setTitle(texts.getString("title.botiga"));
		escenarioPrincipal.show();
		
		

	}
	
	
	 @FXML
	 public void btnProductos(ActionEvent event) {
		 
		 
		 
		 

	 }

	 @FXML
	 public void btnSalir(ActionEvent event) {
		 
		 Platform.exit();

	 }
}
