package engine;

import assets.scripts.epoque.Epoque;
import assets.scripts.epoque.MoyenAge;
import engine.gameobject.GameObject;
import engine.networking.RMIClient;
import engine.networking.RMIServer;

import javax.naming.NamingException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
        SpriteFactory.getInstance().addSprite("Miss", "src/assets/textures/Miss.png");

        assets.scripts.Game g = null;
        try {
            g = new assets.scripts.Game("MoyenAge");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            new RMIServer("battleships", g);
        } catch (RemoteException | NamingException | AlreadyBoundException e) {
            e.printStackTrace();
        }

        //GameSaverFactory.getInstance().save(g);

        //GameSaverFactory.getInstance().load(g);

        System.out.println(g.getEpoque().toString());
    }

    /** This function is called once every frame and updates every GameObjects in the level.
     *
     */
    public void update() {

        for (GameObject gameObject : gameObjects) {

            gameObject.update();
        }
    }

    public GameObject findGameObjectByName(String name) {

        for (GameObject gameObject : gameObjects) {

            if (gameObject.getName().equals(name)) {

                return gameObject;
            }
        }

        return null;
    }
}
