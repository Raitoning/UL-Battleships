package assets.scripts.player;

import assets.scripts.Model;
import assets.scripts.map.Case;
import assets.scripts.map.Map;
import engine.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class IA extends Player{

    protected ArrayList<Case> ar;
    protected ArrayList<Case> priority;

    public IA(int idJoueur, Model m, int gameID){
        super(idJoueur,m,gameID);

        ar = new ArrayList<>();
        priority = new ArrayList<>();

        transform.position().setX(15.5f);
        transform.position().setY(4.5f);

        camera.setMinRenderArea(new Vector2(0.5f, 0f));

    }

    public abstract Case jeuxIA();


    protected void addVoisins(Case s){
        int x = s.getPosX();
        int y = s.getPosY();

        if(x>0){
            if (!model.getEpoque().getCaseAt(opponentID(),x-1,y).estToucher())
                priority.add(model.getEpoque().getCaseAt(opponentID(),x-1,y));
        }

        if(x< Map.NBCASES-1){
            if (!model.getEpoque().getCaseAt(opponentID(),x+1,y).estToucher())
                priority.add(model.getEpoque().getCaseAt(opponentID(),x+1,y));
        }

        if(y>0){
            if (!model.getEpoque().getCaseAt(opponentID(),x,y-1).estToucher())
                priority.add(model.getEpoque().getCaseAt(opponentID(),x,y-1));
        }

        if(y<Map.NBCASES-1){
            if (!model.getEpoque().getCaseAt(opponentID(),x,y+1).estToucher())
                priority.add(model.getEpoque().getCaseAt(opponentID(),x,y+1));
        }
    }

    public void clean(){
        ArrayList<Case> tmp = new ArrayList<>();

        for(Case b: ar){
            if (b.estToucher()){
                tmp.add(b);
            }
        }

        for(Case b: priority){
            if (b.estToucher()){
                tmp.add(b);
            }
        }

        for (Case b: tmp){
            ar.remove(b);
            priority.remove(b);
        }
    }

    protected void extreme(){
        for(int i =0; i < Map.NBCASES;i++){
            for (int j =0;j < Map.NBCASES;j++){
                if(!model.getEpoque().getCaseAt(opponentID(),i,j).estToucher())
                    ar.add(model.getEpoque().getCaseAt(opponentID(),i,j));
            }
        }
    }
}
