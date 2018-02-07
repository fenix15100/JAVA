package controlador;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Cotxe;
import model.Moto;
import model.Vehicle;
import model.Vehicles;

public class VehiclesController {
	//Objecte per gestionar la persistència de les dades al fitxer
	private Vehicles vehicles;
	//Objecte per gestionar el registre actual
	private Vehicle vehicle;
	//objecte per afegir les files de la taula
	private ObservableList<Vehicle> vehiclesData;
	//control de modificacions a la llista
	private boolean modificacions = false;
	//TabPane del formulari inicial
	private TabPane tabPane;	
	//objecte per controlar les pestanyes "filles" obertes
	private ArrayList<Tab> tabsFilles = new ArrayList<Tab> ();

	
	@FXML
	private TableView<Vehicle> vehiclesTableView;
	@FXML
	private TableColumn<Vehicle, String> matriculaColumn;
	@FXML
	private TableColumn<Vehicle, String> marcaColumn;
	@FXML
	private TableColumn<Vehicle, String> modelColumn;
	@FXML
	private TableColumn<Vehicle, String> versioColumn;
	@FXML
	private TableColumn<Vehicle, Integer> emisionsColumn;
	@FXML
	private TableColumn<Vehicle, Double> preuColumn;

	@FXML
	private MenuItem nouCotxeMenuItem;
	@FXML
	private MenuItem nouMotoMenuItem;
	@FXML
	private MenuButton nouMenuButton;
	@FXML
	private Button modificaButton;
	@FXML
	private Button eliminaButton;
	@FXML
	private Button guardarButton;
	@FXML
	private Button exportarButton;
	@FXML
	private TableColumn<Vehicle, LocalDate> datamatriculacolum;
	
	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}
	
	public ArrayList<Tab> getTabsFilles() {
		return tabsFilles;
	}

	public Vehicles getVehicles() {
		return vehicles;
	}

	public boolean isModificacions() {
		return modificacions;
	}

	/**
	 * Inicialitza la classe. JAVA l'executa automàticament després de carregar el fitxer fxml
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	@FXML private void initialize() throws FileNotFoundException {

		try{
			//Obrir el fitxer
			vehicles = new Vehicles();
			vehicles.openAll();
		}catch(FileNotFoundException e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("El fitxer de vehicles no existeix o s'ha esborrat.");
			alert.showAndWait();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}

		// Carregar la taula
		matriculaColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("matricula"));
		marcaColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("marca"));
		modelColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("model"));
		versioColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("versio"));
		emisionsColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("emisionsCO2"));
		datamatriculacolum.setCellValueFactory(new PropertyValueFactory<Vehicle,LocalDate>("data_matriculacio"));
		preuColumn.setCellValueFactory(new PropertyValueFactory<Vehicle,Double>("preu"));
		vehiclesData = FXCollections.observableList(vehicles.toArrayList());
		vehiclesTableView.setItems(vehiclesData);
		//		vehiclesData.addListener((ListChangeListener.Change<? extends Vehicle> change) -> {
		//	        modificacions = true;
		//	    });

		vehiclesData.addListener(new ListChangeListener<Vehicle>() {
			public void onChanged(ListChangeListener.Change<? extends Vehicle> change) {
				//si hi ha un canvi a les dades de la taula, ho marquem per oferir a l'usuari l'opció de guardar en cas de tancar sense guardar
				modificacions = true;
			}
		});

		nouMenuButton.setGraphic(new ImageView(new Image(new FileInputStream("icons/plus.png"))));
		nouCotxeMenuItem.setGraphic(new ImageView(new Image(new FileInputStream("icons/automobile.png"))));
		nouMotoMenuItem.setGraphic(new ImageView(new Image(new FileInputStream("icons/motor-sports.png"))));
		modificaButton.setGraphic(new ImageView(new Image(new FileInputStream("icons/edit.png"))));
		eliminaButton.setGraphic(new ImageView(new Image(new FileInputStream("icons/minus.png"))));
		guardarButton.setGraphic(new ImageView(new Image(new FileInputStream("icons/save.png"))));
		exportarButton.setGraphic(new ImageView(new Image(new FileInputStream("icons/export.png"))));
	}

	@FXML
	void onActionButtonNouCotxe(ActionEvent event) throws IOException {
		//nou cotxe
		vehicle = new Cotxe();
		obrirVehicleDetall(vehicle, true);
	}
	
	
	@FXML
	void onActionButtonNouMoto(ActionEvent event) throws IOException {
		//nou moto
		vehicle = new Moto();
		obrirVehicleDetall(vehicle, true);
	}

	@FXML
	void onActionButtonModifica(ActionEvent event) throws IOException {
		//vehicle a modificar
		vehicle = vehiclesTableView.getSelectionModel().getSelectedItem();
		if(vehicle != null) obrirVehicleDetall(vehicle, false);
	}

	@FXML
	void onActionButtonElimina(ActionEvent event) {
		//vehicle a eliminar
		vehicle = vehiclesTableView.getSelectionModel().getSelectedItem();

		// Mostrar missatge confirmació
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Vol esborrar el vehicle " + vehicle.getMatricula() + "?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if(vehicles.eliminar(vehicle.getMatricula())){ 
				vehiclesData.remove(vehiclesTableView.getSelectionModel().getSelectedIndex());
				vehicles.imprimirTots();
			}
		}
	}

	@FXML
	void onActionButtonGuardar(ActionEvent event) {
		//guardar els vehicles al fitxer
		
		vehicles.saveAll();
	
		//tancar totes les pestanyes de vehicles
		tabPane.getTabs().removeAll(tabsFilles);
	}

	@FXML
	void onActionButtonExportar(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Nom del fitxer a crear");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DEX", "*.dex"));
		fileChooser.setInitialFileName("Vehicles_" + LocalDate.now()+".dex");
		File file = fileChooser.showSaveDialog(tabPane.getParent().getScene().getWindow());
		if (file != null) {
			try {
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
				for (Vehicle vehicle : vehicles.toArrayList()) {
					dos.writeUTF(vehicle.getClass().getSimpleName());
					dos.writeUTF(vehicle.getMatricula());
					dos.writeUTF(vehicle.getMarca());
					dos.writeUTF(vehicle.getModel());
					dos.writeUTF(vehicle.getVersio());
					dos.writeInt(vehicle.getEmisionsCO2());
					if(vehicle instanceof Cotxe){
						dos.writeInt(((Cotxe)vehicle).getPortes());
						dos.writeInt(((Cotxe)vehicle).getPlaces());
					}else if(vehicle instanceof Moto){
						dos.writeUTF(((Moto)vehicle).getCategoria());
						dos.writeInt(((Moto)vehicle).getCilindrada());
					}
				}
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	@FXML
	void onMouseClickedTableView(MouseEvent event) throws IOException {
		if (event.getClickCount() == 2){
			vehicle = vehiclesTableView.getSelectionModel().getSelectedItem();
			if (vehicle != null) obrirVehicleDetall(vehicle, false);
		}
	}

	private void obrirVehicleDetall(Vehicle vehicle, boolean nouRegistre) throws IOException{
		FXMLLoader loader = null;
		AnchorPane panell = null;
		if (vehicle instanceof Cotxe){
			//Carrega el fitxer amb la interficie d'usuari per modificar el cotxe
			loader = new FXMLLoader(getClass().getResource("/vista/CotxeDetallView.fxml"));
			panell = (AnchorPane)loader.load();

			// Pasar els objectes que necessitarem al controller
			CotxeDetallController controller = loader.getController();
			controller.setVehicles(vehicles);
			controller.setVehicle(vehicle);
			controller.setSelectedIndex(vehiclesTableView.getSelectionModel().getSelectedIndex());
			controller.setVehiclesData(vehiclesData);
			controller.setTabPane(tabPane);
			controller.setTabsFilles(tabsFilles);
			controller.setNouRegistre(nouRegistre);
		}
		
		if (vehicle instanceof Moto){
			//Carrega el fitxer amb la interficie d'usuari per modificar el cotxe
			loader = new FXMLLoader(getClass().getResource("/vista/MotoDetallView.fxml"));
			panell = (AnchorPane)loader.load();

			// Pasar els objectes que necessitarem al controller
			MotoDetallController controller = loader.getController();
			controller.setVehicles(vehicles);
			controller.setVehicle(vehicle);
			controller.setSelectedIndex(vehiclesTableView.getSelectionModel().getSelectedIndex());
			controller.setVehiclesData(vehiclesData);
			controller.setTabPane(tabPane);
			controller.setTabsFilles(tabsFilles);
			controller.setNouRegistre(nouRegistre);
		}

		//Crear una nova pestanya per editar el vehicle
		Tab tab = new Tab();
		tab.setText(vehicle.getClass().getSimpleName() + " " + vehicle.getMatricula());
		tab.setContent(panell);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);
		tabsFilles.add(tab);
	}
}
