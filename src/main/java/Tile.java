import javax.swing.*;

public class Tile extends JPanel {
    int x;
    int y;
    boolean isWall = true;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setWall(boolean wall) {
        this.isWall = wall;
    }
}
