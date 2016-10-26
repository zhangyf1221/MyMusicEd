package cs3500.music.model;

/**
 * This is an Enum type represents the 12 basic pitches.
 * So the pitches are separate from octaves.
 */

public enum PitchName {
  /**
   * C.
   */
  C("C", 0),
  /**
   * C-sharp.
   */
  Cs("C#", 1),
  /**
   * D.
   */
  D("D", 2),
  /**
   * D-sharp.
   */
  Ds("D#", 3),
  /**
   * E.
   */
  E("E", 4),
  /**
   * F.
   */
  F("F", 5),
  /**
   * F-sharp.
   */
  Fs("F#", 6),
  /**
   * G.
   */
  G("G", 7),
  /**
   * G-sharp.
   */
  Gs("G#", 8),
  /**
   * A.
   */
  A("A", 9),
  /**
   * A-sharp.
   */
  As("A#", 10),
  /**
   * B.
   */
  B("B", 11), /*Undefined("",12)*/;

  /**
   * Saves the name of the pitch in String.
   */
  private String pitchString;

  /**
   * Save the pitch code of the pitch. (from 0 - 11)
   */
  private int pitchCode;

  /**
   * Constructor of the pitches.
   *
   * @param str       The pitch name.
   * @param pitchCode The pitch code.
   */
  PitchName(String str, int pitchCode) {
    this.pitchString = str;
    this.pitchCode = pitchCode;
  }

  /**
   * Show the name of the pitch.
   *
   * @return The name of the pitch.
   */
  public String toString() {
    return this.pitchString;
  }

  /**
   * Show the pitch code of the pitch.
   *
   * @return The pitch code of the pitch.
   */
  public int toInt() {
    return this.pitchCode;
  }

  /**
   * Transform a valid pitch code to a PitchName.
   *
   * @param pitchCode Integer from 0 - 12.
   * @return The corresponding pitchName.
   */
  public static PitchName cToP(int pitchCode) {
    switch (pitchCode) {
      case 0:
        return C;
      case 1:
        return Cs;
      case 2:
        return D;
      case 3:
        return Ds;
      case 4:
        return E;
      case 5:
        return F;
      case 6:
        return Fs;
      case 7:
        return G;
      case 8:
        return Gs;
      case 9:
        return A;
      case 10:
        return As;
      case 11:
        return B;
      default:
        throw new IllegalArgumentException("Not a valid pitchCode");
    }
  }
}
