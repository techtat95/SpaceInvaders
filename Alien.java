import java.awt.Image;
import javax.swing.ImageIcon;

public class Alien extends Sprite implements Info{
	private Bomb bomb;
	private final int START_Y = 0;
    private final int START_X = 270;
	private final String alienPic = "spaceinvader.png";
	private int width;

	public Alien(int x, int y) {
		initAlien(x,y);
	}
	
    public void initAlien(int x, int y) {
    	
    		ImageIcon al = new ImageIcon(alienPic);
        Image scaleImage = al.getImage().getScaledInstance(SHIP_WIDTH, SHIP_HEIGHT, Image.SCALE_DEFAULT);
        setImage(scaleImage);
    		this.x = x;
        this.y = y;
        bomb = new Bomb(x, y);
        
        
    }
    
    
    public void act(int direction) {
        
        this.x += direction;
    }
    
   public Bomb getBomb() { 
       return bomb;
    }
    
    public class Bomb extends Sprite{
   	 private final String bombPic = "laser.png";
     private boolean destroyed;
        
        public Bomb(int x, int y) {

            initBomb(x, y);
        }

        private void initBomb(int x, int y) {

            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(bombPic);
            
            Image scaleImage = ii.getImage().getScaledInstance(5, 5, Image.SCALE_DEFAULT);
            setImage(scaleImage);

        }

        public void setDestroyed(boolean destroyed) {
        
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
        
            return destroyed;
        }
    }
	
}
