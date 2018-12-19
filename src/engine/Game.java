package engine;

import assets.scripts.Model;
import engine.gameobject.GameObject;
import engine.input.Input;
import engine.networking.RMIServer;
import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;


/**
 * <h1>Model</h1>
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
        SpriteFactory.getInstance().addSprite("Exploded", "src/assets/textures/MoyenAge/Exploded.png");
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

        creationPartie();

        try {
            g = new Model("MoyenAge", gameID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            rmiServer = new RMIServer("battleships", g);
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

            endGame();
        }
    }

    /**
     * @version 18.12.12-tailored-wastelands
     * @since 18.12.12-tailored-wastelads
     */
    private void endGame() {

        g.endGame();

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

    public static void setGameID(int value) {

        gameID = value;
        System.out.println(value);
    }


    private void creationPartie(){
        JFrame fenetre = new JFrame();
        fenetre.setLayout(new FlowLayout());
        fenetre.setLocationRelativeTo(null);
        fenetre.setTitle("Creation de la partie");
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel jpIA = new JPanel();
        JLabel labIA = new JLabel("Choisissez votre adversaire : ");
        jpIA.add(labIA);
        String intelligence[]={ "IACroix", "IACroixLineaire","IARandom","IARandomPlus","IASmartRandom"};
        JComboBox<String> jIA = new JComboBox<String>(intelligence);
        jpIA.add(jIA);
        fenetre.add(jpIA);

        JPanel jpEpoque = new JPanel();
        JLabel labEpoque = new JLabel("Choisissez l'epoque du jeux : ");
        jpEpoque.add(labEpoque);
        String epoques[]={ "MoyenAge", "Renaissance","Espace"};
        JComboBox<String> jEpoque = new JComboBox<String>(epoques);
        jpEpoque.add(jEpoque);
        fenetre.add(jpEpoque);

        JPanel jpButton = new JPanel();
        JButton jbQuitter = new JButton("Quitter");
        JButton jbLancer = new JButton("Lancer");

        jbQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.dispose();
            }
        });

        jbLancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String iaName = jIA.getSelectedItem().toString();
                String epoqueName = jEpoque.getSelectedItem().toString();
                Engine.getInstance().getGame().createModel(epoqueName,iaName);
                fenetre.dispose();
            }
        });

        jpButton.add(jbQuitter);
        jpButton.add(jbLancer);
        fenetre.add(jpButton);

        fenetre.setSize(400,200);
        fenetre.setVisible(true);
    }

    public void createModel(String epoque, String ia){

        //TODO : ajouter numero de partie + ajouter ia dans constructeur
        try{
            g = new Model(epoque,0);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
