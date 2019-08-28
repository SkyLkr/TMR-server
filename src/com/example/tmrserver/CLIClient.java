package com.example.tmrserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CLIClient {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            //System.out.print("Porta: ");
            int port = 333;
            Socket client = new Socket("localhost", port);

            PrintStream out = new PrintStream(client.getOutputStream());

            Scanner in = new Scanner(client.getInputStream());

            /*
            String loginMsg = in.nextLine();
            System.out.println("Login: " + loginMsg);
             */

            while(true) {
                // Send command to server
                System.out.print(":> ");
                String cmd = s.nextLine();

                if (cmd.equalsIgnoreCase("EXIT")) {
                    System.exit(0);
                } else {
                    out.println(cmd);
                }

                // Wait for response
                String msg = in.nextLine();
                String[] m = msg.split(":");
                switch(m[0].toUpperCase()) {
                    case "SUCCESS":
                        System.out.println(m[1]);
                        break;
                    case "ERROR":
                        System.err.println(m[1]);
                        break;
                    default:
                        System.out.println(msg);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CLIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
