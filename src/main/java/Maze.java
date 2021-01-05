import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;


public class Maze extends JFrame {
    public static int rows = 20;
    public static int columns = 20;
    public static int panelSize = 25;
    public static int[][] map;
    public static int endLevelLoc;
    Player p;

    public Maze(String str) {
        this.loadMap(str);
        this.setResizable(false);
        this.setSize(columns * panelSize + 50, rows * panelSize + 70);
        this.setTitle("Maze");
        this.setLayout((LayoutManager) null);
        this.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                Maze.this.revalidate();
                Maze.this.repaint();
                if (key == 87) {
                    Maze.this.p.moveUp();
                }

                if (key == 65) {
                    Maze.this.p.moveLeft();
                }

                if (key == 83) {
                    Maze.this.p.moveDown();
                }

                if (key == 68) {
                    Maze.this.p.moveRight();
                }

                if (Maze.this.p.x == Maze.columns - 1 && Maze.this.p.y == Maze.endLevelLoc) {
                    JOptionPane.showMessageDialog((Component) null, "BRAVO", "End Game", 1);
                    Maze.this.dispose();
                    new MainMenu();
                }

            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyTyped(KeyEvent arg0) {
            }
        });
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setLocationRelativeTo((Component) null);
        this.p = new Player();
        this.p.setVisible(true);
        this.add(this.p);

        for (int y = 0; y < columns; ++y) {
            for (int x = 0; x < rows; ++x) {
                Tile tile = new Tile(x, y);
                tile.setSize(panelSize, panelSize);
                tile.setLocation(x * panelSize + 23, y * panelSize + 25);
                if (map[x][y] == 0) {
                    tile.setBackground(Color.RED);
                } else {
                    tile.setBackground(Color.LIGHT_GRAY);
                    tile.setWall(false);
                    if (x == 0) {
                        this.p.setLocation(x * panelSize + 23, y * panelSize + 25);
                        this.p.y = y;
                    }

                    if (x == columns - 1) {
                        endLevelLoc = y;
                    }
                }

                tile.setVisible(true);
                this.add(tile);
            }
        }

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }

    public void loadMap(String str) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(str));
            StringBuilder sb = new StringBuilder();

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            String mapStr = sb.toString();
            int counter = 0;

            for (int y = 0; y < columns; ++y) {
                for (int x = 0; x < rows; ++x) {
                    String mapChar = mapStr.substring(counter, counter + 1);
                    if (!mapChar.equals("\r\n") && !mapChar.equals("\n") && !mapChar.equals("\r")) {
                        map[x][y] = Integer.parseInt(mapChar);
                    } else {
                        --x;
                        System.out.print(mapChar);
                    }

                    ++counter;
                }
            }
        } catch (Exception var10) {
            System.out.println("Unable to load existing map(if exists), creating new map.");
        }

    }

    static {
        map = new int[columns][rows];
    }
}


