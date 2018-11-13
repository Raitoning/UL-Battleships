package battleship.player;

import battleship.map.Map;
import battleship.map.Position;

public abstract class Player {

    int idJoueur;

    public Player(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public abstract Position play(Map m);


    /**
     * Retourne l'ID du joueur adversaire.
     * @return
     */
    protected int opponentID(){
        if (idJoueur == 1){
            return 0;
        }else
            return 1;
    }
}
