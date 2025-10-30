import java.util.ArrayList;

public class Baum {

    private Knoten startknoten;
    public Baum(Spielzustand startzustand){
        generiereBaum(startzustand);
    }

    private void generiereBaum(Spielzustand startzustand){
        startknoten = new Knoten(startzustand, 0);
        generiereNachfolger(startknoten, 1);
    }


    private ArrayList<Spielzustand> erzeugeAlleMoeglicheNeuenZustaende(Spielzustand aktuellerZustand, int symbol){
        ArrayList<Spielzustand> zustaende = new ArrayList<>();
        boolean gewonnen = aktuellerZustand.gewonnenesSpiel();
        if(gewonnen){
            return zustaende;//leere Liste, da Spiel vorbei. Wir haben ein Blatt erreicht.
        }

        for(int i = 0; i < aktuellerZustand.getLaengeSpielfeld(); i++){
            //erzeugt fuer jedes leere Feld einen neuen Zustand (als Kopie) und setzt an diese Stelle ein Kreuz oder Kreis
            //fuer ein volles Brett wird kein neuer Zustand erzeugt; wir sind beim Baum unten angelangt, keine neuen Knoten werden erzeugt
            if(aktuellerZustand.holeSymbol(i) == 0){
                Spielzustand naechsterZustand = aktuellerZustand.copy(aktuellerZustand);
                naechsterZustand.setzeSymbol(i, symbol);
                zustaende.add(naechsterZustand);
            }
        }
        return zustaende;
    }


    public void generiereNachfolger(Knoten aktuellerKnoten, int symbol){

        //Symbol bleibt fuer gesamte Ebene gleich, wechselt pro Ebene
        ArrayList<Spielzustand> zustaende = erzeugeAlleMoeglicheNeuenZustaende(aktuellerKnoten.getSpielzustand(), symbol);

        int naechstesSymbol = 0;
        if(symbol == 1){
            naechstesSymbol = 2;
        } else if(symbol == 2){
            naechstesSymbol = 1;
        }

        for(int i = 0; i < zustaende.size(); i++){
            Knoten nachfolger = new Knoten(zustaende.get(i), symbol);
            aktuellerKnoten.fuegeNachfolgerHinzu(nachfolger);
            nachfolger.setzeVorgaenger(aktuellerKnoten);
            generiereNachfolger(nachfolger, naechstesSymbol);
        }
    }

    public Knoten getStartKnoten(){
        return startknoten;
    }
}
