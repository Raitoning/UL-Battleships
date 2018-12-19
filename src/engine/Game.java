package engine;

import assets.scripts.Model;
import engine.gameobject.GameObject;
import engine.input.Input;
import engine.networking.RMIServer;
//import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
//import java.rmi.AlreadyBoundException;
//import java.rmi.RemoteException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Objects;


/**
 * <h1>Model</h1>
 * Temporary class supposed to represent a single level (Scene)
 * <p>
 * This class is mostly used for debugging purposes.
 * </p>
 *
 * @author  Raitoning
 * @version 2018.12.19-tailored-wastelands
 * @since   2018.11.14
 */
public class Game {

    private ArrayList<GameObject> gameObjects;
    private Model g;
    private static int gameID = 0;

    private RMIServer rmiServer;

    /** Constructs a new level. Only once should be used at run-time.
     *
     */
    public Game() {

        gameObjects = new ArrayList<>();

        SpriteFactory.getInstance().addSprite("Water", "src/assets/textures/MoyenAge/Water.png");
        SpriteFactory.getInstance().addSprite("Boat", "src/assets/textures/MoyenAge/Boat.png");
        SpriteFactory.getInstance().addSprite("Miss", "src/assets/textures/MoyenAge/Miss.png");
        SpriteFactory.getInstance().addSprite("Exploded", "src/assets/textures/MoyenAge/exploded.png");
        //SpriteFactory.getInstance().addSprite("Victoire", "src/assets/textures/Victoire.png");

        SpriteFactory.getInstance().addSprite("horizontalMiddle", "src/assets/textures/MoyenAge/horizontalMiddle.png");
        SpriteFactory.getInstance().addSprite("verticalMiddle", "src/assets/textures/MoyenAge/verticalMiddle.png");
        SpriteFactory.getInstance().addSprite("horizontalQueue", "src/assets/textures/MoyenAge/horizontalQueue.png");
        SpriteFactory.getInstance().addSprite("verticalQueue", "src/assets/textures/MoyenAge/verticalQueue.png");
        SpriteFactory.getInstance().addSprite("horizontalTete", "src/assets/textures/MoyenAge/horizontalTete.png");
        SpriteFactory.getInstance().addSprite("verticalTete", "src/assets/textures/MoyenAge/verticalTete.png");

        SpriteFactory.getInstance().addSprite("horizontalMiddleFeu", "src/assets/textures/MoyenAge/horizontalMiddleFeu.png");
        SpriteFactory.getInstance().addSprite("verticalMiddleFeu", "src/assets/textures/MoyenAge/verticalMiddleFeu.png");
        SpriteFactory.getInstance().addSprite("horizontalQueueFeu", "src/assets/textures/MoyenAge/horizontalQueueFeu.png");
        SpriteFactory.getInstance().addSprite("verticalQueueFeu", "src/assets/textures/MoyenAge/verticalQueueFeu.png");
        SpriteFactory.getInstance().addSprite("horizontalTeteFeu", "src/assets/textures/MoyenAge/horizontalTeteFeu.png");
        SpriteFactory.getInstance().addSprite("verticalTeteFeu", "src/assets/textures/MoyenAge/verticalTeteFeu.png");

        gameCreation();

//        try {
//
//            g = new Model("MoyenAge", gameID);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

//        try {
//
//            rmiServer = new RMIServer("battleships", g);
//        } catch (RemoteException | NamingException | AlreadyBoundException e) {
//            e.printStackTrace();
//        }

        //GameSaverFactory.getInstance().save(g);

        //GameSaverFactory.getInstance().load(g);

//        System.out.println(g.getEpoque().toString());
    }

    /** This function is called once every frame and updates every GameObjects in the level.
     *
     */
    public void update() {

        for (GameObject gameObject : gameObjects) {

            gameObject.update();
        }

        if(Input.getKey(KeyEvent.VK_SPACE)) {

            endGame();
        }
    }

    /**
     * @version 18.12.19-tailored-wastelands
     * @since 18.12.12-tailored-wastelads
     */
    public void endGame() {

        gameCreation();

//        try {
//            rmiServer.closeServer();
//        } catch (RemoteException | NotBoundException e) {
//            e.printStackTrace();
//        }
    }

    public GameObject findGameObjectByName(String name) {

        for (GameObject gameObject : gameObjects) {

            if (gameObject.getName().equals(name)) {

                return gameObject;
            }
        }

        return null;
    }

    /** Returns the GameID of the runnning Model.
     *
     * @return The GameID of the runnning Model.
     * @version 2018.12.12-tailored-wastelands
     * @since 2018.12.12-tailored-wastelands
     */
    public static int getGameID() {

        return gameID;
    }

    /** Set the GameID of the runnning Model.
     *
     * @param value int The new value of the Model's GameID.
     * @version 2018.12.12-tailored-wastelands
     * @since 2018.12.12-tailored-wastelands
     */
    public static void setGameID(int value) {

        gameID = value;
        System.out.println(value);
    }

    private void gameCreation() {

        JFrame settingsWindow = new JFrame();
        settingsWindow.setLayout(new FlowLayout());
        settingsWindow.setLocationRelativeTo(null);
        settingsWindow.setTitle("Création de la partie");
        settingsWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel aiPanel = new JPanel();

        JLabel aiLabel = new JLabel("Choisissez votre adversaire : ");
        aiPanel.add(aiLabel);

        String aiNames[] = { "IACroix", "IACroixLineaire", "IARandom",
                "IARandomPlus", "IASmartRandom"};

        JComboBox<String> aiComboBox = new JComboBox<>(aiNames);

        aiPanel.add(aiComboBox);
        settingsWindow.add(aiPanel);

        JPanel epoquePanel = new JPanel();

        JLabel epoqueLabel = new JLabel("Choisissez l'epoque du jeux : ");
        epoquePanel.add(epoqueLabel);

        String epoqueNames[] = {"MoyenAge", "Renaissance", "Espace"};

        JComboBox<String> epoqueComboBox = new JComboBox<>(epoqueNames);

        epoquePanel.add(epoqueComboBox);
        settingsWindow.add(epoquePanel);

        JPanel buttonsPanel = new JPanel();
        JButton quitButton = new JButton("Quitter");
        JButton launchButton = new JButton("Lancer");

        quitButton.addActionListener(e -> {

            settingsWindow.dispose();
            Engine.getInstance().exit();
        });

        launchButton.addActionListener(e -> {

            String iaName = Objects.requireNonNull(aiComboBox.getSelectedItem()).toString();
            String epoqueName = Objects.requireNonNull(epoqueComboBox.getSelectedItem()).toString();
            createModel(epoqueName, iaName);
            settingsWindow.dispose();
        });

        buttonsPanel.add(quitButton);
        buttonsPanel.add(launchButton);
        settingsWindow.add(buttonsPanel);

        settingsWindow.setSize(400,200);
        settingsWindow.setVisible(true);
    }

    private void createModel(String epoqueName, String aiName) {

        try {

            setGameID(getGameID() + 1);
            g = new Model(epoqueName, getGameID(), aiName);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
