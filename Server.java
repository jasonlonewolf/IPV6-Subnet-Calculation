
import java.io.*;

import java.net.*;
public class Server{
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	Server(){}
	void run()
	{
		try{
			//1. creating a server socket
			providerSocket = new ServerSocket(5000, 10);
			//2. Wait for connection
	        //InetAddress IA = InetAddress.getByName("2607:fea8:20df:f1e6::10"); 
			//System.out.println(IA.getHostAddress());

			System.out.println("Waiting for connection");
			connection = providerSocket.accept();
			System.out.println("Connection received from " + connection.getInetAddress().getHostName());
			//3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			sendMessage("Connection successful");
			//4. The two parts communicate via the input and output streams
			do{
				try{
					message = (String)in.readObject();
					
					System.out.println("client>" + message);
					
					if (!message.equals("")){
				     //System.out.println("client>" + message);

						try{
							PrintWriter writer = new PrintWriter("server.txt", "UTF-8");
							writer.println(Main.calculate(message));
							//writer.println(IA.getLocalHost());
							writer.close();
						} catch (IOException e) {
						}
						File file = new File("server.txt");
						FileInputStream fis = new FileInputStream(file);
						BufferedInputStream bis = new BufferedInputStream(fis); 

						//Get socket's output stream
						OutputStream os = connection.getOutputStream();

						//Read File Contents into contents array 
						byte[] contents;
						long fileLength = file.length(); 
						long current = 0;

						long start = System.nanoTime();

						while(current!=fileLength){ 
							int size = 10000;
							if(fileLength - current >= size)
								current += size;    
							else{ 
								size = (int)(fileLength - current); 
								current = fileLength;
							} 
							contents = new byte[size]; 
							bis.read(contents, 0, size); 
							os.write(contents);
							//System.out.print("Sending file ... "+(current*100)/fileLength+"% complete!\n");
						}   

						os.flush();
						bis.close();
					}else{
						
						 message = "";
		                 sendMessage(message);
					}
				}
				catch(ClassNotFoundException classnot){
					System.err.println("Data received in unknown format");
				}
			}while(!message.equals(""));
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				providerSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			//System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Server server = new Server();
		while(true){
			server.run();
		}
	}
}