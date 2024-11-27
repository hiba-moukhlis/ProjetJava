import java.util.ArrayList;

public class PhysicEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList<Sprite> environment;
    private ArrayList<TrapSprite> traps;


    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
        traps = new ArrayList<>();
    }


    public void addToMovingSpriteList(DynamicSprite sprite) {
        if (!movingSpriteList.contains(sprite)) {
            movingSpriteList.add(sprite);
        }
    }


    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }


    public void addToTrapsList(TrapSprite trap) {
        if (!traps.contains(trap)) {
            traps.add(trap);
        }
    }


    @Override
    public void update() {
        for (DynamicSprite dynamicSprite : movingSpriteList) {
            dynamicSprite.moveIfPossible(environment);

            for (TrapSprite trap : traps) {
                if (dynamicSprite.getHitBox().intersects(trap.getHitBox())) {
                    dynamicSprite.decreaseHealth(10); // Réduit la santé du héros de 10
                    System.out.println("Hero touched a trap! Health: " + dynamicSprite.getHealth());

                    if (dynamicSprite.getHealth() <= 0) {
                        System.out.println("Game Over! The hero has no health left.");
                    }
                }
            }
        }
    }
}
