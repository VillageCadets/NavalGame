package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.vimdiesels.shipwreck.Server;

import java.net.Socket;
import java.util.List;

public class Lobby implements Runnable {

    private Player player;
    private List<Lobby> lobbyList;
    private String nickName;

    public Lobby(Server serverSocket, Socket playerSocket, String nickName) {
        this.player = new Player(playerSocket);
        this.lobbyList = serverSocket.getLobbyList();
        this.nickName = nickName;
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

                String [] playOptions = {"Create Game", "Join Game"};
                Prompt promptGame = player.getPrompt();
                MenuInputScanner gameOpt = new MenuInputScanner(playOptions);

                int answerIndexPlay= promptGame.getUserInput(gameOpt);

                if(answerIndexPlay == 1){

                    player.changeAvailability();

                    while (checkPlayerAvailability() == null) {
                        System.out.println("-> " + checkPlayerAvailability());
                        checkPlayerAvailability();
                    }
                    player.setInGame(true);
                    player.createGame(player, checkPlayerAvailability());
                    System.out.println("Waiting for players to join");
                    
                }
            }


        }
    }

    private Player checkPlayerAvailability() {
        for (Lobby l : lobbyList) {
            if (!l.equals(this) && l.getPlayer().isAvailableToPlay())
                return l.getPlayer();
        }
        System.out.println("Waiting for other players to join");
        return null;
    }

    public String getNickName() {
        return nickName;
    }

    public Player getPlayer() {
        return player;
    }
}
