package assets.scripts.epoque;

import assets.scripts.Model;
import assets.scripts.map.Case;
import assets.scripts.map.Map;
import assets.scripts.map.Position;
import engine.networking.RMIRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

// import java.rmi.RemoteException;

public abstract class Epoque implements RMIRegistry {

    private Map maps[];
    private ArrayList<Battleship>[] battleships;

    /**
     * Constructeur d'une Epoque Initialisant les tableaux et pas les contenus du tableau !
     */
    // HACK: Utilisation d'un tableau d'ArrayList pour stocker des ArrayList<Battleship>
    Epoque(boolean initShips, Model model, int gameID) {

        this.maps = new Map[2];
        this.battleships = new ArrayList[2];

        maps[0] = new Map(0, model, gameID);
        maps[1] = new Map(1, model, gameID);

        if (initShips) {
            battleshipInit(0);
            battleshipInit(1);
            maps[0].casesEmptyInit();
            maps[1].casesEmptyInit();
        } else {
            battleshipsVoidInit();
        }
    }

    /**
     * Récuperation des données des battleship
     *
     * @param bateaux  ArrayList<Battleship> La liste des bateaux chargés.
     * @param idJoueur int L'identifiant du joueur.
     */
    public void addLoadShip(ArrayList<Battleship> bateaux, int idJoueur) {

        for (Battleship bateau : bateaux) {
            battleships[idJoueur].add(bateau);
            bateau.setMap(maps[idJoueur]);
            maps[idJoueur].add(bateau);
        }
    }

    /**
     * On crée les cases dans le cas d'un chargement
     */
    public void createCase() {

        for (int i = 0; i < 2; i++) {
            maps[i].casesEmptyInit();
        }
    }

    /**
     * On modifie les cases endommagées chargées
     *
     * @param listeTouche ArrayList<Position> La liste des cases touchées.
     */
    public void setCases(ArrayList<Position> listeTouche) {

        Position courant;
        for (Position aListeTouche : listeTouche) {
            courant = aListeTouche;
            if (courant.getX() > Map.NBCASES) {
                maps[1].at(courant.getX() - 1 - Map.NBCASES, courant.getY()).loadToucher();
            } else {
                maps[0].at(courant.getX(), courant.getY()).loadToucher();
            }
        }
    }

    /**
     * Initialise les bateaux du joueur d'id idPlayer
     *
     * @param idPlayer Id du joueur à initialiser
     */
    private void battleshipInit(int idPlayer) {

        battleships[idPlayer] = new ArrayList<>(4);

        HashMap<Integer, Integer> vie = getVieBattleships();

        for (int i = 2; i <= 6; i++) {
            if (i == 6) {
                placeARandomBoat(idPlayer, i / 2, vie.get(i / 2));
            } else {
                placeARandomBoat(idPlayer, i, vie.get(i));
            }
        }

    }

    private void placeARandomBoat(int idPlayer, int taille, int pv) {

        Random random = new Random();

        boolean v = random.nextBoolean();
        int x = random.nextInt(Map.NBCASES - taille);
        int y = random.nextInt(Map.NBCASES - taille);

        while (isThereAShipOnTheWay(idPlayer, x, y, taille, v)) {
            v = random.nextBoolean();
            x = random.nextInt(Map.NBCASES - taille);
            y = random.nextInt(Map.NBCASES - taille);
        }


        addShip(idPlayer, taille, pv, v, new Position(x, y));

    }

    private void addShip(int idPlayer, int length, int hp, boolean v, Position p) {

        getBattleships(idPlayer).add(
                new Battleship(p, hp, length, v, maps[idPlayer]));
    }

    /**
     * Retourne l'ArrayList de Bateaux du joueur idPlayer.
     *
     * @param idPlayer Id du joueur dont on veux les bateaux.
     * @return ArrayList<Battleship> La liste des bateaux du joueur.
     */
    public ArrayList<Battleship> getBattleships(int idPlayer) {

        return battleships[idPlayer];
    }

    /**
     * Fonction il y a t-il un bateau sur le chemin sur la carte du joueur d'ID idPlayer
     * en position x, y avec une longeur length de maniere verticale ou horizontale
     *
     * @param idPlayer int L'identifiiant du joueur.
     * @param x        int Position horizontale à vérifier.
     * @param y        int Position verticale à vérifier.
     * @param length   Longueur du chemin.
     * @param vertical Boolean pour savoir si le chemin est en position verticale ou horizontale.
     * @return boolean Vrai si un bateau se trouve dans la zone vérifiée, faux sinon.
     */
    private boolean isThereAShipOnTheWay(int idPlayer, int x, int y, int length, boolean vertical) {

        for (int i = 0; i < length; i++) {
            if (vertical) {
                if (!(y + i >= 10)) {
                    if (maps[idPlayer].at(x, y + i) != null) {
                        if (maps[idPlayer].at(x, y + i).toString().equals(Case.BATEAU)) {
                            return true;
                        }
                    }
                }
            } else {
                if (!(x + i >= 10)) {
                    if (maps[idPlayer].at(x + i, y) != null) {
                        if (maps[idPlayer].at(x + i, y).toString().equals(Case.BATEAU)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Retourne le bateau présent a la position x,y du joueur id Joueur
     *
     * @param idJoueur int L'identifiant du joueur.
     * @param x        int La position horizontale du bateau.
     * @param y        int La position verticale du bateau.
     * @return Battleship Le bateau ou null s'il n'y en a pas.
     */
    public Battleship getBattleshipAt(int idJoueur, int x, int y) {

        for (Battleship b : getBattleships(idJoueur)) {
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

    public Case getCaseAt(int idJoueur, int x, int y) {

        return maps[idJoueur].at(x, y);
    }

    @Override
    public String toString() {

        return "\t Epoque " + Arrays.toString(maps) + '\n';
    }

    private void battleshipsVoidInit() {

        battleships[0] = new ArrayList<>();
        battleships[1] = new ArrayList<>();
    }

    public boolean hasLost(int idJoueur) {

        for (Battleship b : battleships[idJoueur]) {

            if (b.isAlive()) {
                return false;
            }
        }

        return true;
    }

    protected abstract HashMap<Integer, Integer> getVieBattleships();

}
