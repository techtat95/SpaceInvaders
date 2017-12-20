import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class SpaceAttackers extends JFrame implements Info {

    public SpaceAttackers() {

        initUI();
    }

    private void initUI() {

        add(new Board());
        setTitle("Space Invaders");
        setBackground(Color.black);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            SpaceAttackers ex = new SpaceAttackers();
            ex.setVisible(true);
        });
    }
}
