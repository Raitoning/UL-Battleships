package engine;

import engine.gameobject.GameObject;
import engine.input.Input;
import engine.networking.RMIClient;
import engine.networking.RMIServer;

import javax.naming.NamingException;
import javax.swing.*;
import java.awt.event.KeyEvent;
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
 * @version 2018.12.12-tailored-wastelands
 * @since   2018.11.14
 */
public class Game {

    private ArrayList<GameObject> gameObjects;
    private assets.scripts.Game g;
    private static int gameID = 0;

    /** Constructs a new level. Only once should be used at run-time.
     *
     */
    public Game() {

        gameObjects = new ArrayList<>();

        SpriteFactory.getInstance().addSprite("Water", "src/assets/textures/Water.png");
        SpriteFactory.getInstance().addSprite("Boat", "src/assets/textures/Boat.png");
        SpriteFactory.getInstance().addSprite("Break", "src/assets/textures/break.png");
        SpriteFactory.getInstance().addSprite("Miss", "src/assets/textures/Miss.png");
        SpriteFactory.getInstance().addSprite("Break", "src/assets/textures/Break.png");
        SpriteFactory.getInstance().addSprite("Victoire", "src/assets/textures/Victoire.png");

        SpriteFactory.getInstance().addSprite("Head", "src/assets/textures/Head.png");
        SpriteFactory.getInstance().addSprite("horizontalMiddle", "src/assets/textures/horizontalMiddle.png");
        SpriteFactory.getInstance().addSprite("verticalMiddle", "src/assets/textures/verticalMiddle.png");
        SpriteFactory.getInstance().addSprite("horizontalQueue", "src/assets/textures/horizontalQueue.png");
        SpriteFactory.getInstance().addSprite("verticalQueue", "src/assets/textures/verticalQueue.png");
        SpriteFactory.getInstance().addSprite("horizontalTete", "src/assets/textures/horizontalTete.png");
        SpriteFactory.getInstance().addSprite("verticalTete", "src/assets/textures/verticalTete.png");
        assets.scripts.Game g = null;
        try {
            g = new assets.scripts.Game("MoyenAge", gameID);
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

        if(Input.getKey(KeyEvent.VK_SPACE)) {

            g.endGame();
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

    /** Returns the GameID of the runnning Game.
     *
     * @return The GameID of the runnning Game.
     * @version 2018.12.12-tailored-wastelands
     * @since 2018.12.12-tailored-wastelands
     */
    public static int getGameID() {

        return gameID;
    }
}
