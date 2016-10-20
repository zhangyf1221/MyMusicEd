package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jowenfan on 10/19/16.
 */
public class MusicModelTest {
  @Test
  public void addTest() throws Exception {
    IMusicModel model = new MusicModel();
    assertEquals("No note in music", model.print());
    Note note1 = new Note(0, 12, 60, "", 0);
    model.add(note1);
    assertEquals("", model.print());
  }

  @Test
  public void removeTest() throws Exception {

  }

  @Test
  public void editTest() throws Exception {

  }

  @Test
  public void getTest() throws Exception {

  }

  @Test
  public void combineSimultaneouslyTest() throws Exception {

  }

  @Test
  public void combineConsecutivelyTest() throws Exception {

  }

  @Test
  public void printTest() throws Exception {

  }

}