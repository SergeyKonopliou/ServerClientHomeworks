package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
//		Boolean isConnect = false;
		try {
			// получаем ip адрес текущего компа,т.к. сервер находится на этом же компе
			InetAddress adress = InetAddress.getLocalHost();
			// создали клиентский сокет для общения с сервером
			try (Socket socket = new Socket(adress, 9999)) {

				DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				dos.flush();
				String request = "";

//				while (!isConnect) {
					System.out.println("Введите искомое слово");
					BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
					request = inp.readLine();
					if (!request.equals("exit")) {
						System.out.println("Запрос отправлен на сервер.Ждем ответ...");
						// отправляем запрос на сервер
						dos.writeUTF(request);
						// сбрасывает все, что по-прежнему буферизуется,иначе не будет работать запись
						dos.flush();

						String line = dis.readUTF();// считываем ответ от сервера
						System.out.println("Ответ получен: " + line);
					} else {
//						isConnect = true;
						System.out.println("Соединение завершено");
					}
//				}
			
				dos.close();
				dis.close();
				socket.close();

			} catch (IOException e) {
//				isConnect = true;
				System.out.println("Соединение прервано");
			}
		} catch (UnknownHostException e) {
			System.out.println("Проблемы с получением IP адреса");
		}

	}

}
