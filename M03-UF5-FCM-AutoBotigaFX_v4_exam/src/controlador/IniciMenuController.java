package controlador;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class IniciMenuController extends Application {
	@FXML
	private TabPane tabPane;
	@FXML
	private MenuItem menuItemVehiclesMantenir;
	
	@FXML
	private MenuItem menuItemVehiclesImportar;

	@FXML
	private MenuItem menuItemBotigaSortir;

	@Override
	public void start(Stage primaryStage) throws IOException {
		//Carrega el fitxer amb la interficie d'usuari inicial (Scene)
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IniciMenuView.fxml"));
		Scene fm_inici = new Scene(loader.load());

		//Li assigna la escena a la finestra inicial (primaryStage) i la mostra
		primaryStage.setScene(fm_inici);
		primaryStage.setTitle("AutoBotiga v4");
		primaryStage.show();
	}

	@FXML
	void onActionMenuBotiga(ActionEvent event) {
		if(event.getSource() == menuItemBotigaSortir){
			Platform.exit();
		}
	}

	@FXML
	void onActionMenuVehicles(ActionEvent event) throws IOException {
		if(event.getSource() == menuItemVehiclesMantenir){
			//Carrega el fitxer amb la interficie d'usuari
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VehiclesView.fxml"));
			AnchorPane panell = (AnchorPane)loader.load();
			//Crear un objecte de la clase XXXController ja que necessitarem accedir al mètodes d'aquesta classe
			VehiclesController vehiclesControler = (VehiclesController)loader.getController();
			vehiclesControler.setTabPane(tabPane);
			
			//Crear una nova pestanya 
			Tab tab = new Tab();
			tab.setText("Vehicles");
			tab.setContent(panell);
			tabPane.getTabs().add(tab);
			tabPane.getSelectionModel().select(tab);
			tab.setOnCloseRequest(new EventHandler<Event>() {
				public void handle(Event e) {
					//si hi ha alguna pestanya més oberta, preguntar si es vol tancar
					if(vehiclesControler.getTabsFilles().size() > 0){
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setHeaderText("Les pestanyes obertes amb els vehicles es tancaran. Vols continuar?");
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {
							//tancar totes les pestanyes de vehicles
							tabPane.getTabs().removeAll(vehiclesControler.getTabsFilles());
						}else if(result.get() == ButtonType.CANCEL){
							//cancelar el tancament de la pestanya
							e.consume();
						}
					}

					if(vehiclesControler.isModificacions()){
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setHeaderText("Vols guardar els canvis abans de sortir?");
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {
							//guardar els vehicles al fitxer
							vehiclesControler.getVehicles().saveAll();
							//tancar totes les pestanyes de vehicles
							tabPane.getTabs().removeAll(vehiclesControler.getTabsFilles());
						}else if(result.get() == ButtonType.CANCEL){
							//cancelar el tancament de la pestanya
							e.consume();
						}
					}
				}
			});	
		}
		
		if(event.getSource() == menuItemVehiclesImportar) {
			
			//Carrega el fitxer amb la interficie d'usuari
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VehiclesImportarView.fxml"));
			AnchorPane panell = (AnchorPane)loader.load();
			
			//Crear un objecte de la clase XXXController ja que necessitarem accedir al mètodes d'aquesta classe
			VehiclesImportarController vehiclesImportar = (VehiclesImportarController)loader.getController();
			vehiclesImportar.setTabPane(tabPane);
			

			//Crear una nova pestanya 
			Tab tab = new Tab();
			tab.setText("Importar Vehicles");
			tab.setContent(panell);
			tabPane.getTabs().add(tab);
			tabPane.getSelectionModel().select(tab);
			
			
			
	
		}
	}
	
	
	@FXML
	void onActionMenuItemImportar(ActionEvent event) {
		
	}

	@FXML
	void onActionMenuItemAbout(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setTitle("About AutoBotiga");
		alert.setHeaderText("Autobotiga v0.4");
		alert.setContentText("Manuel Ruiz Morante - mruiz433@xtec.cat"
				+ "\nIcones creades pels usuaris Madebyoliver, kirill.kazachek y descarregades de wwww.flaticon.com. Consultar arxiu credits.txt");
		alert.showAndWait();
	}
}
