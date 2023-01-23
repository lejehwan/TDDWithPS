package ex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoteHighlightTest {

    @Test
    void highlight(){
        assertThat(highlight("abc")).isEqualTo("abc");
        assertThat(highlight("efg")).isEqualTo("efg");
        assertThat(highlight("note")).isEqualTo("{note}");
        assertThat(highlight("1 note")).isEqualTo("1 {note}");
        assertThat(highlight("1 note 2")).isEqualTo("1 {note} 2");
        assertThat(highlight("keynote")).isEqualTo("keynote");
        assertThat(highlight("keyanote")).isEqualTo("keyanote");
        assertThat(highlight("key1note")).isEqualTo("key1note");
        assertThat(highlight("key4note")).isEqualTo("key4note");
        assertThat(highlight("yes note1")).isEqualTo("yes note1");
        assertThat(highlight("yes notea")).isEqualTo("yes notea");
        assertThat(highlight("no a note")).isEqualTo("no a {note}");
        assertThat(highlight("no a note note")).isEqualTo("no a {note} {note}");
        assertThat(highlight("no a note note anote")).isEqualTo("no a {note} {note} anote");
    }

    private String highlight(String str){
        String result = "";
        while (true) {
            int idx = str.indexOf("note");
            if (idx == -1) {
                result += str;
                break;
            }
            if (isPrechNotSpace(str, idx) || isPostchNotSpace(str, idx)) {
                result += str.substring(0, idx + "note".length());
            }else {
                String preStr = idx > 0 ? str.substring(0, idx) : "";
                result += preStr + "{note}";
            }
            str = str.substring(idx + "note".length());

        }
        return result;
    }

    private static boolean isPostchNotSpace(String str, int idx){
        int postchIdx = idx + "note".length();
        return postchIdx < str.length() && isNoteSpace(str.charAt(postchIdx));

    }

    private static boolean isPrechNotSpace(String str, int idx){
        int prechIdx = idx - 1;
        return prechIdx >= 0 && isNoteSpace(str.charAt(prechIdx));
    }

    private static boolean isNoteSpace(char ch) {
        return (ch >= 'a' && ch <= 'y') || (ch >= '0' && ch <= '9');
    }
}
