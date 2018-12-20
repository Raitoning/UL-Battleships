package engine;

import assets.scripts.Model;
import assets.scripts.epoque.MoyenAge;
import assets.scripts.epoque.Space;
import assets.scripts.player.*;
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


        String tmp = MoyenAge.NAME;
        SpriteFactory.getInstance().addSprite(tmp +"Water", "src/assets/textures/MoyenAge/Water.png");
        SpriteFactory.getInstance().addSprite(tmp +"Miss", "src/assets/textures/MoyenAge/Miss.png");
        SpriteFactory.getInstance().addSprite(tmp +"Exploded", "src/assets/textures/MoyenAge/exploded.png");
        //SpriteFactory.getInstance().addSprite("Victoire", "src/assets/textures/Victoire.png");



        //sprite MoyenAge
        SpriteFactory.getInstance().addSprite(tmp +"horizontalMiddle", "src/assets/textures/MoyenAge/horizontalMiddle.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalMiddle", "src/assets/textures/MoyenAge/verticalMiddle.png");
        SpriteFactory.getInstance().addSprite(tmp +"horizontalQueue", "src/assets/textures/MoyenAge/horizontalQueue.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalQueue", "src/assets/textures/MoyenAge/verticalQueue.png");
        SpriteFactory.getInstance().addSprite(tmp +"horizontalTete", "src/assets/textures/MoyenAge/horizontalTete.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalTete", "src/assets/textures/MoyenAge/verticalTete.png");

        SpriteFactory.getInstance().addSprite(tmp +"horizontalMiddleFeu", "src/assets/textures/MoyenAge/horizontalMiddleFeu.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalMiddleFeu", "src/assets/textures/MoyenAge/verticalMiddleFeu.png");
        SpriteFactory.getInstance().addSprite(tmp +"horizontalQueueFeu", "src/assets/textures/MoyenAge/horizontalQueueFeu.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalQueueFeu", "src/assets/textures/MoyenAge/verticalQueueFeu.png");
        SpriteFactory.getInstance().addSprite(tmp +"horizontalTeteFeu", "src/assets/textures/MoyenAge/horizontalTeteFeu.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalTeteFeu", "src/assets/textures/MoyenAge/verticalTeteFeu.png");

        tmp = Space.NAME;
        //sprite espace
        SpriteFactory.getInstance().addSprite(tmp +"horizontalMiddle", "src/assets/textures/Espace/spacehorizontalmiddleup.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalMiddle", "src/assets/textures/Espace/spacemiddleup.png");
        SpriteFactory.getInstance().addSprite(tmp +"horizontalQueue", "src/assets/textures/Espace/spacehorizontaltailup.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalQueue", "src/assets/textures/Espace/spacetailup.png");
        SpriteFactory.getInstance().addSprite(tmp +"horizontalTete", "src/assets/textures/Espace/spacehorizontalheadup.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalTete", "src/assets/textures/Espace/spaceheadup.png");

        SpriteFactory.getInstance().addSprite(tmp +"horizontalMiddleFeu", "src/assets/textures/Espace/spacehorizontalmiddledown.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalMiddleFeu", "src/assets/textures/Espace/spacemiddledown.png");
        SpriteFactory.getInstance().addSprite(tmp +"horizontalQueueFeu", "src/assets/textures/Espace/spacehorizontaltaildown.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalQueueFeu", "src/assets/textures/Espace/spacetaildown.png");
        SpriteFactory.getInstance().addSprite(tmp +"horizontalTeteFeu", "src/assets/textures/Espace/spacehorizontalheaddown.png");
        SpriteFactory.getInstance().addSprite(tmp +"verticalTeteFeu", "src/assets/textures/Espace/spaceheaddown.png");

        SpriteFactory.getInstance().addSprite(tmp +"Water", "src/assets/textures/Espace/space.png");
        SpriteFactory.getInstance().addSprite(tmp +"Miss", "src/assets/textures/Espace/spacemiss.png");
        SpriteFactory.getInstance().addSprite(tmp +"Exploded", "src/assets/textures/Espace/spacehit.png");

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

        String aiNames[] = {IACroix.NAME, IACroixLineaire.NAME, IARandom.NAME,
                IARandomPlus.NAME, IASmartRandom.NAME};

        JComboBox<String> aiComboBox = new JComboBox<>(aiNames);

        aiPanel.add(aiComboBox);
        settingsWindow.add(aiPanel);

        JPanel epoquePanel = new JPanel();

        JLabel epoqueLabel = new JLabel("Choisissez l'epoque du jeux : ");
        epoquePanel.add(epoqueLabel);

        String epoqueNames[] = {MoyenAge.NAME, Space.NAME};

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
