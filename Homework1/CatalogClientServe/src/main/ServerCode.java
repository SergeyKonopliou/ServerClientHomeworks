package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCode {
	
	public static void main(String[] args) {
		ServerCode chat = new ServerCode();
		try {
			chat.createConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		void createConnection() throws IOException 
		{
			Helper help = new Helper();
			help.createMap();

			//номер подключившегося клиента
			int countClient = 1;

			int portNumber = 9999;
			// Создан сокет сервера с номером порта,для подключения клиентов
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(portNumber);
			System.out.println("Сервер запущен.Ждет подключения...");

			// чтобы сервер не отрубался после присоединения клиента
			while (true) {
				try {
					// Создан сокет,в который будем записывать подключившегося клиента; accept -
					// ждем подключения клиента
					Socket client = server.accept();
					// создаем новый поток на новое подключение клиента
					ThreadServerCode thread = new ThreadServerCode(client, help, countClient++);
					Thread newThread = new Thread(thread);
					newThread.start();

				} catch (IOException e) {
					System.out.println("Порт занят");
				}
			}
		}

}
