package assets.scripts.epoque;

import assets.scripts.map.Map;
import assets.scripts.map.Position;

public class Battleship {

    Position coinHautGauche;

    int pv;
    int hauteur;
    int largeur;


    /**
     * Constructeur d'un bateau et le place sur la Map map
     *
     * @param coinHautGauche Position X, Y du coin haut gauche du bateau
     * @param pv Nombre de coups avant la coulée du bateau
     * @param longeur longeur du Bateau
     * @param vertical Boolean pour savoir si le bateau est en position verticale ou horizontale
     * @param map Map sur la quelle placer le Bateau
     */
    public Battleship(Position coinHautGauche, int pv, int longeur, boolean vertical, Map map) {
        this.pv = pv;

        if (vertical){
            this.hauteur = longeur;
            this.largeur = 1;


        } else {
            this.hauteur = 1;
            this.largeur = longeur;
        }

        this.coinHautGauche = coinHautGauche;

        if (map!=null)
            map.add(this);
    }

    /**
     * Fonction a completer, quand un bateau se fait toucher.
     */
    public void hit(){
        pv-=1;
    }

    /**
     * Retourne la position du coin haut Gauche du bateau
     * @return Position
     */
    public Position getPosition() {
        return coinHautGauche;
    }

    /**
     * Retourne la longeur du Bateau
     * @return int
     */
    public int getLength(){
        return Integer.max(hauteur,largeur);
    }

    /**
     * Retourne un booleen vrai si le bateau est en position verticale faux si le bateau est en position horizontale
     * @return
     */
    public boolean isVertical(){
        return largeur == 1;
    }

    public int getPv() {
        return pv;
    }

    public void destroy() {

        coinHautGauche = null;
    }

    public boolean isAlive(){
        return pv>0;
    }
}
