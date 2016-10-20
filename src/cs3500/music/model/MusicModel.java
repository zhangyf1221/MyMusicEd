package cs3500.music.model;

/**
 * Created by GentleFan on 10/18/2016.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Represents the class of the music model.
 */
public class MusicModel implements IMusicModel{
  //define fields
  public Music music;
  private int duration;
  private int highPitch;
  private int lowPitch;

  public MusicModel() {
    this.music = new Music();
    this.duration = 0;
    this.highPitch = 0;
    this.lowPitch = 0;
  }



  @Override
  public void add(Note note) throws IllegalArgumentException {
    if (note.duration + note.startTime > duration) {
      this.duration = note.duration + note.startTime;
    }

    if (note.pitch > highPitch) {
      this.highPitch = note.pitch;
    }

    if (note.pitch < lowPitch) {
      this.lowPitch = note.pitch;
    }

    for (int i = note.startTime; i < note.startTime + note.duration + 1; i++) {
      TreeMap<Integer, List<Note>> pitches;
      List<Note> lon;

      if (music.notes.containsKey(i)) {
        pitches = music.notes.get(i);
        if (pitches.containsKey(note.pitch)) {
          lon = pitches.get(note.pitch);
        } else {
          lon = new ArrayList<>();
        }
      } else {
        pitches = new TreeMap<>();
        lon = new ArrayList<>();
      }

      lon.add(note);
      pitches.put(note.pitch, lon);
      music.notes.put(i, pitches);
    }
  }

  @Override
  public void remove(Note note) throws IllegalArgumentException {

  }

  @Override
  public void edit(Note note, int newStartTime, int newDuration, int newPitch,
                   String newInstrument, int newVolume) throws IllegalArgumentException {

  }

  /**
   * Return the note at given beat time.
   * @param beat An integer represents the beat time in music
   * @return A note object with starting time, duration, pitch, instrument and volume
   * @throws IllegalArgumentException
   */
  private Note get(int beat) throws IllegalArgumentException {
    return null;
  }

  @Override
  public void combineSimultaneously(IMusicModel model) {

  }

  @Override
  public void combineConsecutively(IMusicModel model) {

  }

  @Override
  public String print() {
    String result = "";
    if (music == null) {
      return "No note in music";
    }

    String space = "";
    for (int i = 0; i < (int)Math.log10(duration) + 1; i++) {
      space += " ";
    }

    //first line

  }

}
