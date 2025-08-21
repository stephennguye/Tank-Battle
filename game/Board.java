package game;

public class Board {
    public final int cols, rows;
    public Board(int cols, int rows){ this.cols = cols; this.rows = rows; }
    public boolean isInside(int x, int y){ return x >= 0 && x < cols && y >= 0 && y < rows; }
}