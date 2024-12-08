package ru.sorokinad.server;


import ru.sorokinad.models.Message;
import ru.sorokinad.services.MessageService;
import ru.sorokinad.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<PrintWriter> clientWriters = new CopyOnWriteArraySet<>();
    private static MessageService messageService = new MessageService();
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        System.out.println("Сервер запущен...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String username = in.readLine();
                userService.createUserIfNotExists(username);
                clientWriters.add(out);


                List<Message> chatHistory = messageService.getChatHistory();
                out.println("--- История чата ---");
                chatHistory.forEach(out::println);


                String messageContent;
                while ((messageContent = in.readLine()) != null) {
                    Message message = new Message(username, messageContent, LocalDateTime.now());
                    messageService.saveMessage(username, messageContent);

                    for (PrintWriter writer : clientWriters) {
                        writer.println(message.toString());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clientWriters.remove(out);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
