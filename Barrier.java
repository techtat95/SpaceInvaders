import javax.swing.*;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class Barrier extends Sprite implements Info {
	
	private final String BarrierImg = "barrier1.png";
	private final int START_Y = 520;
    private final int START_X = 400;
	
	public Barrier() {
		
	}
	
    public Barrier(int x, int y) {
		super();
		
		InItBarrier(x,y);
		this.x = x;
		this.y = y;
	}
    
    public void InItBarrier(int x, int y) {
		this.x = x;
		this.y = y;
		ImageIcon bpic = new ImageIcon(BarrierImg);
        setImage(bpic.getImage());
	}
	
	
	

}
