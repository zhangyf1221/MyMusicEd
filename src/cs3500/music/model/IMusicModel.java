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
   * @param note A note object with starting time, duration, pitch, instrument and volume
   * @param newStartTime An integer represents the start time of the new note
   * @param newDuration An integer represents the duration of the new note
   * @param newPitch An integer represents the pitch time of the new note
   * @param newInstrument A String represents the instrument of the new note
   * @param newVolume An integer represents the volume of the new note
   * @throws IllegalArgumentException
   */
  void edit(Note note, int newStartTime, int newDuration, int newPitch, String newInstrument,
            int newVolume) throws IllegalArgumentException;

  /**
   * Return the note at given beat time.
   * @param beat An integer represents the beat time in music
   * @return A note object with starting time, duration, pitch, instrument and volume
   * @throws IllegalArgumentException
   */
  Note get(int beat) throws IllegalArgumentException;

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
