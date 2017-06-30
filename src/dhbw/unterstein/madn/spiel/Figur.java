package dhbw.unterstein.madn.spiel;

public class Figur {

  private Feld aktuellesFeld;

  public boolean stehtAuf(FeldArt art) {
    return aktuellesFeld != null && aktuellesFeld.getArt().equals(art);
  }

  public void setAktuellesFeld(Feld aktuellesFeld) {
    this.aktuellesFeld = aktuellesFeld;
  }

  public Feld getAktuellesFeld() {
    return aktuellesFeld;
  }
}
