package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jowenfan on 10/19/16.
 */
public class NoteTest {
  @Test
  public void toStringTest() throws Exception {
    Note noteC0 = new Note(0, 8, 0, "", 0);
    assertEquals("C0", noteC0.toString());
    Note noteCSharp0 = new Note(0, 8, 1, "", 0);
    assertEquals("C#0", noteCSharp0.toString());
    Note noteC1 = new Note(0, 8, 12, "", 0);
    assertEquals("C1", noteC1.toString());
    Note noteB0 = new Note(0, 8, 11, "", 0);
    assertEquals("B0", noteB0.toString());
    Note noteASharp1 = new Note(0, 8, 22, "", 0);
    assertEquals("A#1", noteASharp1.toString());
    Note noteNegative = new Note(0, 4, -12, "", 0);
    assertEquals("C-1", noteNegative.toString());
    Note noteC5 = new Note(4, 4, 60, "", 0);
    assertEquals("C5", noteC5.toString());
    Note noteC4 = new Note(4, 4, 48, "", 0);
    assertEquals("C4", noteC4.toString());
  }

}