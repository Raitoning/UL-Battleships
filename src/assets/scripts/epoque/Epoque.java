package assets.scripts.epoque;

import assets.scripts.Game;
import assets.scripts.map.Case;
import assets.scripts.map.Map;
import assets.scripts.map.Position;
import engine.networking.RMIRegistry;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Epoque implements RMIRegistry {

    protected Map maps[];
    protected ArrayList<Battleship>[] battleships;
    private Game model;

    /**
     * Constructeur d'une Epoque Initialisant les tableaux et pas les contenus du tableau !
     */
    // HACK: Array of ArrayList, deprecated, should be changed as fast as possible.
    public Epoque(boolean init, Game m) throws RemoteException {

        this.maps = new Map[2];
        this.battleships = new ArrayList[2];


        maps[0] = new Map(0,model);
        maps[1] = new Map(1,model);

        if (init) {
            battleshipInit(0);
            battleshipInit(1);
        } else {
            battleshipsVoidInit();
        }
    }

    /**
     * Initialise les bateaux du joueur d'id idPlayer
     * @param idPlayer Id du joueur a initialiser
     */
    protected abstract void battleshipInit(int idPlayer);

    /**
     * Retourne l'ArrayList de Bateaux du joueur idPlayer
     * @param idPlayer Id du joueur dont on veux les bateaux
     * @return
     */
    public ArrayList<Battleship> getBattleships(int idPlayer) {
        return battleships[idPlayer];
    }


    /**
     * A implementer, Chemin vers les textures a afficher des bateaux
     * @return
     */
    public abstract String getCheminTexture();

    /**
     * Fonction il y a t-il un bateau sur le chemin sur la carte du joueur d'ID idPlayer
     * en position x, y avec une longeur length de maniere verticale ou horizontale
     * @param idPlayer
     * @param x
     * @param y
     * @param length Longeur du chemin
     * @param vertical Boolean pour savoir si le chemin est en position verticale ou horizontale
     * @return
     */
    protected boolean isThereAShipOnTheWay(int idPlayer, int x, int y, int length, boolean vertical){

        for (int i=0;i<length;i++) {
            if (vertical) {
                if (!(y + i >= 10)){
                    if (maps[idPlayer].at(x, y + i).toString().equals(Case.BATEAU)) {
                        return true;
                    }
                }
            } else {
                if (!(x + i >= 10)) {
                    if (maps[idPlayer].at(x + i, y).toString().equals(Case.BATEAU)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    /**
     * Prend une position dans les limites de la carte, si la position donnée en parametre sors de la carte
     * @param pos Position d'entrée
     * @param vertical Boolean pour savoir si le bateau sera en position verticale ou horizontale
     * @param length Longeur du bateau
     * @return La position d'entrée si tout va bien ou une position corrigée sinon.
     */
    protected Position repositionIfOutOfBounds(Position pos, boolean vertical, int length){

        if (vertical){
            if (pos.getY()+length >= Map.NBCASES ){
                return new Position(pos.getX(), pos.getY()-(pos.getY()+length-Map.NBCASES));
            }
        } else if (pos.getX()+length >= Map.NBCASES ){
            return new Position(pos.getX()-(pos.getX()+length-Map.NBCASES), pos.getY());
        }
        return pos;
    }


    /**
     * Retourne le bateau présent a la position x,y du joueur id Joueur
     * @param idJoueur
     * @param x
     * @param y
     * @return Le bateau ou null s'il n'y en a pas.
     */
    public Battleship getAt(int idJoueur, int x, int y){
        for (Battleship b: getBattleships(idJoueur)) {
            for (int i = 0; i < b.getLength(); i++) {
                if (b.isVertical()) {
                    if ((b.getPosition().getX() == x) && ((b.getPosition().getY() + i) == y)) {
                        return b;
                    }
                } else {
                    if (((b.getPosition().getX() + i) == x) && (b.getPosition().getY() == y)) {
                        return b;
                    }
                }
            }
        }
        return null;
    }

    public Case getCaseAt(int i,int  x, int y){
        return maps[i].at(x,y);
    }

    @Override
    public String toString() {
        return "\t Epoque " +
                 Arrays.toString(maps) +
                '\n';
    }

    public abstract String name();

    public void updateCaseAt(int i, int x, int y, Case c){
        maps[i].updateAt(c,x,y);
    }

    public void addShip(int i,Battleship b){
        battleships[i].add(b);
    }

    private void battleshipsVoidInit(){
        battleships[0]= new ArrayList<>(4);
        battleships[1]= new ArrayList<>(4);
    }
}
