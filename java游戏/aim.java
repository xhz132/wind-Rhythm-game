import java.awt.image.BufferedImage;
public class aim {
    private int x = 0;//人物的x坐标
    private int y = 0;//人物的y坐标
    protected BufferedImage[] images={} ;   //图片
    private int index = 0;
    public aim(){
        images = new BufferedImage[]{gamestart.aim1, gamestart.aim2};
        x=285-22;
        y = 480-28;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public BufferedImage getImage() {
        return images[index++/19%2];
    }
}
