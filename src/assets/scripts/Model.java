package assets.scripts;

import assets.scripts.epoque.Epoque;
import assets.scripts.epoque.MoyenAge;
import assets.scripts.epoque.Space;
import assets.scripts.player.*;
import engine.Engine;
import engine.gameobject.GameObject;
import engine.gameobject.component.SpriteRenderer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Model extends UnicastRemoteObject implements NetworkedGame {

    private Player players[];
    private Epoque epoque;
    private int playerTurn;
    private boolean isGameEnded = false;
    private int gameID;
    private String epoqueName;

    /**
     * Construit une instance du jeu à partir d'une Epoque donnée en parametre
     */
    public Model(String epName, int gameID, String aiName) throws RemoteException {

        super();
        this.gameID = gameID;
        epoqueName = epName;
        setEpoque(epName,true);

        this.players = new Player[2];

        this.players[0] = new Human(0,this, gameID);

        setTypeofPlayer(1, aiName);

        playerTurn = 0;

    }

    public String getTypeofPlayer(int playerID) { return players[playerID].toString(); }

    public Epoque getEpoque() { return epoque; }

    public void setTypeofPlayer(int playerID, String typeName){

        if (typeName.equals(Human.name)) {

            players[playerID] = new Human(playerID,this, gameID);

        } else if (typeName.equals(IACroix.NAME)) {

            players[playerID] = new IACroix(playerID,this, gameID);

        } else if (typeName.equals(IACroixLineaire.NAME)) {

            players[playerID] = new IACroixLineaire(playerID,this, gameID);

        } else if (typeName.equals(IARandom.NAME)) {

            players[playerID] = new IARandom(playerID,this, gameID);

        } else if (typeName.equals(IARandomPlus.NAME)) {

            players[playerID] = new IARandomPlus(playerID,this, gameID);

        } else if (typeName.equals(IASmartRandom.NAME)) {

            players[playerID] = new IASmartRandom(playerID,this, gameID);
        }
    }

    public void changerIA(String nomIA){
        setTypeofPlayer(1, nomIA);
        ((IA)players[1]).clean();
    }

    public String getNameEpoque(){
        return epoqueName;
    }

    /**
     * Set l'epoque du jeu
     * @param epoqueName Le nom de l'époque.
     * @param generateShips Booléen pour générer des bateaux ou non.
     */
    public void setEpoque(String epoqueName, boolean generateShips) {

        if(epoqueName.equals(MoyenAge.NAME)) {

            try {

                epoque = new MoyenAge(generateShips, this, gameID);
            } catch (RemoteException e) {

                e.printStackTrace();
            }
        } else if(epoqueName.equals(Space.NAME)) {

            try {

                epoque = new Space(generateShips, this, gameID);
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
