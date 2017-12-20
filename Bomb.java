import java.awt.Image;

import javax.swing.ImageIcon;


public class Bomb extends Sprite{
	private Bomb bomb;
   	 private final String bombPic = "laser.png";
     private boolean destroyed;
     private boolean vis = false;
        
        public Bomb(int x, int y, boolean vis) {

            initBomb(x, y);
            visible = vis;
        }
        
       /* public Bomb(boolean vis) {
    		visible = vis;
        }*/

        private void initBomb(int x, int y) {

            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(bombPic);
            Image scaleImage = ii.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
            setImage(scaleImage);
            bomb = new Bomb(x,y,vis);

        }
        
        public Bomb getBomb() { 
            return bomb;
         }

        public void setDestroyed(boolean destroyed) {
        
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
        
            return destroyed;
        }
    }
