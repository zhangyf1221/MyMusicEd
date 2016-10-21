package cs3500.music.model;

/**
 * Created by jowenfan on 10/19/16.
 */

/**
 * Represents the pitch in a octave. Each octave has 12 pitches.
 */
public enum Pitch {
  C("C"), CS("C#"), D("D"), DS("D#"), E("E"), F("F"),
  FS("F#"), G("G"), GS("G#"), A("A"), AS("A#"), B("B");

  private final String str;

  Pitch(String s) {
    this.str = s;
  }

  /**
   * Print the pitch in String.
   * @return String represents the pitch
   */
  @Override
  public String toString() {
    return this.str;
  }
}