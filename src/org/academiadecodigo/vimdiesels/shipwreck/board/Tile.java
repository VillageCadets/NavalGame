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

    public void draw(boolean hidden) {

        switch (this.type) {
            case HIT:
                representation = "\u001B[31m" + " [X]";
                break;
            case SEA:
                representation = "\u001B[36m" + " [~]";
                break;
            case MISS:
                representation = "\u001B[33m" + " [O]";
                break;
            case SHIP:
                if (hidden) {
                    representation = "\u001B[36m" + " [~]";
                } else {
                    representation = "\u001B[35m" + " [S]";
                }
            default:
                break;
        }

        System.out.print(toString());
    }

    @Override
    public String toString() {
        return representation;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }
}
