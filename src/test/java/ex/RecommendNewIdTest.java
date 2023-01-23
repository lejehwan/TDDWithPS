package ex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RecommendNewIdTest {

    @Test
    void RecommendNewId() {
        assertThat(RecommendNewIdSolved("...!@BaT#*..y.abcdefghijklm")).isEqualTo("bat.y.abcdefghi");
        assertThat(RecommendNewIdSolved("z-+.^.")).isEqualTo("z--");
        assertThat(RecommendNewIdSolved("=.=")).isEqualTo("aaa");
        assertThat(RecommendNewIdSolved("123_.def")).isEqualTo("123_.def");
        assertThat(RecommendNewIdSolved("abcdefghijklmn.p")).isEqualTo("abcdefghijklmn");
        assertThat(RecommendNewIdSolved("a.$.a")).isEqualTo("a.a");
        assertThat(RecommendNewIdSolved("1234567890.123.456")).isEqualTo("1234567890.123");
    }

    private static String answer;

    private String RecommendNewIdSolved(String new_id){
        answer = "";
        new_id = step1(new_id);
        step2(new_id);
        step3();
        step4();
        step5();
        step6();
        step7();
        return answer;
    }

    private String step1(String new_id){
        return new_id.toLowerCase();
    }

    private void step2(String new_id){
        for (int i = 0; i < new_id.length(); i++) {
            char ch = new_id.charAt(i);
            if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '-' || ch == '_' || ch == '.')
                answer += String.valueOf(ch);
        }
    }

    private void step3(){
        while (answer.contains("..")) answer = answer.replace("..", ".");
    }

    private void step4(){
        if (answer.charAt(0) == '.') answer = answer.substring(1, answer.length());
        if (answer.length() >= 1) {
            if (answer.charAt(answer.length() - 1) == '.') answer = answer.substring(0, answer.length() - 1);
        }
    }

    private void step5(){
        if (answer.length() == 0) answer += "a";
    }

    private void step6(){
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            if (answer.charAt(answer.length() - 1) == '.') {
                answer = answer.substring(0, 14);
            }
        }
    }

    private void step7(){
        if (answer.length() <= 2) {
            while (answer.length() < 3) {
                answer += answer.substring(answer.length() - 1);
            }
        }
    }

    private String RecommendNewIdSolvedByRegularExpression(String new_id) {
        answer = "";
        // 1단계
        answer = step1(new_id);
        // 2단계
        answer = answer.replaceAll("[^-_.a-z0-9]", ""); // - _ . a부터z 0부터9를 제외한 것들은 공백으로 치환
        // 3단계
        answer = answer.replaceAll("[.]{2,}", ".");// .이 2번 이상 반복되면 .으로 치환
        // 4단계
        answer = answer.replaceAll("^[.]|[.]$", "");// 시작이 . 또는 종료가 .이면 공백으로 치환
        // 5단계
        step5();
        // 6단계
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            answer = answer.replaceAll("[.]$", ""); // .으로 종료하면 공백으로 치환
        }
        // 7단계
        step7();
        return answer;

    }

}
