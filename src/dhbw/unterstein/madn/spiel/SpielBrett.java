package dhbw.unterstein.madn.spiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpielBrett {

  private static final String ascii =
      "[R3] [R2]       [S38][S39][S0]       [B3] [B2]\n" +
          "[R1] [R0]       [S37][BE0][S1]       [B1] [B0]\n" +
          "                [S36][BE1][S2]\n" +
          "                [S35][BE2][S3]\n" +
          "[S30][S31][S32][S33][S34][BE3][S4][S5][S6][S7][S8]\n" +
          "[S29][RE0][RE1][RE2][RE3]    [GE3][GE2][GE1][GE0][S9]\n" +
          "[S28][S27][S26][S25][S24][YE3][S14][S13][S12][S11][S10]\n" +
          "                [S23][YE2][S15]\n" +
          "                [S22][YE1][S16]\n" +
          "[Y3] [Y2]       [S21][YE0][S17]       [G3] [G2]\n" +
          "[Y1] [Y0]       [S20][S19][S18]       [G1] [G0]";

  private List<Feld> spielFeld;
  private Map<Spieler, List<Feld>> initialFeld;
  private Map<Spieler, List<Feld>> endFeld;

  private Map<Spieler, Feld> startFeld;
  private List<Spieler> spieler;

  public SpielBrett(List<Spieler> alleSpieler) {
    this.spieler = alleSpieler;
    spielFeld = new ArrayList<>();
    initialFeld = new HashMap<>();
    endFeld = new HashMap<>();
    startFeld = new HashMap<>();

    for (int i = 0; i < 40; i++) {
      spielFeld.add(new Feld(FeldArt.SPIEL));
    }
    int counter = 0;
    for (Spieler spieler : spieler) {
      List<Feld> initialFeld = new ArrayList<>();
      List<Feld> endFeld = new ArrayList<>();
      for (int x = 0; x < 4; x++) {
        initialFeld.add(new Feld(FeldArt.INITIAL));
        endFeld.add(new Feld(FeldArt.INITIAL));
      }
      this.initialFeld.put(spieler, initialFeld);
      this.endFeld.put(spieler, endFeld);
      for (int x = 0; x < 4; x++) {
        initialFeld.get(x).setFigur(spieler.getFiguren().get(x));
      }
      startFeld.put(spieler, spielFeld.get(counter * 10));
      counter++;
    }
  }

  public void anzeigen() {
    String local = ascii;
    for (int i = 39; i >= 0; i--) {
      Feld feld = spielFeld.get(i);
      if (feld.getFigur() != null) {
        Spieler s = figurToSpieler(feld.getFigur());
        local = local.replace("S" + i, "" + spieler.indexOf(s) + s.getFiguren().indexOf(feld.getFigur()));
      } else {
        local = local.replace("S" + i, "  ");
      }
    }
    String[] spielerBuchstaben = {"B", "G", "Y", "R"};
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        if (spieler.size() > x && initialFeld.get(spieler.get(x)).size() > y && initialFeld.get(spieler.get(x)).get(y).getFigur() != null) {
          local = local.replace(spielerBuchstaben[x] + y, "" + x + y);
        } else {
          local = local.replace(spielerBuchstaben[x] + y, "  ");
        }
      }
    }

    System.out.println(local);
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
            zielFeld.setFigur(figur);
          }
        }
      }
      break;
      case SPIEL: {
        int aktuellePosition = spielFeld.indexOf(figur.getAktuellesFeld());
        Feld zielFeld = spielFeld.get((aktuellePosition + schritte) % 40);
        bewegeFigur(spieler, figur, zielFeld);
      }
      break;
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
        Spieler alterSpieler = figurToSpieler(alteFigur);
        initialFeld.get(alterSpieler).stream().filter(f -> f.getFigur() == null).findFirst().get().setFigur(alteFigur);
        zielFeld.setFigur(figur);
      }
    } else {
      figur.getAktuellesFeld().setFigur(null);
      zielFeld.setFigur(figur);
    }
  }

  private Spieler figurToSpieler(Figur figur) {
    return spieler.stream().filter(s -> s.getFiguren().contains(figur)).collect(Collectors.toList()).get(0);
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
