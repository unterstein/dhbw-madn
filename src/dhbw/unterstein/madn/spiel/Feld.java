package dhbw.unterstein.madn.spiel;

public class Feld {
  private final FeldArt art;
  private Figur figur;

  public Feld(FeldArt art) {
    this.art = art;
  }

  public FeldArt getArt() {
    return art;
  }

  public Figur getFigur() {
    return figur;
  }

  public void setFigur(Figur figur) {
    this.figur = figur;
    if (figur != null) {
      figur.setAktuellesFeld(this);
    }
  }
}
