package engine;

import assets.scripts.Model;
import assets.scripts.data.GameSaverFactory;
import assets.scripts.data.XMLSaving;
import assets.scripts.epoque.Battleship;
import assets.scripts.epoque.MoyenAge;
import assets.scripts.epoque.Space;
import assets.scripts.map.Position;
import assets.scripts.player.*;
import engine.gameobject.GameObject;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Objects;

//import javax.naming.NamingException;
//import java.rmi.AlreadyBoundException;
//import java.rmi.RemoteException;


/**
 * <h1>Model</h1>
 * Temporary class supposed to represent a single level (Scene)
 * <p>
 * This class is mostly used for debugging purposes.
 * </p>
 *
 * @author Raitoning
 * @version 2018.12.19-tailored-wastelands
 * @since 2018.11.14
 */
public class Game {

    private static int gameID = 0;
    private ArrayList<GameObject> gameObjects;
    private Model model = null;
    //    private RMIServer rmiServer;
    private JFrame outputWindow;

    /**
     * Constructs a new level. Only once should be used at run-time.
     */
    Game() {

        gameObjects = new ArrayList<>();

        // Sprite MoyenAge
        String epoqueName = MoyenAge.NAME;

        SpriteFactory.getInstance().addSprite(epoqueName + "Water", "/assets/textures/MoyenAge/Water.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "Miss", "/assets/textures/MoyenAge/Miss.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "Exploded", "/assets/textures/MoyenAge/exploded.png");
        SpriteFactory.getInstance().addSprite("Victoire", "/assets/textures/Victoire.png");
        SpriteFactory.getInstance().addSprite("Defaite", "/assets/textures/Defaite.png");

        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalMiddle", "/assets/textures/MoyenAge/horizontalMiddle.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalMiddle", "/assets/textures/MoyenAge/verticalMiddle.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalQueue", "/assets/textures/MoyenAge/horizontalQueue.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalQueue", "/assets/textures/MoyenAge/verticalQueue.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalTete", "/assets/textures/MoyenAge/horizontalTete.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalTete", "/assets/textures/MoyenAge/verticalTete.png");

        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalMiddleFeu", "/assets/textures/MoyenAge/horizontalMiddleFeu.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalMiddleFeu", "/assets/textures/MoyenAge/verticalMiddleFeu.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalQueueFeu", "/assets/textures/MoyenAge/horizontalQueueFeu.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalQueueFeu", "/assets/textures/MoyenAge/verticalQueueFeu.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalTeteFeu", "/assets/textures/MoyenAge/horizontalTeteFeu.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalTeteFeu", "/assets/textures/MoyenAge/verticalTeteFeu.png");

        // Sprite Espace
        epoqueName = Space.NAME;

        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalMiddle", "/assets/textures/Espace/spacehorizontalmiddleup.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalMiddle", "/assets/textures/Espace/spacemiddleup.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalQueue", "/assets/textures/Espace/spacehorizontaltailup.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalQueue", "/assets/textures/Espace/spacetailup.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalTete", "/assets/textures/Espace/spacehorizontalheadup.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalTete", "/assets/textures/Espace/spaceheadup.png");

        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalMiddleFeu", "/assets/textures/Espace/spacehorizontalmiddledown.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalMiddleFeu", "/assets/textures/Espace/spacemiddledown.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalQueueFeu", "/assets/textures/Espace/spacehorizontaltaildown.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalQueueFeu", "/assets/textures/Espace/spacetaildown.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "horizontalTeteFeu", "/assets/textures/Espace/spacehorizontalheaddown.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "verticalTeteFeu", "/assets/textures/Espace/spaceheaddown.png");

        SpriteFactory.getInstance().addSprite(epoqueName + "Water", "/assets/textures/Espace/space.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "Miss", "/assets/textures/Espace/spacemiss.png");
        SpriteFactory.getInstance().addSprite(epoqueName + "Exploded", "/assets/textures/Espace/spacehit.png");

        outputWindow = Engine.getInstance().getRenderer().getWindow();

        MenuBar menuBar = new MenuBar();

        // Menu Fichier
        Menu fileMenu = new Menu("Fichier");

        MenuItem newGameItem = new MenuItem("Nouvelle partie");
        newGameItem.addActionListener(e -> endGame());

        MenuItem loadItem = new MenuItem("Charger une partie");
        loadItem.addActionListener(e -> GameSaverFactory.getInstance().load());

        MenuItem saveItem = new MenuItem("Sauvegarder la partie");
        saveItem.addActionListener(e -> GameSaverFactory.getInstance().save(model));

        MenuItem exitItem = new MenuItem("Quitter");
        exitItem.addActionListener(e -> Engine.getInstance().exit());

        fileMenu.add(newGameItem);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        // Menu IA.
        Menu aiMenu = new Menu("IA");

        MenuItem aiCroixItem = new MenuItem(IACroix.NAME);
        aiCroixItem.addActionListener(e -> model.changerIA(IACroix.NAME));
        MenuItem aiCroixLineaireItem = new MenuItem(IACroixLineaire.NAME);
        aiCroixLineaireItem.addActionListener(e -> model.changerIA(IACroixLineaire.NAME));
        MenuItem aiRandom = new MenuItem(IARandom.NAME);
        aiRandom.addActionListener(e -> model.changerIA(IARandom.NAME));
        MenuItem aiRandomPlus = new MenuItem(IARandomPlus.NAME);
        aiRandomPlus.addActionListener(e -> model.changerIA(IARandomPlus.NAME));
        MenuItem aiSmartRandom = new MenuItem(IASmartRandom.NAME);
        aiSmartRandom.addActionListener(e -> model.changerIA(IASmartRandom.NAME));

        aiMenu.add(aiCroixItem);
        aiMenu.add(aiCroixLineaireItem);
        aiMenu.add(aiRandom);
        aiMenu.add(aiRandomPlus);
        aiMenu.add(aiSmartRandom);

        menuBar.add(aiMenu);

        outputWindow.setMenuBar(menuBar);

        gameCreation();

//        try {
//
//            model = new Model("MoyenAge", gameID);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

//        try {
//
//            rmiServer = new RMIServer("battleships", model);
//        } catch (RemoteException | NamingException | AlreadyBoundException e) {
//            e.printStackTrace();
//        }

        //GameSaverFactory.getInstance().save(model);

        //GameSaverFactory.getInstance().load(model);

        //System.out.println(model.getEpoque().toString());
    }

    /**
     * Returns the GameID of the runnning Model.
     *
     * @return The GameID of the runnning Model.
     * @since 2018.12.12-tailored-wastelands
     */
    public static int getGameID() {

        return gameID;
    }

    /**
     * Set the GameID of the runnning Model.
     *
     * @param value int The new value of the Model's GameID.
     * @since 2018.12.12-tailored-wastelands
     */
    public static void setGameID(int value) {

        gameID = value;
    }

    /**
     * This function is called once every frame and updates every GameObjects in the level.
     */
    public void update() {

        for (GameObject gameObject : gameObjects) {

            gameObject.update();
        }
    }

    /**
     * @since 18.12.12-tailored-wastelads
     */
    public void endGame() {

//        outputWindow.setVisible(false);
        outputWindow.setEnabled(false);

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

    private void gameCreation() {

        JDialog settingsWindow = new JDialog();
        settingsWindow.setLayout(new FlowLayout());
        settingsWindow.setLocationRelativeTo(null);
        settingsWindow.setTitle("Création de la partie");

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

        JPanel jpChargement = new JPanel();
        JButton jbChargement = new JButton("Charger une partie");
        jbChargement.addActionListener(e -> {

            XMLSaving.getInstance().load();
            settingsWindow.dispose();
            outputWindow.setEnabled(true);
            outputWindow.setVisible(true);
        });
        jpChargement.add(jbChargement);
        settingsWindow.add(jpChargement);

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
            outputWindow.setEnabled(true);
            outputWindow.setVisible(true);
        });

        buttonsPanel.add(quitButton);
        buttonsPanel.add(launchButton);
        settingsWindow.add(buttonsPanel);

        settingsWindow.setSize(400, 200);
        settingsWindow.setVisible(true);
    }

    private void createModel(String epoqueName, String aiName) {

        try {

            setGameID(getGameID() + 1);
            model = new Model(epoqueName, getGameID(), aiName);

        } catch (RemoteException e) {

            e.printStackTrace();
        }
    }

    public void loadModel(String ep, String adv, ArrayList<Battleship> liste1, ArrayList<Battleship> liste2, ArrayList<Position> casesTouche) {

        try {

            setGameID(getGameID() + 1);
            model = new Model(ep, getGameID(), adv, liste1, liste2, casesTouche);
        } catch (RemoteException e) {

            e.printStackTrace();
        }
    }
}
