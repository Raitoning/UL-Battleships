package assets.scripts.map;

import assets.scripts.Model;
import assets.scripts.player.Human;
import engine.Engine;
import engine.Game;
import engine.gameobject.GameObject;
import engine.gameobject.component.GraphicRaycaster;
import engine.gameobject.component.SpriteRenderer;

public abstract class Case extends GameObject{

    public static String VIDE = "[ ]";
    public static String BATEAU = "[B]";

    protected boolean estToucher;

    protected SpriteRenderer spriteRenderer;
    protected Model model;

    protected int posX;
    protected int posY;
    protected Position p;

    public Case (Position pos,int x, int y, Model m, int gameID) {

        p = pos;

        model = m;
        estToucher = false;
        this.gameID = gameID;

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

        if(model.getTypeofPlayer(model.getPlayerTurn()).equals(Human.name) && num == 1 && !estToucher && gameID == Game.getGameID()){
            model.getPlayer(model.getPlayerTurn()).play(this);
        }
    }

    public abstract String nomSprite();

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public Position getP() {
        return p;
    }

    public void loadToucher(){
        estToucher = true;
        spriteRenderer.setName(nomSprite());
    }

    public void subitTir(){
        estToucher = true;
        spriteRenderer.setName(nomSprite());
    }

    public boolean estToucher(){
        return estToucher;
    }

    protected String getSpriteFolder(){
        return model.getNameEpoque();
    }

}
