
import java.io.*;
import java.net.*;
public class Client{
    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    Client(){}
    void run(String msg)
    {
        try{
            //1. creating a socket to connect to the server
        	socket = new Socket("::1", 5000);
            System.out.println("Connected to localhost in port 5000");
            //2. get Input and Output streams
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            //3: Communicating with the server
            do{
                try{
                    message = (String)in.readObject();
                    System.out.println("server>" + message);
                    sendMessage(msg);
                    message = "";
                    sendMessage(message);
                    
                    out.flush();
                    
                    
                    byte[] contents = new byte[10000];

                	
                    //Initialize the FileOutputStream to the output file's full path.
                    FileOutputStream fos = new FileOutputStream("client.txt");
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    InputStream is = socket.getInputStream();
                    
                    //No of bytes read in one read() call
                    int bytesRead = 0; 
                    
                    while((bytesRead=is.read(contents))!=-1){
                        bos.write(contents, 0, bytesRead); 
                        System.out.println(is.read(contents));

                    }
                    bos.flush(); 
                    fos.flush(); 

                    socket.close(); 
                    
                    System.out.println("File saved successfully!");

                    
                    
                    
                }
                catch(ClassNotFoundException classNot){
                    System.err.println("data received in unknown format");
                }
            }while(!message.equals(""));
        }
        catch(UnknownHostException unknownHost){
            System.err.println("You are trying to connect to an unknown host!");
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                socket.close();
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
           // System.out.println("client>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String args[])
    {
    	
    	Client client = new Client();

    	int i=0;
		for(String tmp:args){
			System.out.println(tmp);
			i++;
		}
		if(i>0)
		{
			//System.out.println(i);
			//System.out.println(args[0]);
	        client.run(args[0]);

		}else{

			//System.out.println(i);
	        client.run("No argument");

		}

    	
    }
}