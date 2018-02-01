import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;



import java.util.Scanner;



import org.json.*;

public class PR10 {
	static Scanner teclado=null;
	static String token="HERETOKEN";
	

	public static void main(String[] args) {
		
		
		URL url=null;
		HttpURLConnection conexion=null;
		DataInputStream reader=null;
		DataOutputStream writer=null;
		BufferedReader readerfile=null;
		
		teclado=new Scanner(System.in);
		int opcion;
		
		
		
		do {
			System.out.println("1.Ver Listado de repositorios\n2.Ver Readme and commits\n3.Salir");
			opcion=teclado.nextInt();
			
			
			switch(opcion) {
			
				case 1:
					try {
						url=new URL("https://api.github.com/users/fenix15100/repos");
						conexion = (HttpURLConnection)url.openConnection();
						conexion.setRequestMethod("GET");
						conexion.setDoInput(true);
						reader=new DataInputStream(conexion.getInputStream());
						writer=new DataOutputStream(new FileOutputStream("json.tmp"));
						
						
						
						
						byte[] buffer=new byte[1024];
						
						int tam=-1;
						
						while((tam=reader.read(buffer))>-1) {
							
							writer.write(buffer,0,tam);
							
							
						}
					
						reader.close();
						writer.close();
						conexion.disconnect();
						
						readerfile=new BufferedReader(new FileReader("json.tmp"));
						
						String json=readerfile.readLine();
						readerfile.close();
						
						JSONArray repojson=new JSONArray(json);
						
						for(int i=0;i<repojson.length();i++) {
							JSONObject repo=repojson.getJSONObject(i);
							System.out.println("//////////////////////");
							System.out.println(repo.get("name"));
							System.out.println(repo.get("description"));
							System.out.println("//////////////////////");
							
							
						}
						
							
						
					} catch (MalformedURLException e) {
						
						System.out.println(e.getMessage());
					} catch (IOException e) {
						
						System.out.println(e.getMessage());
					} catch (JSONException e) {
						System.out.println(e.getMessage());
					}
					
					
					break;
			
			
				case 2:
					System.out.println("Di el nombre del repositorio que quieres ver el readme y el commit");
					String repo=teclado.next();
					
				try {
					
					//Readme
					url=new URL("https://api.github.com/repos/fenix15100/"+repo+"/readme");
					conexion = (HttpURLConnection)url.openConnection();
					conexion.setRequestMethod("GET");
					conexion.setDoInput(true);
					reader=new DataInputStream(conexion.getInputStream());
					writer=new DataOutputStream(new FileOutputStream("json.tmp"));
					
					byte[] buffer=new byte[1024];
					
					int tam=-1;
					
					while((tam=reader.read(buffer))>-1) {
						
						writer.write(buffer,0,tam);
						
						
					}
				
					reader.close();
					writer.close();
					conexion.disconnect();
					
					readerfile=new BufferedReader(new FileReader("json.tmp"));
					
					String json=readerfile.readLine();
					readerfile.close();
					
					JSONObject readmejson=new JSONObject(json);
					
					String readme64=readmejson.getString("content");
					System.out.println(readme64);
					
					

					///////////////////////////////////////////////////
					
					//commits
					url=new URL("https://api.github.com/repos/fenix15100/"+repo+"/commits");
					conexion = (HttpURLConnection)url.openConnection();
					conexion.setRequestMethod("GET");
					conexion.setDoInput(true);
					reader=new DataInputStream(conexion.getInputStream());
					writer=new DataOutputStream(new FileOutputStream("json.tmp"));
					
					buffer=new byte[1024];
					
					tam=-1;
					
					while((tam=reader.read(buffer))>-1) {
						
						writer.write(buffer,0,tam);
						
						
					}
				
					reader.close();
					writer.close();
					conexion.disconnect();
					
					readerfile=new BufferedReader(new FileReader("json.tmp"));
					
					json=readerfile.readLine();
					readerfile.close();
					
					JSONArray commits=new JSONArray(json);
					
					for(int i=0;i<commits.length();i++) {
						JSONObject commit=commits.getJSONObject(i);
	
						JSONObject msgcommit=commit.getJSONObject("commit");
						
						System.out.println(msgcommit.get("message"));
						System.out.println();
						
						
							
						
						
						
						
						
					}
					
					
					
					
					
					
					
				} catch (MalformedURLException e) {
					System.out.println(e.getMessage());
				} catch (ProtocolException e) {
					System.out.println(e.getMessage());
				} catch (JSONException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
					
					
					
					
					
					
					
					
					
					break;
			
			}
			
			
			
		}while(opcion!=3);
		
		
		
		
		
	}

	

}
