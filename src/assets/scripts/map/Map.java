package assets.scripts.map;

import assets.scripts.Game;
import assets.scripts.epoque.Battleship;

public class Map {

    private Case cases[][];
    private int idJoueur;
    private Game model;

    public static final int NBCASES = 10;

    /**
     * Constructeur de la Map par défaut, initialise les cases à vide
     */
    public Map(int idJoueur,Game m) {

        this.cases = new Case[NBCASES][NBCASES];


        model = m;
        this.idJoueur = idJoueur;

        //casesEmptyInit();
    }


    /**
     * Ajoute un Bateau a la carte, met a jour les cases.
     * @param b Le bateau à ajouter.
     */
    public void add(Battleship b){

        for (int i = 0; i < b.getLength(); i++) {

            if (b.isVertical()) {

                updateAt(new Bateau((NBCASES + 1) * idJoueur + b.getPosition().getX(), b.getPosition().getY() + i, model), b.getPosition().getX(), b.getPosition().getY() + i);
            } else {

                updateAt(new Bateau((NBCASES + 1) * idJoueur +b.getPosition().getX() + i, b.getPosition().getY(), model), b.getPosition().getX() + i, b.getPosition().getY());
            }
        }
    }


    /**
     * Methode privée pour intialiser le tableau des cases, puis initialiser les cases a vide
     */
    public void casesEmptyInit(){


        for(int i = 0; i < cases.length; i++) {

            for (int j = 0;j < cases[0].length; j++) {

                if (cases[i][j]==null)
                cases[i][j]=new CaseVide(((NBCASES + 1) * idJoueur) + i, j,model);
            }
        }
    }


    /**
     * Retourne la case présente en x, y
     * @param x La position horizontale de la case.
     * @param y La position verticale de la case.
     * @return La case aux coordonnées spécifiées.
     */
    public Case at(int x,int y){
        return cases[x][y];
    }

    /**
     * Met a jour la case en x, y par la case en parametere
     * @param c La valeur de la nouvelle case.
     * @param x La position horizontale de la case.
     * @param y La position verticale de la case.
     */
    public void updateAt(Case c, int x, int y){
        cases[x][y] = c;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("\n\tMap: \n");

        for(int i = 0; i < cases.length; i++) {

            for (int j = 0;j < cases[0].length; j++) {

                sb.append(cases[j][i].toString());
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
