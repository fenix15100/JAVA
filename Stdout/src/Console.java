import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Console extends Application {

	 @FXML
	 private TextArea area;

	@FXML
	private Button btn;
	   
	
	@Override
	public void start(Stage escenarioPrincipal) throws Exception {
		
		
				FXMLLoader loaderview = new FXMLLoader(getClass().getResource("ConsoleView.fxml"));
				
			
				//Creo una Scena a partir de la vista
				Scene escenaPrincipal = new Scene(loaderview.load());
				
				//Asigno la escena al Escenario principal y abro el telon
				escenarioPrincipal.setScene(escenaPrincipal);
				escenarioPrincipal.setTitle("Consola");
				escenarioPrincipal.show();
				
				
				
	}
	
	
	 @FXML
	    void go(ActionEvent event) {
		 
		 OutputStream out = new OutputStream() {
		        @Override
		        public void write(int b) throws IOException {
		            appendText(String.valueOf((char) b));
		        }
		    };
		    System.setOut(new PrintStream(out, true));
		 
		 for(int i=0;i<80;i++) {
			 
			 System.out.println("Redirecion del stdout al la interfaz");
			 
		 }
		 
		 

	    }
	 
	 
	 public void appendText(String str) {
		    Platform.runLater(() -> area.appendText(str));
		}
	
	
	
	
	
	
}