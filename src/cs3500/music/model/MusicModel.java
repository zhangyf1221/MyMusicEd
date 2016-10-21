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
public class MusicModel implements IMusicModel {
  //define fields
  private Music music;
  private int duration;
  private int highPitch;
  private int lowPitch;

  public MusicModel() {
    this.music = new Music();
    this.duration = -1;
    this.highPitch = 0;
    this.lowPitch = 0;
  }


  @Override
  public void add(Note note) throws IllegalArgumentException {
    if (note.duration + note.startTime > this.duration) {
      this.duration = note.duration + note.startTime;
    }

    if (note.pitch > highPitch) {
      this.highPitch = note.pitch;
      if (music.notes.isEmpty()) {
        this.lowPitch = note.pitch;
      }
    } else if (note.pitch < lowPitch) {
      this.lowPitch = note.pitch;
    }


    TreeMap<Integer, List<Note>> pitches;
    List<Note> lon;

    if (music.notes.containsKey(note.startTime)) {
      pitches = music.notes.get(note.startTime);
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
    music.notes.put(note.startTime, pitches);

  }

  @Override
  public void remove(Note note) throws IllegalArgumentException {

  }

  @Override
  public void edit(int beat, int pitch, Note note) {

  }

  /**
   * Return the longest duration note at given beat time.
   *
   * @param beat  An integer represents the beat time in music
   * @param pitch An integer represents the pitch of music
   * @return A note object with starting time, duration, pitch, instrument and volume
   */
  public Note get(int beat, int pitch) throws IllegalArgumentException {
    if (beat > duration) {
      throw new IllegalArgumentException("Given beat is greater than the music's duration");
    }

    Note target;
    Integer closestStartBeat = music.notes.floorKey(beat);
    if (closestStartBeat == null) {
      throw new IllegalArgumentException("No note at the given beat");
    } else {
      TreeMap<Integer, List<Note>> pitches = music.notes.get(closestStartBeat);
      if (!pitches.containsKey(pitch)) {
        throw new IllegalArgumentException("Given pitch is not in the given beat");
      }
      List<Note> lon = pitches.get(pitch);
      int max = lon.get(0).duration;
      target = lon.get(0);
      for (int i = 1; i < lon.size() - 1; i++) {
        Note temp = lon.get(i);
        int du = temp.duration;
        if (du > max) {
          max = du;
          target = temp;
        }
      }
    }
    if (target.duration + target.startTime < beat) {
      throw new IllegalArgumentException("No note at the given beat");
    } else {
      return target;
    }
  }

  @Override
  public void combineSimultaneously(IMusicModel model) {

  }

  @Override
  public void combineConsecutively(IMusicModel model) {

  }

  private String printPitch(int pitch) {
    int octave = pitch / 12;
    int i = pitch % 12;
    Pitch[] pitches = Pitch.values();

    String result = pitches[i].toString() + octave;
    int length = result.length();
    switch (length) {
      case 2:
        result = "  " + result + " ";
        break;
      case 3:
        result = " " + result + " ";
        break;
      case 4:
        result = " " + result;
        break;
      case 5:
        result = "" + result;
        break;
      default:
        throw new IllegalArgumentException("Pitch number too large to show");
    }
    return result;

  }

  @Override
  public String print() {
    String result = "";
    if (duration == -1) {
      return "No note in music";
    }

    int pitchRange = highPitch - lowPitch;

    int[][] cells = new int[duration][pitchRange + 1];

    for (int i = 0; i < duration; i++) {
      for (int j = 0; j < pitchRange + 1; j++) {
        cells[i][j] = 0;
      }
    }

    for (int i = 0; i < duration; i++) {
      if (music.notes.containsKey(i)) {
        TreeMap<Integer, List<Note>> pitches = music.notes.get(i);
        for (int j = 0; j < pitchRange + 1; j++) {
          if (pitches.containsKey(j + lowPitch)) {
            List<Note> lon = pitches.get(j + lowPitch);
            int maxL = 0;
            for (int m = 0; m < lon.size(); m++) {
              int possibleL = lon.get(m).duration;
              if (possibleL > maxL) {
                maxL = possibleL;
              }
            }
            for (int n = 0; n < maxL; n++) {
              if (n == 0) {
                cells[n + i][j] = 2;
              } else {
                if (cells[n + i][j] != 2) {
                  cells[n + i][j] = 1;
                }
              }
            }
          }
        }
      }
    }
    //Printing first label line
    int digit = String.valueOf(duration).length();
    for (int i = 0; i < digit; i++) {
      result += " ";
    }
    for (int i = lowPitch; i < highPitch + 1; i++) {
      result += printPitch(i);
    }
    result += "\n";

    //Printing contents
    for (int i = 0; i < duration; i++) {
      int delta = digit - String.valueOf(i).length();
      for (int s = 0; s < delta; s++) {
        result += " ";
      }
      //beat label
      result += String.valueOf(i);
      for (int j = 0; j < pitchRange + 1; j++) {
        switch (cells[i][j]) {
          case 0:
            result += "     ";
            break;
          case 1:
            result += "  |  ";
            break;
          case 2:
            result += "  X  ";
            break;
          default:
            throw new IllegalArgumentException("");
        }
      }
      if (i != duration - 1) {
        result += "\n";
      }
    }
    return result;
  }

}
