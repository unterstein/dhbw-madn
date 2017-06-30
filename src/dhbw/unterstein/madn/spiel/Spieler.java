package dhbw.unterstein.madn.spiel;

import java.util.ArrayList;
import java.util.List;

public class Spieler {
  private final String name;
  private final List<Figur> figuren;

  public Spieler(String name) {
    this.name = name;
    this.figuren = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      figuren.add(new Figur());
    }
  }

  public void weiter() {

  }

  public Figur waehleFigur() {
    return null;
  }

  public boolean isFertig() {
    return figuren.stream().allMatch(figur -> figur.stehtAuf(FeldArt.END));
  }

  public String getName() {
    return name;
  }

  public List<Figur> getFiguren() {
    return figuren;
  }
}
