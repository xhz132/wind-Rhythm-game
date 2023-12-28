import java.awt.image.BufferedImage;
import java.util.Random;
public class cabbage extends cut {
    private int time=0;
    private int tiem2=0;
    private int offset;
    cabbage(){
        super();
        this.images = new BufferedImage[]{gamestart.cabbage1, gamestart.cabbage2,gamestart.cabbage3,gamestart.cabbage4,gamestart.cabbage5};
        this.image = gamestart.cabbage1;
        this.x =800;
        this.y = 497-138;
        this.offset = new Random().nextInt(100);
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
    public boolean perfectCutted(){
        return x<280&&x>279;
    }
    public void imageChange(){
        // this.image = images[time++/20%5];
        this.image = images[(time++ + offset) / 65 % 5];
    }

}
