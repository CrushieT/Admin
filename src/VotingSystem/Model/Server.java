package VotingSystem.Model;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public Server() {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(6969)) {
            System.out.println("Server is running and waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                Runnable clientHandler = new ClientHandler(clientSocket);

                executor.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


