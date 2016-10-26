package cs3500.music.model;


/**
 * The interface of the music model.
 * It has the simple ability to add, remove, edit the notes and can print the console view of the
 * piece of music. Could be extended in the future.
 */
public interface IMusicModel {
  /**
   * Add a specific note to the music piece.
   *
   * @param n The note you want to add in.
   */
  void addNote(Note n);

  /**
   * Remove a note has a specific pitch and octave that lies on the nearest beat of the input
   * time (less or equal). Sure, the input time should be in the note's duration rage. And if
   * There exists the multiple notes, remove the first added one.
   *
   * @param time   The beat you choose.
   * @param p      The pitch of the note.
   * @param octave The octave of the note.
   * @return The removed note.
   */
  Note removeNote(int time, PitchName p, int octave); // Always remove the last added one

  /**
   * Print the view of the whole music piece to a String.
   *
   * @return The view of the whole music piece.
   */
  String printView();

  /**
   * Remove a note and add a new one into it.
   *
   * @param time   The beat you choose.
   * @param p      The pitch of the note.
   * @param octave The octave of the note.
   * @param n      The new note you want to add.
   */
  void editNote(int time, PitchName p, int octave, Note n);


}
