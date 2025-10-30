public class Spielzustand {

    private int[] spielfeld = new int[9];

    public int getLaengeSpielfeld(){
        return spielfeld.length;
    }


    public void setzeSymbol(int position, int symbol){
        spielfeld[position] = symbol;
    }


    public int holeSymbol(int position){
        int symbol = spielfeld[position];
        return symbol;
    }

    public boolean gewonnenesSpiel(){

        //waagerecht ab Positionen 0,3,6
        for(int i = 0; i < getLaengeSpielfeld(); i += 3){
            int wert1 = holeSymbol(i);
            int wert2 = holeSymbol(i+1);
            int wert3 = holeSymbol(i+2);
            int ergebnis = wert1+wert2+wert3;

            if(!selbeWerte(wert1, wert2, wert3)) {
                continue;
            }
            if(ergebnis == 3 || ergebnis == 6){
                return true;
            }
        }
        //senkrecht ab Positionen 0,1,2
        for(int i = 0; i < 3; i ++){
            int wert1 = holeSymbol(i);
            int wert2 = holeSymbol(i+3);
            int wert3 = holeSymbol(i+6);
            int ergebnis = wert1+wert2+wert3;

            if(!selbeWerte(wert1, wert2, wert3)) {
                continue;
            }
            if (ergebnis == 3 || ergebnis == 6) {
                return true;
            }
        }

        //diagonale 1
        if(selbeWerte(holeSymbol(0), holeSymbol(4), holeSymbol(8))){
            int diagonale1 = holeSymbol(0)+holeSymbol(4)+holeSymbol(8);
            if (diagonale1 == 3 || diagonale1 == 6) {
                return true;
            }
        }
        //diagonale 2
        if(selbeWerte(holeSymbol(2), holeSymbol(4), holeSymbol(6))){
            int diagonale2 = holeSymbol(2)+holeSymbol(4)+holeSymbol(6);
            if (diagonale2 == 3 || diagonale2 == 6) {
                return true;
            }
        }
        return false;
    }

    private boolean selbeWerte(int wert1, int wert2, int wert3){
        if(wert1 == wert2 && wert2 == wert3){
            return true;
        }
        return false;
    }

    public boolean istSpielfeldVoll(){

        for(int i = 0; i < spielfeld.length; i++){
            if(spielfeld[i] == 0){
                return false;
            }
        }
        return true;
    }

    public Spielzustand copy(Spielzustand zuKopierenderZustand){

        Spielzustand kopie = new Spielzustand();
        //nur das Array kopieren
        for(int i = 0 ; i < zuKopierenderZustand.getLaengeSpielfeld(); i++){
            //hole das Symbol von zuKopierend
            int s = zuKopierenderZustand.holeSymbol(i);
            //setze das Symbol in Kopie
            kopie.setzeSymbol(i, s);
        }
        //neuen Zustand returnen
        return kopie;
    }

}
