package controlador;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import modelo.*;

public class ProductosController {

	private Stage ventana;
	private Productos dao_productos = new Productos();

	// Inyecion de los campos Producto
	@FXML
	private AnchorPane root;

	@FXML
	private TextField idTextfield;

	@FXML
	private TextField nomTexfield;

	@FXML
	private TextField stockTexfield;

	@FXML
	private TextField precioTextfield;

	@FXML
	private DatePicker I_catalogoDatePicker;

	@FXML
	private DatePicker f_catalogoDatePicker;

	@FXML
	private ComboBox<String> tipoComboBox;

	// Tab Pane elements

	@FXML
	private TabPane datosTabpane;

	@FXML
	private Tab jocTab;

	@FXML
	private TextField edadminimaTextfield;

	@FXML
	private TextField proveedorTextField;

	@FXML
	private Tab packTab;

	@FXML
	private TextField descuentoTextField;

	@FXML
	private TextField listadejuegosTextfield;

	@FXML
	private Button guardarButton;

	@FXML
	private Button modificarButton;

	@FXML
	private Button salirButton;

	@FXML
	private void initialize() throws IOException {

		// Inyecto en el ComboBox dos Values.
		tipoComboBox.getItems().addAll("Joc", "Pack");

		// Instancio el DAO Productos y cargo la persitencia de datos
		dao_productos.loadData();

	}

	@FXML
	private void OnActionguardarButton(ActionEvent event) {
		
		//VALIDARDATOS OTRO DIA
		
		if(tipoComboBox.getValue().equals("Joc")) {
			
			Joc juego=new Joc(
					idTextfield.getText(),
					nomTexfield.getText(),
					Double.parseDouble(precioTextfield.getText()),
					0,
					I_catalogoDatePicker.getValue(),
					f_catalogoDatePicker.getValue(),
					Integer.parseInt(edadminimaTextfield.getText()),
					Integer.parseInt(proveedorTextField.getText())
					);
			dao_productos.addProducto(juego);
			limpiarFormulario();
			
		}else if(tipoComboBox.getValue().equals("Pack")) {
			
			Pack pack=new Pack(
					idTextfield.getText(),
					nomTexfield.getText(),
					Double.parseDouble(precioTextfield.getText()),
					0,
					I_catalogoDatePicker.getValue(),
					f_catalogoDatePicker.getValue(),
					generateTreeSetfromString(listadejuegosTextfield.getText()),
					Double.parseDouble(descuentoTextField.getText())
					);
			dao_productos.addProducto(pack);
			limpiarFormulario();
			
			
			
			
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(ventana);
			alert.setTitle("Error al guardar");
			alert.setHeaderText("Tienes que indicar el tipo de Producto");
			alert.setContentText("Melon");

			alert.showAndWait();
			
		}
		

	}

	@FXML
	private void OnActionmodificarButton(ActionEvent event) {
		
		

	}

	@FXML
	private void OnActionsalirButton(ActionEvent event) throws IOException {
		salir();

	}

	@FXML
	private void onKeyPressedId(KeyEvent e) throws IOException {
		
			//Evento principal
		if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.TAB) {
				
			//Si ID existe cargo los datos del producto
			if (dao_productos.searchProducto(idTextfield.getText()) != null) {
				if (dao_productos.searchProducto(idTextfield.getText()) instanceof Joc) {
					Joc juego = (Joc) dao_productos.searchProducto(idTextfield.getText());
					nomTexfield.setText(juego.getNom());
					stockTexfield.setText(String.valueOf(juego.getStock()));
					precioTextfield.setText(String.valueOf(juego.getPreu()));
					I_catalogoDatePicker.setValue(juego.getFecha_inicio());
					f_catalogoDatePicker.setValue(juego.getFecha_final());
					tipoComboBox.setValue("Joc");
					edadminimaTextfield.setText(String.valueOf(juego.getEdad_minima()));
					proveedorTextField.setText(String.valueOf(juego.getId_proveedor()));
					
					//Desabilito el campo combo para que al modificar el producto no se elija
					//otro tipo de producto (Eso seria un nuevo producto no una modificacion)
					tipoComboBox.setDisable(true);
					//Habilito la Tab correspondiente al producto
					packTab.setDisable(true);
					jocTab.setDisable(false);
					//Abro el Tab que toca
					datosTabpane.getSelectionModel().select(jocTab);
				} else {
					Pack pack = (Pack) dao_productos.searchProducto(idTextfield.getText());
					nomTexfield.setText(pack.getNom());
					stockTexfield.setText(String.valueOf(pack.getStock()));
					precioTextfield.setText(String.valueOf(pack.getPreu()));
					I_catalogoDatePicker.setValue(pack.getFecha_inicio());
					f_catalogoDatePicker.setValue(pack.getFecha_final());
					descuentoTextField.setText(String.valueOf(pack.getDescuento()));
					listadejuegosTextfield.setText(pack.listajuegosToString());
					tipoComboBox.setValue("Pack");
					
					//Desabilito el campo combo para que al modificar el producto no se elija
					//otro tipo de producto (Eso seria un nuevo producto no una modificacion)
					tipoComboBox.setDisable(true);
					//Habilito la Tab correspondiente al producto
					jocTab.setDisable(true);
					packTab.setDisable(false);
					//Abro el Tab que toca
					datosTabpane.getSelectionModel().select(packTab);

				}

			} else {
				// Nuevo registro limpio formulario menos ID
				nomTexfield.clear();
				stockTexfield.clear();
				I_catalogoDatePicker.getEditor().clear();
				f_catalogoDatePicker.getEditor().clear();
				//Vuelvo a habilitar el campo combo y lo seteo por defecto
				tipoComboBox.setDisable(false);
				tipoComboBox.setValue("Elije");
				
				precioTextfield.clear();
				edadminimaTextfield.clear();
				proveedorTextField.clear();
				descuentoTextField.clear();
				listadejuegosTextfield.clear();
				
				//quito el tab pane a la espera del evento del combobox en un nuevo registro
				//Para evitar insertar datos de un pack en un joc
				datosTabpane.setDisable(true);

			}
		}

	}

	// Metodos para cargar una ventana al controlador
	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}

	// Metodo que se ejecuta al salir de la aplicacion
	public void salir() throws IOException {

		// Guarda la estructura de datos en un fichero y cierra el escenario
		dao_productos.saveData();
		ventana.close();

	}

	
	private TreeSet<String> generateTreeSetfromString(String data){
		
		//http://java-tweets.blogspot.com.es/2012/07/convert-array-to-treeset-in-java.html
		
		String[] vector =data.split(",");
		List<String> list = Arrays.asList(vector);
		TreeSet<String>set = new TreeSet<String>(list);


		return set;
		
		
		
		
		
	}
	private void limpiarFormulario() {
		idTextfield.clear();
		nomTexfield.clear();
		stockTexfield.clear();
		I_catalogoDatePicker.getEditor().clear();
		f_catalogoDatePicker.getEditor().clear();
		tipoComboBox.setValue("Elige");
		precioTextfield.clear();
		edadminimaTextfield.clear();
		proveedorTextField.clear();
		descuentoTextField.clear();
		listadejuegosTextfield.clear();
		packTab.setDisable(false);
		jocTab.setDisable(false);

	}

}
