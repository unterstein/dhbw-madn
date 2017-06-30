package dhbw.unterstein.madn.spiel;

import java.util.List;

public class Spiel {

  private List<Spieler> spieler;
  private Spieler aktuellerSpieler;
  private SpielBrett spielBrett;

  public Spiel(List<Spieler> spieler) {
    this.spieler = spieler;
    this.spielBrett = new SpielBrett(spieler);
    this.aktuellerSpieler = spieler.get(0);
  }

  public void start() {

  }

  public void neu() {

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
