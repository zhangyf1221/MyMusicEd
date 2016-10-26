package cs3500.music.model;


import java.util.ArrayList;

import java.util.TreeMap;
import java.util.Set;

/**
 * This class implements the IMusicModel Interface.
 */
public class SimpleMusicModel implements IMusicModel {
  /**
   * This is the end beat of the music piece.
   */
  private Integer fullLength;
  //private Note Highest;
  //private Note Lowest;
  /**
   * The first integer key is the time (asa beat).
   * The second integer key means a combination of pitchName and octave,
   * since there are 12 pitchNames (0 - 11), the key will be shown as
   * 12 * octave + pitchCode, which ensures the uniqueness of every key.
   */
  private TreeMap<Integer, TreeMap<Integer, ArrayList<Note>>> musicPiece;

  /**
   * The constructor of the model.
   */
  public SimpleMusicModel() {
    fullLength = -1;
    //Highest = new Note();
    //Lowest = new Note();
    musicPiece = new TreeMap<>();
  }

  /**
   * This function combines 2 pieces of music so that they can play simultaneously.
   *
   * @param a The first piece of music.
   * @param b The second piece.
   * @return The "merged" piece.
   */
  public static SimpleMusicModel combineSimu(SimpleMusicModel a, SimpleMusicModel b) {
    SimpleMusicModel total = new SimpleMusicModel();
    int l = (a.fullLength > b.fullLength) ? a.fullLength : b.fullLength;
    for (int i = 0; i <= l; i++) {
      if (a.musicPiece.containsKey(i)) {
        Set<Integer> allPitch = a.musicPiece.get(i).keySet();
        for (int j : allPitch) {
          for (int m = 0; m < a.musicPiece.get(i).get(j).size(); m++) {
            total.addNote(a.musicPiece.get(i).get(j).get(m).getACopy());
          }
        }
      }
      if (b.musicPiece.containsKey(i)) {
        Set<Integer> allPitch = b.musicPiece.get(i).keySet();
        for (int j : allPitch) {
          for (int m = 0; m < b.musicPiece.get(i).get(j).size(); m++) {
            total.addNote(b.musicPiece.get(i).get(j).get(m).getACopy());
          }
        }
      }
    }
    return total;
  }

  /**
   * This function combines 2 pieces of music so that they can play consecutively.
   *
   * @param a The first piece of music.
   * @param b The second piece.
   * @return The "merged" piece.
   */
  public static SimpleMusicModel combineCons(SimpleMusicModel a, SimpleMusicModel b) {
    SimpleMusicModel total = new SimpleMusicModel();
    int l = a.fullLength + b.fullLength + 1;
    for (int i = 0; i <= a.fullLength; i++) {
      if (a.musicPiece.containsKey(i)) {
        Set<Integer> allPitch = a.musicPiece.get(i).keySet();
        for (int j : allPitch) {
          for (int m = 0; m < a.musicPiece.get(i).get(j).size(); m++) {
            total.addNote(a.musicPiece.get(i).get(j).get(m).getACopy());
          }
        }
      }
    }
    for (int p = 0; p <= b.fullLength; p++) {
      if (b.musicPiece.containsKey(p)) {
        Set<Integer> allPitch = b.musicPiece.get(p).keySet();
        for (int j : allPitch) {
          for (int m = 0; m < b.musicPiece.get(p).get(j).size(); m++) {
            Note reference = b.musicPiece.get(p).get(j).get(m).getACopy();
            Note toAdd = new Note(reference.getPitchName(), reference.getOctave()
                    , reference.getStartTime() + a.fullLength + 1, reference.getLength());
            total.addNote(toAdd);
          }
        }
      }
    }
    return total;
  }

  /**
   * Get the Highest pitch in the music piece.
   *
   * @return The Highest pitch in the music piece.
   */
  public int getHigh() {
    return findHigh();
  }

  /**
   * Get the lowest pitch in the music piece.
   *
   * @return The lowest pitch in the music piece.
   */
  public int getLow() {
    return findLow();
  }

  /**
   * Get the end beat of the music piece.
   *
   * @return The end beat of the music piece.
   */
  public int getFinalTime() {
    return fullLength;
  }

  /**
   * Find the Highest pitch in the music piece.
   *
   * @return The Highest pitch in the music piece.
   */
  private int findHigh() { // Time saving than just save it
    int candidate = -1;
    Set<Integer> timeSets = musicPiece.keySet();
    if (timeSets.size() > 0) {
      for (int startTime : timeSets) {
        TreeMap<Integer, ArrayList<Note>> possibleMap = musicPiece.get(startTime);
        Integer pitch = possibleMap.lastKey();
        if (pitch != null && possibleMap.get(pitch).size() != 0) {
          candidate = (pitch > candidate) ? pitch : candidate;
        }
      }
    }
    return candidate;
  }

  /**
   * Find The lowest pitch in the music piece.
   *
   * @return The lowest pitch in the music piece.
   */
  private int findLow() {
    int candidate = 1000;
    Set<Integer> timeSets = musicPiece.keySet();
    if (timeSets.size() > 0) {
      for (int startTime : timeSets) {
        TreeMap<Integer, ArrayList<Note>> possibleMap = musicPiece.get(startTime);
        Integer pitch = possibleMap.lastKey();
        if (pitch != null && possibleMap.get(pitch).size() != 0) {
          candidate = (pitch < candidate) ? pitch : candidate;
        }
      }
    }
    return candidate;
  }

  /**
   * This method refreshes the full length to the fullLength field.
   */
  private void refreshFullLength() { // for each needs null-check??
    fullLength = 0;
    Set<Integer> largeStartTimeSets = musicPiece.keySet();
    for (int largeStartTime : largeStartTimeSets) {
      TreeMap<Integer, ArrayList<Note>> possibleMap = musicPiece.get(largeStartTime);
      Set<Integer> pitches = possibleMap.keySet();
      for (int pitch : pitches) {
        ArrayList<Note> noteList = possibleMap.get(pitch);
        for (int i = 0; i < noteList.size(); i++) {
          Note target = noteList.get(i);
          if (target.getEndTime() > fullLength) {
            fullLength = target.getEndTime();
          }
        }
      }
    }
  }

  @Override
  public void addNote(Note n) {
    fullLength = (fullLength > n.getEndTime()) ? fullLength : n.getEndTime();
    if (!musicPiece.containsKey(n.getStartTime())) {
      ArrayList<Note> tempList = new ArrayList<>();
      tempList.add(n);
      TreeMap<Integer, ArrayList<Note>> m = new TreeMap<>();
      m.put(n.getNoteCode(), tempList);
      musicPiece.put(n.getStartTime(), m);
    } else {
      TreeMap<Integer, ArrayList<Note>> m = musicPiece.get(n.getStartTime());
      if (!m.containsKey(n.getNoteCode())) {
        ArrayList<Note> tempList = new ArrayList<>();
        tempList.add(n);
        m.put(n.getNoteCode(), tempList);
      } else {
        ArrayList<Note> tempList = m.get(n.getNoteCode());
        tempList.add(n);
      }
    }
  }

  // may remove a list to null and not delete the key value pair
  @Override
  public Note removeNote(int time, PitchName p, int octave) {
    Note tobeRemoved = getNote(time, p, octave);
    if (tobeRemoved == null) {
      throw new IllegalArgumentException("Nothing here to be removed!");
    } else {
      int key = tobeRemoved.getStartTime();
      int noteCode = tobeRemoved.getNoteCode();
      int noteLength = tobeRemoved.getLength();
      ArrayList<Note> candidates = musicPiece.get(key).get(noteCode);
      for (int i = 0; i < candidates.size(); i++) {
        if (candidates.get(i).equals(tobeRemoved)) {
          candidates.remove(i);
          break;
        }
      }
      refreshFullLength();
      return tobeRemoved;
    }
  }

  @Override
  public void editNote(int time, PitchName p, int octave, Note n) {
    removeNote(time, p, octave);
    addNote(n);
  }

  // 0 -> null, 1 -> body, 2 -> head
  @Override
  public String printView() {
    int high = findHigh();
    int low = findLow();
    int pitchRep = high - low;
    if (fullLength == -1) {
      return "";
    }
    int[][] flag = new int[fullLength + 1][pitchRep + 1];
    for (int i = 0; i <= fullLength; i++) {
      for (int j = 0; j <= pitchRep; j++) {
        flag[i][j] = 0;
      }
    }
    for (int i = 0; i <= fullLength; i++) {
      if (musicPiece.containsKey(i)) {
        TreeMap<Integer, ArrayList<Note>> temp = musicPiece.get(i);
        for (int j = 0; j <= pitchRep; j++) {
          if (temp.containsKey(j + low)) {
            ArrayList<Note> noteList = temp.get(j + low);
            int maxL = 0;
            for (int m = 0; m < noteList.size(); m++) {
              int possibleL = noteList.get(m).getLength();
              maxL = (possibleL > maxL) ? possibleL : maxL;
            }
            for (int n = 0; n < maxL; n++) {
              if (n == 0) {
                flag[i + n][j] = 2;
              } else {
                if (flag[i + n][j] != 2) {
                  flag[i + n][j] = 1;
                }
              }
            }
          }
        }
      }
    }
    int digit = String.valueOf(fullLength).length();
    String output = "";
    for (int i = 0; i < digit; i++) {
      output = output + " ";
    }
    for (int i = 0; i <= pitchRep; i++) {
      output += printPitch(i + low);
    }
    output += "\n";
    for (int i = 0; i <= fullLength; i++) {
      //print spaces
      int delta = digit - String.valueOf(i).length();
      for (int s = 0; s < delta; s++) {
        output += " ";
      }
      output += String.valueOf(i);
      for (int j = 0; j <= pitchRep; j++) {
        switch (flag[i][j]) {
          case 0:
            output += "     ";
            break;
          case 1:
            output += "  |  ";
            break;
          case 2:
            output += "  X  ";
            break;
          default:
        }
      }
      output = output + "\n";
    }
    return output;
  }

  /**
   * Print the demanded format of each pitch.
   *
   * @param fullPitchCode pitchCode + 12 * octave of a note.
   * @return The well-formatted string.
   */
  private String printPitch(int fullPitchCode) {
    int octave = fullPitchCode / 12;
    int pitchCode = fullPitchCode % 12;
    String printOut = PitchName.cToP(pitchCode).toString() + octave;
    int bit = printOut.length();
    switch (bit) {
      case 2:
        return "  " + printOut + " ";
      case 3:
        return " " + printOut + " ";
      case 4:
        return " " + printOut;
      case 5:
        return printOut;
      default:
    }
    return "";
  }

  /**
   * Get a note at a certain time, the time may just contains the body of the note. If there
   * are multiple choices, return the earliest added one.
   *
   * @param time   the chosen beat.
   * @param p      the chosen pitchName.
   * @param octave the chosen octave of the pitchName.
   * @return the list of notes on a certain beat which is less than or equal to the input time.
   */
  private Note getNote(int time, PitchName p, int octave) {
    // Always get the nearest one and first - in one.
    int exactTime;
    if (!musicPiece.containsKey(time)) {
      Integer nearestTime = musicPiece.lowerKey(time);
      if (nearestTime == null) {
        return null;
      } else {
        exactTime = nearestTime;
      }
    } else {
      exactTime = time;
    }
    TreeMap<Integer, ArrayList<Note>> notesOnBeat = musicPiece.get(exactTime);
    int exactNoteCode = octave * 12 + p.toInt();
    if (!notesOnBeat.containsKey(exactNoteCode)) {
      return null;
    } else {
      //return notesOnBeat.get(exactNoteCode);
      int tp = notesOnBeat.get(exactNoteCode).size();
      for (int i = 0; i < tp; i++) {
        if (notesOnBeat.get(exactNoteCode).get(i).getEndTime() >= time) {
          return notesOnBeat.get(exactNoteCode).get(i);
        }
      }
      return null;
    }
  }

}
