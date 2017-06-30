package dhbw.unterstein.madn.spiel;

import java.util.List;
import java.util.Random;

public class Spiel {

  private List<Spieler> spieler;
  private Spieler aktuellerSpieler;
  private SpielBrett spielBrett;

  private Random wuerfel = new Random();

  public Spiel(List<Spieler> spieler) {
    this.spieler = spieler;
    this.spielBrett = new SpielBrett(spieler);
    this.aktuellerSpieler = spieler.get(0);
    while (true) {
      System.out.println("Spieler " + spieler.indexOf(aktuellerSpieler) + " - " + aktuellerSpieler.getName() + " ist am Zug.");
      spielBrett.anzeigen();

      int wurf = Math.abs(wuerfel.nextInt() % 6) + 1;
      System.out.println("Wurf: " + wurf);
      boolean eingabeNotwendig = true;
      while (eingabeNotwendig) {
        try {
          Figur figur = aktuellerSpieler.waehleFigur();
          eingabeNotwendig = false;
          if (figur != null) {
            spielBrett.bewegen(aktuellerSpieler, figur, wurf);
          }
        } catch (IllegalArgumentException e) {
          System.out.println("Fehler: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
          System.out.println("Fehler: Falsche Figur gewählt!");
        }
      }
      aktuellerSpieler = spieler.get((spieler.indexOf(aktuellerSpieler) + 1) % spieler.size());
    }
  }

  public List<Spieler> getSpieler() {
    return spieler;
  }

  public SpielBrett getSpielBrett() {
    return spielBrett;
  }

  public Spieler getAktuellerSpieler() {
    return aktuellerSpieler;
  }

}
