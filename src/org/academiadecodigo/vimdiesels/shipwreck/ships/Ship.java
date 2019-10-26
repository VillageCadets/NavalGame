package org.academiadecodigo.vimdiesels.shipwreck.ships;

import org.academiadecodigo.vimdiesels.shipwreck.board.Board;
import org.academiadecodigo.vimdiesels.shipwreck.board.Tile;

public class Ship {

    private int shipSize;

    public Ship(int size) {

        this.shipSize = size;
    }

    private void placeShip(Board board){

    }

    private void shipWithinBoard(Board board, Tile firstTile ){

        boolean insideBoard = true;

        for (int i = 0; i < shipSize ; i++) {
            //(if (!board.))
        }
    }

    public int getShipSize() {
        return shipSize;
    }
}

