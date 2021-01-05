import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class MainMenu extends JFrame {
    JFrame Menu = new JFrame("Maze");
    JButton Start = new JButton("Play");
    JButton Exit = new JButton("Exit");
    JButton MapMaker = new JButton("Map Maker");
    ImageIcon picture = new ImageIcon("res/mainMenuPhoto.raw");
    JLabel imageLabel;
    ArrayList<String> mapList;
    JComboBox<String> lvlList;
    int menuWidth;
    int menuHeight;
    int menuY;
    int WIDTH;
    int HEIGHT;
    static boolean levelsExistAlready = false;

    public MainMenu() {
        this.imageLabel = new JLabel(this.picture);
        this.mapList = new ArrayList();
        this.menuWidth = 100;
        this.menuHeight = 30;
        this.menuY = 460;
        this.WIDTH = 1000;
        this.HEIGHT = 1000;
        this.getMapList();
        this.lvlList = new JComboBox((String[]) this.mapList.toArray(new String[this.mapList.size()]));
        this.Menu.setResizable(false);
        this.Menu.setSize(this.WIDTH, this.HEIGHT);
        this.Menu.setLayout((LayoutManager) null);
        this.Menu.setLocationRelativeTo((Component) null);
        this.Menu.setDefaultCloseOperation(3);
        this.Start.setSize(this.menuWidth, this.menuHeight);
        this.Start.setLocation(10, this.menuY);
        this.Menu.add(this.Start);
        this.Start.addActionListener((arg0) -> {
            new Maze(this.lvlList.getSelectedItem().toString());
            this.Menu.setVisible(false);
        });
        this.MapMaker.setSize(this.menuWidth, this.menuHeight);
        this.MapMaker.setLocation(120, this.menuY);
        this.Menu.add(this.MapMaker);
        this.MapMaker.addActionListener((e) -> {
            new MazeMaker();
            this.Menu.setVisible(false);
        });
        this.lvlList.setSize(this.menuWidth + 35, this.menuHeight);
        this.lvlList.setLocation(230, this.menuY);
        this.Menu.add(this.lvlList);
        this.Exit.setSize(this.menuWidth, this.menuHeight);
        this.Exit.setLocation(375, this.menuY);
        this.Menu.add(this.Exit);
        this.Exit.addActionListener((e) -> {
            System.exit(0);
        });
        this.imageLabel.setBounds((this.WIDTH - 412) / 2, 25, 412, 412);
        this.imageLabel.setVisible(true);
        this.Menu.add(this.imageLabel);
        this.Menu.setVisible(true);
    }

    public void getMapList() {
        for (int i = 0; i < 99; ++i) {
            File map = new File("./Level " + i + ".map");
            if (map.exists()) {
                System.out.println("Level " + i + " exists");
                this.mapList.add("Level " + i + ".map");
                levelsExistAlready = true;
            }
        }

    }

}

