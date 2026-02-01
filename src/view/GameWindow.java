package view;

import java.awt.Component;
import javax.swing.JFrame;

public class GameWindow {
   private JFrame jFrame = new JFrame();

   public GameWindow(GameView gameView) {
      this.jFrame.setSize(960, 960);
      this.jFrame.add(gameView);

      this.jFrame.setResizable(false);      
      this.jFrame.setTitle("Expedition 67");
      this.jFrame.setDefaultCloseOperation(3);
      this.jFrame.setLocationRelativeTo((Component)null);
      this.jFrame.setVisible(true);
   }
}
