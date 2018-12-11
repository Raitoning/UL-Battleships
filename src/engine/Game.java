package engine;

import assets.scripts.epoque.MoyenAge;
import engine.gameobject.GameObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * <h1>Game</h1>
 * Temporary class supposed to represent a single level (Scene)
 * <p>
 * This class is mostly used for debugging purposes.
 * </p>
 *
 * @author  Raitoning
 * @version 2018-11-14
 * @since   2018-11-14
 */
public class Game {

    private ArrayList<GameObject> gameObjects;

    /** Constructs a new level. Only once should be used at run-time.
     *
     */
    public Game() {

        gameObjects = new ArrayList<>();

        SpriteFactory.getInstance().addSprite("Water", "src/assets/textures/Water.png");
        SpriteFactory.getInstance().addSprite("Boat", "src/assets/textures/Boat.png");

        assets.scripts.Game g = new assets.scripts.Game();
        g.setEpoque("MoyenAge");

        //GameSaverFactory.getInstance().save(g);

        //GameSaverFactory.getInstance().load(g);

        System.out.println(g.getEpoque().toString());
    }

    /** This function is called once every frame and updates every GameObjects in the level.
     *
     */
    public void update() {

        for (int i = 0; i < gameObjects.size(); i++) {

            gameObjects.get(i).update();
        }
    }

    public GameObject findGameObjectByName(String name) {

        for (int i = 0; i < gameObjects.size(); i++) {

            if (gameObjects.get(i).getName().equals(name)) {

                return gameObjects.get(i);
            }
        }

        return null;
    }
}
