import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;
    private long timeLeft;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
        timeLeft = 15000;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }


    public void addToRenderList(ArrayList<Displayable> displayable) {
        if (!renderList.contains(displayable)) {
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }


        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Time left: " + timeLeft / 1000 + "s", 10, 30);
    }

    @Override
    public void update() {
        this.repaint();
    }
}
