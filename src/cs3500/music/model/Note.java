package cs3500.music.model;

/**
 * Created by GentleFan on 10/17/2016.
 */

/**
 * Represents a note with given parameters.
 */
final public class Note {
  int startTime;
  int duration;
  int pitch;
  String instrument;
  int volume;

  Note(int startTime, int duration, int pitch, String instrument, int volume) {
    if (startTime < 0) {
      throw new IllegalArgumentException("Starting time should be greater than or equal to 0.");
    }
    if (duration <= 0) {
      throw new IllegalArgumentException("Duration of a pitch should be greater than 0.");
    }


    this.startTime = startTime;
    this.duration = duration;
    this.pitch = pitch;
    this.instrument = instrument;
    this.volume = volume;
  }

  /**
   * Prints the note in String.
   * @return the pitch and octave of a note in String
   */
  @Override
  public String toString() {
    int octave = pitch / 12;
    int i = pitch % 12;
    Pitch[] pitches = Pitch.values();

    return pitches[i].toString() + octave;
  }

}
