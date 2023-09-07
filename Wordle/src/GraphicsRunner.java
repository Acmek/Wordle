import javax.swing.JFrame;
import java.awt.Component;

public class GraphicsRunner extends JFrame {

    //640 : 480 = 654 : 517
    //480 : 320 = 494 : 357
    private static final int WIDTH = 654; //+14 if the Window Bar is Enabled
    private static final int HEIGHT = 517; //+37 if the Window Bar is Enabled

    public GraphicsRunner() {
        super("Wordle");
        setSize(WIDTH, HEIGHT);

        Wordle game = new Wordle();

        ((Component)game).setFocusable(true);
        getContentPane().add(game);
        
        setLocationRelativeTo(null);

        setUndecorated(false);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }
    public static void main(String[] args) throws Exception {
        GraphicsRunner run = new GraphicsRunner();
    }
}
