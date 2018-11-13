package battleship.map;

import battleship.epoque.Battleship;

import java.util.Arrays;

public class Map {

    Case cases[][];

    public static final int NBCASES = 10;


    /**
     * Constructeur de la Map par défaut, initialise les cases à vide
     */
    public Map() {
        casesEmptyInit();
    }


    /**
     * Ajoute un Bateau a la carte, met a jour les cases.
     * @param b
     */
    public void add(Battleship b){

        for (int i=0; i<b.getLength();i++){
            if (b.isVertical()){
                updateAt(Case.Bateau, b.getPosition().getX(), b.getPosition().getY()+i);
            }else {
                updateAt(Case.Bateau, b.getPosition().getX()+i, b.getPosition().getY());
            }
        }

    }


    /**
     * Methode privée pour intialiser le tableau des cases, puis initialiser les cases a vide
     */
    private void casesEmptyInit(){
        this.cases = new Case[NBCASES][NBCASES];
        for(int i=0; i<cases.length;i++){

            for (int j=0;j<cases[0].length;j++){

                cases[i][j]=Case.Vide;
            }
        }
    }


    /**
     * Retourne la case présente en x, y
     * @param x
     * @param y
     * @return une Case
     */
    public Case at(int x,int y){
        return cases[x][y];
    }

    /**
     * Met a jour la case en x, y par la case en parametere
     * @param c
     * @param x
     * @param y
     */
    public void updateAt(Case c, int x, int y){
        cases[x][y] = c;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n\tMap: \n");

        for(int i=0; i<cases.length;i++){

            for (int j=0;j<cases[0].length;j++){
                sb.append(cases[i][j].toString());
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
