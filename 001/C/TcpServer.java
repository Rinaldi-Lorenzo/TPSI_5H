package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws Exception {
		
		int severPort=8765;
		int contCons=0;
		int contVoc=0;
		int contCar=0;
		int contSpa=0;
		int i;
		String clientMsg = "";
		
		try {			 
			// Creazione del socket sul server e ascolto sulla porta
			ServerSocket serverSocket = new ServerSocket(severPort);
			System.out.println("Server: in ascolto sulla porta " + severPort);

			// Attesa della connessione con il client
			Socket clientSocket = serverSocket.accept();
			
			// Create input and output streams to read/write data
			DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());	

			// Scambio di dati tra client e server
			while(!clientMsg.equals("quit")) {
				//Lettura dato da stream di rete
				clientMsg = inStream.readUTF();
				System.out.println("Server: ricevuto messaggio " + clientMsg );
				
				for(i=0;i<clientMsg.length();i++)
				{
					if(clientMsg.charAt(i)==('a') || clientMsg.charAt(i)==('e') || clientMsg.charAt(i)==('i') || clientMsg.charAt(i)==('o') || clientMsg.charAt(i)==('u') || clientMsg.charAt(i)==('y') || clientMsg.charAt(i)==('A') || clientMsg.charAt(i)==('E') || clientMsg.charAt(i)==('I') || clientMsg.charAt(i)==('O') || clientMsg.charAt(i)==('U') || clientMsg.charAt(i)==('Y'))
						contVoc++;
					else
						if(clientMsg.charAt(i)==('!') || clientMsg.charAt(i)==('?') || clientMsg.charAt(i)==(';') || clientMsg.charAt(i)==(',') || clientMsg.charAt(i)==('.') || clientMsg.charAt(i)==(':'))
							contCar++;
						else
							if(clientMsg.charAt(i)==(' '))
								contSpa++;
							else
								contCons++;		
				}
				
				if((contVoc*2)==contCons)
				{
					System.out.println("Numero vocali: " +contVoc);
					System.out.println("Numero consonanti: " +contCons);
					System.out.println("Numero caratteri vari: " +contCar);
					System.out.println("Numero spazi: " +contSpa);
					System.out.println("Uscita dal programma");
					clientMsg.equals("quit");
				}else {
					
					System.out.println("Numero vocali: " +contVoc);
					System.out.println("Numero consonanti: " +contCons);
					System.out.println("Numero caratteri vari: " +contCar);
					System.out.println("Numero spazi: " +contSpa);
				}
				

				
				contVoc=0;
				contCons=0;
				contCar=0;
				contSpa=0;
				
				//Invio dati su stream di rete
				outStream.writeUTF("Echo from server : "         + clientMsg);
				outStream.flush();
				System.out.println("Server: invio messaggio "    + clientMsg );
			}

			// Close resources
			serverSocket.close();
			clientSocket.close();
			inStream.close();
			outStream.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
