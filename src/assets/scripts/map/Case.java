package assets.scripts.map;

import assets.scripts.Model;
import assets.scripts.player.Human;
import engine.Game;
import engine.gameobject.GameObject;
import engine.gameobject.component.GraphicRaycaster;
import engine.gameobject.component.SpriteRenderer;

public abstract class Case extends GameObject {

    public static String BATEAU = "[B]";
    static String VIDE = "[ ]";
    protected Model model;
    protected Position p;
    SpriteRenderer spriteRenderer;
    private boolean estToucher;
    private int posX;
    private int posY;

    public Case(Position pos, int x, int y, Model model, int gameID) {

        p = pos;

        this.model = model;
        this.gameID = gameID;

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
    public void onRaycast(int num) {

        if (model.getTypeofPlayer(model.getPlayerTurn()).equals(Human.name) && num == 1 && !estToucher && gameID == Game.getGameID()) {
            model.getPlayer(model.getPlayerTurn()).play(this);
        }
    }

    public abstract String nomSprite();

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Position getP() {
        return p;
    }

    public void loadToucher() {
        estToucher = true;
        spriteRenderer.setName(nomSprite());
    }

    public void subitTir() {
        estToucher = true;
        spriteRenderer.setName(nomSprite());
    }

    public boolean estToucher() {
        return estToucher;
    }

    String getSpriteFolder() {
        return model.getNameEpoque();
    }

}
