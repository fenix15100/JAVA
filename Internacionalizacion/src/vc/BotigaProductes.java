package vc;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import bo.*;
import dao.*;

public class BotigaProductes {

	private static Scanner teclado;
	private static DateTimeFormatter parse_date=null;

	public static void main(String[] args) throws SecurityException, IOException {

		Logger log=Logger.getLogger("botiga.Botiga");
		FileHandler fh= new FileHandler("log.txt",true);
		fh.setFormatter(new SimpleFormatter());
		log.addHandler(fh);
		log.setLevel(Level.ALL);
		log.info("Start Log");
		try {
				
				menu();
			}catch (RuntimeException e) {
				log.log(Level.SEVERE,"Problema Greu",e);	
			}catch (Exception e) {
				log.log(Level.WARNING,"Posible Problema",e);
			}
		log.fine("Fin Del Log");
		
		
		
		
		
		
		
		

	}
	
	
	public  static void menu() throws IOException {
		
		//Instanciamos nuestro DAO para poder acceder a nuestra estructura de datos.
		
		Productos lista_productos=new Productos();
		
		lista_productos.loadData();
	
		teclado = new Scanner(System.in);
		
		parse_date=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		//Intenacionalizacion
		
		//Locale localeFormat=Locale.getDefault(Category.FORMAT);
		Locale localeDisplay=Locale.getDefault(Category.DISPLAY);
		ResourceBundle texts=ResourceBundle.getBundle("locale.Texts", localeDisplay);
		
		int opcion;
		
		//Menu Do while con la acciones de la interfaz de usuario
		do {
			System.out.println(texts.getString("0000"));
			System.out.println(texts.getString("0001"));
			System.out.println(texts.getString("0002"));
			System.out.println(texts.getString("0003"));
			System.out.println(texts.getString("0004"));
			System.out.println(texts.getString("0005"));
			System.out.println(texts.getString("0006"));
			System.out.println(texts.getString("0007"));
			System.out.println(texts.getString("0008"));
			System.out.println(texts.getString("0009"));
			System.out.println(texts.getString("0010"));
			opcion=teclado.nextInt();
			
			switch (opcion) {
				
				//Opcion que permite añadir un producto (joc o pack) al sistema, pide los datos pertinentes
				//Crea el objeto y se lo pasa al metodo de nuestro DAO que se encarga de esta accion.
				case 1:
					
					System.out.println("Que quieres introducir?: 1.Joc 2.Pack");
					int choice=teclado.nextInt();
					
					
					if(choice==1) {
						
						System.out.println("Dime el ID del juego");
						String id=teclado.next();
						
						System.out.println("Dime El nombre del juego");
						String nom=teclado.next();
						
						System.out.println("Dime El precio del juego");
						double preu=teclado.nextDouble();
						
						System.out.println("Dime El Stock del juego");
						int stock=teclado.nextInt();
						
						System.out.println("Dime la fecha de ingreso en el iventario");
						String fecha_ini_string=teclado.next();
						
						LocalDate fecha_ini=LocalDate.parse(fecha_ini_string, parse_date);
						
						System.out.println("Dime la fecha de descatalogado en el iventario");
						String fecha_desca_string=teclado.next();
						
						LocalDate fecha_desca=LocalDate.parse(fecha_desca_string, parse_date);
						
						
						System.out.println("Dime el PEGI del Juego");
						int edad_minima=teclado.nextInt();
						
						System.out.println("Dime El ID del Proveedor del juego");
						int id_proveedor=teclado.nextInt();
						
						
						Joc productemp=new Joc(id, nom, preu,stock,fecha_ini,fecha_desca,edad_minima, id_proveedor);
						
						if(!lista_productos.addProducto(productemp)) {
							System.out.println("Esta ID ya existe en el sistema");
						}else {
							lista_productos.addProducto(productemp);
							System.out.println("El producto ha sido introducido en el sistema");
							
						}
						
						
						
					}else {
						
						
						System.out.println("Dime el ID del Pack");
						String id=teclado.next();
						
						System.out.println("Dime El nombre del Pack");
						String nom=teclado.next();
						
						System.out.println("Dime El precio del Pack");
						double preu=teclado.nextDouble();
						
						System.out.println("Dime El stock de pack");
						int stock=teclado.nextInt();
						
						System.out.println("Dime la fecha de ingreso en el iventario");
						String fecha_ini_string=teclado.next();
						
						LocalDate fecha_ini=LocalDate.parse(fecha_ini_string, parse_date);
						
						System.out.println("Dime la fecha de descatalogado en el iventario");
						String fecha_desca_string=teclado.next();
						
						LocalDate fecha_desca=LocalDate.parse(fecha_desca_string, parse_date);
						
						System.out.println("Cuantos juegos quieres incluir en el pack? ");
						int cantidad=teclado.nextInt();
						
						TreeSet<String> ListaJuegos=new TreeSet<String>();
						
						for(int i=0;i<cantidad;i++) {
							
							System.out.println("Dime el ID del juego del "+(i+1)+" ejemplar del pack");
							String id_joc=teclado.next();
							if(ListaJuegos.contains(id)) {
								
								System.out.println("Esta Id de juego ya esta en el pack");
							}else {
								ListaJuegos.add(id_joc);
							}
							
							
							
						}
						
						System.out.println("Cuantos descuento tiene el pack? ");
						double descuento=teclado.nextDouble();
						
						Pack productemp=new Pack(id,nom,preu,stock,fecha_ini,fecha_desca,ListaJuegos,descuento);
						
						if(!lista_productos.addProducto(productemp)) {
							System.out.println("Esta ID ya existe en el sistema");
						}else {
							lista_productos.addProducto(productemp);
							System.out.println("El producto ha sido introducido en el sistema");
							
						}
						
						
						
						
						
						
					}
					
					
					break;
				
				//Opcion que permite Buscar un producto en nuestro sistema, si lo encuentra
				//El metodo del DAO que se encarga de esta accion nos devuelve el Producto
				//Por ultimo llamamos al los metodos de impresion de datos del producto y lo mostramos
				case 2:
						
						System.out.println("Dime el ID del producto");
						String id=teclado.next();
						if(lista_productos.searchProducto(id)==null) {
							System.out.println("Producto no encontrado");
						}else {
							lista_productos.searchProducto(id).imprimir();
							
						}
						
						
					
					break;
			
					//Opcion que permite modificar un producto del sistema, para ello
					//Busca el producto y si existe nos lo devuelve, despues lo copiamos en un
					//Objeto tipo producto vacio, mas tarde mostramos datos de producto y preguntamos
					//Si se quiere cambiar, si es asi, hacemos las preguntas pertinentes al usuario
					//Y con los metodos SETTERS del producto actualizamos la informacion.
					//Por ultimo enviamos este producto modificado al Metodo de nuestro DAO que se
					//Encargara de machacar en el sistema la informacion nueva del producto
				case 3:
					System.out.println("Dime el ID del producto a modificar");
					 id=teclado.next();
					if(lista_productos.searchProducto(id)==null) {
						System.out.println("Producto no encontrado");
					}else {
						if(lista_productos.searchProducto(id) instanceof Joc){
								//Cast de Producto a Joc
								Joc joctemp=(Joc)lista_productos.searchProducto(id);
								
								System.out.println("Los datos del producto son: ");
								joctemp.imprimir();
								
								System.out.println("Quieres cambiar los datos? 1.Si 2.No");
								choice=teclado.nextInt();
								
								if(choice==1) {
									System.out.println("Dime El nombre del juego");
									String nom=teclado.next();
									
									System.out.println("Dime El precio del juego");
									double preu=teclado.nextDouble();
									
									System.out.println("Dime la fecha de ingreso en el iventario");
									String fecha_ini_string=teclado.next();
									
									LocalDate fecha_ini=LocalDate.parse(fecha_ini_string, parse_date);
									
									System.out.println("Dime la fecha de descatalogado en el iventario");
									String fecha_desca_string=teclado.next();
									
									LocalDate fecha_desca=LocalDate.parse(fecha_desca_string, parse_date);
									
									System.out.println("Dime el PEGI del Juego");
									int edad_minima=teclado.nextInt();
									
									System.out.println("Dime El ID del Proveedor del juego");
									int id_proveedor=teclado.nextInt();
									
									joctemp.setNom(nom);
									joctemp.setPreu(preu);
									joctemp.setEdad_minima(edad_minima);
									joctemp.setId_proveedor(id_proveedor);
									joctemp.setFecha_inicio(fecha_ini);
									joctemp.setFecha_final(fecha_desca);
									
									lista_productos.updateProducto(joctemp);
									
									
									
								}else {
									break;
								}
								
								
								
							
						}else if(lista_productos.searchProducto(id) instanceof Pack) {
							//Cast de Producto a Pack
							Pack packtemp=(Pack)lista_productos.searchProducto(id);
							
							System.out.println("Los datos del producto son: ");
							packtemp.imprimir();
							
							
							System.out.println("Qieres cambiar los datos? 1.Si 2.No");
							choice=teclado.nextInt();
							
							if(choice==1) {
								System.out.println("Dime El nombre del pack");
								String nom=teclado.next();
								
								System.out.println("Dime El precio del pack");
								double preu=teclado.nextDouble();
								
								
								System.out.println("Dime la fecha de ingreso en el iventario");
								String fecha_ini_string=teclado.next();
								
								LocalDate fecha_ini=LocalDate.parse(fecha_ini_string, parse_date);
								
								System.out.println("Dime la fecha de descatalogado en el iventario");
								String fecha_desca_string=teclado.next();
								
								LocalDate fecha_desca=LocalDate.parse(fecha_desca_string, parse_date);
								
								
								System.out.println("Dime el descuento del pack");
								double descuento=teclado.nextInt();
								
								
								int modpack=0;
								while(modpack!=3) {
									System.out.println("1.Añadir Juegos al pack 2.BorrarJuegos del pack 3.Nada mas");
									modpack=teclado.nextInt();
									
									if(modpack==1) {
										
										
										System.out.println("Dime la ID de juego que quieres añadir al pack");
										id=teclado.next();
										
										if(packtemp.addGame(id)) {
											packtemp.addGame(id);
											
										}else {
											System.out.println("Este ID de juego ya existe en el pack");
										}
										
										
										
									
									}else if(modpack==2) {
										
										System.out.println("Dime la ID de juego que quieres borrar del pack");
										id=teclado.next();
										
										
										if(packtemp.deleteGame(id)) {
											packtemp.deleteGame(id);
											
										}else {
											System.out.println("Este ID de juego no existe en el pack");
										}
										
										
										
									}
								
									
									
									
								}
								
								
							packtemp.setNom(nom);
							packtemp.setPreu(preu);
							packtemp.setDescuento(descuento);
							packtemp.setFecha_inicio(fecha_ini);
							packtemp.setFecha_final(fecha_desca);
							lista_productos.updateProducto(packtemp);	
							}
							
							
						
						}
					
					}  
					
					break;
					
					
				//Añade stock de un producto existente 
				case 4:
					System.out.println("1.Carga Manual\n2.Carga Por fichero");
					int op=teclado.nextInt();
					
					switch(op) {
					
						case 1:
							System.out.println("Dime el ID del producto al que añadir stock");
							 id=teclado.next();
							if(lista_productos.searchProducto(id)==null) {
								System.out.println("Producto no encontrado");
							}else {
								if(lista_productos.searchProducto(id) instanceof Joc){
										//Cast de Producto a Joc
										Joc joctemp=(Joc)lista_productos.searchProducto(id);
										
										System.out.println("El stock actual del producto es: "+joctemp.getStock());
										System.out.println("Cuanto Stock vas a añadir? ");
										int stock=teclado.nextInt();
										joctemp.ponStock(stock);
										lista_productos.updateProducto(joctemp);
										
										
								}else if(lista_productos.searchProducto(id) instanceof Pack) {
									
									Pack packtemp=(Pack)lista_productos.searchProducto(id);
									System.out.println("El stock actual del producto es: "+packtemp.getStock());
									System.out.println("Cuanto Stock vas a poner? ");
									int stock=teclado.nextInt();
									packtemp.ponStock(stock);
									lista_productos.updateProducto(packtemp);
									
									
									
									
									
									
								}
								
							}
							
							
							break;
						
						case 2:
							//Declaro Objeto de entrada de datos
							DataInputStream reader=null;
							
							try {
								//Lo vinculo a un fichero previamente preparado con DataOutputStram
								System.out.println("Introduce el nombre del fichero");
								String filename=teclado.next();
								reader=new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
								String ID=null;
								int stock=0;
								
								//Bucle infinito que lee una ID y luego un entero
								//Busca el producto con esa ID en el sistema si se encuentra
								//Augmenta el stock del producto con el entero leido en el fichero
								//El bucle acabara al saltar la Exception de final de fichero EOF
								//en culquier caso al final del proceso cierro el stream
								
								while(true) {
									ID=reader.readUTF();
									stock=reader.readInt();
									
									if(lista_productos.searchProducto(ID)!=null) {
										if(lista_productos.searchProducto(ID) instanceof Joc) {
											Joc joctemp=(Joc)lista_productos.searchProducto(ID);
											joctemp.ponStock(stock);
											lista_productos.updateProducto(joctemp);
											
											
											
											
										}else if (lista_productos.searchProducto(ID) instanceof Pack) {
											Pack pactemp=(Pack)lista_productos.searchProducto(ID);
											pactemp.ponStock(stock);
											lista_productos.updateProducto(pactemp);
											
										}
										
										
									}else {
										System.out.println("Producto "+ID+" No encontrado en el sistema");
									}
								}
								
								
								
								
							} catch (FileNotFoundException e1) {
								
								System.out.println(e1.getMessage());
							} catch (EOFException e2) {
								
								System.out.println("Lectura de Fichero Finalizada");
							} catch (IOException e3) {
								
								System.out.println(e3.getMessage());
							}finally {
								if(reader!=null) {
									reader.close();
									
								}
							}
							
							
						
							
							break;
					
					
					
					}
					
					
					
					
					
					break;
					
			
				case 5:
					
					System.out.println("Dime el ID del producto al que quitar stock");
					 id=teclado.next();
					if(lista_productos.searchProducto(id)==null) {
						System.out.println("Producto no encontrado");
					}else {
						if(lista_productos.searchProducto(id) instanceof Joc){
								//Cast de Producto a Joc
								Joc joctemp=(Joc)lista_productos.searchProducto(id);
								
								System.out.println("El stock actual del producto es: "+joctemp.getStock());
								System.out.println("Cuanto Stock vas a quitar? ");
								int stock=teclado.nextInt();
								try {
									joctemp.quitarStock(stock);
									lista_productos.updateProducto(joctemp);
								} catch (StockInsuficientException e) {
									
									System.out.println(e.getMessage());
								}
								
								
								
						}else if(lista_productos.searchProducto(id) instanceof Pack) {
							
								Pack packtemp=(Pack)lista_productos.searchProducto(id);
								System.out.println("El stock actual del producto es: "+packtemp.getStock());
								System.out.println("Cuanto Stock vas a quitar? ");
								int stock=teclado.nextInt();
								try {
									packtemp.quitarStock(stock);
									lista_productos.updateProducto(packtemp);
									
								} catch (StockInsuficientException e) {
								
									System.out.println(e.getMessage());
								}
								
							
							
							
							
							
						}
						
					}
					
				
					break;
					
				
			//Opcion que permite Borrar un producto del sistema, envia al metodo del DAO encargado de
			//Esta funcion la ID de un producto y este lo elimina del sistema	
				case 6:
					System.out.println("Dime el ID del producto a eliminar");
					id=teclado.next();
					
					
					if(!lista_productos.deleteProducto(id)) {
						System.out.println("El producto no existe, no hay nada que borrar");
						
					}else {
						lista_productos.deleteProducto(id);
						System.out.println("Producto eliminado");
					}
				
					
					
					break;
					
			
					
			//Opcion que ejecuta un  metodo de nuestro DAO que devuelve todas las entradas de productos del
			//Sistema.	
					
			case 7:
				
				lista_productos.showAll();
				break;
					
			case 8:
				System.out.println("Como se va a llamar el fichero?");
				String filename=teclado.next();
				File fichero=new File(filename);
				
				if(!fichero.exists()) {
					fichero.createNewFile();
				}
				
				DataOutputStream writer=null;
				
				writer=new DataOutputStream(new FileOutputStream(fichero));
				
				System.out.println("Cuantos registros quieres hacer?");
				int num_reg=teclado.nextInt();
				
				for(int i=0;i<num_reg;i++) {
					System.out.println("Producto: "+i);
					System.out.println("Dime la ID del producto .");
					String ID=teclado.next();
					System.out.println("Dime el stock del producto");
					int stock=teclado.nextInt();
					writer.writeUTF(ID);
					writer.writeInt(stock);
				}
				
				writer.close();
				
				break;
				
			case 9:
				System.out.println("Por que quieres ordenar?\n1 - Precio\n2 - Stock\n3 - Nombre\nElige: ");
                int opcionOrdenar = teclado.nextInt();
                switch (opcionOrdenar){
                    case 1:
                        lista_productos.OrderBy("precio");
                        break;
                    case 2:
                        lista_productos.OrderBy("stock");
                        break;
                    case 3:
                        lista_productos.OrderBy("nombre");
                        break;
                }
				break;
			case 10:
				System.out.println("Introduce una Fecha para ver los productos descatalogados dd/mm/yyyy");
				String fecha_s=teclado.next();
				LocalDate fecha=LocalDate.parse(fecha_s, parse_date);
				
				lista_productos.imprimirDescatalogados(fecha);
				
				
				
				break;
			
			case 11:
				System.out.println("Guardando los cambios en el sistema");
				lista_productos.saveData();
				System.out.println("Hasta Otra");
				
				
				break;
				
					
			default:
				
				System.out.println("Opcion no valida");
				break;
			}
			
		
					
		}while(opcion!=11);	

	}
}





 