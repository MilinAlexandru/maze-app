import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapMakerTile extends JPanel {
    int x,y;

    public MapMakerTile(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    MapMakerTile.this.setBackground(Color.WHITE);
                    MazeMaker.map[x][y] = 1;
                }

                if (e.getButton() == 3) {
                    MapMakerTile.this.setBackground(Color.GRAY);
                    MazeMaker.map[x][y] = 0;
                }

            }
        });
    }
}

