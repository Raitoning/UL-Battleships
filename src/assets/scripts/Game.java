package assets.scripts;

import assets.scripts.epoque.Epoque;
import assets.scripts.epoque.MoyenAge;
import assets.scripts.epoque.Renaissance;
import assets.scripts.player.*;
import engine.gameobject.GameObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class Game extends UnicastRemoteObject implements NetworkedGame {

    private int score[];
    private Player players[];
    private Epoque epoque;
    private int playerTurn;

    /**
     * Construit une instance du jeu à partir d'une Epoque donnée en parametre
     */
    public Game(String e) throws RemoteException {
        super();
        setEpoque(e,true);
        this.score = new int[2];
        Arrays.fill(score,0);

        this.players = new Player[2];

        this.players[0] = new Human(0,this);

        this.players[1] = new IARandom(1,this);

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

            players[k] = new Human(k,this);

        } else if ("IACroix".equals(t)) {

            players[k] = new IACroix(k,this);

        } else if ("IARandom".equals(t)) {

            players[k] = new IARandom(k,this);
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
                epoque = new MoyenAge(b,this);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if(t.equals("Renaissance")) {

            try {
                epoque = new Renaissance(b, this);
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
}
