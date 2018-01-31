package controlador;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
	
	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}

	public void salir() throws IOException {
		
		//Guarda la estructura de datos en un fichero y cierra el escenario
		dao_productos.saveData();
		ventana.close();
		
	}

}
