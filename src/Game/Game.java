package Game;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private int snakexlength[] = new int[750];
	private int snakeylength[] = new int[750];
	
	private int wallx[] = new int [500];
	private int wally[] = new int [500];
	
	private int minex[] = new int [500];
	private int miney[] = new int [500];
	
	private int scores[] = new int[20];
	private int index=0;	
	private int lengthofsnake=3;
	
	private boolean left = false;
	private boolean right = true;
	private boolean up = false;
	private boolean down = false;
	private boolean flag2 = true;
	private boolean flag3 = true;
	private boolean flag4 = true;
	private boolean flag5 = true;
	
	private ImageIcon leftmouth;
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private Timer timer;
	private int delay=120;
	private ImageIcon snakeimage;
	private ImageIcon candy;
	private ImageIcon wall;
	private ImageIcon mine;
	
	private int score=0;
	private int level=1;
	
	private int xpos=((int)(Math.random() * 34) + 1)* 25;
	private int ypos=((int)(Math.random() * 21) + 3)* 25;
	
	
	private int moves=0;
	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer =new Timer(delay, this);
		timer.start();
	}
	
	public void printarray(Graphics g) {
		if(index==0)
			return;
		
		int max=scores[0],min=scores[0];
		for(int i=0;i<index;i++) {
			if(scores[i]>max) {
				max=scores[i];
			}
			if(scores[i]<min) {
				min=scores[i];
			}
		}
		
		int h=422;
		for(int i=0;i<index;i++) {
			
			g.setColor(new java.awt.Color(33, 47, 60));
			g.setFont(new Font("comic sans ms",Font.BOLD,18));
			if(scores[i]==max) {
				g.drawString(i+1+") "+scores[i]+" (Highest)", 935, h);
			}
			if(scores[i]==min && i>0) {
				g.drawString(i+1+") "+scores[i]+" (Lowest)", 935, h);
			}
			g.drawString(i+1+") "+scores[i], 935, h);
			h+=23;
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0, 1194, 700);
		
		if(moves == 0) {
			snakexlength[0]=100;
			snakexlength[1]=75;
			snakexlength[2]=50;
			
			snakeylength[0]=100;
			snakeylength[1]=100;
			snakeylength[2]=100;
			
			while((xpos == 100 && ypos==100) || (xpos == 75 && ypos==100) || (xpos == 50 && ypos==100)) {
				xpos=((int)(Math.random() * 34) + 1)* 25;
				ypos=((int)(Math.random() * 21) + 3)* 25;
			}
		}
		for(int s=1;s<=lengthofsnake;s++) {
			if(xpos==snakexlength[s] && ypos==snakeylength[s]) {
				xpos=((int)(Math.random() * 34) + 1)* 25;
				ypos=((int)(Math.random() * 21) + 3)* 25;
			}
		}
//		print title
		g.setColor(new java.awt.Color(214, 234, 248));
		g.setFont(new Font("comic sans ms",Font.PLAIN,60));
		g.drawString("Snake Game", 400, 60);
		
//		set border for play area
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 852, 577);
		
//		set background for play area
		g.setColor(new java.awt.Color(33, 47, 60));
		g.fillRect(25, 75, 851, 575);
				
		
//		set right panel
		g.setColor(new java.awt.Color(33, 47, 60));
		g.fillRect(890, 75, 265, 575);
		g.setColor(Color.WHITE);
		g.drawRect(889, 74, 266, 577);
		
//		print level in right panel
		g.setColor(new java.awt.Color(214, 234, 248));
		g.setFont(new Font("comic sans ms",Font.PLAIN,40));
		g.drawString(" Level: "+level, 935, 200);
		
//		print scores at top
				
		g.setColor(new java.awt.Color(214, 234, 248));
		g.setFont(new Font("comic sans ms",Font.PLAIN,40));
		g.drawString("Score: "+score, 935, 250);
		
		
		
//		recent scores list box
		
		g.setColor(new java.awt.Color(33, 97, 140));
		g.fillRoundRect(900, 400, 245, 240, 15, 15);
		g.setColor(Color.WHITE);
		g.drawRoundRect(900, 400, 245, 240, 15, 15);
		
		g.setColor(new java.awt.Color(133, 193, 233));
		g.setFont(new Font("comic sans ms",Font.BOLD,20));
		g.drawString("Recent scores:", 950, 380);
		
		printarray(g);
		
		
		if(level == 2) {
			wall = new ImageIcon("wall.png");
			
			wallx[0]=850; wally[0]=75;
			wallx[1]=825; wally[1]=75;
			wallx[2]=800; wally[2]=75;
			wallx[3]=850; wally[3]=100;
			wallx[4]=850; wally[4]=125;
			
			wallx[5]=850; wally[5]=625;
			wallx[6]=850; wally[6]=600;
			wallx[7]=850; wally[7]=575;
			wallx[8]=825; wally[8]=625;
			wallx[9]=800; wally[9]=625;
			
			wallx[10]=25; wally[10]=75;
			wallx[11]=50; wally[11]=75;
			wallx[12]=75; wally[12]=75;
			wallx[13]=25; wally[13]=100;
			wallx[14]=25; wally[14]=125;
			
			wallx[15]=25; wally[15]=625;
			wallx[16]=50; wally[16]=625;
			wallx[17]=75; wally[17]=625;
			wallx[18]=25; wally[18]=600;
			wallx[19]=25; wally[19]=575;
			
			
			
			for(int w=0;wallx[w]!=0 && wally[w]!=0;w++) {
				wall.paintIcon(this, g, wallx[w], wally[w]);
			}
			
			mine =new ImageIcon("mine.png");
			minex[0]=600; miney[0]=300;
			minex[1]=150; miney[1]=450;
			minex[2]=675; miney[2]=225;
			
			while((xpos == minex[0] && ypos == miney[0]) ||(xpos == minex[1] && ypos == miney[1]) ||(xpos == minex[2] && ypos == miney[2])) {
				xpos=((int)(Math.random() * 34) + 1)* 25;
				ypos=((int)(Math.random() * 21) + 3)* 25;
			}
			
			mine.paintIcon(this, g, minex[0], miney[0]);
			mine.paintIcon(this, g, minex[1], miney[1]);
			mine.paintIcon(this, g, minex[2], miney[2]);
			
			
			if(score ==10 && flag2) {
				flag2=false;
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				right=true;
				left=false;
				up=false;
				down=false;

			}
			
		}
		
		if(level == 3) {
			wall = new ImageIcon("wall.png");
			
			wallx[0]=850; wally[0]=75;
			wallx[1]=825; wally[1]=75;
			wallx[2]=800; wally[2]=75;
			wallx[3]=850; wally[3]=100;
			wallx[4]=850; wally[4]=125;
			
			wallx[5]=850; wally[5]=625;
			wallx[6]=850; wally[6]=600;
			wallx[7]=850; wally[7]=575;
			wallx[8]=825; wally[8]=625;
			wallx[9]=800; wally[9]=625;
			
			wallx[10]=25; wally[10]=75;
			wallx[11]=50; wally[11]=75;
			wallx[12]=75; wally[12]=75;
			wallx[13]=25; wally[13]=100;
			wallx[14]=25; wally[14]=125;
			
			wallx[15]=25; wally[15]=625;
			wallx[16]=50; wally[16]=625;
			wallx[17]=75; wally[17]=625;
			wallx[18]=25; wally[18]=600;
			wallx[19]=25; wally[19]=575;
			
			wallx[20]=200; wally[20]=350;
			wallx[21]=225; wally[21]=350;
			wallx[22]=250; wally[22]=350;
			wallx[23]=275; wally[23]=350;
			wallx[24]=300; wally[24]=350;
			wallx[25]=325; wally[25]=350;
			wallx[26]=350; wally[26]=350;
			wallx[27]=375; wally[27]=350;
			wallx[28]=400; wally[28]=350;
			wallx[29]=425; wally[29]=350;
			wallx[30]=450; wally[30]=350;
			wallx[31]=475; wally[31]=350;
			wallx[32]=500; wally[32]=350;
			wallx[33]=525; wally[33]=350;
			wallx[34]=550; wally[34]=350;
			wallx[35]=575; wally[35]=350;
			wallx[36]=600; wally[36]=350;
			wallx[37]=625; wally[37]=350;
			wallx[38]=650; wally[38]=350;
			wallx[39]=675; wally[39]=350;
			

			
			for(int w=0;wallx[w]!=0 && wally[w]!=0;w++) {
				wall.paintIcon(this, g, wallx[w], wally[w]);
			}
			
			mine =new ImageIcon("mine.png");
			minex[0]=600; miney[0]=300;
			minex[1]=150; miney[1]=450;
			minex[2]=675; miney[2]=225;
			minex[3]=325; miney[3]=625;
			
			while((xpos == minex[0] && ypos == miney[0]) ||(xpos == minex[1] && ypos == miney[1]) ||(xpos == minex[2] && ypos == miney[2]) || (xpos == minex[3] && ypos == miney[3])) {
				xpos=((int)(Math.random() * 34) + 1)* 25;
				ypos=((int)(Math.random() * 21) + 3)* 25;
			}
			
			mine.paintIcon(this, g, minex[0], miney[0]);
			mine.paintIcon(this, g, minex[1], miney[1]);
			mine.paintIcon(this, g, minex[2], miney[2]);
			mine.paintIcon(this, g, minex[3], miney[3]);
			
			
			if(score ==20 && flag3) {
				flag3=false;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				right=true;
				left=false;
				up=false;
				down=false;
			}
			
		}
		
		if(level == 4) {
			wall = new ImageIcon("wall.png");
			
			wallx[0]=850; wally[0]=75;
			wallx[1]=825; wally[1]=75;
			wallx[2]=800; wally[2]=75;
			wallx[3]=850; wally[3]=100;
			wallx[4]=850; wally[4]=125;
			
			wallx[5]=850; wally[5]=625;
			wallx[6]=850; wally[6]=600;
			wallx[7]=850; wally[7]=575;
			wallx[8]=825; wally[8]=625;
			wallx[9]=800; wally[9]=625;
			
			wallx[10]=25; wally[10]=75;
			wallx[11]=50; wally[11]=75;
			wallx[12]=75; wally[12]=75;
			wallx[13]=25; wally[13]=100;
			wallx[14]=25; wally[14]=125;
			
			wallx[15]=25; wally[15]=625;
			wallx[16]=50; wally[16]=625;
			wallx[17]=75; wally[17]=625;
			wallx[18]=25; wally[18]=600;
			wallx[19]=25; wally[19]=575;
			
			wallx[20]=200; wally[20]=350;
			wallx[21]=225; wally[21]=350;
			wallx[22]=250; wally[22]=350;
			wallx[23]=275; wally[23]=350;
			wallx[24]=300; wally[24]=350;
			wallx[25]=325; wally[25]=350;
			wallx[26]=350; wally[26]=350;
			wallx[27]=375; wally[27]=350;
			wallx[28]=400; wally[28]=350;
			wallx[29]=425; wally[29]=350;
			wallx[30]=450; wally[30]=350;
			wallx[31]=475; wally[31]=350;
			wallx[32]=500; wally[32]=350;
			wallx[33]=525; wally[33]=350;
			wallx[34]=550; wally[34]=350;
			wallx[35]=575; wally[35]=350;
			wallx[36]=600; wally[36]=350;
			wallx[37]=625; wally[37]=350;
			wallx[38]=650; wally[38]=350;
			wallx[39]=675; wally[39]=350;

			wallx[40]=450; wally[40]=325;
			wallx[41]=450; wally[41]=300;
			wallx[42]=450; wally[42]=275;
			wallx[43]=450; wally[43]=250;
			wallx[44]=450; wally[44]=225;
			wallx[45]=450; wally[45]=200;

			
			wallx[46]=425; wally[46]=325;
			wallx[47]=425; wally[47]=300;
			wallx[48]=425; wally[48]=275;
			wallx[49]=425; wally[49]=250;
			wallx[50]=425; wally[50]=225;
			wallx[51]=425; wally[51]=200;
			
			wallx[52]=450; wally[52]=375;
			wallx[53]=450; wally[53]=400;
			wallx[54]=450; wally[54]=425;
			wallx[55]=450; wally[55]=450;
			wallx[56]=450; wally[56]=475;
			wallx[57]=450; wally[57]=500;
			
			wallx[58]=425; wally[58]=375;
			wallx[59]=425; wally[59]=400;
			wallx[60]=425; wally[60]=425;
			wallx[61]=425; wally[61]=450;
			wallx[62]=425; wally[62]=475;
			wallx[63]=425; wally[63]=500;
			
			
			for(int w=0;wallx[w]!=0 && wally[w]!=0;w++) {
				wall.paintIcon(this, g, wallx[w], wally[w]);
			}
			
			mine =new ImageIcon("mine.png");
			minex[0]=600; miney[0]=300;
			minex[1]=150; miney[1]=450;
			minex[2]=675; miney[2]=225;
			minex[3]=325; miney[3]=625;
			minex[4]=75; miney[4]=200;
			
			while((xpos == minex[0] && ypos == miney[0]) ||(xpos == minex[1] && ypos == miney[1]) ||(xpos == minex[2] && ypos == miney[2]) || (xpos == minex[3] && ypos == miney[3]) ||(xpos == minex[4] && ypos == miney[4])) {
				xpos=((int)(Math.random() * 34) + 1)* 25;
				ypos=((int)(Math.random() * 21) + 3)* 25;
			}
			
			mine.paintIcon(this, g, minex[0], miney[0]);
			mine.paintIcon(this, g, minex[1], miney[1]);
			mine.paintIcon(this, g, minex[2], miney[2]);
			mine.paintIcon(this, g, minex[3], miney[3]);
			mine.paintIcon(this, g, minex[4], miney[4]);
			
			
			if(score ==30 && flag4) {
				flag4=false;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				right=true;
				left=false;
				up=false;
				down=false;
			}
			
		}
		
		if(level == 5) {
			wall = new ImageIcon("wall.png");
			
			wallx[0]=850; wally[0]=75;
			wallx[1]=825; wally[1]=75;
			wallx[2]=800; wally[2]=75;
			wallx[3]=850; wally[3]=100;
			wallx[4]=850; wally[4]=125;
			
			wallx[5]=850; wally[5]=625;
			wallx[6]=850; wally[6]=600;
			wallx[7]=850; wally[7]=575;
			wallx[8]=825; wally[8]=625;
			wallx[9]=800; wally[9]=625;
			
			wallx[10]=25; wally[10]=75;
			wallx[11]=50; wally[11]=75;
			wallx[12]=75; wally[12]=75;
			wallx[13]=25; wally[13]=100;
			wallx[14]=25; wally[14]=125;
			
			wallx[15]=25; wally[15]=625;
			wallx[16]=50; wally[16]=625;
			wallx[17]=75; wally[17]=625;
			wallx[18]=25; wally[18]=600;
			wallx[19]=25; wally[19]=575;
			
			wallx[20]=200; wally[20]=350;
			wallx[21]=225; wally[21]=350;
			wallx[22]=250; wally[22]=350;
			wallx[23]=275; wally[23]=350;
			wallx[24]=300; wally[24]=350;
			wallx[25]=325; wally[25]=350;
			wallx[26]=350; wally[26]=350;
			wallx[27]=375; wally[27]=350;
			wallx[28]=400; wally[28]=350;
			wallx[29]=425; wally[29]=350;
			wallx[30]=450; wally[30]=350;
			wallx[31]=475; wally[31]=350;
			wallx[32]=500; wally[32]=350;
			wallx[33]=525; wally[33]=350;
			wallx[34]=550; wally[34]=350;
			wallx[35]=575; wally[35]=350;
			wallx[36]=600; wally[36]=350;
			wallx[37]=625; wally[37]=350;
			wallx[38]=650; wally[38]=350;
			wallx[39]=675; wally[39]=350;

			wallx[40]=450; wally[40]=325;
			wallx[41]=450; wally[41]=300;
			wallx[42]=450; wally[42]=275;
			wallx[43]=450; wally[43]=250;
			wallx[44]=450; wally[44]=225;
			wallx[45]=450; wally[45]=200;

			
			wallx[46]=425; wally[46]=325;
			wallx[47]=425; wally[47]=300;
			wallx[48]=425; wally[48]=275;
			wallx[49]=425; wally[49]=250;
			wallx[50]=425; wally[50]=225;
			wallx[51]=425; wally[51]=200;
			
			wallx[52]=450; wally[52]=375;
			wallx[53]=450; wally[53]=400;
			wallx[54]=450; wally[54]=425;
			wallx[55]=450; wally[55]=450;
			wallx[56]=450; wally[56]=475;
			wallx[57]=450; wally[57]=500;
			
			wallx[58]=425; wally[58]=375;
			wallx[59]=425; wally[59]=400;
			wallx[60]=425; wally[60]=425;
			wallx[61]=425; wally[61]=450;
			wallx[62]=425; wally[62]=475;
			wallx[63]=425; wally[63]=500;
			
			int h=64;
			for(int j=100;j<=775;j+=25) {
				wallx[h]=j;
				wally[h++]=75;
			}
			
			for(int j=150;j<=550;j+=25) {
				wallx[h]=850;
				wally[h++]=j;
			}
			
			for(int j=100;j<=775;j+=25) {
				wallx[h]=j;
				wally[h++]=625;
			}
			
			for(int j=150;j<=550;j+=25) {
				wallx[h]=25;
				wally[h++]=j;
			}
			
			
			
			for(int w=0;wallx[w]!=0 && wally[w]!=0;w++) {
				wall.paintIcon(this, g, wallx[w], wally[w]);
			}
			
			mine =new ImageIcon("mine.png");
			minex[0]=600; miney[0]=300;
			minex[1]=150; miney[1]=450;
			minex[2]=675; miney[2]=225;
			minex[3]=325; miney[3]=625;
			minex[4]=75; miney[4]=200;
			
			while((xpos == minex[0] && ypos == miney[0]) ||(xpos == minex[1] && ypos == miney[1]) ||(xpos == minex[2] && ypos == miney[2]) || (xpos == minex[3] && ypos == miney[3])||(xpos == minex[4] && ypos == miney[4])) {
				xpos=((int)(Math.random() * 34) + 1)* 25;
				ypos=((int)(Math.random() * 21) + 3)* 25;
			}
			
			mine.paintIcon(this, g, minex[0], miney[0]);
			mine.paintIcon(this, g, minex[1], miney[1]);
			mine.paintIcon(this, g, minex[2], miney[2]);
			mine.paintIcon(this, g, minex[3], miney[3]);
			mine.paintIcon(this, g, minex[4], miney[4]);
			
			if(score ==40 && flag5) {
				flag5=false;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				right=true;
				left=false;
				up=false;
				down=false;
			}
			
		}
		

//		rightmouth = new ImageIcon("rightmouth.png");
//		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int a=0;a<lengthofsnake;a++) {
			
			if(a==0 && right) {
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}
			
			if(a==0 && left) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}
			
			if(a==0 && up) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}
			
			if(a==0 && down) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}
			
			if(a!=0) {
				snakeimage = new ImageIcon("skin.png");
				snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
//			set border for play area
			g.setColor(Color.WHITE);
			g.drawRect(24, 74, 853, 577);

		}
		
		
		candy = new ImageIcon("apple.png");
		
		if(xpos == snakexlength[0] && ypos == snakeylength[0]) {
			 score++;
			 lengthofsnake++;
			 if(score==10)
			 {
				 	g.setColor(new java.awt.Color(133, 193, 233));
					g.setFont(new Font("comic sans ms",Font.BOLD,80));
					g.drawString("Level 2", 310, 350);
					
			 }
			 if(score==20)
			 {
				 	g.setColor(new java.awt.Color(133, 193, 233));
					g.setFont(new Font("comic sans ms",Font.BOLD,80));
					g.drawString("Level 3", 310, 350);
					
			 }
			 if(score==30)
			 {
				 	g.setColor(new java.awt.Color(133, 193, 233));
					g.setFont(new Font("comic sans ms",Font.BOLD,80));
					g.drawString("Level 4", 310, 350);
					
			 }
			 if(score==40)
			 {
				 	g.setColor(new java.awt.Color(133, 193, 233));
					g.setFont(new Font("comic sans ms",Font.BOLD,80));
					g.drawString("Level 5", 310, 350);
					
			 }
			 xpos=((int)(Math.random() * 34) + 1)* 25;
			 ypos=((int)(Math.random() * 21) + 3)* 25;
			 
			 for (int x=0, w=0;(wallx[w]!=0 && wally[w]!=0 )|| x<=lengthofsnake;w++) {
				 if((xpos==wallx[w] && ypos==wally[w])||(xpos == snakexlength[x] && ypos == snakeylength[x]) || (xpos==minex[w] && ypos== miney[w])){
					 xpos=((int)(Math.random() * 34) + 1)* 25;
					 ypos=((int)(Math.random() * 21) + 3)* 25;
				 }
				 x++;
				 if(w==499)
					 w=0;
			 }
			 
			 if(score < 10)
				 level=1;
			 if(score >= 10 && score < 20) {
				 level=2;
				 if(score==10) {
					 right=true;
					 left=false;
					 up=false;
					 down=false;
					 int l=0;
					 for(int k=350;k>=50;k-=25) {
						 snakexlength[l]=k;
						 snakeylength[l++]=100;
						 }
					 for (int x=0;x<=lengthofsnake;x++) {
						 if((xpos == snakexlength[x] && ypos == snakeylength[x])){
							 xpos=((int)(Math.random() * 34) + 1)* 25;
							 ypos=((int)(Math.random() * 21) + 3)* 25;
						 } 
					 }
					 
				}
			 }
			 if(score >= 20 && score < 30) {
				 level=3;
				 if(score==20) {
					 right=true;
					 left=false;
					 up=false;
					 down=false;
					 int l=0;
					 for(int k=350;k>=50;k-=25) {
						 snakexlength[l]=k;
						 snakeylength[l++]=100;
						 }
					 for(int k=50;l!=23;k+=25) {
						 snakexlength[l]=k;
						 snakeylength[l++]=125;
					 }
					 for (int x=0;x<=lengthofsnake;x++) {
						 if((xpos == snakexlength[x] && ypos == snakeylength[x])){
							 xpos=((int)(Math.random() * 34) + 1)* 25;
							 ypos=((int)(Math.random() * 21) + 3)* 25;
						 } 
					 }
				}
			 }
			 if(score >= 30 && score < 40) {
				 level=4;
				 if(score==30) {
					 right=true;
					 left=false;
					 up=false;
					 down=false;
					 int l=0;
					 for(int k=350;k>=50;k-=25) {
						 snakexlength[l]=k;
						 snakeylength[l++]=100;
						 }
					 for(int k=50;l!=33;k+=25) {
						 snakexlength[l]=k;
						 snakeylength[l++]=125;
					 }
					 for (int x=0;x<=lengthofsnake;x++) {
						 if((xpos == snakexlength[x] && ypos == snakeylength[x])){
							 xpos=((int)(Math.random() * 34) + 1)* 25;
							 ypos=((int)(Math.random() * 21) + 3)* 25;
						 } 
					 }
				}
			 }
			 if(score >= 40) {
				 level=5;
				 if(score==40) {
					 right=true;
					 left=false;
					 up=false;
					 down=false;
					 int l=0;
					 for(int k=350;k>=50;k-=25) {
						 snakexlength[l]=k;
						 snakeylength[l++]=100;
						 }
					 for(int k=50;l!=43;k+=25) {
						 snakexlength[l]=k;
						 snakeylength[l++]=125;
					 }
					 for (int x=0;x<=lengthofsnake;x++) {
						 if((xpos == snakexlength[x] && ypos == snakeylength[x])){
							 xpos=((int)(Math.random() * 34) + 1)* 25;
							 ypos=((int)(Math.random() * 21) + 3)* 25;
						 } 
					 }
				}
			 }
		}
		if(score<50)
		candy.paintIcon(this, g, xpos, ypos);
		
		for(int i = 1; i < lengthofsnake; i++) {
			if(snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				g.setColor(new java.awt.Color(133, 193, 233));
				g.setFont(new Font("comic sans ms",Font.BOLD,50));
				g.drawString("Game Over", 300, 300);
				
				g.setColor(new java.awt.Color(235, 245, 251));
				g.setFont(new Font("comic sans ms",Font.BOLD,20));
				g.drawString("Press Space to RESTART", 312, 340);
				
				scores[index++]=score;
				timer.setDelay(100);
				for(int o=0;wallx[o]!=0 && wally[o]!=0;o++) {
					wallx[o]=0;
					wally[o]=0;
				}
				timer.stop();
			}
		}
		for (int w=0;wallx[w]!=0 && wally[w]!=0;w++) {

				if((snakexlength[0]==wallx[w] && snakeylength[0]==wally[w] )|| (snakexlength[0]==minex[w] && snakeylength[0]==miney[w])) {
					right = false;
					left = false;
					up = false;
					down = false;
					g.setColor(new java.awt.Color(133, 193, 233));
					g.setFont(new Font("comic sans ms",Font.BOLD,50));
					g.drawString("Game Over", 300, 300);
					
					g.setColor(new java.awt.Color(235, 245, 251));
					g.setFont(new Font("comic sans ms",Font.BOLD,20));
					g.drawString("Press Space to RESTART", 312, 340);
					
					scores[index++]=score;
					timer.setDelay(100);
					
					for(int o=0;wallx[o]!=0 && wally[o]!=0;o++) {
						wallx[o]=0;
						wally[o]=0;
					}
					timer.stop();
				}
		}
		if(score==50) {
			scores[index++]=score;
			g.setColor(new java.awt.Color(133, 193, 233));
			g.setFont(new Font("comic sans ms",Font.BOLD,50));
			g.drawString(" You Win", 305, 300);
			
			g.setColor(new java.awt.Color(235, 245, 251));
			g.setFont(new Font("comic sans ms",Font.BOLD,20));
			g.drawString("Press Space to RESTART", 312, 340);
			
			g.setColor(new java.awt.Color(33, 47, 60));
			g.fillRect(890, 75, 265, 575);
			g.setColor(Color.WHITE);
			g.drawRect(889, 74, 266, 577);
			
			
			g.setColor(new java.awt.Color(214, 234, 248));
			g.setFont(new Font("comic sans ms",Font.PLAIN,40));
			g.drawString(" Level: "+level, 935, 200);
			
					
			g.setColor(new java.awt.Color(214, 234, 248));
			g.setFont(new Font("comic sans ms",Font.PLAIN,40));
			g.drawString("Score: 50", 935, 250);
			
			g.setColor(new java.awt.Color(33, 97, 140));
			g.fillRoundRect(900, 400, 245, 240, 15, 15);
			g.setColor(Color.WHITE);
			g.drawRoundRect(900, 400, 245, 240, 15, 15);
			
			g.setColor(new java.awt.Color(133, 193, 233));
			g.setFont(new Font("comic sans ms",Font.BOLD,20));
			g.drawString("Recent scores:", 950, 380);
			
			printarray(g);
			
			right = false;
			left = false;
			up = false;
			down = false;
			for(int o=0;wallx[o]!=0 && wally[o]!=0;o++) {
				wallx[o]=0;
				wally[o]=0;
			}
			timer.stop();
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.restart();
		if(right) {
			for(int r = lengthofsnake-1; r >= 0; r--) {
				 snakeylength[r+1] = snakeylength[r];
			}
			for(int r = lengthofsnake; r >= 0; r--) {
				if(r==0) {
					snakexlength[r] += 25;
				}
				else {
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r] > 850)
					snakexlength[r] = 25;
			}
			repaint();
		}
		
		if(left) {
			for(int r = lengthofsnake-1; r >= 0; r--) {
				 snakeylength[r+1] = snakeylength[r];
			}
			for(int r = lengthofsnake; r >= 0; r--) {
				if(r==0) {
					snakexlength[r] -= 25;
				}
				else {
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r] < 25)
					snakexlength[r] = 850;
			}
			repaint();
		}
		
		if(down) {
			for(int r = lengthofsnake-1; r >= 0; r--) {
				 snakexlength[r+1] = snakexlength[r];
			}
			for(int r = lengthofsnake; r >= 0; r--) {
				if(r==0) {
					snakeylength[r] += 25;
				}
				else {
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r] > 625)
					snakeylength[r] = 75;
			}
			repaint();
		}
		if(up) {
			for(int r = lengthofsnake-1; r >= 0; r--) {
				 snakexlength[r+1] = snakexlength[r];
			}
			for(int r = lengthofsnake; r >= 0; r--) {
				if(r==0) {
					snakeylength[r] -= 25;
				}
				else {
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r] < 75)
					snakeylength[r] = 625;
			}
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(left == false && right == false && up == false && down == false) {
				timer.restart();
				moves=0;
				lengthofsnake=3;
				level=1;
				score=0;
				right=true;
				repaint();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(!(moves!=0 && left==false && right==false && up==false && down==false)) {
				
			moves++;
			right=true;
			if(!left) {
				right=true;
			}
			else {
				left=true;
				right=false;
			}
			up=false;
			down=false;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(!(moves!=0 && left==false && right==false && up==false && down==false)) {
			moves++;
			left=true;
			if(!right) {
				left=true;
			}
			else {
				right=true;
				left=false;
			}
			up=false;
			down=false;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(!(moves!=0 && left==false && right==false && up==false && down==false)) {
			moves++;
			up=true;
			if(!down) {
				up=true;
			}
			else {
				down=true;
				up=false;
			}
			right=false;
			left=false;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(!(moves!=0 && left==false && right==false && up==false && down==false)) {
			moves++;
			down=true;
			if(!up) {
				down=true;
			}
			else {
				up=true;
				down=false;
			}
			right=false;
			left=false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}