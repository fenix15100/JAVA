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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class IniciBotonsController extends Application {

	//Injecció dels panells i controls de la UI definida al fitxer fxml
	@FXML private Parent root;
	@FXML private Button btnPersones;
	@FXML private Button btnSortir;
	@FXML 

	@Override
	public void start(Stage primaryStage) throws IOException {

		//Carrega el fitxer amb la interficie d'usuari inicial (Scene)
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IniciBotonsView.fxml"));
		
		//Carregar fitxer de textos multiidioma de la localització actual
		Locale localitzacioDisplay = Locale.getDefault(Category.DISPLAY);
		ResourceBundle texts = ResourceBundle.getBundle("vista.Texts", localitzacioDisplay);
		//fins aquí tot igual, només falta assignar el fitxer de recursos al formulari
		loader.setResources(texts);

		Scene fm_inici = new Scene(loader.load());
		
		//Li assigna la escena a la finestra inicial (primaryStage) i la mostra
		primaryStage.setScene(fm_inici);
		primaryStage.setTitle(texts.getString("title.agenda"));
		primaryStage.show();
	}

	@FXML
	private void onAction(ActionEvent e) throws Exception {
		if(e.getSource() == btnPersones){
			//Carrega el fitxer amb la interficie d'usuari
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PersonasView.fxml"));
			AnchorPane pagina = (AnchorPane)loader.load();
			
			//Crea una nova finestra i l'obre 
			Stage stage = new Stage();
			Scene fm_personas = new Scene(pagina);
			stage.setTitle("Persones");
			stage.setScene(fm_personas);
			stage.show();
			
			//Crear un objecte de la clase PersonasController ja que necessitarem accedir al mètodes d'aquesta classe
			PersonasController personasControler = (PersonasController)loader.getController();
			personasControler.setVentana(stage);
			
			//Programem l'event que s'executará quan es tanqui la finestra
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					personasControler.sortir();
				}
			});   

		}else if(e.getSource() == btnSortir){
			Platform.exit();
		}

	}
}
