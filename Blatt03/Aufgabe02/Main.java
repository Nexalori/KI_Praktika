//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Spielzustand start = new Spielzustand();
        Baum baumbart = new Baum(start);

        Knoten startKnoten = baumbart.getStartKnoten();
        Minimax max = new Minimax();
        Minimax pruning = new Minimax();
        int knotenAnzahl = Knoten.getAnzahlKnoten();

        int ergebnis = max.maxValue(startKnoten);
        int zaehler = max.getKnotenZaehler();

        int ergebnisPruning = pruning.maxValueMitPruning(startKnoten, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int zaehlerPruning = pruning.getKnotenZaehlerPruning();


        System.out.print("Anzahl Knoten im Baum: " + knotenAnzahl);
        System.out.println();
        System.out.print("Ergebnis Max:" + ergebnis + ", besuchte Knoten: " + zaehler);

        System.out.println();
        System.out.print("Ergebnis Pruning:" + ergebnisPruning + ", besuchte Knoten: " + zaehlerPruning);
    }
}