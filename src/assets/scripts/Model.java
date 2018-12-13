package assets.scripts;

import assets.scripts.epoque.Epoque;
import assets.scripts.epoque.MoyenAge;
import assets.scripts.epoque.Renaissance;
import assets.scripts.player.*;
import engine.Engine;
import engine.gameobject.GameObject;
import engine.gameobject.component.SpriteRenderer;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class Model extends UnicastRemoteObject implements NetworkedGame {

    private int score[];
    private Player players[];
    private Epoque epoque;
    private int playerTurn;
    private boolean isGameEnded = false;
    private int gameID;

    /**
     * Construit une instance du jeu à partir d'une Epoque donnée en parametre
     */
    public Model(String e, int gameID) throws RemoteException {
        super();
        this.gameID = gameID;

        replay();

        setEpoque(e,true);
        this.score = new int[2];
        Arrays.fill(score,0);

        this.players = new Player[2];

        this.players[0] = new Human(0,this, gameID);

        this.players[1] = new IARandomPlus(1,this, gameID);

        playerTurn = 0;

    }

    public int getScore(int k) {
        return score[k];
    }

    public String getTypeofPlayer(int k){
        return players[k].toString();
    }

    public Epoque getEpoque() {
        return epoque;
    }

    public void setScore(int id, int score) {
        this.score[id] = score;
    }

    public void setTypeofPlayer(int k, String t){

        if ("Human".equals(t)) {

            players[k] = new Human(k,this, gameID);

        } else if ("IACroix".equals(t)) {

            players[k] = new IACroix(k,this, gameID);

        } else if ("IARandom".equals(t)) {

            players[k] = new IARandom(k,this, gameID);
        }
    }

    /**
     * Set l'epoque du jeu
     * @param t
     * @param b false : pas de generation de bateau
     */
    public void setEpoque(String t, boolean b) {

        if(t.equals("MoyenAge")) {

            try {
                epoque = new MoyenAge(b,this, gameID);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if(t.equals("Renaissance")) {

            try {
                epoque = new Renaissance(b, this, gameID);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

    public Player getPlayer(int k){
        return players[k];
    }

    public void nextTurn(){
        playerTurn = (playerTurn+1)%2;

        if(!getTypeofPlayer(playerTurn).equals("Human")){
            getPlayer(playerTurn).play(((IA)getPlayer(playerTurn)).jeuxIA());
        }

    }

    public boolean hasWon(int idJoueur){
        return epoque.hasLost(players[idJoueur].opponentID());
    }

    public void endGame() {

        GameObject endGame = new GameObject(gameID);
        endGame.getTransform().setPosition(10.5f, 5f);
        endGame.getTransform().setScale(10f, 10f);

        endGame.addComponent(new SpriteRenderer("Victoire", endGame));

        if(isGameEnded) {
            try {
                Thread.sleep(5000);
                engine.Game.setGameID(engine.Game.getGameID() + 1);
                Engine.exit();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isGameEnded = true;
    }

    public int getGameID() {

        return gameID;
    }

    private void replay(){

        boolean solo;
        JOptionPane d = new JOptionPane();
        // les textes figurant // sur les boutons
        String lesTextes[]={ "Solo", "Reseau"};
        int retour = d.showOptionDialog(null, "le message", "le titre", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, lesTextes,  lesTextes[0]);
        if( retour!=JOptionPane.CLOSED_OPTION){

        } else{

        }
    }
}
