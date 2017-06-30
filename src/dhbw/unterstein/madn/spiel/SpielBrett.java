package dhbw.unterstein.madn.spiel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpielBrett {

  private List<Feld> spielFeld;
  private Map<Spieler, List<Feld>> initialFeld;
  private Map<Spieler, List<Feld>> endFeld;

  private Map<Spieler, Feld> startFeld;

  public SpielBrett(List<Spieler> alleSpieler) {
    alleSpieler = new ArrayList<>();
    for (int i = 0; i < 40; i++) {
      spielFeld.add(new Feld(FeldArt.SPIEL));
    }
    for (Spieler spieler : alleSpieler) {
      List<Feld> initialFeld = new ArrayList<>();
      List<Feld> endFeld = new ArrayList<>();
      for (int x = 0; x < 4; x++) {
        initialFeld.add(new Feld(FeldArt.INITIAL));
        endFeld.add(new Feld(FeldArt.INITIAL));
      }
      for (Figur figur : spieler.getFiguren()) {
        if (figur.getAktuellesFeld() != null) {

        }
      }
    }
  }

  public void anzeigen() {

  }

  public void bewegen(Figur figur, int schritte) {

  }

  public List<Feld> getSpielFeld() {
    return spielFeld;
  }

  public List<Feld> getInitialFeld(Spieler spieler) {
    return initialFeld.get(spieler);
  }

  public List<Feld> getEndFeld(Spieler spieler) {
    return endFeld.get(spieler);
  }
}
