package dhbw.unterstein.madn.spiel;

import java.util.List;

public class Spieler {
  private final String name;
  private final List<Figur> figuren;
  private final Feld startFeld;

  public Spieler(String name, List<Figur> figuren, Feld startFeld) {
    this.name = name;
    this.figuren = figuren;
    this.startFeld = startFeld;
  }

  public void weiter() {

  }

  public Figur waehleFigur() {
    return null;
  }

  public boolean isFertig() {
    return figuren.stream().allMatch(Figur::isFertig);
  }
}
