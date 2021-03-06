package assets.scripts.epoque;

import assets.scripts.map.Map;
import assets.scripts.map.Position;

public class Battleship {

    private Position coinHautGauche;
    private int pv;
    private int hauteur;
    private int largeur;
    private Map map;

    /**
     * Constructeur d'un bateau et le place sur la Map map
     *
     * @param coinHautGauche Position X, Y du coin haut gauche du bateau
     * @param pv             Nombre de coups avant la coulée du bateau
     * @param longeur        longeur du Bateau
     * @param vertical       Boolean pour savoir si le bateau est en position verticale ou horizontale
     * @param map            Map sur la quelle placer le Bateau
     */
    public Battleship(Position coinHautGauche, int pv, int longeur, boolean vertical, Map map) {

        this.pv = pv;
        this.map = map;
        if (vertical) {
            this.hauteur = longeur;
            this.largeur = 1;

        } else {
            this.hauteur = 1;
            this.largeur = longeur;
        }

        this.coinHautGauche = coinHautGauche;

        if (map != null) {
            map.add(this);
        }
    }

    public void setMap(Map map) {

        this.map = map;
    }

    public void hit() {

        if (isAlive()) {
            pv -= 1;

            if (pv == 0) {
                map.explodeBattleship(this);
            }
        }
    }

    /**
     * Retourne la position du coin haut Gauche du bateau
     *
     * @return Position La position en haut à gauche du bateau.
     */
    public Position getPosition() {

        return coinHautGauche;
    }

    /**
     * Retourne la longeur du Bateau
     *
     * @return int La longueur du bateau.
     */
    public int getLength() {

        return Integer.max(hauteur, largeur);
    }

    /**
     * Retourne un booleen vrai si le bateau est en position verticale faux si le bateau est en position horizontale
     *
     * @return boolean Vrai si le bateau est positionné verticalement, faux sinon.
     */
    public boolean isVertical() {

        return largeur == 1;
    }

    public int getPv() {

        return pv;
    }

    boolean isAlive() {

        return pv > 0;
    }
}
