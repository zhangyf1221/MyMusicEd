package cs3500.music.model;

/**
 * Created by GentleFan on 10/18/2016.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Represents the class of the music model.
 */
public class MusicModel implements IMusicModel {
  //define fields
  private Music music;
  private int duration;
  private int highPitch;
  private int lowPitch;

  /**
   * Constructor.
   */
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
  public void remove(int beat, int pitch) throws IllegalArgumentException {
    Note note = get(beat, pitch);
    removeHelper(note);
  }

  private void removeHelper(Note note) {
    TreeMap<Integer, List<Note>> pitches = music.notes.get(note.startTime);
    List<Note> lon = pitches.get(note.pitch);
    lon.remove(note);

    if (lon.isEmpty()) {
      pitches.remove(note.pitch);
      if (pitches.isEmpty()) {
        music.notes.remove(note.startTime);
      }
    }

    if (music.notes.isEmpty()) {
      duration = -1;
      highPitch = 0;
      lowPitch = 0;
    } else {
      if (note.duration + note.startTime == duration) {
        this.duration = music.notes.lastKey() + 1;
      }
      if (note.pitch == highPitch) {
        List<Integer> pitches1 = music.notes.values().stream().map(TreeMap<Integer,
                List<Note>>::lastKey).collect(Collectors.toList());
        this.highPitch = Collections.max(pitches1);
      } else if (note.pitch == lowPitch) {
        List<Integer> pitches2 = music.notes.values().stream().map(TreeMap<Integer,
                List<Note>>::firstKey).collect(Collectors.toList());
        this.lowPitch = Collections.min(pitches2);
      }
    }
  }

  @Override
  public void edit(int beat, int pitch, Note newNote) {
    if (beat > duration || pitch > highPitch || pitch < lowPitch) {
      throw new IllegalArgumentException("Cannot not find note");
    }

    remove(beat, pitch);
    add(newNote);
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
    if (target.duration + target.startTime - 1 < beat) {
      throw new IllegalArgumentException("No note at the given beat");
    } else {
      return target;
    }
  }

  @Override
  public void combineSimultaneously(IMusicModel model) {
    MusicModel cast = (MusicModel) model;
    Music copied = new Music(cast.music);
    Set<Map.Entry<Integer, TreeMap<Integer, List<Note>>>>
            beats = copied.notes.entrySet();

    for (Map.Entry<Integer, TreeMap<Integer, List<Note>>> eachBeat : beats) {
      Set<Map.Entry<Integer, List<Note>>>
              pitches = eachBeat.getValue().entrySet();

      for (Map.Entry<Integer, List<Note>> eachPitch : pitches) {
        List<Note> theNotes = eachPitch.getValue();

        theNotes.forEach(this::add);
      }
    }
  }

  @Override
  public void combineConsecutively(IMusicModel model) {
    MusicModel cast = (MusicModel) model;
    Music copied = new Music(cast.music);
    int originalEndTime = duration;
    Set<Map.Entry<Integer, TreeMap<Integer, List<Note>>>>
            beats = copied.notes.entrySet();

    for (Map.Entry<Integer, TreeMap<Integer, List<Note>>> eachBeat : beats) {
      Set<Map.Entry<Integer, List<Note>>>
              pitches = eachBeat.getValue().entrySet();

      for (Map.Entry<Integer, List<Note>> eachPitch : pitches) {
        List<Note> theNotes = eachPitch.getValue();

        for (Note eachNote : theNotes) {
          eachNote.startTime += originalEndTime;
          eachNote.endTime += originalEndTime;
          add(eachNote);
        }
      }
    }
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
            int maxDuration = 0;
            for (int m = 0; m < lon.size(); m++) {
              int tempDuration = lon.get(m).duration;
              if (tempDuration > maxDuration) {
                maxDuration = tempDuration;
              }
            }
            for (int n = 0; n < maxDuration; n++) {
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
