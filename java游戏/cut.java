import java.awt.image.BufferedImage;
public abstract class cut {
    protected int x;    //x坐标
	protected int y;    //y坐标
	protected int width;    //宽
	protected int height;   //高
    protected int Bounds;  //边界
	protected BufferedImage image;   //图片
    protected BufferedImage[] images= {};
    public cut() {
        this.Bounds=gamestart.WIDTH;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public void step() {
        x=x-1;
    }
    public  boolean outOfBounds(){
        return this.x<=-this.Bounds;
    }
    protected static final double G = 9.8;
    protected double t;
    protected double initialY;
    protected double initialVelocity;
    //重力--有点难写以后看着加
    // public void gravity() {
    //     y = (int)(initialY + initialVelocity * t - 0.5 * G * t * t);
    //     t = t + 1;
    // }

}
