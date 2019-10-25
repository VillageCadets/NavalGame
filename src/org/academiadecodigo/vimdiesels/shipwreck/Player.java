package org.academiadecodigo.vimdiesels.shipwreck;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class Player implements Runnable {

    private String username;
    private Socket playerSocket;
    private Server server;
    private BufferedReader in;
    private BufferedWriter out;

    public Player(Server server, Socket playerSocket) {
        this.server = server;
        this.playerSocket = playerSocket;


    }

    @Override
    public void run() {

        while(!playerSocket.isClosed() && playerSocket != null){

            String[] menuOptions = {"Play ", "Exit"};
            Prompt prompt = new Prompt(System.in, System.out);
            MenuInputScanner opt = new MenuInputScanner(menuOptions);
            opt.setMessage("====== SHIP WRECK ======");

            int answerIndex = prompt.getUserInput(opt);
            //if (answerIndex == 1){

            //}
            System.out.println("User wants to " + menuOptions[answerIndex - 1] + "\n");
        }
    }


}
