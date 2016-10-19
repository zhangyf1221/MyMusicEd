package cs3500.music.model;

/**
 * Created by GentleFan on 10/18/2016.
 */

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
    this.duration = 0;
    this.highPitch = 0;
    this.lowPitch = 0;
  }



  @Override
  public void add(Note note) throws IllegalArgumentException {
    if (note.duration > duration) {
      this.duration = note.duration;
    }

    if (note.pitch > highPitch) {
      this.highPitch = note.pitch;
    }

    if (note.pitch < lowPitch) {
      this.lowPitch = note.pitch;
    }
  }

  @Override
  public void remove(Note note) throws IllegalArgumentException {

  }

  @Override
  public void edit(Note note, int newStartTime, int newDuration, int newPitch,
                   String newInstrument, int newVolume) throws IllegalArgumentException {

  }

  @Override
  public Note get(int beat) throws IllegalArgumentException {
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
    return "";
  }
}
