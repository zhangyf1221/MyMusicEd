package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.SimpleMusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.PitchName;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangzifeng on 10/19/16.
 */
public class SimpleMusicModelTest {
  @Test
  public void test1() {
    // an initial test and feel for all methods, so no assertEquals.
    // This test in face ensures the basic correctness of printView.
    final boolean T = true;
    SimpleMusicModel music = new SimpleMusicModel();
    Note n = new Note(PitchName.C, 4, 0, 4);
    music.addNote(n);
    System.out.println(music.getFinalTime());
    System.out.println(music.getHigh());
    System.out.println(music.getLow());
    music.addNote(new Note(PitchName.Cs, 5, 124, 8));
    System.out.println(music.getFinalTime());
    System.out.println(music.getHigh());
    System.out.println(music.getLow());
    System.out.println(music.printView());
    Note x = music.removeNote(125, PitchName.Cs, 5);
    System.out.println(music.getFinalTime());
    System.out.println(music.getHigh());
    System.out.println(music.getLow());
    System.out.println(music.printView());
    assertEquals(true, T);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoteWrongStart() {
    Note n = new Note(PitchName.Cs, 10, -123, 33);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoteWrongLength() {
    Note n = new Note(PitchName.Cs, 10, 44, -33);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoteWrongOctavePOS() {
    Note n = new Note(PitchName.Cs, 100, 44, -33);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoteWrongOctaveNeg() {
    Note n = new Note(PitchName.Cs, -10, 44, -33);
  }


  @Test
  public void test2() { // Test the null model for printing
    SimpleMusicModel music = new SimpleMusicModel();
    assertEquals("", music.printView());
  }

  @Test
  public void testAddBasic() {
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 4));
    assertEquals("   E6 \n" +
            "0     \n" +
            "1     \n" +
            "2     \n" +
            "3     \n" +
            "4  X  \n" +
            "5  |  \n" +
            "6  |  \n" +
            "7  |  \n", music.printView());
  }

  @Test
  public void testAddOverlapShorter() {
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 4));
    music.addNote(new Note(PitchName.E, 6, 6, 2));
    assertEquals("   E6 \n" +
            "0     \n" +
            "1     \n" +
            "2     \n" +
            "3     \n" +
            "4  X  \n" +
            "5  |  \n" +
            "6  X  \n" +
            "7  |  \n", music.printView());
  }

  @Test
  public void testAddOverlapLonger() {
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 4));
    music.addNote(new Note(PitchName.E, 6, 6, 6));
    assertEquals("    E6 \n" +
            " 0     \n" +
            " 1     \n" +
            " 2     \n" +
            " 3     \n" +
            " 4  X  \n" +
            " 5  |  \n" +
            " 6  X  \n" +
            " 7  |  \n" +
            " 8  |  \n" +
            " 9  |  \n" +
            "10  |  \n" +
            "11  |  \n", music.printView());
  }

  @Test
  public void testAddSeveral() {
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 4));
    music.addNote(new Note(PitchName.G, 7, 6, 6));
    music.addNote(new Note(PitchName.Cs, 7, 13, 6));
    assertEquals("    E6   F6  F#6   G6  G#6   A6  A#6   B6   C7  C#7   D7  D#7   E7   F7  F#7   " +
            "G7 \n" +
            " 0                                                                                \n" +
            " 1                                                                                \n" +
            " 2                                                                                \n" +
            " 3                                                                                \n" +
            " 4  X                                                                             \n" +
            " 5  |                                                                             \n" +
            " 6  |                                                                          X  \n" +
            " 7  |                                                                          |  \n" +
            " 8                                                                             |  \n" +
            " 9                                                                             |  \n" +
            "10                                                                             |  \n" +
            "11                                                                             |  \n" +
            "12                                                                                \n" +
            "13                                               X                                \n" +
            "14                                               |                                \n" +
            "15                                               |                                \n" +
            "16                                               |                                \n" +
            "17                                               |                                \n" +
            "18                                               |                                \n",
            music.printView());
    System.out.println(music.printView());
  }

  @Test
  public void testRemoveOnPoint() {
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 4));
    music.addNote(new Note(PitchName.G, 7, 6, 6));
    music.addNote(new Note(PitchName.Cs, 7, 13, 6));
    music.removeNote(13, PitchName.Cs, 7);
    assertEquals("    E6   F6  F#6   G6  G#6   A6  A#6   B6   C7  C#7   D7  D#7   E7   F7  " +
                    "F#7   G7 \n" +
            " 0                                                                                \n" +
            " 1                                                                                \n" +
            " 2                                                                                \n" +
            " 3                                                                                \n" +
            " 4  X                                                                             \n" +
            " 5  |                                                                             \n" +
            " 6  |                                                                          X  \n" +
            " 7  |                                                                          |  \n" +
            " 8                                                                             |  \n" +
            " 9                                                                             |  \n" +
            "10                                                                             |  \n" +
            "11                                                                             |  \n",
            music.printView());
  }

  @Test
  public void testRemoveNotOnPoint() {
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 4));
    music.addNote(new Note(PitchName.G, 7, 6, 6));
    music.addNote(new Note(PitchName.Cs, 7, 13, 6));
    music.removeNote(13, PitchName.Cs, 7);
    music.removeNote(8, PitchName.G, 7);
    assertEquals("   E6 \n" +
            "0     \n" +
            "1     \n" +
            "2     \n" +
            "3     \n" +
            "4  X  \n" +
            "5  |  \n" +
            "6  |  \n" +
            "7  |  \n", music.printView());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNull() {
    SimpleMusicModel music = new SimpleMusicModel();
    music.removeNote(0, PitchName.Cs, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNoSuchThingPitch() {
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 4));
    music.removeNote(6, PitchName.Cs, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNoSuchThingOctave() {
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 4));
    music.removeNote(6, PitchName.E, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveFail() { // not right time
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 4));
    music.addNote(new Note(PitchName.G, 7, 6, 6));
    music.addNote(new Note(PitchName.Cs, 7, 13, 6));
    music.removeNote(13, PitchName.Cs, 7);
    music.removeNote(8, PitchName.E, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveFail2() { //time out of range
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 4));
    music.addNote(new Note(PitchName.G, 7, 6, 6));
    music.addNote(new Note(PitchName.Cs, 7, 13, 6));
    music.removeNote(13, PitchName.Cs, 7);
    music.removeNote(12, PitchName.E, 6);
  }

  @Test
  public void testRemoveOverlap() { // Remove the nearest one
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 8));
    music.addNote(new Note(PitchName.E, 6, 5, 9));
    music.addNote(new Note(PitchName.E, 6, 6, 10));
    music.removeNote(7, PitchName.E, 6);
    assertEquals("    E6 \n" +
            " 0     \n" +
            " 1     \n" +
            " 2     \n" +
            " 3     \n" +
            " 4  X  \n" +
            " 5  X  \n" +
            " 6  |  \n" +
            " 7  |  \n" +
            " 8  |  \n" +
            " 9  |  \n" +
            "10  |  \n" +
            "11  |  \n" +
            "12  |  \n" +
            "13  |  \n", music.printView());
  }

  @Test
  public void testRemoveOverlapSameHead() { // Remove the first added in one!
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 10));
    music.addNote(new Note(PitchName.E, 6, 4, 9));
    music.addNote(new Note(PitchName.E, 6, 4, 8));
    music.removeNote(6, PitchName.E, 6);
    assertEquals("    E6 \n" +
            " 0     \n" +
            " 1     \n" +
            " 2     \n" +
            " 3     \n" +
            " 4  X  \n" +
            " 5  |  \n" +
            " 6  |  \n" +
            " 7  |  \n" +
            " 8  |  \n" +
            " 9  |  \n" +
            "10  |  \n" +
            "11  |  \n" +
            "12  |  \n", music.printView());
    music.removeNote(6, PitchName.E, 6);
    assertEquals("    E6 \n" +
            " 0     \n" +
            " 1     \n" +
            " 2     \n" +
            " 3     \n" +
            " 4  X  \n" +
            " 5  |  \n" +
            " 6  |  \n" +
            " 7  |  \n" +
            " 8  |  \n" +
            " 9  |  \n" +
            "10  |  \n" +
            "11  |  \n", music.printView());
  }

  @Test
  public void testEdit() { // A combination of add and remove, so doesn't need further test.
    SimpleMusicModel music = new SimpleMusicModel();
    music.addNote(new Note(PitchName.E, 6, 4, 10));
    music.addNote(new Note(PitchName.E, 6, 4, 9));
    music.addNote(new Note(PitchName.E, 6, 4, 8));
    music.editNote(13, PitchName.E, 6, new Note(PitchName.Cs, 5, 7, 16));
    assertEquals("   C#5   D5  D#5   E5   F5  F#5   G5  G#5   A5  A#5   B5   " +
                    "C6  C#6   D6  D#6   E6 \n" +
                    " 0                                                                " +
                    "                \n" +
                    " 1                                                                   " +
                    "             \n" +
                    " 2                                                                    " +
                    "            \n" +
                    " 3                                                                      " +
                    "          \n" +
                    " 4                                                                      " +
                    "       X  \n" +
                    " 5                                                                      " +
                    "       |  \n" +
                    " 6                                                                      " +
                    "       |  \n" +
                    " 7  X                                                                   " +
                    "       |  \n" +
                    " 8  |                                                                   " +
                    "       |  \n" +
                    " 9  |                                                                   " +
                    "       |  \n" +
                    "10  |                                                                   " +
                    "       |  \n" +
                    "11  |                                                                   " +
                    "       |  \n" +
                    "12  |                                                                   " +
                    "       |  \n" +
                    "13  |                                                                   " +
                    "          \n" +
                    "14  |                                                                   " +
                    "          \n" +
                    "15  |                                                                   " +
                    "          \n" +
                    "16  |                                                                   " +
                    "          \n" +
                    "17  |                                                                   " +
                    "          \n" +
                    "18  |                                                                   " +
                    "          \n" +
                    "19  |                                                                   " +
                    "          \n" +
                    "20  |                                                                   " +
                    "          \n" +
                    "21  |                                                                   " +
                    "          \n" +
                    "22  |                                                                   " +
                    "          \n",
            music.printView());
  }

  @Test
  public void testCombineSimu() {
    SimpleMusicModel musicA = new SimpleMusicModel();
    musicA.addNote(new Note(PitchName.E, 6, 4, 10));
    musicA.addNote(new Note(PitchName.E, 6, 4, 9));
    SimpleMusicModel musicB = new SimpleMusicModel();
    musicB.addNote(new Note(PitchName.F, 6, 6, 4));
    musicB.addNote(new Note(PitchName.Gs, 6, 7, 9));
    SimpleMusicModel musicAB = SimpleMusicModel.combineSimu(musicA, musicB);
    System.out.println(musicAB.getFinalTime());
    assertEquals("    E6   F6  F#6   G6  G#6 \n" +
            " 0                         \n" +
            " 1                         \n" +
            " 2                         \n" +
            " 3                         \n" +
            " 4  X                      \n" +
            " 5  |                      \n" +
            " 6  |    X                 \n" +
            " 7  |    |              X  \n" +
            " 8  |    |              |  \n" +
            " 9  |    |              |  \n" +
            "10  |                   |  \n" +
            "11  |                   |  \n" +
            "12  |                   |  \n" +
            "13  |                   |  \n" +
            "14                      |  \n" +
            "15                      |  \n", musicAB.printView());
  }

  @Test
  public void testCombineSimuOneNull() {
    SimpleMusicModel musicA = new SimpleMusicModel();
    musicA.addNote(new Note(PitchName.E, 6, 4, 10));
    musicA.addNote(new Note(PitchName.E, 6, 4, 9));
    SimpleMusicModel musicB = new SimpleMusicModel();
    SimpleMusicModel musicAB = SimpleMusicModel.combineSimu(musicA, musicB);
    System.out.println(musicAB.getFinalTime());
    assertEquals("    E6 \n" +
            " 0     \n" +
            " 1     \n" +
            " 2     \n" +
            " 3     \n" +
            " 4  X  \n" +
            " 5  |  \n" +
            " 6  |  \n" +
            " 7  |  \n" +
            " 8  |  \n" +
            " 9  |  \n" +
            "10  |  \n" +
            "11  |  \n" +
            "12  |  \n" +
            "13  |  \n", musicAB.printView());
  }

  @Test
  public void testCombineSimuTwoNull() {
    SimpleMusicModel musicA = new SimpleMusicModel();
    SimpleMusicModel musicB = new SimpleMusicModel();
    SimpleMusicModel musicAB = SimpleMusicModel.combineSimu(musicA, musicB);
    System.out.println(musicAB.getFinalTime());
    assertEquals("", musicAB.printView());
  }

  @Test
  public void testCombineCons() {
    SimpleMusicModel musicA = new SimpleMusicModel();
    musicA.addNote(new Note(PitchName.E, 6, 4, 10));
    musicA.addNote(new Note(PitchName.E, 6, 4, 9));
    SimpleMusicModel musicB = new SimpleMusicModel();
    musicB.addNote(new Note(PitchName.F, 6, 6, 4));
    musicB.addNote(new Note(PitchName.Gs, 6, 7, 9));
    SimpleMusicModel musicAB = SimpleMusicModel.combineCons(musicA, musicB);
    assertEquals("    E6   F6  F#6   G6  G#6 \n" +
            " 0                         \n" +
            " 1                         \n" +
            " 2                         \n" +
            " 3                         \n" +
            " 4  X                      \n" +
            " 5  |                      \n" +
            " 6  |                      \n" +
            " 7  |                      \n" +
            " 8  |                      \n" +
            " 9  |                      \n" +
            "10  |                      \n" +
            "11  |                      \n" +
            "12  |                      \n" +
            "13  |                      \n" +
            "14                         \n" +
            "15                         \n" +
            "16                         \n" +
            "17                         \n" +
            "18                         \n" +
            "19                         \n" +
            "20       X                 \n" +
            "21       |              X  \n" +
            "22       |              |  \n" +
            "23       |              |  \n" +
            "24                      |  \n" +
            "25                      |  \n" +
            "26                      |  \n" +
            "27                      |  \n" +
            "28                      |  \n" +
            "29                      |  \n", musicAB.printView());
  }

  @Test
  public void testCombineConsOneNull() {
    SimpleMusicModel musicA = new SimpleMusicModel();
    SimpleMusicModel musicB = new SimpleMusicModel();
    musicB.addNote(new Note(PitchName.F, 6, 6, 4));
    musicB.addNote(new Note(PitchName.Gs, 6, 7, 9));
    SimpleMusicModel musicAB = SimpleMusicModel.combineCons(musicA, musicB);
    assertEquals("    F6  F#6   G6  G#6 \n" +
            " 0                    \n" +
            " 1                    \n" +
            " 2                    \n" +
            " 3                    \n" +
            " 4                    \n" +
            " 5                    \n" +
            " 6  X                 \n" +
            " 7  |              X  \n" +
            " 8  |              |  \n" +
            " 9  |              |  \n" +
            "10                 |  \n" +
            "11                 |  \n" +
            "12                 |  \n" +
            "13                 |  \n" +
            "14                 |  \n" +
            "15                 |  \n", musicAB.printView());
  }

  @Test
  public void testCombineConsTwoNull() {
    SimpleMusicModel musicA = new SimpleMusicModel();
    SimpleMusicModel musicB = new SimpleMusicModel();
    SimpleMusicModel musicAB = SimpleMusicModel.combineCons(musicA, musicB);
    assertEquals("", musicAB.printView());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCodeToPitchWrong() {
    PitchName.cToP(12);
  }
}