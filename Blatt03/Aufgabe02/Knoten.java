import java.util.ArrayList;

public class Knoten {

    private static int anzahlKnoten;

    private Spielzustand spielzustand;

    private int letztesSymbol;

    private Knoten vorgaenger;

    private ArrayList<Knoten> nachfolger = new ArrayList<>();

    public Knoten(Spielzustand spielzustand, int letztesSymbol){
        this.spielzustand = spielzustand;
        this.letztesSymbol = letztesSymbol;
        anzahlKnoten++;
    }

    public void fuegeNachfolgerHinzu(Knoten n){
        nachfolger.add(n);
    }

    public void setzeVorgaenger(Knoten v){
        this.vorgaenger = v;
    }

    public Spielzustand getSpielzustand(){
        return spielzustand;
    }

    public int getLetztesSymbol(){
        return letztesSymbol;
    }

    public int getNachfolgerAnzahl(){
        return nachfolger.size();
    }

    public Knoten getNachfolgeKnoten(int index){
        return nachfolger.get(index);
    }

    public static int getAnzahlKnoten(){
        return anzahlKnoten;
    }

}
