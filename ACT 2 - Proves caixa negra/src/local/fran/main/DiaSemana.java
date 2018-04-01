package local.fran.main;

import java.time.DateTimeException;
import java.time.LocalDate;



public class DiaSemana {
	
	
	public  static String getDiaSemana(int dia, int mes, int ano) {
		
		
		try {
			LocalDate fecha = LocalDate.of(ano,mes,dia);
			switch (fecha.getDayOfWeek().toString()) {
			case "SUNDAY":
				
				return "Domingo";
			case "MONDAY":
				
				return "Lunes";
			case "TUESDAY":
				
				return "Martes";
			case "WEDNESDAY":
				
				return "Miercoles";
			case "THURSDAY":
				
				return "Jueves";
			case "FRIDAY":
				
				return "Viernes";
					
			case "SATURDAY":
				return "Sabado";
			
			default:
				return " ";
				
			}
				
			
		}catch (DateTimeException e) {
			return "ERROR "+e.getMessage();
		}
		 	
		
        
    }

}


