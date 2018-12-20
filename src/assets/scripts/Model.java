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
    public Model(String epoqueName, int gameID, String aiName) throws RemoteException {

        super();
        this.gameID = gameID;

        setEpoque(epoqueName,true);
        this.score = new int[2];
        Arrays.fill(score,0);

        this.players = new Player[2];

        this.players[0] = new Human(0,this, gameID);

        setTypeofPlayer(1, aiName);

        playerTurn = 0;

    }

    public int getScore(int playerID) { return score[playerID]; }

    public String getTypeofPlayer(int playerID) { return players[playerID].toString(); }

    public Epoque getEpoque() { return epoque; }

    public void setScore(int playerID, int score) { this.score[playerID] = score; }

    public void setTypeofPlayer(int playerID, String typeName){

        if ("Human".equals(typeName)) {

            players[playerID] = new Human(playerID,this, gameID);

        } else if ("IACroix".equals(typeName)) {

            players[playerID] = new IACroix(playerID,this, gameID);

        } else if ("IACroixLineaire".equals(typeName)) {

            players[playerID] = new IACroixLineaire(playerID,this, gameID);

        } else if ("IARandom".equals(typeName)) {

            players[playerID] = new IARandom(playerID,this, gameID);

        } else if ("IARandomPlus".equals(typeName)) {

            players[playerID] = new IARandomPlus(playerID,this, gameID);

        } else if ("IASmartRandom".equals(typeName)) {

            players[playerID] = new IASmartRandom(playerID,this, gameID);
        }
    }

    /**
     * Set l'epoque du jeu
     * @param epoqueName Le nom de l'époque.
     * @param generateShips Booléen pour générer des bateaux ou non.
     */
    public void setEpoque(String epoqueName, boolean generateShips) {

        if(epoqueName.equals("MoyenAge")) {

            try {

                epoque = new MoyenAge(generateShips, this, gameID);
            } catch (RemoteException e) {

                e.printStackTrace();
            }
        } else if(epoqueName.equals("Renaissance")) {

            try {

                epoque = new Renaissance(generateShips, this, gameID);
            } catch (RemoteException e) {

                e.printStackTrace();
            }
        }
    }

    public int getPlayerTurn(){ return playerTurn; }

    public Player getPlayer(int playerID){ return players[playerID]; }

    public void nextTurn(){

        if(!hasWon(0) && !hasWon(1)) {

            playerTurn = (playerTurn + 1) % 2;

            if(!getTypeofPlayer(playerTurn).equals("Human")){

                getPlayer(playerTurn).play(((IA)getPlayer(playerTurn)).jeuxIA());
            }
        } else {

            endGame();
            Engine.getInstance().getGame().endGame();
        }
    }

    public boolean hasWon(int playerID){

        return epoque.hasLost(players[playerID].opponentID());
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
                Engine.getInstance().exit();

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

        isGameEnded = true;
    }

    public int getGameID() {

        return gameID;
    }
}
