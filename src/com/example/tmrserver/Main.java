package com.example.tmrserver;

import com.example.tmrserver.exceptions.InvalidCommandException;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int port = 333;

        try {
            ServerSocket server = new ServerSocket(port);

            System.out.println("Server open at port " + port);

            Socket client = server.accept();
            System.out.println("Conectado com o cliente " + client.getInetAddress());

            Scanner s = new Scanner(client.getInputStream());
            PrintStream out = new PrintStream(client.getOutputStream());

            while(s.hasNextLine()) {
                String command = s.nextLine();
                System.out.println(client.getInetAddress() + "> " + command);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void runCommand(String command) throws InvalidCommandException {
        String[] cmd = command.split(":");

        if (cmd.length != 2)
            throw new InvalidCommandException();

        String op = cmd[0];

        switch (op.toUpperCase()) {
            case "SET":
                boolean arg = Boolean.parseBoolean(cmd[1]);
                System.out.println("Setting alarm to " + arg);
                break;
            default:
                throw new InvalidCommandException();
        }
    }
}
