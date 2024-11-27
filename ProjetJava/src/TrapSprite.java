import java.awt.*;

public class TrapSprite extends SolidSprite {
    public TrapSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    @Override
    public void draw(Graphics g) {
        // Afficher le piège
        g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }
}
