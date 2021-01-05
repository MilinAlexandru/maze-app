import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    int x;
    int y;

    public Player() {
        this.setBackground(Color.getHSBColor(3,2,6));
        this.setSize(Maze.panelSize, Maze.panelSize);
    }

    public void moveLeft() {
        if (this.x > 0 && Maze.map[this.x - 1][this.y] == 1) {
            this.setLocation(this.getX() - 25, this.getY());
            --this.x;
        }

    }

    public void moveRight() {
        if (this.x < Maze.columns - 1 && Maze.map[this.x + 1][this.y] == 1) {
            this.setLocation(this.getX() + 25, this.getY());
            ++this.x;
        }

    }

    public void moveUp() {
        if (this.y > 0 && Maze.map[this.x][this.y - 1] == 1) {
            this.setLocation(this.getX(), this.getY() - 25);
            --this.y;
        }

    }

    public void moveDown() {
        if (this.y < Maze.rows - 1 && Maze.map[this.x][this.y + 1] == 1) {
            this.setLocation(this.getX(), this.getY() + 25);
            ++this.y;
        }

    }
}
