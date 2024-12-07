package retan.io;

public class RetanEffect {

    public int time;
    public int n1;
    public int n2;
    public int id;
    public boolean b1;
    public boolean b2;
    public boolean T=true;

    public RetanEffect(int time, int id) {
        this.time = time;
        this.id=id;
    }

    public int TimeEffect(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
        if(time>0){
        time--;
        }
        if (time <= 0) {
            this.n2 = this.n1;
        }
//        System.out.println("n2:"+this.n2);
        return this.n2;
    }

    public boolean TimeEffect(boolean b1, boolean b2) {
        this.b1 = b1;
        this.b2 = b2;
        time--;
        if (time <= 0) {
            this.b2 = this.b1;
        }
        return this.b2;
    }

    public void TimeEffect() {
        if(time>0){
            time--;
//            System.out.println("time="+time);
        }
    }
        
//    public boolean onoff(int time){
//        this.time=time;
//        TimeEffect();
//        if()
//        return T;
//    }   

    

}
