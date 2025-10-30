public class Minimax {

    private int knotenZaehler = 0;
    private int knotenZaehlerPruning = 0;
    //in utility bin ich im Blatt
    private int utility(Knoten kn){

        boolean gewonnen = kn.getSpielzustand().gewonnenesSpiel();

        if(!gewonnen){
            return 0;//unentschieden
        }
        int gewinner = kn.getLetztesSymbol(); //1=Kreuz(MAX=1), 2=Kreis gewonnen(MIN=-1)

        if(gewinner == 1){
            return 1; //MAX gewinnt
        }
        return -1;//MIN gewinnt
    }

    private boolean terminalTest(Knoten kn){
        //Spielbrett voll
        boolean voll = kn.getSpielzustand().istSpielfeldVoll();
        //oder gewinner
        boolean gewonnen = kn.getSpielzustand().gewonnenesSpiel();

        if(voll || gewonnen){
            return true;
        }
        return false;
    }

    public int maxValue(Knoten kn){
        knotenZaehler++;
        if (terminalTest(kn)) {
            return utility(kn);
        }

        int v = Integer.MIN_VALUE;

        for(int i = 0; i < kn.getNachfolgerAnzahl(); i++){
            Knoten k = kn.getNachfolgeKnoten(i);
            v = Math.max(v, minValue(k));
        }
        return v;
    }

    public int minValue(Knoten kn){
        knotenZaehler++;
        if (terminalTest(kn)) {
            return utility(kn);
        }

        int v = Integer.MAX_VALUE;

        for(int i = 0; i < kn.getNachfolgerAnzahl(); i++){
            Knoten k = kn.getNachfolgeKnoten(i);
            v = Math.min(v, maxValue(k));
        }
        return v;
    }

    public int getKnotenZaehler(){
        return knotenZaehler;
    }


    public int maxValueMitPruning(Knoten kn, int alpha, int beta){
        knotenZaehlerPruning++;
        if (terminalTest(kn)) {
            return utility(kn);
        }

        int v = Integer.MIN_VALUE;

        for(int i = 0; i < kn.getNachfolgerAnzahl(); i++){
            Knoten k = kn.getNachfolgeKnoten(i);
            v = Math.max(v, minValueMitPruning(k, alpha, beta));
            if(v >= beta){
                return v;
            }
            alpha = Math.max(alpha, v);
        }
        return v;
    }

    public int minValueMitPruning(Knoten kn, int alpha, int beta){
        knotenZaehlerPruning++;
        if (terminalTest(kn)) {
            return utility(kn);
        }

        int v = Integer.MAX_VALUE;

        for(int i = 0; i < kn.getNachfolgerAnzahl(); i++){
            Knoten k = kn.getNachfolgeKnoten(i);
            v = Math.min(v, maxValueMitPruning(k, alpha, beta));

            if(v <= alpha){
                return v;
            }
            beta = Math.min(beta, v);
        }
        return v;
    }

    public int getKnotenZaehlerPruning(){
        return knotenZaehlerPruning;
    }

}
