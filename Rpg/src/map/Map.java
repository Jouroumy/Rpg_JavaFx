package map;

import java.io.*;

public class Map {
    public Cell[][] map;

    public Map(int rows, int columns) {
        this.map = new Cell[rows][columns];
    }

    public void fillMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j ++) {
                if((i == 0 || i == (map.length - 1)) || (j == 0 || j == (map.length - 1))) {
                this.map[i][j] = new Wall(); }
                else { this.map[i][j] = new Ground(); }
            }
        }
    }

    public Cell[][] getMap() {
        return map;
    }

    public void setRandomlyMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j ++) {
                if((i == 0 || i == (map.length - 1)) || (j == 0 || j == (map.length - 1))) {
                    this.map[i][j] = new Wall();
                } else {
                    double rand = Math.random()*15;
                    int chance = (int) rand;
                    switch (chance) {
                        case 0: this.map[i][j] = new Sol();
                            break;
                        case 1: this.map[i][j] = new Lava();
                            break;
                        default: this.map[i][j] = new Ground();
                    }

                }

            }
        }
    }

    public void readMap() throws Exception {
        File file = new File("src/map/load/map.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = br.readLine();
        char[] ch = new char[st.length()];
        while (st != null) {
            for (int  i = 0; i < map.length; i++) {
                for (int x = 0; x < st.length(); x++) {
                    ch[x] = st.charAt(x);
                }
                st = br.readLine();
                for (int j = 0; j < map.length; j++){
                    if((i == 0 || i == (map.length - 1)) || (j == 0 || j == (map.length - 1))) {
                        this.map[i][j] = new Wall();
                    } else {
                        switch (ch[j]) {
                            case '#':
                                this.map[i][j] = new Wall();
                                break;
                            case 'L':
                                this.map[i][j] = new Lava();
                                break;
                            case '*':
                                this.map[i][j] = new Sol();
                                break;
                            default:
                                this.map[i][j] = new Ground();
                                break;

                        }

                    }
                }


            }
        }



    }
}
