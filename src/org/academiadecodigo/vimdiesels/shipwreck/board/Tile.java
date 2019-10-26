package org.academiadecodigo.vimdiesels.shipwreck.board;

public class Tile {

    private int x;
    private int y;
    private TileType type;
    String representation;

    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {

        switch (this.type) {
            case HIT:
                representation = "[X]";
                break;
            case SEA:
                representation = " [~]";
                break;
            case MISS:
                representation = " [O]";
                break;
            case SHIP:
                representation = " [S]";
            default:
                break;
        }

        System.out.print(toString());
    }

    @Override
    public String toString() {
        return representation ;
    }
}
