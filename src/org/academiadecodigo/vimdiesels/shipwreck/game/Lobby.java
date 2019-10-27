package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.vimdiesels.shipwreck.Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lobby implements Runnable {

    private Player player;
    private List<Lobby> lobbyList;
    private LinkedList<Game> gamesList;
    private String nickName;

    public Lobby(Server serverSocket, Socket playerSocket, String nickName) {
        this.player = new Player(playerSocket);
        this.lobbyList = serverSocket.getLobbyList();
        this.nickName = nickName;
        gamesList = new LinkedList<>();
    }

    @Override
    public void run() {
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

                String[] playOptions = {"Create Game", "Join Game", "Back"};
                Prompt promptGame = player.getPrompt();
                MenuInputScanner gameOpt = new MenuInputScanner(playOptions);

                int answerIndexPlay = promptGame.getUserInput(gameOpt);

                switch (answerIndexPlay) {
                    case 1:
                        player.changeAvailability();
                        player.setInGame(true);
                        //Game game = player.createGame();
                        gamesList.add(player.createGame());
                        gamesList.add(player.createGame());
                        gamesList.add(player.createGame());
                        gamesList.add(player.createGame());
                        gamesList.add(player.createGame());
                        break;

                    case 2:
                        System.out.println("JOINING A GAME");
                        Game gameToJoin = getGameOnHold();
                        gameToJoin.addPlayer(player);
                        gameToJoin.init();
                        player.changeAvailability();
                        // TODO: 26/10/2019 Talvez um sync aqui faça sentido...

                        break;

                    default:
                        // TODO: 26/10/2019 go to previous menu
                        break;

                }

            }
        }
    }

    private Game getGameOnHold() {
        System.out.println(gamesList.size());
        for (int i = 0; i < gamesList.size(); i++) {

            System.out.println("GAME -> " + gamesList.get(i).isOnHold());
         //   if (g.isOnHold()) {
          //      System.out.println(g);
             //   return g;
           // }
        }
        return null;
    }

    public String getNickName() {
        return nickName;
    }

    public Player getPlayer() {
        return player;
    }
}
