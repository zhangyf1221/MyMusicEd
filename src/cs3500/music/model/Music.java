package cs3500.music.model;

/**
 * Created by GentleFan on 10/18/2016.
 */

import java.util.TreeMap;
import java.util.List;

/**
 * Represents a music which is a collection of notes.
 */
final class Music {
  TreeMap<Integer, TreeMap<Integer, List<Note>>> music;

  Music() {
    this.music = new TreeMap<>();
  }
//TODO: Constructor
}
