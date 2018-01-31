package controlador;


import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
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
    private TextField precioTextfield;

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
		
		double precio=3.6;
		
		Joc j=new Joc("1","parchis",precio,10,null,null,0,0);
		dao_productos.addProducto(j);
		
		System.out.println(Double.toString(j.getPreu()));
		
		
		
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
			
			if(dao_productos.searchProducto(idTextfield.getText())!=null) {
				if(dao_productos.searchProducto(idTextfield.getText()) instanceof Joc) {
					Joc juego=(Joc)dao_productos.searchProducto(idTextfield.getText());
					nomTexfield.setText(juego.getNom());
					stockTexfield.setText(String.valueOf(juego.getStock()));
					precioTextfield.setText(Double.toString(juego.getPreu()));
					I_catalogoDatePicker.setValue(juego.getFecha_inicio());
					f_catalogoDatePicker.setValue(juego.getFecha_final());
					
					
				}else {
					Pack pack=(Pack)dao_productos.searchProducto(idTextfield.getText());
					nomTexfield.setText(pack.getNom());
					stockTexfield.setText(String.valueOf(pack.getStock()));
					precioTextfield.setText(Double.toString(pack.getPreu()));
					I_catalogoDatePicker.setValue(pack.getFecha_inicio());
					f_catalogoDatePicker.setValue(pack.getFecha_final());
				}
				
			}
			
			
			
			
		}else {
			limpiarFormulario();
			
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
	
	private void limpiarFormulario(){
		idTextfield.clear();
		nomTexfield.clear();
		stockTexfield.clear();
		I_catalogoDatePicker.getEditor().clear();
		f_catalogoDatePicker.getEditor().clear();
		tipoComboBox.setValue("Elige");
		precioTextfield.clear();
	
	}

}
