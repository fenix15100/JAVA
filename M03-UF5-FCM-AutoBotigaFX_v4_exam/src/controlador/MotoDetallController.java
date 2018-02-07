package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.Cotxe;
import model.Moto;
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

public class MotoDetallController {

	//Objecte per gestionar la persistència de les dades al fitxer
	private Vehicles vehicles;
	//Vehicle que s'està editant
	private Moto moto;
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
	private TextField categoriaTextField;

	@FXML
	private TextField cilindadraTexfield;

	@FXML
	private DatePicker dataMatriculacioDatePicker;

	@FXML
	private Button acceptarButton;
	private int selectedIndex;

	public void setVehicles(Vehicles vehicles){
		this.vehicles = vehicles;
	}

	public void setVehicle(Vehicle vehicle){
		this.moto = (Moto)vehicle;

		matriculaTextField.setText(moto.getMatricula());
		marcaTextField.setText(moto.getMarca());
		modelTextField.setText(moto.getModel());
		versioTextField.setText(moto.getVersio());
		emisionsTextField.setText(String.valueOf(moto.getEmisionsCO2()));
		preuTextField.setText(String.valueOf(moto.getPreu()));
		categoriaTextField.setText(moto.getCategoria());
		cilindadraTexfield.setText(String.valueOf(moto.getCilindrada()));
		dataMatriculacioDatePicker.setValue(moto.getDataMatriculacio());
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
		
		acceptarButton.setGraphic(new ImageView(new Image(new FileInputStream("icons/success.png"))));
	}

	@FXML
	void onActionAcceptar(ActionEvent event) {
		//verificar si les dades són vàlides
		if(isDatosValidos()){
			moto.setMatricula(matriculaTextField.getText());
			moto.setMarca(marcaTextField.getText());
			moto.setModel(modelTextField.getText());
			moto.setVersio(versioTextField.getText());
			moto.setEmisionsCO2(Integer.parseInt(emisionsTextField.getText()));
			moto.setPreu(Double.parseDouble(preuTextField.getText()));
			
			moto.setDataMatriculacio(dataMatriculacioDatePicker.getValue());
		}

		vehicles.guardar(moto);
		netejarFormulari();
		tabsFilles.remove(tabPane.getSelectionModel().getSelectedItem());
		tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
		if (nouRegistre) vehiclesData.add(moto);
		else vehiclesData.set(selectedIndex, moto);

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
		
		dataMatriculacioDatePicker.setValue(null);
	}

	private boolean isDatosValidos() {
		return true;
	}	
}
