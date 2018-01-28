import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class PR9 {

	public static void main(String[] args) {

		URL url=null;
		HttpURLConnection conexion=null;
		DataInputStream reader=null;
		DataOutputStream writer=null;
		
		switch(args[0]) {
		
			case "GET":
				
				
				try {
					url = new URL(args[1]);
					conexion = (HttpURLConnection)url.openConnection();
					conexion.setRequestMethod("GET");
					conexion.setDoInput(true);
					reader=new DataInputStream(conexion.getInputStream());
					
					byte[] buffer=new byte[1024];
					
					int tamaño=-1;
					
					while((tamaño=reader.read(buffer))>-1) {
						
						System.out.write(buffer,0,tamaño);
						
						
					}
					
					conexion.disconnect();
						
					
				} catch (MalformedURLException e) {
					
					System.out.println(e.getMessage());
				} catch (ProtocolException e) {
				
					System.out.println(e.getMessage());
				} catch (IOException e) {
					
					System.out.println(e.getMessage());
					
				} finally {
					if(reader!=null) {
						try {
							reader.close();
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					}
					
				}
					
					
				break;
			
			case "PUT":
				
				
				try {
					url = new URL(args[2]);
					conexion = (HttpURLConnection)url.openConnection();
					conexion.setRequestMethod("PUT");
					conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
					conexion.setDoInput(true);
					conexion.setDoOutput(true);
					writer=new DataOutputStream(conexion.getOutputStream());
					
					
					writer.writeBytes(args[1]);
					reader=new DataInputStream(conexion.getInputStream());
					byte[] buffer=new byte[1024];
					int tamaño=-1;
					
					while((tamaño=reader.read(buffer))>-1) {
						
						System.out.write(buffer,0,tamaño);
						
						
					}
					
					
			
					
					conexion.disconnect();
					
					
				} catch (MalformedURLException e) {
					System.out.println(e.getMessage());
				} catch (ProtocolException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					
					System.out.println(e.getMessage());
					System.out.println("Fichero ya existente");
				}finally {
					if(reader!=null) {
						try {
							reader.close();
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					}
					if(writer!=null) {
						try {
							writer.close();
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					}
					
					
				 }
				
				
				break;
			
			case "POST":
				try {
					url = new URL(args[2]);
					conexion = (HttpURLConnection)url.openConnection();
					conexion.setRequestMethod("POST");
					conexion.setRequestProperty("Content-Type", "text/plain; charset=utf-8" );
					conexion.setDoInput(true);
					conexion.setDoOutput(true);
					writer=new DataOutputStream(conexion.getOutputStream());
					writer.writeBytes(args[1]);
					reader=new DataInputStream(conexion.getInputStream());
					
					byte[] buffer=new byte[1024];
					
					int tamaño=-1;
					
					while((tamaño=reader.read(buffer))>-1) {
						
						System.out.write(buffer,0,tamaño);
						
						
					}
					
					conexion.disconnect();
					
				} catch (MalformedURLException e) {
					System.out.println(e.getMessage());
				} catch (ProtocolException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}finally {
					if(reader!=null) {
						try {
							reader.close();
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					}
					if(writer!=null) {
						try {
							writer.close();
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					}
					
					
				 }
				
				
				break;
			
			case "DELETE":
				
				try {
					url = new URL(args[1]);
					conexion = (HttpURLConnection)url.openConnection();
					conexion.setRequestMethod("DELETE");
					conexion.setDoInput(true);
					reader=new DataInputStream(conexion.getInputStream());
					
					byte[] buffer=new byte[1024];
					
					int tamaño=-1;
					
					while((tamaño=reader.read(buffer))>-1) {
						
						System.out.write(buffer,0,tamaño);
						
						
					}
					
					conexion.disconnect();
						
					
				} catch (MalformedURLException e) {
					
					System.out.println(e.getMessage());
				} catch (ProtocolException e) {
				
					System.out.println(e.getMessage());
				} catch (IOException e) {
					
					System.out.println(e.getMessage());
					
				} finally {
					if(reader!=null) {
						try {
							reader.close();
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					}
					
				}
			
				
				
		}//Fin Swicht
		
	}//Fin main

}//
