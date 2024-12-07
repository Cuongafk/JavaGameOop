package retan.io;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.*;

public class RetanIo extends JFrame {

    public JPanel p;

    public RetanIo() {
        setTitle("Retan.io");
        setSize(500, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setUndecorated(true); // Ẩn thanh tiêu đề và thanh Taskbar
//        add(new SquarePanel());
        setLocationRelativeTo(null);
        // Chỉnh bàn phím sang tiếng Anh khi chương trình chạy
        InputContext inputContext = getInputContext();
        inputContext.selectInputMethod(new Locale("en", "US"));

        p = new GamePanel(); // Khởi tạo gamePanel
        Color bg = new Color(0, 150, 0);
        p.setBackground(bg); // Thiết lập màu nền
        add(p, BorderLayout.CENTER); // Thêm gamePanel vào khung
    }
}

// Bảng điều khiển game
class GamePanel extends JPanel {

    public Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
    public int cr = (int) scr.getWidth();
    public int cc = (int) scr.getHeight();

    // Người chơi
    Ronin pl = new Ronin(100, 100);
    River pl2 = new River(cr - 100, cc - 100);

    public GamePanel() {

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                pl.keyPressed(e);
                pl2.keyPressed(e);
                pl.Skill1(e);
                pl2.Skill2(e);

                if (key == KeyEvent.VK_ESCAPE) {
                    restart();//Thoat

                }

            }

            public void restart() {
                // Thoat chương trình
                System.exit(0);
            }

            @Override
            public void keyReleased(KeyEvent e) {

                pl.keyReleased(e);
                pl2.keyReleased(e);
            }

        });
        setFocusable(true); // Cho phép panel nhận focus để xử lý sự kiện phím
        addKeyListener(new KeyAdapter() {
        });
        //khu vuc khoi tao
        Timer timer = new Timer(10, (ActionEvent e) -> {
            //khu vuc lap
            pl.move();
            pl2.move2();

            repaint(); // Vẽ lại hình vuông ở vị trí mới
        });
        timer.start(); // Bắt đầu timer
        //khu vuc ket thuc
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //ve skill unti 1
        for (int i = 0; i < pl.oj.size(); i++) {
            pl.oj.get(i).draw(g);
        }
        //ve skill unti 2
        for (int i = 0; i < pl2.oj.size(); i++) {
            pl2.oj.get(i).draw(g);
        }
        // Vẽ người chơi
        if (pl.hp > 0) {
            pl.draw(g);
        }
        if (pl2.hp > 0) {
            pl2.draw(g);
        }

        ST();
//        AutoShot();
    }

    public void ST() {
        //skill
        for (int i = 0; i < pl.sk.size(); i++) {
            if (pl.sk.get(i).getBounds().intersects(pl2.getBounds())) {
                pl2.hp -= pl.sk.get(i).dam;
                pl.sk.remove(i);
                pl.nt++;

            }
        }
        for (int i = 0; i < pl2.sk.size(); i++) {
            if (pl2.sk.get(i).getBounds().intersects(pl.getBounds())) {
                pl.hp -= pl2.sk.get(i).dam;
                pl2.sk.remove(i);
                pl2.nt++;
            }
        }
        //untiys
        for (int i = 0; i < pl.oj.size(); i++) {
            for (int j = 0; j < pl2.sk.size(); j++) {
                if (pl.oj.get(i).getBounds().intersects(pl2.sk.get(j).getBounds())) {
                    pl2.sk.remove(j);
                }
            }
        }
        for (int i = 0; i < pl2.oj.size(); i++) {
            for (int j = 0; j < pl.sk.size(); j++) {
                if (pl2.oj.get(i).getBounds().intersects(pl.sk.get(j).getBounds())) {
                    pl.sk.remove(j);
                }
            }
        }

    }

    public void AutoShot() {
        //dan
        boolean Hh[] = new boolean[4];
        if (pl.x < pl2.x) {
            Hh[1] = true;
            pl.skill(Hh);

        }

    }

    public static void main(String[] args) {
        RetanIo r = new RetanIo();
        SwingUtilities.invokeLater(() -> {
            r.setVisible(true);
        });

    }
}
