package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import javax.xml.transform.Source;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class Lobby implements Runnable {

    private Player player;
    private ArrayList<Player> waitingPlayers;

    public Lobby() {

        waitingPlayers = new ArrayList<>();

    }

    public void addPlayerToLobby(Socket playerSocket) {
        this.player = new Player(playerSocket);
        waitingPlayers.add(player);
    }

    @Override
    public void run() {
        //System.out.println("Player " + username + " started.");
        createMenu();
    }

    private void createMenu() {

        while (!player.isInGame()) {

            String[] menuOptions = {"Play ", "Exit"};
            Prompt prompt = player.getPrompt();
            MenuInputScanner opt = new MenuInputScanner(menuOptions);

            opt.setMessage("====== SHIP WRECK ======");

            int answerIndex = prompt.getUserInput(opt);

            if (answerIndex == 1) {
                System.out.println("Waiting for players to join");
                player.changeAvailability();
                Player player2 = checkForAvailablePlayers();
                System.out.println(player + " - " + player2);
            }

            System.out.println("User wants to " + menuOptions[answerIndex - 1] + "\n");
        }
    }

    private Player checkForAvailablePlayers() {

        for (Player p: waitingPlayers) {
            if (p != player || p.isAvailableToPlay()) {
                return p;
            }
        }

        return null;
    }
}
