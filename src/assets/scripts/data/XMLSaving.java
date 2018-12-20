package assets.scripts.data;

import assets.scripts.Model;
import assets.scripts.epoque.Battleship;
import assets.scripts.map.Map;

import java.io.*;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLSaving extends GameSaverFactory {

    XMLSaving() {

    }

    @Override
    public void load(Model model) {

        try {
            loadFile(new File("random.xml"), model);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(Model model) {
        try {
            saveFile(new File("random.xml"), model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**Fonction de lectrure de fichier LEGACY
     *
     * @param file fichier à lire
     * @throws IOException erreure de lecture fichier
    public void loadFile(File file,Model model) throws IOException {
        FileReader flot ;
        boolean vc=true;

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
                    if (!line.equals("<model>"))
                        println(vc,"No model found");
                } else if (i==2){
                    if (!line.equals("\t<score>"))
                        println(vc,"No score found");
                }else if (i<5){
                    String tmp;
                    tmp = line.split("'")[1];
                    int p = Integer.parseInt(""+tmp.charAt(0));

                    tmp = line.split(">")[1];
                    tmp = tmp.split("<")[0];
                    model.setScore(p,Integer.parseInt(tmp));
                }else if (i==5) {
                    if (!line.equals("\t</score>"))
                        println(vc,"No score closing found");
                }else if (i==6){
                    if (!line.equals("\t<type>"))
                        println(vc,"No player type found");
                } else if (i<9){
                    String tmp;
                    tmp = line.split("'")[1];
                    int p = Integer.parseInt(""+tmp.charAt(0));

                    tmp = line.split(">")[1];
                    tmp = tmp.split("<")[0];
                    model.setTypeofPlayer(p,tmp);

                } else if (i==9){
                    if (!line.equals("\t</type>"))
                    println(vc,"No player type closing found");
                }  else if (i==10) {
                    if (!line.equals("\t<epoque>"))
                        println(vc,"No epoque found");
                } else if (i==11) {
                    String tmp;
                    tmp = line.split(">")[1];
                    tmp = tmp.split("<")[0];

                    model.setEpoque(tmp,false);
                } else if (i==12) {
                    if (!line.equals("\t\t<map>"))
                        println(vc,"No map found");
                } else if (i==13) {
                    if (!line.equals("\t\t\t<joueur id='0'>"))
                        println(vc,"No player id=0 found");
                } else if (i<114) {
                    String tmp;
                    tmp = line.split("'")[1];
                    int x = Integer.parseInt(""+tmp.charAt(0));
                    tmp = line.split("'")[3];
                    int y = Integer.parseInt(""+tmp.charAt(0));


                    tmp = line.split(">")[1];
                    tmp = tmp.split("<")[0];
                    model.getEpoque().updateCaseAt(0,x,y,Case.fromString(tmp,x,y, model));
                } else if (i==114) {
                    if (!line.equals("\t\t\t</joueur>"))
                        println(vc,"No player type closing found");
                } else if (i==115) {
                    if (!line.equals("\t\t\t<joueur id='1'>"))
                        println(vc,"No player id=1 found");
                } else if (i<216) {
                    String tmp;
                    tmp = line.split("'")[1];
                    int x = Integer.parseInt(""+tmp.charAt(0));
                    tmp = line.split("'")[3];
                    int y = Integer.parseInt(""+tmp.charAt(0));


                    tmp = line.split(">")[1];
                    tmp = tmp.split("<")[0];
                    model.getEpoque().updateCaseAt(1,x,y,Case.fromString(tmp,x,y, model));
                } else if (i==216) {
                    if (!line.equals("\t\t\t</joueur>"))
                        println(vc,"No player type closing found");
                } else if (i==217) {
                    if (!line.equals("\t\t</map>"))
                        println(vc,"No map closing found");
                } else if (i==218) {
                    if (!line.equals("\t\t<battleships>"))
                        println(vc,"No battleships found");
                } else if (i==219) {
                    if (!line.equals("\t\t\t<joueur id='0'>"))
                        println(vc,"No player id=0 found");
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

                    /*model.getEpoque().addShip(0,
                            new Battleship(new Position(x,y),h,l,v,null));*/
                } else if (i==224) {
                    if (!line.equals("\t\t\t</joueur>"))
                        println(vc,"No player type closing found");
                } else if (i==225) {
                    if (!line.equals("\t\t\t<joueur id='1'>"))
                        println(vc,"No player id=1 found");
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

                    /*model.getEpoque().addShip(1,
                        new Battleship(new Position(x,y),h,l,v,null));*/
                } else if (i==230) {
                    if (!line.equals("\t\t\t</joueur>"))
                        println(vc,"No player type closing found");
                } else if (i==231) {
                    if (!line.equals("\t\t</battleships>"))
                        println(vc,"No assets.battleship closing found");
                } else if (i==232) {
                    if (!line.equals("\t</epoque>"))
                        println(vc,"No assets.battleship closing found");
                } else if (i==233) {
                    if (!line.equals("</model>"))
                        println(vc,"No model closing found");
                }
            }
            sc.close();
        }

    }
    */

    /**Fonction d'ecriture de fichier
     *
     * @param file fichier à ecrire
     * @throws IOException erreure de lecture/ecriture fichier
     */
    public void saveFile(File file,Model model) throws IOException{

        FileWriter flot ;
        PrintWriter flotFiltre ;
        flot = new FileWriter(file, false) ;
        flotFiltre = new PrintWriter(new BufferedWriter(flot)) ;

        flotFiltre.println("<model>");

        // ----------Scores-------------------
        flotFiltre.println("\t<score>");
        for (int i=0;i<2;i++) {
            flotFiltre.println("\t\t<joueur id='"+i+"'>"+ model.getScore(i)+"</joueur>");

        }
        flotFiltre.println("\t</score>");
        // ----------Type de Joueur-------------------
        flotFiltre.println("\t<type>");
        for (int i=0;i<2;i++) {
            flotFiltre.println("\t\t<joueur id='"+i+"'>"+ model.getTypeofPlayer(i)+"</joueur>");
        }
        flotFiltre.println("\t</type>");

        // ----------Epoque-------------------
        flotFiltre.println("\t<epoque>");
        flotFiltre.println("\t\t<name>"+ model.getEpoque().name()+"</name>");

        // ----------Map-------------------
        flotFiltre.println("\t\t<map>");
        for (int i=0;i<2;i++) {

            flotFiltre.println("\t\t\t<joueur id='"+i+"'>");

            for(int k=0; k< Map.NBCASES;k++){
                for (int j=0;j<Map.NBCASES;j++){
                    flotFiltre.println("\t\t\t\t<case x='"+k+"' y='"+j+"'>"+ model.getEpoque().getCaseAt(i,k,j)+"</case>");
                }
            }

            flotFiltre.println("\t\t\t</joueur>");
        }

        flotFiltre.println("\t\t</map>");


        // ----------Battleships-------------------

        flotFiltre.println("\t\t<battleships>");
        for (int i=0;i<2;i++) {

            flotFiltre.println("\t\t\t<joueur id='"+i+"'>");



            for (Battleship b : model.getEpoque().getBattleships(i)) {

                flotFiltre.println("\t\t\t\t<battleship x='" + b.getPosition().getX()+
                            "' y='"+ b.getPosition().getY()+"' l='"+b.getLength()+"' v='" +
                            b.isVertical() + "' h='"+ b.getPv()+"'> </battleship>");
            }



            flotFiltre.println("\t\t\t</joueur>");
        }

        flotFiltre.println("\t\t</battleships>");

        flotFiltre.println("\t</epoque>");

        flotFiltre.println("</model>");

        flotFiltre.close() ;
    }


    public void loadFile(File file,Model model) throws IOException{

        // Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            boolean v = false; //Passer à true pour afficher les contenus du fichierXML
        try {
            // Etape 2 : création d'un parseur
            final DocumentBuilder builder = factory.newDocumentBuilder();

            //Etape 3 : création d'un Document
            final Document document = builder.parse(file);


            final Element racine = document.getDocumentElement();

            //Affichage de l'élément racine
            println(v,racine.getNodeName());

            if (!racine.getNodeName().equals("model")){
                println(v,"Erreur pas racine =/= model");
            }

	        // Etape 5 : récupération des personnes
            final NodeList racineNoeuds = racine.getChildNodes();
            final int nbRacineNoeuds = racineNoeuds.getLength();

            for (int i = 0; i<nbRacineNoeuds; i++) {
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element node = (Element) racineNoeuds.item(i);

                    final String name = node.getNodeName();
                    if(name.equals("score")){
                        println(v,"\n*************Score************");
                        final Element joueur = (Element) node.getElementsByTagName("joueur").item(0);
                        println(v,"score: "+joueur.getTextContent()+" ");
                        println(v,"id : " + joueur.getAttribute("id"));
                        final Element joueur2 = (Element) node.getElementsByTagName("joueur").item(1);
                        println(v,"score: "+joueur2.getTextContent()+" ");
                        println(v,"id : " + joueur2.getAttribute("id"));

                    }else if(name.equals("type")){
                        println(v,"\n*************Type Of Players************");
                        final Element joueur = (Element) node.getElementsByTagName("joueur").item(0);
                        println(v,"type: "+joueur.getTextContent()+" ");
                        println(v,"id : " + joueur.getAttribute("id"));
                        final Element joueur2 = (Element) node.getElementsByTagName("joueur").item(1);
                        println(v,"Type: "+joueur2.getTextContent()+" ");
                        println(v,"id : " + joueur2.getAttribute("id"));

                    } else if(name.equals("epoque")){
                        println(v,"\n*************Epoque************");
                        final Element ename = (Element) node.getElementsByTagName("name").item(0);
                        println(v,"type: "+ename.getTextContent());
                        println(v,"\n*************Map************");
                        final Element map = (Element) node.getElementsByTagName("map").item(0);

                        final Element joueur = (Element) map.getElementsByTagName("joueur").item(0);
                        println(v,"id : " + joueur.getAttribute("id"));
                        final NodeList casesj1 = joueur.getChildNodes();
                        final int nbCasesj1 = casesj1.getLength();
                        for (int j = 0; j<nbCasesj1; j++) {
                            if(casesj1.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                final Element case1 = (Element) casesj1.item(j);
                                println(v,"case: "+case1.getTextContent()+" x:"
                                        + case1.getAttribute("x")+" y:"
                                        + case1.getAttribute("y"));
                            }
                        }

                        final Element joueur2 = (Element) map.getElementsByTagName("joueur").item(1);
                        println(v,"id : " + joueur2.getAttribute("id"));
                        final NodeList casesj2 = joueur.getChildNodes();
                        final int nbCasesj2 = casesj2.getLength();
                        for (int j = 0; j<nbCasesj2; j++) {
                            if(casesj2.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                final Element case1 = (Element) casesj2.item(j);
                                println(v,"case: "+case1.getTextContent()+" x:"
                                        + case1.getAttribute("x")+" y:"
                                        + case1.getAttribute("y"));
                            }
                        }


                        println(v,"\n*************Battleships************");
                        final Element battleships = (Element) node.getElementsByTagName("battleships").item(0);
                        final Element player = (Element) battleships.getElementsByTagName("joueur").item(0);
                        println(v,"id : " + player.getAttribute("id"));
                        final NodeList ships1 = player.getChildNodes();
                        final int nbshipsj1 = ships1.getLength();
                        for (int j = 0; j<nbshipsj1; j++) {
                            if(ships1.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                final Element ship1 = (Element) ships1.item(j);
                                println(v,"case: "+ship1.getTextContent()
                                        +" x:" + ship1.getAttribute("x")
                                        +" y:" + ship1.getAttribute("y")
                                        +" l:" + ship1.getAttribute("l")
                                        +" v:" + ship1.getAttribute("v")
                                        +" h:" + ship1.getAttribute("h"));
                            }
                        }

                        final Element player2 = (Element) battleships.getElementsByTagName("joueur").item(1);
                        println(v,"id : " + player2.getAttribute("id"));
                        final NodeList ships2 = player2.getChildNodes();
                        final int nbshipsj2 = ships2.getLength();
                        for (int j = 0; j<nbshipsj2; j++) {
                            if(ships2.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                final Element ship2 = (Element) ships1.item(j);
                                println(v,"case: "+ship2.getTextContent()
                                        +" x:" + ship2.getAttribute("x")
                                        +" y:" + ship2.getAttribute("y")
                                        +" l:" + ship2.getAttribute("l")
                                        +" v:" + ship2.getAttribute("v")
                                        +" h:" + ship2.getAttribute("h"));
                            }
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void println(boolean v, String s){
        if (v)
            System.out.println(s);
    }

}
