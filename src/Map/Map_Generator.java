package Map;

import java.awt.*;

public class Map_Generator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public Map_Generator(int row, int column) {
        map = new int[row][column];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;//this brick for show, not intersect with the ball
                //if don't want to draw any particular brick in panel->change the value of particular position to 0
            }
        }
        brickWidth = 540 / column;//540 total width in pixel divide to 1 brick to calculate total number of brick
        brickHeight = 150 / row;// would come in total area
    }
    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] >0) {
                    graphics2D.setColor(Color.WHITE);
                    graphics2D.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    //set stroke to seperate
                    graphics2D.setStroke(new BasicStroke(3));
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}
