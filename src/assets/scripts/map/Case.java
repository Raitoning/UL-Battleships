package assets.scripts.map;

import assets.scripts.Game;
import engine.gameobject.GameObject;

public abstract class Case extends GameObject{

    public static String VIDE = "[ ]";
    public static String BATEAU = "[B]";
    public static String TIRRATE = "[o]";
    public static String TIRSURBATEAU = "[x]";
    public static String BATEAUDETRUIT = "[^]";

    public static Case fromString(String s){

        if (s.equals(VIDE)){
            return new CaseVide();
        } else if(s.equals(BATEAU)){
            return new Bateau();
        }else if (s.equals(TIRRATE)){
            return new TirRate();
        } else if (s.equals(TIRSURBATEAU)){
            return new TirSurBateau();
        } else if (s.equals(BATEAUDETRUIT)){
            return new BateauDetruit();
        } else{
            return null;
        }
    }

    @Override
    public void update(){
        System.out.println("55555");
    }

}
