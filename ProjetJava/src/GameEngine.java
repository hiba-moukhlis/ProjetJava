import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    private DynamicSprite hero;
    private long startTime;
    private final long gameDuration = 15000;
    private boolean gameOver = false;
    private RenderEngine renderEngine;

    public GameEngine(DynamicSprite hero, RenderEngine renderEngine) {
        this.hero = hero;
        this.renderEngine = renderEngine;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if (gameOver) return;

        long currentTime = System.currentTimeMillis();
        long timeElapsed = currentTime - startTime;
        long timeLeft = gameDuration - timeElapsed;

        renderEngine.setTimeLeft(timeLeft);

        if (timeLeft <= 0) {
            gameOver = true;
            showGameOverScreen();
        }
    }

    private void showGameOverScreen() {
        System.out.println("Game Over!");
        JOptionPane.showMessageDialog(null, "Game Over! Time's up!");
        System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> hero.setDirection(Direction.NORTH);
            case KeyEvent.VK_DOWN -> hero.setDirection(Direction.SOUTH);
            case KeyEvent.VK_LEFT -> hero.setDirection(Direction.WEST);
            case KeyEvent.VK_RIGHT -> hero.setDirection(Direction.EAST);
            case KeyEvent.VK_CONTROL -> hero.setRunning(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            hero.setRunning(false);
        }
    }
}
