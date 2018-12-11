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

    protected boolean estToucher;

    protected SpriteRenderer spriteRenderer;
    protected Game model;

    protected int posX;
    protected int posY;

    public Case (int x, int y, Game m) {

        model = m;
        estToucher = false;

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
        if(model.getTypeofPlayer(model.getPlayerTurn()).equals("Human") && num == 1 && !estToucher){
            model.getPlayer(model.getPlayerTurn()).play(this);
        }
    }

    public static Case fromString(String s, int x, int y, Game m){
        return null;
    }

    public abstract String nomSprite();

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public void subitTir(){
        estToucher = true;
        spriteRenderer.setName(nomSprite());
    }

    public boolean estToucher(){
        return estToucher;
    }

}
