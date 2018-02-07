package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.Cotxe;
import model.Vehicle;
import model.Vehicles;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;

public class CotxeDetallController {

	//Objecte per gestionar la persistència de les dades al fitxer
	private Vehicles vehicles;
	//Vehicle que s'està editant
	private Cotxe cotxe;
	//Indicatiu de si es tracta d'un nou registre o una mofidicació
	private boolean nouRegistre;
	private ObservableList<Vehicle> vehiclesData;
	private TabPane tabPane;
	//objecte per controlar les pestanyes "filles" obertes
	private ArrayList<Tab> tabsFilles = new ArrayList<Tab> ();

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TextField versioTextField;

	@FXML
	private TextField preuTextField;

	@FXML
	private TextField marcaTextField;

	@FXML
	private TextField modelTextField;

	@FXML
	private TextField emisionsTextField;

	@FXML
	private TextField matriculaTextField;

	@FXML
	private ComboBox<String> portesComboBox;

	@FXML
	private ComboBox<String> placesComboBox;

	@FXML
	private DatePicker dataMatriculacioDatePicker;

	@FXML
	private Button acceptarButton;
	private int selectedIndex;

	public void setVehicles(Vehicles vehicles){
		this.vehicles = vehicles;
	}

	public void setVehicle(Vehicle vehicle){
		this.cotxe = (Cotxe)vehicle;

		matriculaTextField.setText(cotxe.getMatricula());
		marcaTextField.setText(cotxe.getMarca());
		modelTextField.setText(cotxe.getModel());
		versioTextField.setText(cotxe.getVersio());
		emisionsTextField.setText(String.valueOf(cotxe.getEmisionsCO2()));
		preuTextField.setText(String.valueOf(cotxe.getPreu()));
		portesComboBox.setValue(String.valueOf(cotxe.getPortes()));
		placesComboBox.setValue(String.valueOf(cotxe.getPlaces()));
		dataMatriculacioDatePicker.setValue(cotxe.getDataMatriculacio());
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public void setVehiclesData(ObservableList<Vehicle> vehiclesData) {
		this.vehiclesData = vehiclesData;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}
	public void setNouRegistre(boolean nouRegistre) {
		this.nouRegistre = nouRegistre;
	}

	public void setTabsFilles(ArrayList<Tab> tabsFilles) {
		this.tabsFilles = tabsFilles;
	}

	/**
	 * Inicialitza la classe. JAVA l'executa automàticament després de carregar el fitxer fxml
	 * @throws FileNotFoundException 
	 */
	@FXML private void initialize() throws FileNotFoundException {
		portesComboBox.getItems().addAll("3","4","5");
		placesComboBox.getItems().addAll("2","4","5","7","8");
		acceptarButton.setGraphic(new ImageView(new Image(new FileInputStream("icons/success.png"))));
	}

	@FXML
	void onActionAcceptar(ActionEvent event) {
		//verificar si les dades són vàlides
		if(isDatosValidos()){
			cotxe.setMatricula(matriculaTextField.getText());
			cotxe.setMarca(marcaTextField.getText());
			cotxe.setModel(modelTextField.getText());
			cotxe.setVersio(versioTextField.getText());
			cotxe.setEmisionsCO2(Integer.parseInt(emisionsTextField.getText()));
			cotxe.setPreu(Double.parseDouble(preuTextField.getText()));
			cotxe.setPortes(Integer.parseInt(portesComboBox.getValue()));
			cotxe.setPlaces(Integer.parseInt(placesComboBox.getValue()));
			cotxe.setDataMatriculacio(dataMatriculacioDatePicker.getValue());
		}

		vehicles.guardar(cotxe);
		netejarFormulari();
		tabsFilles.remove(tabPane.getSelectionModel().getSelectedItem());
		tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
		if (nouRegistre) vehiclesData.add(cotxe);
		else vehiclesData.set(selectedIndex, cotxe);

		//vehiclesTableView.refresh();
		vehicles.imprimirTots();
	}

	private void netejarFormulari() {
		matriculaTextField.setText("");
		marcaTextField.setText("");
		modelTextField.setText("");
		versioTextField.setText("");
		emisionsTextField.setText("");
		preuTextField.setText("");
		portesComboBox.setValue(null);
		placesComboBox.setValue(null);
		dataMatriculacioDatePicker.setValue(null);
	}

	private boolean isDatosValidos() {
		return true;
	}	
}
