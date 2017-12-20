import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.image.*;

public class Sprite implements Info{

    protected boolean visible;
    protected Image image;
    protected int x;
    protected int y;
    protected boolean dying;
    protected int dx;
    protected int dy;

    public Sprite(String path) {
    		visible = true;
    		ImageIcon imgic = new ImageIcon(path);
    		image =  imgic.getImage();
    }
    
    public Sprite() {
    
        visible = true;
    }

    public void die() {
    
        visible = false;
    }

    public Image getImage() {
        return image;
    }

    protected void setVisible(boolean visible) {
    
        this.visible = visible;
    }

    public void setImage(Image image) {
    
        this.image = image;
    }
    
    public int getY() {
        
        return y;
    }

    public int getX() {
    
        return x;
    }

    public void setX(int x) {
    
        this.x = x;
    }

    public void setY(int y) {
    
        this.y = y;
    }

    public boolean isVisible() {
        
        return visible;
    }
    
    public boolean isDying() {
        
        return this.dying;
    }
    
    public void setDying(boolean dying) {
    
        this.dying = dying;
    }

   
}
