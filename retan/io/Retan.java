package retan.io;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Retan {

    protected int x;
    protected int y;
    protected int speed;
    protected boolean kt = false;
    protected boolean h[] = new boolean[4];
    protected boolean H[] = new boolean[4];
    protected final double c = 0.7;
    protected int size; // Thuộc tính mới: Kích thước
    protected Color color; // Thuộc tính mới: Màu sắc
    protected Rectangle bounds;

    public Retan(int x, int y, int speed, int size, Color color) {
        this.x = x;
        this.y = y;
        this.speed = speed;

        this.size = size;
        this.color = color;
    }

    public void draw(Graphics g) {
        
        // Vẽ hình dạng Retan với kích thước và màu sắc được chỉ định
        g.setColor(color);
        g.fillRect(x, y, size, size);

//         Vẽ hộp giới hạn của ReTan
//        g.setColor(Color.red);
//        g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

    }

    public Rectangle getBounds() {
        bounds = new Rectangle(x, y, size, size);
        return bounds;
    }

    public void move(boolean h[]) {

        int d = 0;
        int Speed = speed;
        
        for (int i = 0; i < 4; i++) {
            if (h[i]) {
                d++;
            }
        }
        if (d >= 2) {
            Speed *= c;
        }

        // Di chuyển theo hướng cụ thể
        if (h[0]) {
            x -= Speed;
        }

        if (h[1]) {
            x += Speed;
        }

        if (h[2]) {
            y -= Speed;
        }

        if (h[3]) {
            y += Speed;
        }

        for (int i = 0; i < 4; i++) {
            if (h[i]) {
                if ((!h[0] || !h[1]) && (!h[2] || !h[3])) {
                    kt = true;
                }

            }
        }
        if (kt) {
            for (int i = 0; i < 4; i++) {
                H[i] = h[i];
            }
            kt=false;
        }
        for (int i = 0; i < 4; i++) {
//                h[i]=false;
//            System.out.println("h"+i+":"+h[i]);
            }
    }

    public void attack() {
        // Hành động tấn công chung
    }

}
