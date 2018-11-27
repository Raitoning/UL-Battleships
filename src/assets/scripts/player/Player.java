package assets.scripts.player;

import assets.scripts.map.Map;
import assets.scripts.map.Position;
import engine.gameobject.GameObject;
import engine.gameobject.component.Camera;

public abstract class Player extends GameObject {

    private int idJoueur;
    Camera camera;

    Player(int idJoueur) {

        this.idJoueur = idJoueur;

        camera = new Camera(10.5f, 0f, 10f, this);

        addComponent(camera);
    }

    public abstract Position play(Map m);

    /**
     * Retourne l'ID du joueur adversaire.
     * @return L'identifiant du joueur adversaire.
     */
    protected int opponentID(){
        if (idJoueur == 1){
            return 0;
        }else
            return 1;
    }
}
