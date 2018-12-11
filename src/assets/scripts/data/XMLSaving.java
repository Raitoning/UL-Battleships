package assets.scripts.data;

import assets.scripts.Game;
import assets.scripts.epoque.Battleship;
import assets.scripts.map.Case;
import assets.scripts.map.Map;
import assets.scripts.map.Position;

import java.io.*;
import java.util.Scanner;

public class XMLSaving extends GameSaverFactory {

    XMLSaving() {

    }

    @Override
    public void load(Game game) {

        try {
            loadFile(new File("random.xml"),game);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(Game game) {
        try {
            saveFile(new File("random.xml"),game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**Fonction de lectrure de fichier
     *
     * @param file fichier à lire
     * @throws IOException erreure de lecture fichier
     */
    public void loadFile(File file,Game game) throws IOException {
        FileReader flot ;
        BufferedReader flotFiltre ;
        if (file.exists()){
            flot = new FileReader(file) ;
            flotFiltre = new BufferedReader(flot);
            Scanner sc = new Scanner(flotFiltre);
            int i =0;
            while (sc.hasNextLine()){
                ++i;
                String line = sc.nextLine();
                if(i==1){
                    if (!line.equals("<game>"))
                        System.out.println("No game found");
                } else if (i==2){
                    if (!line.equals("\t<score>"))
                        System.out.println("No score found");
                }else if (i<5){
                    String tmp;
                    tmp = line.split("'")[1];
                    int p = Integer.parseInt(""+tmp.charAt(0));

                    tmp = line.split(">")[1];
                    tmp = tmp.split("<")[0];
                    game.setScore(p,Integer.parseInt(tmp));
                }else if (i==5) {
                    if (!line.equals("\t</score>"))
                        System.out.println("No score closing found");
                }else if (i==6){
                    if (!line.equals("\t<type>"))
                        System.out.println("No player type found");
                } else if (i<9){
                    String tmp;
                    tmp = line.split("'")[1];
                    int p = Integer.parseInt(""+tmp.charAt(0));

                    tmp = line.split(">")[1];
                    tmp = tmp.split("<")[0];
                    game.setTypeofPlayer(p,tmp);

                } else if (i==9){
                    if (!line.equals("\t</type>"))
                    System.out.println("No player type closing found");
                }  else if (i==10) {
                    if (!line.equals("\t<epoque>"))
                        System.out.println("No epoque found");
                } else if (i==11) {
                    String tmp;
                    tmp = line.split(">")[1];
                    tmp = tmp.split("<")[0];

                    game.setEpoque(tmp,false);
                } else if (i==12) {
                    if (!line.equals("\t\t<map>"))
                        System.out.println("No map found");
                } else if (i==13) {
                    if (!line.equals("\t\t\t<joueur id='0'>"))
                        System.out.println("No player id=0 found");
                } else if (i<114) {
                    String tmp;
                    tmp = line.split("'")[1];
                    int x = Integer.parseInt(""+tmp.charAt(0));
                    tmp = line.split("'")[3];
                    int y = Integer.parseInt(""+tmp.charAt(0));


                    tmp = line.split(">")[1];
                    tmp = tmp.split("<")[0];
                    game.getEpoque().updateCaseAt(0,x,y,Case.fromString(tmp,x,y,game));
                } else if (i==114) {
                    if (!line.equals("\t\t\t</joueur>"))
                        System.out.println("No player type closing found");
                } else if (i==115) {
                    if (!line.equals("\t\t\t<joueur id='1'>"))
                        System.out.println("No player id=1 found");
                } else if (i<216) {
                    String tmp;
                    tmp = line.split("'")[1];
                    int x = Integer.parseInt(""+tmp.charAt(0));
                    tmp = line.split("'")[3];
                    int y = Integer.parseInt(""+tmp.charAt(0));


                    tmp = line.split(">")[1];
                    tmp = tmp.split("<")[0];
                    game.getEpoque().updateCaseAt(1,x,y,Case.fromString(tmp,x,y,game));
                } else if (i==216) {
                    if (!line.equals("\t\t\t</joueur>"))
                        System.out.println("No player type closing found");
                } else if (i==217) {
                    if (!line.equals("\t\t</map>"))
                        System.out.println("No map closing found");
                } else if (i==218) {
                    if (!line.equals("\t\t<battleships>"))
                        System.out.println("No battleships found");
                } else if (i==219) {
                    if (!line.equals("\t\t\t<joueur id='0'>"))
                        System.out.println("No player id=0 found");
                } else if (i<224) {
                    String tmp;
                    tmp = line.split("'")[1];
                    int x = Integer.parseInt(""+tmp.charAt(0));
                    tmp = line.split("'")[3];
                    int y = Integer.parseInt(""+tmp.charAt(0));
                    tmp = line.split("'")[3];
                    int l = Integer.parseInt(""+tmp.charAt(0));
                    tmp = line.split("'")[3];
                    boolean v = Boolean.parseBoolean(tmp);
                    tmp = line.split("'")[3];
                    int h = Integer.parseInt(""+tmp.charAt(0));

                    game.getEpoque().addShip(0,
                            new Battleship(new Position(x,y),h,l,v,null));
                } else if (i==224) {
                    if (!line.equals("\t\t\t</joueur>"))
                        System.out.println("No player type closing found");
                } else if (i==225) {
                    if (!line.equals("\t\t\t<joueur id='1'>"))
                        System.out.println("No player id=1 found");
                } else if (i<230) {
                    String tmp;
                    tmp = line.split("'")[1];
                    int x = Integer.parseInt(""+tmp.charAt(0));
                    tmp = line.split("'")[3];
                    int y = Integer.parseInt(""+tmp.charAt(0));
                    tmp = line.split("'")[3];
                    int l = Integer.parseInt(""+tmp.charAt(0));
                    tmp = line.split("'")[3];
                    boolean v = Boolean.parseBoolean(tmp);
                    tmp = line.split("'")[3];
                    int h = Integer.parseInt(""+tmp.charAt(0));

                    game.getEpoque().addShip(1,
                        new Battleship(new Position(x,y),h,l,v,null));
                } else if (i==230) {
                    if (!line.equals("\t\t\t</joueur>"))
                        System.out.println("No player type closing found");
                } else if (i==231) {
                    if (!line.equals("\t\t</battleships>"))
                        System.out.println("No assets.battleship closing found");
                } else if (i==232) {
                    if (!line.equals("\t</epoque>"))
                        System.out.println("No assets.battleship closing found");
                } else if (i==233) {
                    if (!line.equals("</game>"))
                        System.out.println("No game closing found");
                }
            }
            sc.close();
        }

    }

    /**Fonction d'ecriture de fichier
     *
     * @param file fichier à ecrire
     * @throws IOException erreure de lecture/ecriture fichier
     */
    public void saveFile(File file,Game game) throws IOException{

        FileWriter flot ;
        PrintWriter flotFiltre ;
        flot = new FileWriter(file, false) ;
        flotFiltre = new PrintWriter(new BufferedWriter(flot)) ;

        flotFiltre.println("<game>");

        // ----------Scores-------------------
        flotFiltre.println("\t<score>");
        for (int i=0;i<2;i++) {
            flotFiltre.println("\t\t<joueur id='"+i+"'>"+game.getScore(i)+"</joueur>");

        }
        flotFiltre.println("\t</score>");
        // ----------Type de Joueur-------------------
        flotFiltre.println("\t<type>");
        for (int i=0;i<2;i++) {
            flotFiltre.println("\t\t<joueur id='"+i+"'>"+game.getTypeofPlayer(i)+"</joueur>");
        }
        flotFiltre.println("\t</type>");

        // ----------Epoque-------------------
        flotFiltre.println("\t<epoque>");
        flotFiltre.println("\t\t<name>"+game.getEpoque().name()+"</name>");

        // ----------Map-------------------
        flotFiltre.println("\t\t<map>");
        for (int i=0;i<2;i++) {

            flotFiltre.println("\t\t\t<joueur id='"+i+"'>");

            for(int k=0; k< Map.NBCASES;k++){
                for (int j=0;j<Map.NBCASES;j++){
                    flotFiltre.println("\t\t\t\t<case x='"+k+"' y='"+j+"'>"+game.getEpoque().getCaseAt(i,k,j)+"</case>");
                }
            }

            flotFiltre.println("\t\t\t</joueur>");
        }

        flotFiltre.println("\t\t</map>");


        // ----------Battleships-------------------

        flotFiltre.println("\t\t<battleships>");
        for (int i=0;i<2;i++) {

            flotFiltre.println("\t\t\t<joueur id='"+i+"'>");



            for (Battleship b :game.getEpoque().getBattleships(i)) {

                flotFiltre.println("\t\t\t\t</assets.battleship x='" + b.getPosition().getX()+
                            "' y='"+ b.getPosition().getY()+"' l='"+b.getLength()+"' v='" +
                            b.isVertical() + "' h='"+ b.getPv()+"'>");
            }



            flotFiltre.println("\t\t\t</joueur>");
        }

        flotFiltre.println("\t\t</battleships>");

        flotFiltre.println("\t</epoque>");

        flotFiltre.println("</game>");

        flotFiltre.close() ;
    }
}
