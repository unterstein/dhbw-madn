package dhbw.unterstein.madn.spiel;

public class Figur {

  private Feld aktuellesFeld;

  public boolean isFertig() {
    return aktuellesFeld != null && aktuellesFeld.isFertig();
  }
}
