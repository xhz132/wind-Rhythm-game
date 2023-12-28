import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gamestart extends JPanel implements KeyListener {
    public static final int WIDTH = 800; // 面板宽
	public static final int HEIGHT = 700; // 面板高
    /** 游戏的当前状态: START RUNNING PAUSE GAME_OVER */
	private int state;
	//static声明后变为静态变量
	private static final int START = 0;
	private static final int RUNNING = 1;
	// private static final int PAUSE = 2;
	private static final int GAME_OVER = 3;
	private int score = 0; // 得分
		
	private int intervel = 1000 / 100; // 时间间隔(毫秒)
	private Timer timer; // 定时器
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	//private character character = new character(character0,0);
	public static BufferedImage background;
	public static BufferedImage image1;
	public static BufferedImage image2;
	public static BufferedImage image3;
	public static BufferedImage image4;
	public static BufferedImage aim1;
	public static BufferedImage aim2;
	public static BufferedImage cabbage1;
	public static BufferedImage cabbage2;
	public static BufferedImage cabbage3;
	public static BufferedImage cabbage4;
	public static BufferedImage cabbage5;
	public static BufferedImage RC;
	public static BufferedImage RC30;
	public static BufferedImage RC60;
	public static BufferedImage RC90;
	public static BufferedImage RC120;
	public static BufferedImage RC150;
	public static BufferedImage RC180;
	public static BufferedImage RC210;
	public static BufferedImage RC240;
	public static BufferedImage RC270;
	public static BufferedImage RC300;
	public static BufferedImage RC330;
	public static BufferedImage gameover;
	public static BufferedImage gameclick;
	public static BufferedImage Icon;
	private aim aim = new aim();
	private image image = new image();
	//private cabbage cabbage = new cabbage();
	private cabbage[] cabbages = {};
	private RC[] RCs = {};
    private static Clip clip;
	private static Clip clip2;
	private static Clip clip3;
	static { // 静态代码块，初始化图片资源
		try {
			//通过ImageIO.read()方法读取图片
			//getResource()方法获取图片的路径
			//getResource()是java.lang.Class类的方法
			// 打开声音文件
			File soundFile1 = new File("大背头.wav");
			AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(soundFile1);
			// 获取音频剪辑
			clip = AudioSystem.getClip();
			// 打开音频剪辑，并加载音频输入流
			clip.open(audioInputStream1);
			aim1 = ImageIO.read(gamestart.class.getResource("aim1.png"));
			aim2 = ImageIO.read(gamestart.class.getResource("aim2.png"));
			image1 = ImageIO.read(gamestart.class.getResource("image1.png"));
			image2 = ImageIO.read(gamestart.class.getResource("image2.png"));
			image3 = ImageIO.read(gamestart.class.getResource("image3.png"));
			image4 = ImageIO.read(gamestart.class.getResource("image4.png"));
			cabbage1 = ImageIO.read(gamestart.class.getResource("cabbage1.png"));
			cabbage2 = ImageIO.read(gamestart.class.getResource("cabbage2.png"));
			cabbage3 = ImageIO.read(gamestart.class.getResource("cabbage3.png"));
			cabbage4 = ImageIO.read(gamestart.class.getResource("cabbage4.png"));
			cabbage5 = ImageIO.read(gamestart.class.getResource("cabbage5.png"));
			RC = ImageIO.read(gamestart.class.getResource("RC.png"));
			RC30 = ImageIO.read(gamestart.class.getResource("RC30.png"));
			RC60 = ImageIO.read(gamestart.class.getResource("RC60.png"));
			RC90 = ImageIO.read(gamestart.class.getResource("RC90.png"));
			RC120 = ImageIO.read(gamestart.class.getResource("RC120.png"));
			RC150 = ImageIO.read(gamestart.class.getResource("RC150.png"));
			RC180 = ImageIO.read(gamestart.class.getResource("RC180.png"));
			RC210 = ImageIO.read(gamestart.class.getResource("RC210.png"));
			RC240 = ImageIO.read(gamestart.class.getResource("RC240.png"));
			RC270 = ImageIO.read(gamestart.class.getResource("RC270.png"));
			RC300 = ImageIO.read(gamestart.class.getResource("RC300.png"));
			RC330 = ImageIO.read(gamestart.class.getResource("RC330.png"));
			gameclick = ImageIO.read(gamestart.class.getResource("gameclick.png"));
			gameover = ImageIO.read(gamestart.class.getResource("gameover.png"));
			Icon = ImageIO.read(gamestart.class.getResource("Icon.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void playclickmusic(){
		try{
		File soundFile2 = new File("空击.wav");
		AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(soundFile2);
		clip2 = AudioSystem.getClip();
		clip2.open(audioInputStream2);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		clip2.start();
	}

	void playclickmusic2(){
		try{
		File soundFile3 = new File("切割.wav");
		AudioInputStream audioInputStream3= AudioSystem.getAudioInputStream(soundFile3);
		clip3 = AudioSystem.getClip();
		clip3.open(audioInputStream3);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		clip3.start();
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (state == START) {
			g.drawImage(gameclick, 100, 260,600,80,null);
		}
		else if(state == RUNNING){
		Graphics2D g2d = (Graphics2D) g;
		paintaim(g2d);
		//用g在200，300的位置画image1
		if(image.getImage()==image1||image.getImage()==image2){
		g2d.drawImage(image.getImage(), 250, 150,332,326,null);
		}
		else{
			g2d.drawImage(image.getImage(), 250, 150,332,326,null);
		}
		paintcabbage(g2d);
		paintRC(g2d);
		paintScore(g2d);
		}
		else if(state == GAME_OVER){
			g.drawImage(gameover, 200, 260, null);
			g.setFont(new Font("Arial", Font.BOLD, 60));
			g.drawString("totalscore: " + totalscore, 237, 400);
		}
	}


	//画cabbage，并且指定画的大小
	public void paintcabbage(Graphics2D g) {
		for(int i = 0;i<cabbages.length;i++){
			g.drawImage(cabbages[i].getImage(), cabbages[i].getX(), cabbages[i].getY(), 100,120,null);
		}
	}
	public void paintRC(Graphics2D g) {
		for(int i = 0;i<RCs.length;i++){
			g.drawImage(RCs[i].getImage(), RCs[i].getX(), RCs[i].getY(), 100,120,null);
		}
	}
	public void paintaim(Graphics2D g) {
		g.drawImage(aim.getImage(),aim.getX(),aim.getY(),30,35,null);
	}

	public void paintScore(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("score: " + totalscore, 10, 25);
		g.drawString("hit combo: " + score, 260, 25);
	}

    public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("风吹大背头");
		gamestart game = new gamestart();
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setBackground(Color.decode("#FBD750"));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.action(frame,game);
		game.addKeyListener(game);
		game.setFocusable(true);
		Image icon = Toolkit.getDefaultToolkit().getImage("Icon.png");
		frame.setIconImage(icon);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE){
		if (state == START) { // 启动状态下运行
			state = RUNNING;
		}
		else if(state == RUNNING){
			image.click();
			becutted();
		}
		else if(state==GAME_OVER){
			state = START;
			score = 0;
			EnteredIndex = 0;
			imagestate = 0;
			EnteredIndex1 = 0;
			cabbages = new cabbage[0];
			RCs = new RC[0];
			score = 0;
			totalscore = 0;
		}
		repaint();
		}
		else if (keyCode == KeyEvent.VK_ESCAPE){
			state = GAME_OVER;
		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }



    	/** 启动执行代码 */
	public void action(JFrame frame,gamestart game) {
		// gamestart game = this;
		// 鼠标监听事件
		MouseAdapter l = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 鼠标点击
				if (state == START) { // 启动状态下运行
					state = RUNNING;
				}
				else if(state == RUNNING){
					image.click();
					becutted();
				}
				else if(state==GAME_OVER){
					state = START;
					score = 0;
					EnteredIndex = 0;
					imagestate = 0;
					EnteredIndex1 = 0;
					cabbages = new cabbage[0];
					RCs = new RC[0];
					score = 0;
					totalscore = 0;
				}
				repaint();
			}


		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);

		timer = new Timer(); // 主流程控制
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(state==RUNNING){
				image.change();
				}
				repaint();
			}
		}, intervel, intervel);

		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				music();
				if(state==RUNNING){
				enter();
				cabbagedelete();
				RCdelete();
				RCstep();
				cabbageStep();
				}
			}
		}, 1, 1, TimeUnit.MILLISECONDS);
	}
	private int EnteredIndex1 = 0;
	public void music(){
		if(state==RUNNING){
			if(EnteredIndex1++<750){
			}
			else{
				clip.start();
			}
		}
		else if(state == GAME_OVER){
			clip.stop();
			clip.setFramePosition(0);
		}
	}
	
	private int EnteredIndex = 0;
	private int imagestate = 0;
	public void enter(){
		if(imagestate==0&&EnteredIndex++>=450){
			imagestate = 1;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==1&&EnteredIndex++>=450){
			imagestate = 2;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==2&&EnteredIndex++>=450){
			imagestate = 3;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==3&&EnteredIndex++>=450){
			imagestate = 4;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.433     约掉333333333333333333
		if(imagestate==4&&EnteredIndex++>=433){
			imagestate = 5;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==5&&EnteredIndex++>=450){
			imagestate = 6;
			EnteredIndex = 0;
			createcabbage();
		}



		//0.433     约掉333333333333333333
		if(imagestate==6&&EnteredIndex++>=433){
			imagestate = 7;
			EnteredIndex = 0;
			createRC();
		}

		//0.2166666666666666 加上一个33333333还差一个3333333
		if(imagestate==7&&EnteredIndex++>=220){
			imagestate = 8;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666666 加上一个33333333不差3333333了
		if(imagestate==8&&EnteredIndex++>=220){
			imagestate = 9;
			EnteredIndex = 0;
			createcabbage();
		}



		if(imagestate==9&&EnteredIndex++>=450){
			imagestate = 10;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.433     约掉333333333333333333
		if(imagestate==10&&EnteredIndex++>=433){
			imagestate = 11;
			EnteredIndex = 0;
			createcabbage();
		}
		
		if(imagestate==11&&EnteredIndex++>=450){
			imagestate = 12;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==12&&EnteredIndex++>=450){
			imagestate = 13;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.433     约掉333333333333333333
		if(imagestate==13&&EnteredIndex++>=433){
			imagestate = 14;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==14&&EnteredIndex++>=450){
			imagestate = 15;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==15&&EnteredIndex++>=450){
			imagestate = 16;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.433     进一位333333不缺3333333了
		if(imagestate==16&&EnteredIndex++>=434){
			imagestate = 17;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==17&&EnteredIndex++>=450){
			imagestate = 18;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.433     约掉333333333333333333
		if(imagestate==18&&EnteredIndex++>=433){
			imagestate = 19;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==19&&EnteredIndex++>=450){
			imagestate = 20;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==20&&EnteredIndex++>=450){
			imagestate = 21;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.433     约掉333333333333333333
		if(imagestate==21&&EnteredIndex++>=433){
			imagestate = 22;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==22&&EnteredIndex++>=450){
			imagestate = 23;
			EnteredIndex = 0;
			createcabbage();
		}




		if(imagestate==23&&EnteredIndex++>=450){
			imagestate ++;
			EnteredIndex = 0;
			createRC();
		}
		//0.2166666666666666 加上一个33333333还差一个3333333
		if(imagestate==24&&EnteredIndex++>=217){
			imagestate ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666666 		加上一个33333333不差3333333了
		if(imagestate==25&&EnteredIndex++>=217){
			imagestate ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.466     约掉66666666666666666
		if(imagestate==26&&EnteredIndex++>=466){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.433     合上6666666666      平
		if(imagestate==27&&EnteredIndex++>=434){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==28&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==29&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.433     约掉333333333333333333
		if(imagestate==30&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==31&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==32&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		//0.2133333333333    舍去333333333
		if(imagestate==33&&EnteredIndex++>=213){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}

		if(imagestate==34&&EnteredIndex++>=200){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==35&&EnteredIndex++>=250){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}


		if(imagestate==36&&EnteredIndex++>=200){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.66666666666666666 加上一个33333333  还差一个3333333
		if(imagestate==37&&EnteredIndex++>=667){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666666 加上一个33333333   平
		if(imagestate==38&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666666 舍去666666666
		if(imagestate==39&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333  加上6666666666   平
		if(imagestate==40&&EnteredIndex++>=234){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}



		//0.43333333333   舍去33333333
		if(imagestate==41&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.233333333333333 舍去3333333333
		if(imagestate==42&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333   加上两个3333333  平
		if(imagestate==43&&EnteredIndex++>=234){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.433333333333   舍去333333
		if(imagestate==44&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216           加上3333333  平
		if(imagestate==45&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216 舍去666666666
		if(imagestate==46&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}



		if(imagestate==47&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}




		//0.66666666666666666 加上一个33333333剩下一个33333333
		if(imagestate==48&&EnteredIndex++>=667){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666   加上一个3333333333333 平
		if(imagestate==49&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216666666         舍去6666666666 
		if(imagestate==50&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666       加上一个333333333333
		if(imagestate==51&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.433333333333333333   舍去33333333333
		if(imagestate==52&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333333333      加上6666666666 平
		if(imagestate==53&&EnteredIndex++>=234){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666666666     舍去666666666
		if(imagestate==54&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}



		if(imagestate==55&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216666666666666666      加上33333333
		if(imagestate==56&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216666666666666666      加上33333333 平
		if(imagestate==57&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		//0.4666666666666666      舍去666666666
		if(imagestate==58&&EnteredIndex++>=467){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}



		//0.66666666666666666       加上66666666666  剩下3333333333
		if(imagestate==59&&EnteredIndex++>=667){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666        加上3333333333 平
		if(imagestate==60&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666        舍去6666666666
		if(imagestate==61&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		//0.233333333333333 加上66666666666 平
		if(imagestate==62&&EnteredIndex++>=234){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.433333333333333  舍去   333333333333333
		if(imagestate==63&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333333333  舍去   3333333333333
		if(imagestate==64&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216666666666   加上一个3333333333  剩下一个
		if(imagestate==65&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		if(imagestate==66&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666        加上3333333333333 平
		if(imagestate==67&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666       舍去66666666666
		if(imagestate==68&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}





		if(imagestate==69&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


	
		//0.66666666666666666    加上一个3333333333
		if(imagestate==70&&EnteredIndex++>=667){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216666666      加上一个333333333 平
		if(imagestate==71&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666    舍去66666
		if(imagestate==72&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.233333333333    加上6666666666 平
		if(imagestate==73&&EnteredIndex++>=234){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.433333333333       舍去33333333333
		if(imagestate==74&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333333    舍去3333333333
		if(imagestate==75&&EnteredIndex++>=223){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666       加上3333333333333 剩一个33333333
		if(imagestate==76&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216666666       加上3333333333333 平
		if(imagestate==77&&EnteredIndex++>=214){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.23333333333333  舍去3333333333
		if(imagestate==78&&EnteredIndex++>=223){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}



		//0.883333333333333舍去3333333333333
		if(imagestate==79&&EnteredIndex++>=883){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.21666666666666   加上333333333333333
		if(imagestate==80&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		//0.2166666666666666 加上333333333333333  平
		if(imagestate==81&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666  舍去6666666666
		if(imagestate==82&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		
		//0.66666666666       加上333333333333
		if(imagestate==83&&EnteredIndex++>=661){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216666666666      加上333333333333 平
		if(imagestate==84&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		//0.23333333333        舍去333333333333
		if(imagestate==85&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666    加上33333333333 平
		if(imagestate==86&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666    舍去6666666666666
		if(imagestate==87&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21333333333333    加上66666666666 平
		if(imagestate==88&&EnteredIndex++>=214){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.466666666666666    舍去66666666666
		if(imagestate==89&&EnteredIndex++>=466){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}


		//0.43333333333333     加上66666666666   平
		if(imagestate==90&&EnteredIndex++>=434){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}



		if(imagestate==91&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}

		//0.4333333333333333     舍去3333333333
		if(imagestate==92&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.66666666   加上333333     平
		if(imagestate==93&&EnteredIndex++>=667){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666   约掉333333333
		if(imagestate==94&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.233333333333333 约掉3333333333333
		if(imagestate==95&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666   加上3333333333333 剩33333333
		if(imagestate==96&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666   加上3333333333333 平
		if(imagestate==97&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333333333   舍去333333333333
		if(imagestate==98&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		if(imagestate==99&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==100&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		//0.4333333333333333   舍去333333333333
		if(imagestate==101&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333333333   加上66666666666 平
		if(imagestate==102&&EnteredIndex++>=234){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.233333333333333    舍去333333333333
		if(imagestate==103&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.43333333333    舍去333333333333
		if(imagestate==104&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==105&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.23333333333    加上66666666666 平
		if(imagestate==106&&EnteredIndex++>=234){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216666666       舍去6666666666
		if(imagestate==107&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666    加上333333333
		if(imagestate==108&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.23333333333    舍去3333333333333
		if(imagestate==109&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}





		//0.46666666666     加上33333333333333
		if(imagestate==110&&EnteredIndex++>=467){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		//0.416666666       加上33333333333333 平
		if(imagestate==111&&EnteredIndex++>=417){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==112&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==113&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}









		//0.4333333333333333   舍去333333333333
		if(imagestate==114&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.4333333333333333   舍去333333333333
		if(imagestate==115&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333333333   加上66666666666 平
		if(imagestate==116&&EnteredIndex++>=234){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.233333333333     舍去333333333333
		if(imagestate==117&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.233333333333     舍去333333333333
		if(imagestate==118&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		//0.48333333333     加上666666666平
		if(imagestate==119&&EnteredIndex++>=484){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		//0.416666666666  舍去666666666666
		if(imagestate==120&&EnteredIndex++>=416){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}





		//0.466666666666666     加上3333333333333剩下33333333333
		if(imagestate==121&&EnteredIndex++>=467){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==122&&EnteredIndex++>=200){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666   加上3333333333333 平
		if(imagestate==123&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.6833333333333    舍去333333333333
		if(imagestate==124&&EnteredIndex++>=683){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666     加上33333333333 平
		if(imagestate==125&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.233333333333333 舍去333333333333 
		if(imagestate==126&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666 加上3333333333333 平
		if(imagestate==127&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}		
		//0.2166666666666666  舍去66666666
		if(imagestate==128&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333333333  加上66666666666  平
		if(imagestate==129&&EnteredIndex++>=234){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}				


		//0.46666666666    舍去66666666
		if(imagestate==130&&EnteredIndex++>=466){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}		
		//0.416666666666    加上33333333333
		if(imagestate==131&&EnteredIndex++>=417){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==132&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}				

		//0.43333333333  舍去33333333333
		if(imagestate==133&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}		


		//0.6666666666      加上33333333333
		if(imagestate==134&&EnteredIndex++>=667){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666   加上33333333333
		if(imagestate==135&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		//0.2333333333333333  舍去333333333333
		if(imagestate==136&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666  加上3333333333333 
		if(imagestate==137&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.23333333333     舍去33333333
		if(imagestate==138&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666  加上3333333333333 
		if(imagestate==139&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}



		if(imagestate==140&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}


		//0.416666666    加上3333333333333 平
		if(imagestate==141&&EnteredIndex++>=417){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.4333333333333333  舍去333333333333
		if(imagestate==142&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.233333333333333   舍去333333333333
		if(imagestate==143&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666  加上333333333333 
		if(imagestate==144&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.23333333333333      舍去333333333333
		if(imagestate==145&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		//0.46666666666666   加上33333333333
		if(imagestate==146&&EnteredIndex++>=467){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}



		//0.4333333333333333  舍去333333333333
		if(imagestate==147&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216666666666666   加上33333333333
		if(imagestate==148&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.216666666666666666 加上3333333333333 平
		if(imagestate==149&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333333333  舍去333333333333
		if(imagestate==150&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.233333333333     舍去333333333333
		if(imagestate==151&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666  加上3333333333333
		if(imagestate==152&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666666 加上3333333333333 平
		if(imagestate==153&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333   舍去3333333333
		if(imagestate==154&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666  加上3333333333333 平
		if(imagestate==155&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.233333333333   舍去3333333333333
		if(imagestate==156&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.21666666666666666  加上3333333333333 平
		if(imagestate==157&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666  舍去6666666666
		if(imagestate==158&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2333333333333333  加上66666666666 平
		if(imagestate==159&&EnteredIndex++>=234){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		//0.2333333333333333  舍去333333333333
		if(imagestate==160&&EnteredIndex++>=233){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==161&&EnteredIndex++>=250){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==162&&EnteredIndex++>=200){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}


		if(imagestate==163&&EnteredIndex++>=200){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.43333333333  舍去333333333333
		if(imagestate==164&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==165&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==166&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.433333333333 加上66666666666666 平
		if(imagestate==167&&EnteredIndex++>=434){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==168&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}



		if(imagestate==169&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		//0.2166666666  舍去66666666
		if(imagestate==170&&EnteredIndex++>=216){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666  加上3333333333
		if(imagestate==171&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
	
		if(imagestate==172&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.43333333333     舍去33333333333
		if(imagestate==173&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==174&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==175&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.43333333333   加上6666666666平
		if(imagestate==176&&EnteredIndex++>=434){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==177&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}		
		if(imagestate==178&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.43333333333   舍去33333
		if(imagestate==179&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.4666666666666  加上33333    平
		if(imagestate==180&&EnteredIndex++>=467){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.43333333333   舍去33333
		if(imagestate==181&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==182&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==183&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.43333333333   舍去33333
		if(imagestate==184&&EnteredIndex++>=433){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==185&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==186&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		//0.2166666666666    加上333333
		if(imagestate==187&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666    加上333333  平
		if(imagestate==188&&EnteredIndex++>=217){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		//0.2166666666666    加上333333
		if(imagestate==189&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==190&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		int couttemp =190;
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		if(imagestate==couttemp++&&EnteredIndex++>=1767){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=1783){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		if(imagestate==couttemp++&&EnteredIndex++>=666){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}

		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=666){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}


		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=150){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=200){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=150){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=250){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=200){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}





		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}




		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}		
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	

		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	

		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=150){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=150){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=450){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	

		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}			
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}			
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}			
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}			
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}			
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}			
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createcabbage();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=225){
			imagestate  ++;
			EnteredIndex = 0;
			createRC();
		}	
		if(imagestate==couttemp++&&EnteredIndex++>=10000){
			state = GAME_OVER;
		}
	}

	public void createcabbage(){
		cabbage cabbage = new cabbage();
		cabbages = Arrays.copyOf(cabbages,cabbages.length+1);
		cabbages[cabbages.length-1] = cabbage;
	}
	public void cabbageStep(){
		for(int i = 0;i<cabbages.length;i++){
			cabbages[i].step();
			cabbages[i].imageChange();
		}
	}
	//删除越界的cabbage
	public void cabbagedelete(){
		int index = 0;
		cabbage[] cabbagesLives = new cabbage[cabbages.length];
		for(int i=0;i<cabbages.length;i++){
			cabbage g = cabbages[i];
			if (!g.outOfBounds()) {
				cabbagesLives[index++] = g; // 不越界的留着
			}
		}
		cabbages = Arrays.copyOf(cabbagesLives, index);
	}
	public void createRC(){
		RC RC = new RC();
		RCs = Arrays.copyOf(RCs,RCs.length+1);
		RCs[RCs.length-1] = RC;
	}
	public void RCstep(){
		for(int i = 0;i<RCs.length;i++){
			RCs[i].step();
			RCs[i].imageChange();
		}
	}
	public void RCdelete(){
		int index = 0;
		RC[] RCsLives = new RC[RCs.length];
		for(int i=0;i<RCs.length;i++){
			RC g = RCs[i];
			if (!g.outOfBounds()) {
				RCsLives[index++] = g; // 不越界的留着
			}
		}
		RCs = Arrays.copyOf(RCsLives, index);
	}
	private int totalscore = 0;
	int flag = 0;
	public void becutted(){
		for(int i=0;i<cabbages.length;i++){
			if(cabbages[i].cutted()){
				//将第i个cabbage删除
				cabbage temp = cabbages[i];
				cabbages[i] = cabbages[cabbages.length-1];
				cabbages[cabbages.length-1] = temp;
				cabbages = Arrays.copyOf(cabbages,cabbages.length-1);
				flag =1;
			}
		}
		if(flag==1){
			score++;
			totalscore += score;
			flag = 0;
			playclickmusic2();
		}
		else{
			playclickmusic();
			score = 0;
			flag = 0;
		}
		//如果rc被切到游戏结束
		for(int i=0;i<RCs.length;i++){
			if(RCs[i].cutted()){
				//将第i个cabbage删除
				RC temp = RCs[i];
				RCs[i] = RCs[RCs.length-1];
				RCs[RCs.length-1] = temp;
				RCs = Arrays.copyOf(RCs,RCs.length-1);
				//游戏结束将游戏状态改为GAME_OVER
				state = GAME_OVER;
			}
		}
	}
}


