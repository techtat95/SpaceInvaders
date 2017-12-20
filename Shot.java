import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class Shot extends Sprite {

	    private final String lazerpic = "laser.png";
	    private final int MISSX_SPACE = 1;
	    private final int MISSY_SPACE = 10;
	    private final boolean vis = false;

	    public Shot() {
    			initShot(x, y);
	    }
	    
	    public Shot(boolean vis) {
	    		visible = vis;
	    }
	    
	    
	    public Shot(int x, int y) {

	        initShot(x, y);
	    }
	    
	    

	    private void initShot(int x, int y) {
    			ImageIcon imageIconShot = new ImageIcon(lazerpic);
    			//setImage(imageIconShot.getImage());
    			Image scaleImage = imageIconShot.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
    			setImage(scaleImage);
    			
    			
    			
	        setX(x + MISSX_SPACE);
	        setY(y - MISSY_SPACE);
	    }
}
