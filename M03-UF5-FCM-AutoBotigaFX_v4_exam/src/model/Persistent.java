package model;

import java.util.ArrayList;

public interface Persistent {
	/**
	 * Guarda un vehicle a la taula (nou o existent)
	 * @param vehicle
	 * @return void
	 */
	public void guardar(Vehicle vehicle);
	
	/**
	 * Elimina el vehicle de la taula que coincideixi amb la matrícula
	 * @param matricula
	 * @return true, si l'ha eliminat
	 * 			o false, si no
	 */
	public boolean eliminar(String matricula);
	
	/**
	 * Busca un vehicle a la taula a partir de la seva matrícula
	 * @param matricula
	 * @return El vehicle trovat
	 * 			0 null, si no el trova
	 */
	public Vehicle buscar(String matricula);
    
    /**
     * Imprimeix per la consola tots els vehicles de la taula 
     */
    public void imprimirTots();
    
    public ArrayList<?> toArrayList();
}
