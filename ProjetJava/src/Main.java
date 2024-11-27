import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception {
        displayZoneFrame = new JFrame("Dungeon Crawler");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, renderEngine);

        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TitleScreen(() -> {
                try {
                    new Main(); // Démarre le jeu après l'écran titre
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
