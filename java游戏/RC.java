import java.awt.image.BufferedImage;
public class RC extends cut{
    private int time=0;
    private int tiem2=0;;
    RC(){
        super();
        this.images = new BufferedImage[]{gamestart.RC,gamestart.RC30,gamestart.RC60,gamestart.RC90,gamestart.RC120,gamestart.RC150,gamestart.RC180,gamestart.RC150,gamestart.RC210,gamestart.RC240,gamestart.RC270,gamestart.RC300,gamestart.RC330,};
        this.image = gamestart.cabbage1;
        this.x =800;
        this.y = 497-138;
        this.initialY = (double)y;
    }
    @Override
    public void step() {
        if(tiem2++%2==0){
        x=x-1;
        }
    }
    //边界检测
    public boolean outOfBounds(){
        return x<0;
    }
    //碰撞检测
    public boolean cutted(){
        return x<290&&x>180;
    }
    public void imageChange(){
        this.image = images[time++/35%12];
    }

}
