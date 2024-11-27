import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private Direction direction = Direction.EAST;
    private double speed = 5;
    private double runningSpeed = 10;
    private boolean isRunning = false;
    private double timeBetweenFrame = 250;
    private final int spriteSheetNumberOfColumn = 10;

    private int health = 100;

    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }


    public int getHealth() {
        return health;
    }


    public void decreaseHealth(int amount) {
        this.health -= amount;
        if (health < 0) {
            health = 0;
        }
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }


    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double moved = new Rectangle2D.Double();
        double currentSpeed = isRunning ? runningSpeed : speed;

        switch (direction) {
            case EAST -> moved.setRect(super.getHitBox().getX() + currentSpeed, super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case WEST -> moved.setRect(super.getHitBox().getX() - currentSpeed, super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case NORTH -> moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - currentSpeed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case SOUTH -> moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + currentSpeed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
        }

        for (Sprite s : environment) {
            if (s instanceof SolidSprite && s != this) {
                if (((SolidSprite) s).intersect(moved)) {
                    return false;
                }
            }
        }
        return true;
    }


    private void move() {
        double currentSpeed = isRunning ? runningSpeed : speed;
        switch (direction) {
            case NORTH -> this.y -= currentSpeed;
            case SOUTH -> this.y += currentSpeed;
            case EAST -> this.x += currentSpeed;
            case WEST -> this.x -= currentSpeed;
        }
    }


    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
    }

    @Override
    public void draw(Graphics g) {
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);

        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                (int) (index * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((index + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null);
    }
}
