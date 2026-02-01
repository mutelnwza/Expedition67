
import view.GameView;
import view.GameWindow;

public class Game implements Runnable {

    private final GameWindow gameWindow;
    private final GameView gameView;
    private Thread gameLoopThread;
    private final int FPS = 30;

    public Game() {
        gameView = new GameView();
        gameWindow = new GameWindow(gameView);
        gameView.requestFocus();
        start();
    }

    private void start(){
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000.0 / FPS; //10^9 nano sec = 1 sec
        long lastFrame = System.nanoTime();
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastFrame) / interval;
            lastFrame = now;

            if (delta >= 1) {
                update(); //logics
                render(); //visuals
                delta--;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
    }

    private void render() {
    }
}
