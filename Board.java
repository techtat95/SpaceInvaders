import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
//import StdDraw.java;
import javax.swing.*;
import java.awt.Image;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Info {

	Random r = new Random();
	private final int ALIEN_INIT_X = 250;
	private final int ALIEN_INIT_Y = 5;
	private final int BARRIER_INIT_X = 150;
	private final int BARRIER_INIT_Y = 5;
	private int direction = -1; 
	private int fatalities = 0;
	private int level = 1;
	private Dimension d;
	private ArrayList<Bomb> beamList = new ArrayList();
    private ArrayList<Alien> aliens;
    private ArrayList<Barrier> barriers;
    private PlayerPrac player;
    private Shot shot;
    private Bomb bomb;
    private boolean playing = true; //ingame
    private String text = "Game Over";
    private final String explImg = "src/explosion.Png";
    private final String spaceback = "src/space 2.png";
    private Thread animator;  
    private ImageIcon icon;
    private JLabel label;
    private boolean newBeamCanFire = true;
    
    
   
    
    public Board() {
    		
        initBoard();
        
    }
    
  /*  StdDraw.setScale(0,4);
    int[][] grid = new int [4][4];
    for(int x = 0; x < grid.length; x++) {
    		for(int y = 0; y < grid[0].length; y++) {
    			StdDraw.setPenColor(StdDraw.Blue);
    			StdDraw.filledSquare(grid[x][y], grid[x+1][y+1], 1);
    		}
    }
    */
    
    public void gameInit() {

        aliens = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {

                Alien alien = new Alien(ALIEN_INIT_X + 75 * j, ALIEN_INIT_Y + 50 * i);
                aliens.add(alien);
            }
        }

        player = new PlayerPrac();
        shot = new Shot(false);
        

        if (animator == null || !playing) {

            animator = new Thread(this);
            animator.start();
        }
    }
    
   public void BarrierInit() {
    		barriers = new ArrayList<Barrier>();
    		int x = 200;
    		int y = 400;
    		for(int i = 0; i < 2; i++) {
    			for(int j = 0; j < 6; j++) {
    			if(i == 0) y = GROUND - (SHIP_HEIGHT * 3);
    			else y = GROUND - (SHIP_HEIGHT * 4);
    				
    				if(j<2) 
    					x = GAME_LEFT * (SHIP_WIDTH * (1+j)) + (SHIP_WIDTH / 2);
    				else if (j == 4) 
    					x = GAME_RIGHT - (SHIP_WIDTH * 3) - (SHIP_WIDTH / 2);
    				else if (j == 5)
    					x = GAME_RIGHT - (SHIP_WIDTH * 2) - (SHIP_WIDTH / 2);
    				else
    					x = (BOARD_WIDTH / 2) + ((-3+j) * SHIP_WIDTH);
    				barriers.add(new Barrier(x, y));
    			}
    		}
    		
    }
    
   /*public static void music() {  
       AudioPlayer MGP = AudioPlayer.player;  
       AudioStream BGM;  
       AudioData MD;  
       ContinuousAudioDataStream loop = null;  

       try {  
           BGM = new AudioStream(new FileInputStream("som.wav"));  
           MD = BGM.getData();  
           loop = new ContinuousAudioDataStream(MD);  
       } catch(IOException error)  {  
           System.out.println("Error!!!");  

       }  
       MGP.start(loop);  
   }  */
    
    
    private void initBoard() {

       addKeyListener(new TAdapter());
       setFocusable(true);
       d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
       ImageIcon backIcon = new ImageIcon(spaceback);
       //setImage(backIcon.getImage());
       setBackground(Color.BLACK);
       gameInit(); //gameInit
       BarrierInit();
       setDoubleBuffered(true);    
    }
    
    public void addNotify() {
    		super.addNotify();
    		gameInit();
    		BarrierInit();
    	}
    
    
    public void gameOver() {

        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(text, (BOARD_WIDTH - metr.stringWidth(text)) / 2,
                BOARD_WIDTH / 2);
    }

    
    
    public void animationCycle() {
    		
    		player.act();
    		int y = shot.getY() - 10;
    		
    		if (shot.isVisible()) {

                int shotX = shot.getX();
                int shotY = shot.getY();

                for (Alien alien: aliens) {

                    int alienX = alien.getX();
                    int alienY = alien.getY();

                    if (alien.isVisible() && shot.isVisible()) {
                        if (shotX >= (alienX)
                                && shotX <= (alienX + ALIEN_WIDTH)
                                && shotY >= (alienY)
                                && shotY <= (alienY + ALIEN_HEIGHT)) {
                            alien.setDying(true);
                            fatalities++;
                            shot.die();
                        }
                    }
                }
    		
    		
    		if(y<0) {
    			shot.die();
    		}
    		if(shot.isVisible()) {
    			shot.setY(y);
    			}
    		}
    		
    		
    		for (Alien alien: aliens) {

                int x = alien.getX();

                if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {

                    direction = -1;
                    Iterator i1 = aliens.iterator();

                    while (i1.hasNext()) {

                        Alien a2 = (Alien) i1.next();
                        a2.setY(a2.getY() + GO_DOWN);
                    }
                }

                if (x <= BORDER_LEFT && direction != 1) {

                    direction = 1;

                    Iterator i2 = aliens.iterator();

                    while (i2.hasNext()) {

                        Alien a = (Alien) i2.next();
                        a.setY(a.getY() + GO_DOWN);
                    }
                }
            }
    		
    		Iterator it = aliens.iterator();

            while (it.hasNext()) {
                
                Alien alien = (Alien) it.next();
                
                if (alien.isVisible()) {

                    int y1 = alien.getY();

                    if (y1 > GROUND - ALIEN_HEIGHT) {
                        playing = false;
                        text = "Invasion!";
                    }

                    alien.act(direction);
                }
            }
            /*if (level != 3 && level != 6 && level != 9 && level != 12) {
                if (newBeamCanFire) {
                    for (int index = 0; index < aliens.size(); index++) {
                        if (r.nextInt(30) == index) {
                            bomb = new Bomb(aliens.get(index).getX(), aliens.get(index).getY(), false);
                            beamList.add(bomb);
                        }
                        newBeamCanFire = false;
                    }
                }
            }*/
            Random generator = new Random();

            for (Alien alien: aliens) {

                int shot = generator.nextInt(15);
                Alien.Bomb b = alien.getBomb();

                if (shot == CHANCE && alien.isVisible() && b.isDestroyed()) {

                    b.setDestroyed(false);
                    b.setX(alien.getX());
                    b.setY(alien.getY());
                }

                int bombX = b.getX();
                int bombY = b.getY();
                int playerX = player.getX();
                int playerY = player.getY();

                if (player.isVisible() && !b.isDestroyed()) {

                    if (bombX >= (playerX)
                            && bombX <= (playerX + DEFENDER_WIDTH)
                            && bombY >= (playerY)
                            && bombY <= (playerY + DEFENDER_HEIGHT)) {
                        ImageIcon ii
                                = new ImageIcon(explImg);
                        player.setImage(ii.getImage());
                        player.setDying(true);
                        b.setDestroyed(true);
                    }
                }

                if (!b.isDestroyed()) {
                    
                    b.setY(b.getY() + 1);
                    
                    if (b.getY() >= GROUND - BOMB_HEIGHT) {
                        b.setDestroyed(true);
                    }
                }
            }
            
   
    	}
    		
    
    				
    			

    

    public void drawShot(Graphics g) {

        if (shot.isVisible()) {
            
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }
    
    public void drawBarriers(Graphics g) {
    	//	Rectangle r = new Rectangle(x, y, width, height);
    	//	g.fillRect(x, y, width, height);
    		for(Barrier barrier:barriers) {
    			if(barrier.isVisible()) {
    				g.setColor(Color.GREEN);
    				g.fillRect(barrier.getX(), barrier.getY(), SHIP_WIDTH, SHIP_HEIGHT);
    				g.setColor(Color.black);
    				g.drawRect(barrier.getX(), barrier.getY(), SHIP_WIDTH, SHIP_HEIGHT);
    			}
    		}
    	
    	
    }
    
    
    public void drawBombing(Graphics g) {

        for (Alien a : aliens) {
            
            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {
                
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    		/*for (int index = 0; index < beamList.size(); index++) {
            //beamList.get(index).draw(g);
    			Bomb b = beamList.get(index);
    			g.drawImage(b.getImage(), b.getX(), b.getY(), this);
        }*/
    }
    
    public void drawAliens(Graphics g) {

        Iterator it = aliens.iterator();

        for (Alien alien: aliens) {

            if (alien.isVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {

                alien.die();
            }
        }
    }

    
    public void drawDefender(Graphics g) {

        if (player.isVisible()) {
            
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {

            player.die();
            playing = false;
        }
    }
    
   public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (playing) {

          
            drawAliens(g);
            drawDefender(g);
            drawShot(g);
            drawBombing(g);
            drawBarriers(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

 
    public void animator() {
    	
    }
    
    @Override
	public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (playing) {

            repaint();
            animationCycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            
            beforeTime = System.currentTimeMillis();
        }

        gameOver();
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                if (playing) {
                    if (!shot.isVisible()) {
                        shot = new Shot(x, y);
                    }
                }
                
            }
        }
    }
}
