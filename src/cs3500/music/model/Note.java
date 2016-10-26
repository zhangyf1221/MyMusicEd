package cs3500.music.model;


import java.util.Objects;

/**
 * A class represents a single note, which contains pitchName, octave, startTime and length.
 * Also, volume and timbre may be added into future design.
 */

public class Note {
  private final PitchName pitchName;
  private final int octave;
  private final int startTime;
  private final int length;

  /**
   * The constructor of Note.
   *
   * @param pitchName It's the pitchName of the note (i.e. C, Cs, ...)
   * @param octave    It's the octave the note lies in.
   * @param startTime It's the start time of a note.
   * @param length    It's the full length of a note.
   */
  public Note(PitchName pitchName, int octave, int startTime, int length) {
    if (startTime < 0) {
      throw new IllegalArgumentException("The start time should not be negative!");
    }
    if (length <= 0) {
      throw new IllegalArgumentException("The duration should be positive!");
    }
    if (octave > 99 || octave <= -9) {
      throw new IllegalArgumentException("Not a valid octave range!");
    }
    this.pitchName = pitchName;
    this.octave = octave;
    this.startTime = startTime;
    this.length = length;
  }

  /**
   * Get a deep copy of the note.
   *
   * @return The exact deep copy of the note.
   */
  public Note getACopy() {
    return new Note(this.pitchName, this.octave, this.startTime, this.length);
  }


  /**
   * Get the start time of the note.
   *
   * @return The start time of the note.
   */
  public int getStartTime() {
    return startTime;
  }

  /**
   * Get the total length of the note.
   *
   * @return The total length of the note.
   */
  public int getLength() {
    return length;
  }

  /**
   * Get the pitchName of the note.
   *
   * @return The pitchName of the note.
   */
  public PitchName getPitchName() {
    return pitchName;
  }

  /**
   * Get the end time of the note.
   *
   * @return The end time of the note.
   */
  public int getEndTime() {
    return startTime + length - 1;
  }

  /**
   * Get the octave of the note.
   *
   * @return The octave of the note.
   */
  public int getOctave() {
    return octave;
  }

  /**
   * Get the note code.
   *
   * @return The note code(i.e. octave * 12 + pitchCode( 0, 1, ..., 11)) of the note.
   */
  public int getNoteCode() {
    return octave * 12 + pitchName.toInt();
  }

  /**
   * Judge 2 notes are equal or not.
   *
   * @param n Another note.
   * @return True if they are equal and vice versa.
   */
  @Override
  public boolean equals(Object n) {
    if (n instanceof Note) {
      Note ob = (Note)n;
      return (this.pitchName == ob.pitchName) && (this.length == ob.length)
              && (this.octave == ob.octave) && (this.startTime == ob.startTime);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }
}
