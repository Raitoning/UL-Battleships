package assets.scripts.map;

import assets.scripts.Game;
import engine.gameobject.GameObject;
import engine.gameobject.component.GraphicRaycaster;
import engine.gameobject.component.SpriteRenderer;

public abstract class Case extends GameObject{

    public static String VIDE = "[ ]";
    public static String BATEAU = "[B]";
    public static String TIRRATE = "[o]";
    public static String TIRSURBATEAU = "[x]";
    public static String BATEAUDETRUIT = "[^]";

    protected SpriteRenderer spriteRenderer;
    protected Game model;

    protected int posX;
    protected int posY;

    public Case (int x, int y, Game m) {

        model = m;

        posX = x;
        posY = y;

        transform.position().setX(x);
        transform.position().setY(y);
        transform.position().setZ(1f);

        spriteRenderer = new SpriteRenderer(nomSprite(), this);

        addComponent(new GraphicRaycaster(this));

    }

    @Override
    public void onRaycast(int num){
        if(model.getTypeofPlayer(model.getPlayerTurn()).equals("Human") && num == 1){
            model.getPlayer(model.getPlayerTurn()).play(this);
        }
    }

    public static Case fromString(String s, int x, int y, Game m){

        if (s.equals(VIDE)){
            return new CaseVide(x,y, m);
        } else if(s.equals(BATEAU)){
            return new Bateau(x,y,m);
        }else if (s.equals(TIRRATE)){
            return new TirRate(x,y,m);
        } else if (s.equals(TIRSURBATEAU)){
            return new TirSurBateau(x,y,m);
        } else if (s.equals(BATEAUDETRUIT)){
            return new BateauDetruit(x,y,m);
        } else{
            return null;
        }
    }

    public abstract String nomSprite();

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public int matriceX(){
        if(getPosX() > 10)
            return getPosX()-11;
        else return getPosX();
    }

    public int matriceY(){
        return getPosY();
    }

}
