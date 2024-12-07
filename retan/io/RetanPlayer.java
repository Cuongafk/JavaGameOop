package retan.io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class RetanPlayer extends Retan {

    protected int hp;
    protected int HP;
    protected double td;
    protected double tdn;
    protected int tdd;
    protected int TD;
    protected int nt;
    protected double mn;
    protected double mnn;
    protected int MN;
    protected int dam;


    // Sử dụng HashMap để lưu trữ trạng thái của các phím
    private final HashMap<Integer, Boolean> keyState = new HashMap<>();
    protected ArrayList<RetanSkill> sk = new ArrayList();
//    protected ArrayList<RetanSkill> skef = new ArrayList();
    protected ArrayList<RetanSkill> oj = new ArrayList();
    protected ArrayList<RetanEffect> rf = new ArrayList();
    protected ArrayList<RetanEffect> rfut = new ArrayList();
    protected Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
    protected int cr = (int) scr.getWidth();
    protected int cc = (int) scr.getHeight() - 25;

    public RetanPlayer(int x, int y, int speed, int hp, int td, int mn, int size, Color color) {
        super(x, y, speed, size, color);
        this.hp = hp;
        this.HP = hp;
        this.td = td;
        this.TD = td;

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keyState.put(key, Boolean.TRUE); // Đặt trạng thái của phím là true

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keyState.put(key, Boolean.FALSE); // Đặt trạng thái của phím là false

    }

    @Override
    public Rectangle getBounds() {
        super.getBounds();

        return bounds;
    }

    @Override
    public void draw(Graphics g) {
        //ve skill
        for (int i = 0; i < sk.size(); i++) {
            sk.get(i).draw(g);
        }
        
        super.draw(g);
        //ve thanh hp
        g.setColor(Color.GREEN);
        g.fillRect(x - HP / 4, y - 30, hp, 10);
        g.setColor(Color.black);
        g.drawRect(x - HP / 4, y - 30, HP, 10);
        //ve thanh toc danh
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x - HP / 4, y - 15, (int)td, 5);
        g.setColor(Color.black);
        g.drawRect(x - HP / 4, y - 15, TD, 5);
        //ve thanh mana
        g.setColor(Color.yellow);
        g.fillRect(x - HP / 4, y - 20, (int)mn, 5);
        g.setColor(Color.black);
        g.drawRect(x - HP / 4, y - 20, MN, 5);
        
        
        removeball();
        stop();
//        System.out.println("dt:"+sk.size());
    }
    public void drawT(Graphics g) {
         //ve skill
        for (int i = 0; i < sk.size(); i++) {
            sk.get(i).draw(g);
        }
         super.draw(g);
         removeball();
        stop();
    }

    public void removeball() {
        //kiem duyet
        for (int i = 0; i < sk.size(); i++) {
            if (sk.get(i).x > cr || sk.get(i).x < -sk.get(i).size || sk.get(i).y > cc || sk.get(i).y < -sk.get(i).size) {
                sk.remove(i);
            }
        }

    }

    public void stop() {
        //kiem duyet
        if (x < 0) {
            x = 0;
        }
        if (x > cr - size) {
            x = cr - size;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > cc - size) {
            y = cc - size;
        }
    }

    public void move() {
        for (int i = 0; i < 4; i++) {
            h[i] = false;
        }

        // Kiểm tra trạng thái của các phím để cập nhật vận tốc
        if (keyState.getOrDefault(KeyEvent.VK_A, Boolean.FALSE)) {
            h[0] = true; // Di chuyển sang trái
        }
        if (keyState.getOrDefault(KeyEvent.VK_D, Boolean.FALSE)) {

            h[1] = true; // Di chuyển sang phải
        }
        if (keyState.getOrDefault(KeyEvent.VK_W, Boolean.FALSE)) {
            h[2] = true; // Di chuyển lên trên
        }
        if (keyState.getOrDefault(KeyEvent.VK_S, Boolean.FALSE)) {
            h[3] = true; // Di chuyển xuống dưới
        }

        move(h);

    }

    public void move2() {

        for (int i = 0; i < 4; i++) {
            h[i] = false;
        }

        // Kiểm tra trạng thái của các phím để cập nhật vận tốc
        if (keyState.getOrDefault(KeyEvent.VK_LEFT, Boolean.FALSE)) {
            h[0] = true; // Di chuyển sang trái
        }
        if (keyState.getOrDefault(KeyEvent.VK_RIGHT, Boolean.FALSE)) {
            h[1] = true; // Di chuyển sang phải
        }
        if (keyState.getOrDefault(KeyEvent.VK_UP, Boolean.FALSE)) {
            h[2] = true; // Di chuyển lên trên
        }
        if (keyState.getOrDefault(KeyEvent.VK_DOWN, Boolean.FALSE)) {
            h[3] = true; // Di chuyển xuống dưới
        }

        move(h);

    }

}
