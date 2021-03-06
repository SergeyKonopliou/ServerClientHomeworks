package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCodeRunnable implements Runnable {
	Socket client = null;
	Helper help;
	int count;

	public ServerCodeRunnable(Socket socket, Helper help, int count) {
		this.client = socket;
		this.help = help;
		this.count = count;
	}

	@Override
	public void run() {
		boolean done = false;
		while (!done) {
			try {
				// создаем входной поток для принятия сообщения от клиента
				DataInputStream streamIn = new DataInputStream(new BufferedInputStream(client.getInputStream()));
				String request = streamIn.readUTF();// считываем запрос клиента

				if (!request.equalsIgnoreCase("exit")) {// условие отключения клиента от сервера
					System.out.println(
							"Клиент №" + count + " прислал запрос на поиск слова " + request + ". Ищем результат...");
					// ищем искомое слово в нашем листе
					KeyWord result = help.checkWord(request);

					// создаем выходной поток для отправки ответа клиенту
					DataOutputStream streamOut = new DataOutputStream(
							new BufferedOutputStream(client.getOutputStream()));
					// проверяем наличие искомого слова и формируем ответ для клиента
					String answer = (result != null) ? (result.getName() + " - " + result.getText()) : ("Такое слово не найдено");
					System.out.println("Результат поиска: " + answer);
					System.out.println("Отсылаем результат клинту №" + count);
					streamOut.flush();
					streamOut.writeUTF(answer);
					streamOut.flush();// сбрасывает все, что по-прежнему буферизуется
				} else {
					done = true;
					break;
				}

			} catch (IOException e) {
				done = true;
				System.out.println("Kлиента №" + count + " отключен");
			}
		}

	}

}
