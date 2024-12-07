package retan.io;

import java.awt.Color;
import java.awt.Graphics;

public class RetanSkill extends Retan {

    protected boolean HH[] = new boolean[4];
    protected int dam;
//    protected Color color;

    public RetanSkill(int x, int y, int speed, boolean H[], int size, int dam, Color color) {
        super(x, y, speed, size, color);
        for (int i = 0; i < 4; i++) {
            this.HH[i] = H[i];
            this.dam = dam;
//            this.color=color;

        }
    }

    @Override
    public void draw(Graphics g) {
        super.move(HH);
        super.draw(g);
    }



}
