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



  @Override
  public void add(Note note) throws IllegalArgumentException {

  }

  @Override
  public void remove(Note note) throws IllegalArgumentException {

  }

  @Override
  public void edit(Note note, int newStartTime, int newDuration, int newPitch, String newInstrument, int newVolume) throws IllegalArgumentException {

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
}
