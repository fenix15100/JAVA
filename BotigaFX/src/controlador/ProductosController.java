package controlador;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.*;

public class ProductosController {
	
	private Stage ventana;
	private Productos dao_productos=new Productos();
	
	//Inyecion de los elementos de la Vista
	@FXML
    private AnchorPane root;

    @FXML
    private TextField idTextfield;

    @FXML
    private TextField nomTexfield;

    @FXML
    private TextField stockTexfield;

    @FXML
    private DatePicker I_catalogoDatePicker;

    @FXML
    private DatePicker f_catalogoDatePicker;

    @FXML
    private ComboBox<String> tipoComboBox;
	
	
    
    
	
	@FXML private void initialize() throws IOException {
		
		//Inyecto en el ComboBox dos Values.
		tipoComboBox.getItems().addAll("Joc","Pack");
		
		//Instancio el DAO Productos y cargo la persitencia de datos
		dao_productos.loadData();
		
		
	}
	
	
	@FXML
	public void OnActionbtnGuardar(ActionEvent event) throws IOException {
	
	}
	
	@FXML
	public void OnActionbtnEliminar(ActionEvent event) throws IOException {
		
	}
	
	@FXML 
	public void OnActionbtnSalir(ActionEvent event) throws IOException {
		
	}
	
	@FXML 
	private void onKeyPressedId(KeyEvent e) throws IOException {
		if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.TAB){
			
		}else {
			//Nuevo registro
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Metodos para cargar una ventana al controlador
	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}

	
	//Metodo que se ejecuta al salir de la aplicacion
	public void salir() throws IOException {
		
		//Guarda la estructura de datos en un fichero y cierra el escenario
		dao_productos.saveData();
		ventana.close();
		
	}

}
