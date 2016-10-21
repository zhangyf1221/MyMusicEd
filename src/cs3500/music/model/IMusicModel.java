package cs3500.music.model;

/**
 * Created by GentleFan on 10/17/2016.
 */

/**
 * Represents an interface of the music model.
 */
public interface IMusicModel {

  /**
   * Add the given note to the music.
   * @param note A note object with starting time, duration, pitch, instrument and volume
   * @throws IllegalArgumentException
   */
  void add(Note note) throws IllegalArgumentException;

  /**
   * Remove the given note form the music.
   * @param note A note object with starting time, duration, pitch, instrument and volume
   * @throws IllegalArgumentException
   */
  void remove(Note note) throws IllegalArgumentException;

  /**
   * Edit the given note with given parameters.
   * @param beat An integer represents the time of note need to be edited
   * @param pitch An integer represents the pitch the note need to be edited
   * @param note A note object with starting time, duration, pitch, instrument and volume
   * @throws IllegalArgumentException
   */
  void edit(int beat, int pitch, Note note) throws IllegalArgumentException;


  /**
   * Return the longest duration note at given beat time.
   * @param beat  An integer represents the beat time in music
   * @param pitch An integer represents the pitch of music
   * @return A note object with starting time, duration, pitch, instrument and volume
   * @throws IllegalArgumentException
   */
  Note get(int beat, int pitch) throws IllegalArgumentException;

  /**
   * Combine the music model with the given new music model then they can play simultaneously.
   * @param model A music model
   */
  void combineSimultaneously(IMusicModel model);

  /**
   * Combine the music model with the given new music model then they can play consecutively.
   * @param model A music model
   */
  void combineConsecutively(IMusicModel model);

  /**
   * Print the music in String.
   * @return String represents the music model
   */
  String print();


}
