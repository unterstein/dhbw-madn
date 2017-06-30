package dhbw.unterstein.madn.spiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpielBrett {

  private List<Feld> spielFeld;
  private Map<Spieler, List<Feld>> initialFeld;
  private Map<Spieler, List<Feld>> endFeld;

  private Map<Spieler, Feld> startFeld;

  public SpielBrett(List<Spieler> alleSpieler) {
    spielFeld = new ArrayList<>();
    initialFeld = new HashMap<>();
    endFeld = new HashMap<>();
    startFeld = new HashMap<>();

    for (int i = 0; i < 40; i++) {
      spielFeld.add(new Feld(FeldArt.SPIEL));
    }
    int counter = 0;
    for (Spieler spieler : alleSpieler) {
      List<Feld> initialFeld = new ArrayList<>();
      List<Feld> endFeld = new ArrayList<>();
      for (int x = 0; x < 4; x++) {
        initialFeld.add(new Feld(FeldArt.INITIAL));
        endFeld.add(new Feld(FeldArt.INITIAL));
      }
      this.initialFeld.put(spieler, initialFeld);
      this.endFeld.put(spieler, endFeld);
      for (int x = 0; x < 4; x++) {
        spieler.getFiguren().get(x).setAktuellesFeld(initialFeld.get(x));
      }
      startFeld.put(spieler, spielFeld.get(counter * 10));
    }
  }

  public void anzeigen() {

  }

  public void bewegen(Spieler spieler, Figur figur, int schritte) throws IllegalArgumentException {
    switch (figur.getAktuellesFeld().getArt()) {
      case END: {
        int aktuellePosition = endFeld.get(spieler).indexOf(figur);
        if (aktuellePosition + schritte < 4) {
          throw new IllegalArgumentException("Zu weit!");
        } else {
          Feld zielFeld = endFeld.get(spieler).get(aktuellePosition + schritte);
          if (zielFeld.getFigur() == null) {
            throw new IllegalArgumentException("Besetzt!");
          } else {
            figur.getAktuellesFeld().setFigur(null);
            figur.setAktuellesFeld(zielFeld);
          }
        }
      }
      case SPIEL: {
        int aktuellePosition = spielFeld.indexOf(figur);
        Feld zielFeld = spielFeld.get((aktuellePosition + schritte) % 40);
        bewegeFigur(spieler, figur, zielFeld);
      }
      case INITIAL:
        if (schritte != 6) {
          throw new IllegalArgumentException("Keine 6 :-/");
        }
        bewegeFigur(spieler, figur, startFeld.get(spieler));
    }
  }

  private void bewegeFigur(Spieler spieler, Figur figur, Feld zielFeld) {
    if (zielFeld.getFigur() != null) {
      if (spieler.getFiguren().contains(zielFeld.getFigur())) {
        throw new IllegalArgumentException("Du kannst dich nicht selber raus schmeißen!");
      } else {
        Figur alteFigur = zielFeld.getFigur();
        Spieler alterSpieler = initialFeld.keySet().stream().filter(s -> s.getFiguren().contains(alteFigur)).collect(Collectors.toList()).get(0);
        initialFeld.get(alterSpieler).stream().filter(f -> f.getFigur() == null).findFirst().get().setFigur(alteFigur);
        zielFeld.setFigur(figur);
      }
    }
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
