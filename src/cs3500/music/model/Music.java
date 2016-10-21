package cs3500.music.model;

/**
 * Created by GentleFan on 10/18/2016.
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a music which is a collection of notes.
 */
final class Music {
  TreeMap<Integer, TreeMap<Integer, List<Note>>> notes;

  Music() {
    this.notes = new TreeMap<>();
  }

  //Copy constructor
  Music(Music music) {
    this.notes = new TreeMap<>();
    Set<Map.Entry<Integer, TreeMap<Integer, List<Note>>>>
            beats = music.notes.entrySet();

    for (Map.Entry<Integer, TreeMap<Integer, List<Note>>> eachBeat : beats) {
      TreeMap<Integer, List<Note>>
              newPitches = new TreeMap<>();
      int theBeat = eachBeat.getKey();
      Set<Map.Entry<Integer, List<Note>>>
              pitches = eachBeat.getValue().entrySet();

      for (Map.Entry<Integer, List<Note>> eachPitch : pitches) {
        List<Note> newNotes = new ArrayList<>();
        int thePitchIdx = eachPitch.getKey();
        List<Note> theNotes = eachPitch.getValue();
        newNotes.addAll(theNotes.stream().map(Note::new).collect(Collectors.toList()));
        newPitches.put(thePitchIdx, newNotes);
      }
      this.notes.put(theBeat, newPitches);
    }
  }
}
