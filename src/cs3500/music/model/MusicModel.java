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
  public void edit(int beat, int pitch, Note note) {

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
        result = result;
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

    int[][] cells = new int[duration + 1][pitchRange + 1];
    for (int i = 0; i < duration + 1; i++) {
      for (int j = 0; j < pitchRange + 1; j++) {
        cells[i][j] = 0;
      }
    }

    for (int i = 0; i < duration + 1; i++) {
      if (music.notes.containsKey(i)) {
        TreeMap<Integer, List<Note>> pitches = music.notes.get(i);
        for (int j = 0; j < pitchRange + 1; j++) {
          if (pitches.containsKey(j + lowPitch)) {
            List<Note> lon = pitches.get(j + lowPitch);
            int maxL = 0;
            for (int m = 0; m < lon.size(); m++) {
              int possibleL = lon.get(m).toString().length();
              maxL = (possibleL > maxL) ? possibleL : maxL;
            }
            for (int n = 0; n < maxL; n++) {
              if (n == 0) {
                cells[i + n][j] = 2;
              } else {
                if (cells[i + n][j] != 2) {
                  cells[i + n][j] = 1;
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
    for (int i = 0; i < pitchRange + 1; i++) {
      result += printPitch(i + lowPitch);
    }
    result += "\n";

    //Printing contents
    for (int i = 0; i < duration + 1; i++) {
      int delta = digit - String.valueOf(i).length();
      for (int s = 0; s < delta; s++) {
        result += " ";
      }
      //beat label
      result += String.valueOf(i);
      for (int j = 0; j < pitchRange + 1; j++) {
        switch(cells[i][j]) {
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
      result += "\n";
    }
    return result;
  }

}
