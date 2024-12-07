package retan.io;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Ra extends RetanPlayer{
    private boolean kttd = true;
    private boolean ktmn = false;
    public int nt;
    private Color lx = new Color(250, 250, 250, 200);
    private Color lx2 = new Color(250, 250, 250, 50);

    public Ra(int x, int y) {
        super(x, y, 7, 100, 100, 100, 50, new Color(100, 200, 200));
        this.dam = 5;
        this.tdn = 1.5;
        this.tdd = 10;
        this.MN = 100;
        this.mn = 0;
        this.nt = 0;
        this.mnn = 0.25;
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
                sk.add(new RetanSkill(x + 25 - size / 2, y + 25 - size / 2, tdd, H, size, dam, Color.WHITE));
                kttd = false;
                td = 0;
            }

    }



    public void unti() {
        if (ktmn) {
            int size = 20;
            int sz = size / 2;
            int d = 11;//so le
            if (H[0] || H[1]) {
                for (int i = 0; i < d; i++) {
                    oj.add(new RetanSkill(x + 25 - sz, y + 25 - sz - (d / 2 - i) * sz, 0, H, size, 0, Color.WHITE));
                }
                rf.add(new RetanEffect(180, 1));
            }
            if (H[2] || H[3]) {
                for (int i = 0; i < d; i++) {
                    oj.add(new RetanSkill(x + 25 - sz - (d / 2 - i) * sz, y + 25 - sz, 0, H, size, 0, Color.WHITE));
                }
                rf.add(new RetanEffect(180, 1));
            }

            rf.add(new RetanEffect(10, 2));
            td = 100;
            mn -= MN;
            if (mn < MN) {
                ktmn = false;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        attack();
        buff();
    }

    @Override
    public void attack() {
        //nap dan
        if (!kttd) {
            td += tdn;
            if (td >= TD) {
                td = TD;
                kttd = true;
            }
        }
        //nap mn
        if (mn < MN) {
            mn += mnn;
        }
        if (!ktmn) {
            if (mn >= MN) {
                mn = MN;
                ktmn = true;
            }
        }

        //effect
        boolean kt = false;
        for (int i = 0; i < sk.size(); i++) {
            if (sk.get(i).color == lx || sk.get(i).color == lx2) {
                kt = true;
                nt = 0;
            }
        }
        if (kt) {
            for (int i = 0; i < rf.size(); i++) {
                if (rf.get(i).id == 0) {
                    rf.get(i).TimeEffect();
                    if (rf.get(i).time <= 0) {

                        rf.remove(i);
                        rf.add(new RetanEffect(5, 0));
                    }
                }

            }
        }
        if (!kt) {
            for (int i = 0; i < rf.size(); i++) {
                if (rf.get(i).id == 0) {
                    rf.remove(i);
                }
            }
        }
        //effect unti
        for (int i = 0; i < rf.size(); i++) {
            if (rf.get(i).id == 2) {
                for (int j = 0; j < oj.size(); j++) {
                    oj.get(j).speed = 20;
                }
                rf.get(i).TimeEffect();
                if (rf.get(i).time <= 0) {
                    for (int j = 0; j < oj.size(); j++) {
                        oj.get(j).speed = 0;
                    }
                    rf.remove(i);
                }
            }
        }
        for (int i = 0; i < rf.size(); i++) {
            if (rf.get(i).id == 1) {
                rf.get(i).TimeEffect();
                if (rf.get(i).time <= 0) {
                    rf.remove(i);
                    if (oj != null) {
                        for (int j = 0; j < 11; j++) {
                            oj.remove(0);
                        }
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

        if (nt >= 2) {
            speed = 12;
        } else {
            speed = 7;
        }
    }

}
/*
___________Ronin__________
-Độ khó: (6/10)

- Nội tại: Phong ba bão táp(Cường hóa)
+ Tốc độ nạp đạn cao nhưng sát thương giảm 1 nửa.

- Kỹ năng [F]&[1]: Bão kiếm (Cơ động cường hóa)
+ bắn một viên đạn tầm xa nếu dích kẻ địch sẽ tích 1 tụ bão.
+ Khi tích được 2 tụ bão sẽ được tăng mạnh tốc chạy và cường hóa đòn đánh tiếp theo.
+ Đòn đánh cường hóa tổng bằng 8 lần tấn công nếu kẻ địch dính toàn bộ cũng sẽ tích 1 tụ bão.

- Chiêu cuối [G]&[2]: Tường gió (Phòng thủ)(Hồi chiêu 7s)
+ Tạo tường gió chắn đạn trong 3s.
+ Sau khi kích hoạt ngay lập tức nạp đầy đạn.



 */


