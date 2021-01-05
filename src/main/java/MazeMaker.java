import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MazeMaker extends JFrame {
    static int rows = 20;
    static int columns = 20;
    int panelSize = 25;
    static int map[][] ;
    ArrayList<String> mapList = new ArrayList<String>();
    int level = 0;
    boolean levelsExistAlready = false;

    public MazeMaker() {
        getMapList();
        getLevelChoice();
        if (level != -1){
            loadMap();
            this.setResizable(false);
            this.setSize((columns*panelSize)+50,(rows*panelSize)+70);
            this.setTitle("Maze Maker");
            this.setLayout(null);

            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent event){
                    saveMap();
                    new MainMenu();
                }
            });
            this.setLocationRelativeTo(null);
            for (int y= 0;y<columns;y++){
                for (int x=0;x<rows;x++){
                    MapMakerTile tile= new MapMakerTile(x,y);
                    tile.setSize((x*panelSize)+23,(y*panelSize)+25);
                    if (map[x][y]==0){
                        tile.setBackground(Color.GRAY);
                    }else {
                        tile.setBackground(Color.CYAN);
                    }
                    tile.setVisible(true);
                    this.add(tile);
                }
            }
            this.setVisible(true);
        }else {
            new MainMenu();
        }

    }

    private void getLevelChoice() {
        if (levelsExistAlready) {
            String maps[] = new String[99];
            mapList.toArray(maps);
            maps[mapList.size()] = "New level";
            String choice = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose a level",
                    "Maze Level selector", JOptionPane.QUESTION_MESSAGE,
                    null, maps, maps[0]);
            System.out.println(choice);
            if (choice != null && choice.equals("New level")) {
                level = Integer.parseInt((choice.replace("Level", "").replace(".map", "")));
            } else if (choice == null) {
                level = -1;
            } else {
                level = mapList.size();
            }
        }
    }

    private void getMapList() {
        for (int i = 0; i < 99; i++) {
            File map = new File("./Level" + i + ".map");
            if (map.exists()) {
                System.out.println("Level" + i + ".map");
                mapList.add("Level" + i + ".map");
                levelsExistAlready = true;
            }
        }
    }

    public void saveMap() {
        try {
            PrintWriter writer = new PrintWriter("Level" + level + "map", "UTF-8");
            for (int y = 0; y < columns; y++) {
                for (int x = 0; x < rows; x++) {
                    writer.print(map[x][y]);

                }
                writer.print("\r\n");
            }
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Level" + level + ".map"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            String mapString = stringBuilder.toString();
            int counter = 0;
            for (int y = 0; y < columns; y++) {
                for (int x = 0; x < rows; x++) {
                    String mapChar = mapString.substring(counter, counter + 1);
                    if (!mapChar.equals("\r\n") && !mapChar.equals("\n") && !mapChar.equals("\r")) {//If it's a number
                        map[x][y] = Integer.parseInt(mapChar);

                    }else {
                        x--;
                    }
                    counter++;
                }
            }
        }catch (Exception e){
            System.out.println("Unable to load existing map(if exists), creating new map.");
            for (int y = 0; y<columns; y++){
                for (int x=0; x<rows;x++){
                    map[x][y]=0;
                }
            }
        }
    }
    static {
        map = new int[columns][rows];
    }
}
