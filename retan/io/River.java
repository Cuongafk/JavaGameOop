package retan.io;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class River extends RetanPlayer {

    private boolean kttd = true;
    private boolean ktmn = false;
    private boolean ktxy = true;
    private int xt, yt;



    public River(int x, int y) {
        super(x, y, 7, 100, 100, 100, 50, Color.BLUE);
        this.dam=10;
        this.tdn=1;
        this.tdd=20;
        this.MN=100;
        this.mn=0;
        this.mnn=0.1;
    }

    public void Skill1(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_F) {
            skill(H);

        }
        if (key == KeyEvent.VK_G) {
            unti();

        }
    }

    public void Skill2(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_NUMPAD1) {
            skill(H);
        }
        if (key == KeyEvent.VK_NUMPAD2) {
            unti();
        }

    }
//60=1s

    public void skill(boolean[] H) {
        if (kttd) {
            int size = 20;
            sk.add(new RetanSkill(x + 25 - size / 2, y + 25 - size / 2, tdd, H, size, dam, Color.BLUE));
            kttd = false;
            td = 0;
            rf.add(new RetanEffect(20, 0));

        }

    }
    
    public void unti() {
        if (ktmn) {
        int size = 600;
        oj.add(new RetanSkill(x + 25 - size / 2, y + 25 - size / 2, 0, H, size, 0, Color.BLUE));
        rf.add(new RetanEffect(300, 1));
        td=50;
        mn-=MN/2;
        if(mn<MN/2){
            ktmn=false;
        }
        }
    }
    

    @Override
    public void draw(Graphics g) {
        super.drawT(g);
        if (ktxy) {
            xt = x;
            yt = y;
        }
        //ve thanh hp
        g.setColor(Color.GREEN);
        g.fillRect(xt - HP / 4, yt - 30, hp, 10);
        g.setColor(Color.black);
        g.drawRect(xt - HP / 4, yt - 30, HP, 10);
        //ve thanh toc danh
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(xt - HP / 4, yt - 15, (int)td, 5);
        g.setColor(Color.black);
        g.drawRect(xt - HP / 4, yt - 15, TD, 5);
        //ve thanh mana
        g.setColor(Color.yellow);
        g.fillRect(xt - HP / 4, yt - 20, (int)mn, 5);
        g.setColor(Color.black);
        g.drawRect(xt - HP / 4, yt - 20, MN, 5);
        g.drawRect(xt - HP / 4, yt - 20, MN/2, 5);


        attack();
        buff();
    }

    @Override
    public void attack() {
        //nap dan
        if (!kttd) {
            td+=tdn;
            if (td >= TD) {
                td = TD;
                kttd = true;
            }
        }
        //nap mn
        if (mn < MN) {
            mn+=mnn;
            }
        if (!ktmn) {
            if (mn >= MN) {
                mn = MN;
                ktmn = true;
            }
        }

        //effect
        for (int i = 0; i < rf.size(); i++) {
            if (rf.get(i).id == 0) {
                speed = rf.get(i).TimeEffect(7, 15);
                if (rf.get(i).time <= 0) {
                    rf.remove(i);
                }
            }
        }
        //effect unti
        for (int i = 0; i < rf.size(); i++) {
            if (rf.get(i).id == 1) {
                rf.get(i).TimeEffect();
                if (rf.get(i).time <= 0) {
                    rf.remove(i);
                    if (oj != null) {
                        oj.remove(0);
                    }

                }
            }
//            System.out.println("rf=" + rf.size());
        }
        

    }

    @Override
    public Rectangle getBounds() {
        super.getBounds();

        return bounds;
    }

    public void buff() {
        //skill
        ktxy = true;
        this.TD=100;
        this.dam=10;
        this.tdd=20;
        for (int i = 0; i < oj.size(); i++) {
            if (oj.get(i).getBounds().intersects(getBounds())) {
                this.TD = 50;
                this.dam = 20;
                this.tdd = 30;
                ktxy = false;

            }

        }
    }

}
/*
___________River__________
-Độ khó: (8/10)

- Nội tại: Áp suất biển sâu(Cường hóa)
+ Tốc độ đạn cao.

- Kỹ năng [F]&[1]: Dòng chảy bất tận (Cơ động)
+ Bắn một viên đạn tầm xa sau đó tăng mạnh tốc chạy trong chốc lát.

- Chiêu cuối [G]&[2]: Thủy triều đỏ (Ẩn thân cường hóa)(Hồi chiêu: 16s)
+ Chiêu này có thể tích trữ và kích hoạt 2 lần khi đầy mana.
+ Sau khi kích hoạt ngay lập tức nạp đầy đạn.
+ Tạo ra một vùng nước giúp ẩn thân và ẩn đạn trong 5s.
+ Tăng gấp đôi sức mạnh.
+ Giảm một nửa thời gian nạp đạn.
+ Tăng mạnh tốc độ đạn.

*/
