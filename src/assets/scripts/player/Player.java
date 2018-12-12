package assets.scripts.player;

import assets.scripts.Game;
import assets.scripts.map.Case;
import engine.gameobject.GameObject;
import engine.gameobject.component.Camera;

public abstract class Player extends GameObject {

    private int idJoueur;
    protected Camera camera;
    protected Game model;

    Player(int idJoueur, Game m) {
        model = m;
        this.idJoueur = idJoueur;

        camera = new Camera(10.5f, 0f, 10f, this);

        addComponent(camera);
    }

    public void play(Case c){
        if(!c.estToucher()){
            c.subitTir();
            model.nextTurn();
        }
        else{
            if(!toString().equals("Human"))
                play(((IA)this).jeuxIA());
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
