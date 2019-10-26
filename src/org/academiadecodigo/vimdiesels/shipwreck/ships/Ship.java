package org.academiadecodigo.vimdiesels.shipwreck.ships;

import org.academiadecodigo.vimdiesels.shipwreck.board.Board;
import org.academiadecodigo.vimdiesels.shipwreck.board.Tile;
import org.academiadecodigo.vimdiesels.shipwreck.board.TileType;

public class Ship {

    private int shipSize;

    public Ship(int size) {

        this.shipSize = size;
    }

    public void placeShip(Board board, Tile firstTile, Direction direction) throws OverShipException, OutOfBoardException {

        int x = firstTile.getX();
        int y = firstTile.getY();

        if (!shipWithinBoard(board, firstTile, direction)){

            placeShip(board, board.getTile((int) (Math.random() * (board.getSize() - 2)), (int) (Math.random() * (board.getSize() - 2))),
                    direction);
            return;
        }
        if (shipOverShip(board,firstTile,direction) ) {

            placeShip(board, board.getTile((int) (Math.random() * (board.getSize() - 2)), (int) (Math.random() * (board.getSize() - 2))),
                    direction);
            return;
        }

        if (direction.equals(Direction.HORIZONTAL)) {

            for (int i = x; i < x + shipSize; i++) {

                board.getTile(i , y).setType(TileType.SHIP);

            }


        }

        if (direction.equals(Direction.VERTICAL)) {

            for (int i = y; i < y + shipSize; i++) {

                board.getTile(x , i).setType(TileType.SHIP);

            }


        }
    }

    private boolean shipWithinBoard(Board board, Tile firstTile, Direction direction) {

        boolean insideBoard = true;
        int x = firstTile.getX();
        int y = firstTile.getY();

        if (direction.equals(Direction.HORIZONTAL)) {
            if ((x + shipSize) > board.getSize()) {
                insideBoard = false;
            }
        }
        if (direction.equals(Direction.VERTICAL)) {
            if ((y + shipSize) > board.getSize()) {
                insideBoard = false;
            }
        }
        return insideBoard;
    }

    private boolean shipOverShip(Board board, Tile firstTile, Direction direction) throws OverShipException{

        boolean overShip = false;
        int x = firstTile.getX();
        int y = firstTile.getY();


        if (direction.equals(Direction.HORIZONTAL)) {

            for (int i = x; i < x + shipSize; i++) {
                if (board.getTile(i, y).getType().equals(TileType.SHIP)) {

                    overShip = true;


                }
            }
        }

        if (direction.equals(Direction.VERTICAL)) {

            for (int i = y; i < y + shipSize; i++) {

                if (board.getTile(x, i).getType().equals(TileType.SHIP)) {

                    overShip = true;

                }
            }
        }


        return overShip;
    }

    public int getShipSize() {
        return shipSize;
    }
}

