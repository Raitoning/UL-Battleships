package assets.scripts.epoque;

import assets.scripts.Model;
import assets.scripts.map.Case;
import assets.scripts.map.Map;
import assets.scripts.map.Position;
import engine.networking.RMIRegistry;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public abstract class Epoque implements RMIRegistry {

    protected Map maps[];
    protected ArrayList<Battleship>[] battleships;
    private Model model;
    private int gameID;

    /**
     * Constructeur d'une Epoque Initialisant les tableaux et pas les contenus du tableau !
     */
    public Epoque(boolean init, Model m, int gameID) throws RemoteException {

        this.gameID = gameID;
        this.maps = new Map[2];
        model = m;
        this.battleships = new ArrayList[2];

        maps[0] = new Map(0,model, gameID);
        maps[1] = new Map(1,model, gameID);

        if (init) {
            battleshipInit(0);
            battleshipInit(1);
            maps[0].casesEmptyInit();
            maps[1].casesEmptyInit();
        } else {
            battleshipsVoidInit();
        }
    }

    /**
     * recuperatio ndes données des battleship
     * @param liste
     * @param map
     */
    public void addLoadShip(ArrayList<Battleship> liste,int map){
        for(int i =0;i < liste.size();i++){
            liste.get(i).setMap(maps[map]);
            maps[map].add(liste.get(i));
        }
    }

    /**
     * On crée les cases danss le cas d'un chargement
     */
    public void createCase(){
        for(int i = 0;i < 2;i++){
            for(int j = 0;j <battleships[i].size();j++){
                maps[i].add(battleships[i].get(j));
            }
            maps[i].casesEmptyInit();
        }
    }

    /**
     * On modifie les cases endommagée chargée
     * @param listeTouche
     */
    public void setCases(ArrayList<Position> listeTouche){

        Position courant ;
        for(int i =0;i < listeTouche.size();i++){
            courant = listeTouche.get(i);
            if(courant.getX() > Map.NBCASES){
                maps[1].at(courant.getX()-1-Map.NBCASES,courant.getY()).loadToucher();
            }
            else{
                maps[0].at(courant.getX(),courant.getY()).loadToucher();
            }
        }
    }

    /**
     * Initialise les bateaux du joueur d'id idPlayer
     * @param idPlayer Id du joueur a initialiser
     */
    protected void battleshipInit(int idPlayer) {

        Random r = new Random();
        battleships[idPlayer]= new ArrayList<>(4);

        HashMap<Integer, Integer> vie = getVieBattleships();

        for (int i =2; i<=6; i++) {
            if (i == 6){
                placeARandomBoat(r,idPlayer,i/2,vie.get(i/2));
            } else {
                placeARandomBoat(r,idPlayer,i,vie.get(i));
            }
        }

    }

    private void placeARandomBoat(Random r, int idPlayer, int taille, int pv){

        boolean v = r.nextBoolean();
        int x = r.nextInt(Map.NBCASES-taille);
        int y = r.nextInt(Map.NBCASES-taille);

        while(isThereAShipOnTheWay(idPlayer,x,y,taille,v)){
            v = r.nextBoolean();
            x = r.nextInt(Map.NBCASES-taille);
            y = r.nextInt(Map.NBCASES-taille);
        }


        addShip(idPlayer, taille, pv, v, new Position(x,y));

    }

    private void addShip(int idPlayer, int length, int hp, boolean v, Position p){
        getBattleships(idPlayer).add(
                new Battleship(p, hp, length, v,maps[idPlayer]));
    }

    /**
     * Retourne l'ArrayList de Bateaux du joueur idPlayer
     * @param idPlayer Id du joueur dont on veux les bateaux
     * @return
     */
    public ArrayList<Battleship> getBattleships(int idPlayer) {
        return battleships[idPlayer];
    }

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
                    if(maps[idPlayer].at(x, y+i) != null)
                        if (maps[idPlayer].at(x, y + i).toString().equals(Case.BATEAU)) {
                            return true;
                        }
                }
            } else {
                if (!(x + i >= 10)) {
                    if(maps[idPlayer].at(x + i, y) != null)
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
    public Battleship getBattleshipAt(int idJoueur, int x, int y){
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

    public void updateCaseAt(int i, int x, int y, Case c){
        maps[i].updateAt(c,x,y);
    }

    /*public void addShip(int i,Battleship b){
        battleships[i].add(b);
    }*/

    private void battleshipsVoidInit(){
        battleships[0]= new ArrayList<>();
        battleships[1]= new ArrayList<>();
    }

    public boolean hasLost(int idJoueur){
        boolean r = true;

        for(Battleship b:battleships[idJoueur]) {

            if(b.isAlive()) {
               r = false;
            }
        }

        return r;
    }

    protected abstract HashMap<Integer, Integer> getVieBattleships();

}
