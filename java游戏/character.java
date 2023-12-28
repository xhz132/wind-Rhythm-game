import java.awt.image.BufferedImage;
//实现人物的交互逻辑
public class character {
    private int x = 0;//人物的x坐标
    private int y = 0;//人物的y坐标
    private int movingline = 0 ;//人物的移动范围
    protected BufferedImage image;   //图片
    private int aimx =0;
    public character(BufferedImage image1,int x1,int amix1){
        x = x1;
        image = image1;
        movingline = 200;
        y = movingline;
        aimx = amix1;
    }
    void step(){
        
    }
    public BufferedImage getImage() {
        return image;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    public void move(){
        if(Math.abs(x - aimx) < 2){
            x = aimx;
        }
        else if(x < aimx){
            x += 2;
        }
        else if(x > aimx){
            x -= 2;
        }
    }
}
