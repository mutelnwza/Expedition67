package view;

import controller.MouseInputs;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameView extends JPanel{
    
    private final MouseInputs mouseInputs;

    
    public GameView(){
        mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
