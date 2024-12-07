package retan.io;

import java.awt.Color;
import java.awt.Graphics;

public class Map {

    private String MaMap;
    

  

  

    public void draw(Graphics g) { 
       
    }

    public class MapObject {

        private int x=0;
        private int y=0;
        private int width=0;
        private int height=0;
        public Color color;

        public MapObject(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }


        public void draw(Graphics g) {

            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }

}
