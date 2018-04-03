package local.fran.main;

import java.time.DateTimeException;
import java.time.LocalDate;



public class DiaSemana {
	
	
	public  String getDiaSemana(int dia, int mes, int ano) {
		
		
		try {
			LocalDate fecha = LocalDate.of(ano,mes,dia);
			switch (fecha.getDayOfWeek().toString()) {
			case "SUNDAY":
				
				return "DOMINGO";
			case "MONDAY":
				
				return "LUNES";
			case "TUESDAY":
				
				return "MARTES";
			case "WEDNESDAY":
				
				return "MIERCOLES";
			case "THURSDAY":
				
				return "JUEVES";
			case "FRIDAY":
				
				return "VIERNES";
					
			case "SATURDAY":
				return "SABADO";
			
			default:
				return " ";
				
			}
				
			
		}catch (DateTimeException e) {
			return "ERROR";
		}
		 	
		
        
    }

}


