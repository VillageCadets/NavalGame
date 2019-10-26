package org.academiadecodigo.vimdiesels.shipwreck;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.*;
import java.net.Socket;

public class Player implements Runnable {

    private String username;
    private Socket playerSocket;
    private Server server;
    private BufferedReader in;
    private BufferedWriter out;
    private Player[] players;

    public Player(Server server, Socket playerSocket) throws IOException {
        this.server = server;
        this.playerSocket = playerSocket;
        this.in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(playerSocket.getOutputStream()));
    }

    @Override
    public void run() {
        System.out.println("Player " + username + " started.");

        try {
            InputStream input = playerSocket.getInputStream();
            PrintStream output = new PrintStream(playerSocket.getOutputStream());

            while (!playerSocket.isClosed() && playerSocket != null) {

                String[] menuOptions = {"Play ", "Exit"};
                Prompt prompt = new Prompt(input, output);
                MenuInputScanner opt = new MenuInputScanner(menuOptions);

                opt.setMessage("====== SHIP WRECK ======");

                int answerIndex = prompt.getUserInput(opt);

                if (answerIndex == 1) {
                    if (players.length == 2) {
                        Game game = new Game()
                    }

                }
                System.out.println("User wants to " + menuOptions[answerIndex - 1] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
