package controlador;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.*;

public class VehiclesImportarController {
	
	@FXML
	private AnchorPane rootimport;
	
    @FXML
    private Button importarbtn;
    
    @FXML
    private TableView<Vehicle> tableviewimportar;

    @FXML
    private TableColumn<Vehicle, String> cmatricula;

    @FXML
    private TableColumn<Vehicle, String> cMarca;

    @FXML
    private TableColumn<Vehicle, String> cModel;

    @FXML
    private TableColumn<Vehicle, String> cVersio;

    @FXML
    private TableColumn<Vehicle, Integer> cEmisions;
    
    @FXML
    private Button selectficherbtn;
    @FXML
    private Label label;
    
    private ArrayList<Vehicle> lista=null;


	
	
	private TabPane tab;
	private File file;


	public TabPane getTabPane() {
		return tab;
	}


	public void setTabPane(TabPane tab) {
		this.tab = tab;
	}
	
	
	@FXML 
	private void initialize() {
		
		
		
		
		
		
		
	}
	
	
	
	 

	    @FXML
	    void OnActionImportar(ActionEvent event) {

	    }	
	    
	    @FXML
	    void OnActionselectficher(ActionEvent event) throws IOException {
	    	
	    	FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Nom del fitxer a importar");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("DEX", "*.dex"));
			file = fileChooser.showOpenDialog(tab.getParent().getScene().getWindow());
			
			label.setText(file.getAbsolutePath());
			
			DataInputStream reader=new DataInputStream(new FileInputStream(file));
			
			while(true) {
				
				String clase=reader.readUTF();
				
				switch(clase) {
				
				case "Cotxe":
					String matricula=reader.readUTF();
					String Marca=reader.readUTF();
					String Model=reader.readUTF();
					String Versio=reader.readUTF();
					int emision=reader.readInt();
					
					//Sabia como hacerlo no tengo tiempo
					//Cotxe coche=new Cotxe(matricula,Marca,Model,Versio,emision);
					break;
				
				
				
				
				}
				
				
				
			}
			
			
			
			
			
			
			
			

	    }
	
	
	
	
	
	
	

}
