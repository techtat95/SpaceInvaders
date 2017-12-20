import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import java.awt.Image;


public class PlayerPrac extends Sprite implements Info {

    private final int START_Y = 520;
    private final int START_X = 400;
    private final static String playerImg = "src/Player-2.png"; //pulls image
    private int width;

    public PlayerPrac() {
    		super(playerImg);
    		
    		image = image.getScaledInstance(SHIP_WIDTH, SHIP_HEIGHT, image.SCALE_DEFAULT);
    		this.x = (BOARD_WIDTH / 2) - (SHIP_WIDTH / 2);
    	    this.y = GROUND - SHIP_HEIGHT;
    }

   /* private void initPlayer() {
    
    		
        ImageIcon picp = new ImageIcon(playerImg);

        //width = picp.getImage().getWidth(null);
        

        setImage(picp.getImage());
        this.x = (BOARD_WIDTH / 2) - (SHIP_WIDTH / 2);
        this.y = GROUND - SHIP_HEIGHT;
    }*/

    public void act() {
        
        x += dx;
        
        if (x <= 2) {
            x = 2;
        }
        
        if (x >= BOARD_WIDTH - 8 - SHIP_WIDTH) {
            x = BOARD_WIDTH - 8 - SHIP_WIDTH;
        }
    }

    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
        
            dx = 2;
        }
        
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
        
            dx = 0;
        }
        
        
    }
}