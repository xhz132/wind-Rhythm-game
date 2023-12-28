import java.awt.image.BufferedImage;


public class image {
    private BufferedImage[] images = {}; 
    private int index = 0;
    private BufferedImage image;
    public image(){
        images = new BufferedImage[]{gamestart.image1, gamestart.image2,gamestart.image3,gamestart.image4};
        image = gamestart.image1;
    }
    public BufferedImage getImage() {
        return image;
    }
    private boolean isClicked = false;
    private int clickImageCount = 0;

    public void change() {
        if(images.length > 0){
            if(isClicked){
                // 当被点击时，播放 image3 和 image4
                if (clickImageCount % 6 == 0||clickImageCount % 6 == 1||clickImageCount % 6 == 2) {  // 每 1000 次调用 change 方法才切换图片
                    image = images[2];  // 假设 image3 和 image4 是 images 数组的第 3 和第 4 个元素
                    clickImageCount++;
                }
                else if(clickImageCount % 6== 4||clickImageCount % 6 == 5||clickImageCount % 6 == 3){
                    image = images[3];
                    clickImageCount++;
                    if(clickImageCount > 5){
                        isClicked = false;
                        clickImageCount = 0;
                    }
                }
            }else{
                // 当没有被点击时，播放 image1 和 image2
                image = images[index++/19%2];  // 假设 image1 和 image2 是 images 数组的第 1 和第 2 个元素
            }
        }
    }
    public void click() {
        // 当鼠标点击时，调用这个方法
        isClicked = true;
    }

}
