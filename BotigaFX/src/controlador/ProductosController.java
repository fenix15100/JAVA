package controlador;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.*;

public class ProductosController {
	
	private Stage ventana;
	
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
	
	
    
    
	
	@FXML private void initialize() {
		
		//Inyecto en el ComboBox dos Values.
		tipoComboBox.getItems().addAll("Joc","Pack");
		
		
		
		
		
	
		
	}
	
	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}

	public void salir() {
		//HERE SAVE Productos and exit
		ventana.close();
		
	}

}
