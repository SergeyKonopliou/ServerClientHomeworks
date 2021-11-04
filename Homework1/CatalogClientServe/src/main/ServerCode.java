package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCode {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Helper help = new Helper();
		help.createMap();

		// номер подключившегося клиента
		int countClient = 1;

		int portNumber = 9999;
		// Создан сокет сервера с номером порта,для подключения клиентов

		ServerSocket server = null;
		try {
			server = new ServerSocket(portNumber);
		} catch (IOException e1) {
			System.out.println("Клиент №" + countClient + " отключен");
		}
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
