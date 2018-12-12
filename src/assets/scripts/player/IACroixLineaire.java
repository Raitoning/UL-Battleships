package assets.scripts.player;

import assets.scripts.Game;
import assets.scripts.epoque.Battleship;
import assets.scripts.epoque.Epoque;
import assets.scripts.map.Bateau;
import assets.scripts.map.Case;
import assets.scripts.map.Map;
import assets.scripts.map.Position;
import engine.Vector2;

import java.util.ArrayList;

public class IACroixLineaire extends Player implements IA{

    private ArrayList<Case> ar;
    private ArrayList<Case> priority;

    public IACroixLineaire(int idJoueur, Game m) {

        super(idJoueur, m);

        ar =new ArrayList<>();
        priority =new ArrayList<>();

        transform.position().setX(15.5f);
        transform.position().setY(4.5f);

        camera.setMinRenderArea(new Vector2(0.5f, 0f));

        for(int i =0; i < Map.NBCASES;i++){
            for (int j =0;j < Map.NBCASES;j++){
                if((i%2==0&&j%2==1)||(i%2==1&&j%2==0))
                    ar.add(model.getEpoque().getCaseAt(opponentID(),i,j));
            }
        }
        System.out.println(ar.size());
    }

    @Override
    public void play(Case c) {
        super.play(c);
    }

    @Override
    public String toString() {

        return "IACroix";
    }

    @Override
    public Case jeuxIA() {
        int x; Case res;

        clean();

        if(!priority.isEmpty()){
            x= (int)(Math.random()*(priority.size()));
            res = priority.get(x);
            priority.remove(x);

        }else {
            res = ar.get(ar.size()-1);
        }

        if (ar.contains(res))
            ar.remove(res);

        if (ar.isEmpty()){
            extreme();
        }

        if (res.toString().equals(Case.BATEAU)){
            addVoisins(res);
        }

        return res;
    }

    private void addVoisins(Case s){
        int x = s.getPosX();
        int y = s.getPosY();

        if(x>0){
            if (!model.getEpoque().getCaseAt(opponentID(),x-1,y).estToucher())
                priority.add(model.getEpoque().getCaseAt(opponentID(),x-1,y));
        }

        if(x<Map.NBCASES-1){
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

    private void clean(){
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

    private void extreme(){
        for(int i =0; i < Map.NBCASES;i++){
            for (int j =0;j < Map.NBCASES;j++){
                if(!model.getEpoque().getCaseAt(opponentID(),i,j).estToucher())
                    ar.add(model.getEpoque().getCaseAt(opponentID(),i,j));
            }
        }
    }
}
