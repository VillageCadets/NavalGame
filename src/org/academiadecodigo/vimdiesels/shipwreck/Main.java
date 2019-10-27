package org.academiadecodigo.vimdiesels.shipwreck;

import org.academiadecodigo.vimdiesels.shipwreck.board.Board;
import org.academiadecodigo.vimdiesels.shipwreck.ships.Ship;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.run(4050);



    }
}
