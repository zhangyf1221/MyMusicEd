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
    Note note1 = new Note(0, 4, 60, "", 0);
    model.add(note1);
    Note note2 = new Note(1, 4, 66, "", 0);
    model.add(note2);
    assertEquals("   C5  C#5   D5  D#5   E5   F5  F#5 \n" +
                 "0  X                                \n" +
                 "1  |                             X  \n" +
                 "2  |                             |  \n" +
                 "3  |                             |  \n" +
                 "4                                |  ", model.print());
    Note note3 = new Note(4, 8, 64, "", 0);
    model.add(note3);
    assertEquals("    C5  C#5   D5  D#5   E5   F5  F#5 \n" +
                 " 0  X                                \n" +
                 " 1  |                             X  \n" +
                 " 2  |                             |  \n" +
                 " 3  |                             |  \n" +
                 " 4                      X         |  \n" +
                 " 5                      |            \n" +
                 " 6                      |            \n" +
                 " 7                      |            \n" +
                 " 8                      |            \n" +
                 " 9                      |            \n" +
                 "10                      |            \n" +
                 "11                      |            ", model.print());
  }

  @Test
  public void removeTest() throws Exception {
    IMusicModel model = new MusicModel();
    Note note1 = new Note(0, 4, 60, "", 0);
    model.add(note1);
    Note note2 = new Note(1, 4, 66, "", 0);
    model.add(note2);
    Note note3 = new Note(4, 8, 64, "", 0);
    model.add(note3);
    assertEquals("    C5  C#5   D5  D#5   E5   F5  F#5 \n" +
                 " 0  X                                \n" +
                 " 1  |                             X  \n" +
                 " 2  |                             |  \n" +
                 " 3  |                             |  \n" +
                 " 4                      X         |  \n" +
                 " 5                      |            \n" +
                 " 6                      |            \n" +
                 " 7                      |            \n" +
                 " 8                      |            \n" +
                 " 9                      |            \n" +
                 "10                      |            \n" +
                 "11                      |            ", model.print());
    model.remove(3, 66);
    assertEquals("    C5  C#5   D5  D#5   E5 \n" +
                 " 0  X                      \n" +
                 " 1  |                      \n" +
                 " 2  |                      \n" +
                 " 3  |                      \n" +
                 " 4                      X  \n" +
                 " 5                      |  \n" +
                 " 6                      |  \n" +
                 " 7                      |  \n" +
                 " 8                      |  \n" +
                 " 9                      |  \n" +
                 "10                      |  \n" +
                 "11                      |  ", model.print());

  }

  @Test
  public void editTest() throws Exception {
    IMusicModel model = new MusicModel();
    Note note1 = new Note(0, 4, 60, "", 0);
    model.add(note1);
    Note note2 = new Note(1, 4, 66, "", 0);
    model.add(note2);
    Note note3 = new Note(5, 8, 64, "", 0);
    model.add(note3);
    assertEquals("    C5  C#5   D5  D#5   E5   F5  F#5 \n" +
                 " 0  X                                \n" +
                 " 1  |                             X  \n" +
                 " 2  |                             |  \n" +
                 " 3  |                             |  \n" +
                 " 4                                |  \n" +
                 " 5                      X            \n" +
                 " 6                      |            \n" +
                 " 7                      |            \n" +
                 " 8                      |            \n" +
                 " 9                      |            \n" +
                 "10                      |            \n" +
                 "11                      |            \n" +
                 "12                      |            ", model.print());
    model.edit(4, 66, new Note(2, 8, 66, "", 0));
    assertEquals("    C5  C#5   D5  D#5   E5   F5  F#5 \n" +
                 " 0  X                                \n" +
                 " 1  |                                \n" +
                 " 2  |                             X  \n" +
                 " 3  |                             |  \n" +
                 " 4                                |  \n" +
                 " 5                      X         |  \n" +
                 " 6                      |         |  \n" +
                 " 7                      |         |  \n" +
                 " 8                      |         |  \n" +
                 " 9                      |         |  \n" +
                 "10                      |            \n" +
                 "11                      |            \n" +
                 "12                      |            ", model.print());
  }

  @Test
  public void getTest() throws Exception {
    IMusicModel model = new MusicModel();
    Note note1 = new Note(0, 4, 60, "", 0);
    model.add(note1);
    Note note2 = new Note(0, 4, 60, "", 0);
    Note note3 = new Note(4, 8, 64, "", 0);
    model.add(note3);
    assertEquals(true, note1.equals(note2));
    assertEquals(false, note1.equals(note3));
    assertEquals(note1, model.get(0, 60));
    assertEquals(note1, model.get(1, 60));
    assertEquals(note1, model.get(3, 60));
    assertEquals(note3, model.get(11, 64));
    Note note4 = new Note(1, 4, 66, "", 0);
    model.add(note4);
    assertEquals(note4, model.get(3, 66));
  }

  @Test(expected = IllegalArgumentException.class)//empty model
  public void illegalGetTest1() {
    IMusicModel model = new MusicModel();
    model.get(1, 1);
  }

  @Test(expected = IllegalArgumentException.class)//wrong beat index
  public void illegalGetTest2() {
    IMusicModel model = new MusicModel();
    Note note1 = new Note(0, 4, 60, "", 0);
    model.add(note1);
    model.get(5, 60);
  }

  @Test(expected = IllegalArgumentException.class)//wrong pitch index
  public void illegalGetTest3() {
    IMusicModel model = new MusicModel();
    Note note1 = new Note(0, 4, 60, "", 0);
    model.add(note1);
    model.get(0, 59);
  }



  @Test
  public void combineSimultaneouslyTest() throws Exception {
    IMusicModel model1 = new MusicModel();
    IMusicModel model2 = new MusicModel();
    Note note1 = new Note(0, 4, 60, "", 0);
    Note note2 = new Note(4, 4, 66, "", 0);
    Note note3 = new Note(6, 4, 63, "", 0);
    Note note4 = new Note(10, 4, 69, "", 0);

    model1.add(note1);
    model1.add(note3);
    model2.add(note2);
    model2.add(note4);

    assertEquals("    C5  C#5   D5  D#5 \n" +
                 " 0  X                 \n" +
                 " 1  |                 \n" +
                 " 2  |                 \n" +
                 " 3  |                 \n" +
                 " 4                    \n" +
                 " 5                    \n" +
                 " 6                 X  \n" +
                 " 7                 |  \n" +
                 " 8                 |  \n" +
                 " 9                 |  ", model1.print());
    assertEquals("   F#5   G5  G#5   A5 \n" +
                 " 0                    \n" +
                 " 1                    \n" +
                 " 2                    \n" +
                 " 3                    \n" +
                 " 4  X                 \n" +
                 " 5  |                 \n" +
                 " 6  |                 \n" +
                 " 7  |                 \n" +
                 " 8                    \n" +
                 " 9                    \n" +
                 "10                 X  \n" +
                 "11                 |  \n" +
                 "12                 |  \n" +
                 "13                 |  ", model2.print());

    model1.combineSimultaneously(model2);

    assertEquals(
            "    C5  C#5   D5  D#5   E5   F5  F#5   G5  G#5   A5 \n" +
            " 0  X                                               \n" +
            " 1  |                                               \n" +
            " 2  |                                               \n" +
            " 3  |                                               \n" +
            " 4                                X                 \n" +
            " 5                                |                 \n" +
            " 6                 X              |                 \n" +
            " 7                 |              |                 \n" +
            " 8                 |                                \n" +
            " 9                 |                                \n" +
            "10                                               X  \n" +
            "11                                               |  \n" +
            "12                                               |  \n" +
            "13                                               |  ", model1.print());

  }

  @Test
  public void combineConsecutivelyTest() throws Exception {
    IMusicModel model1 = new MusicModel();
    IMusicModel model2 = new MusicModel();
    Note note1 = new Note(0, 4, 60, "", 0);
    Note note2 = new Note(4, 4, 66, "", 0);
    Note note3 = new Note(6, 4, 63, "", 0);
    Note note4 = new Note(10, 4, 69, "", 0);

    model1.add(note1);
    model1.add(note3);
    model2.add(note2);
    model2.add(note4);

    model1.combineConsecutively(model2);

    assertEquals(
            "    C5  C#5   D5  D#5   E5   F5  F#5   G5  G#5   A5 \n" +
            " 0  X                                               \n" +
            " 1  |                                               \n" +
            " 2  |                                               \n" +
            " 3  |                                               \n" +
            " 4                                                  \n" +
            " 5                                                  \n" +
            " 6                 X                                \n" +
            " 7                 |                                \n" +
            " 8                 |                                \n" +
            " 9                 |                                \n" +
            "10                                                  \n" +
            "11                                                  \n" +
            "12                                                  \n" +
            "13                                                  \n" +
            "14                                X                 \n" +
            "15                                |                 \n" +
            "16                                |                 \n" +
            "17                                |                 \n" +
            "18                                                  \n" +
            "19                                                  \n" +
            "20                                               X  \n" +
            "21                                               |  \n" +
            "22                                               |  \n" +
            "23                                               |  ", model1.print());

  }

  @Test
  public void printTest() throws Exception {

  }

}