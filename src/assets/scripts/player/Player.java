package assets.scripts.player;

import assets.scripts.Game;
import assets.scripts.map.Case;
import engine.gameobject.GameObject;
import engine.gameobject.component.Camera;

public abstract class Player extends GameObject {

    private int idJoueur;
    protected Camera camera;
    protected Game model;

    Player(int idJoueur, Game m, int gameID) {
        model = m;
        this.idJoueur = idJoueur;
        this.gameID = gameID;

        camera = new Camera(10.5f, 0f, 10f, this);

        addComponent(camera);
    }

    public void play(Case c){
        if(!c.estToucher()){
            c.subitTir();
            model.nextTurn();
        }
    }

    /**
     * Retourne l'ID du joueur adversaire.
     * @return L'identifiant du joueur adversaire.
     */
    public int opponentID(){
        if (idJoueur == 1){
            return 0;
        }else
            return 1;
    }
}
