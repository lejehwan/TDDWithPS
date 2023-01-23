package ex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 1. 모든 문자가 대문자
 * 2. 모든 문자가 소문자
 * 3. 가장 앞의 문자만 대문자
 * 위의 1,2,3의 조건을 충족하면 true 를 리턴
 * 그렇지 않으면 false 를 리턴
 */
public class DetectCapitalTest {

    @Test
    void DetectCapital(){
        assertThat(DetectCapital("USA")).isEqualTo(true);
        assertThat(DetectCapital("A")).isEqualTo(true);
        assertThat(DetectCapital("leetcode")).isEqualTo(true);
        assertThat(DetectCapital("a")).isEqualTo(true);
        assertThat(DetectCapital("FlaG")).isEqualTo(false);
        assertThat(DetectCapital("aA")).isEqualTo(false);
        assertThat(DetectCapital("aAA")).isEqualTo(false);
        assertThat(DetectCapital("aaaBBBccc")).isEqualTo(false);
        assertThat(DetectCapital("aaaB")).isEqualTo(false);
    }

    private static boolean DetectCapital(String word){
        char[] capital = word.toCharArray();
        if(capital.length == 1) return true;
        boolean first = Character.isUpperCase(capital[0]);
        boolean second = Character.isUpperCase(capital[1]);
        if (first && second){ // 첫번째 문자와 두번째 문자가 대문자인 경우
            for (int i = 2; i < capital.length; i++){
                if (Character.isLowerCase(capital[i])) return false;
            }
        }else if (first){// 첫번째 문자는 대문자 두번째 문자는 소문자인 경우
            for (int i = 2; i < capital.length; i++){
                if (Character.isUpperCase(capital[i])) return false;
            }
        }else if(!second) {// 첫번째 문자와 두번째 문자가 소문자인 경우
            for (int i = 2; i < capital.length; i++){
                if (Character.isUpperCase(capital[i])) return false;
            }
        }else if (second) {// 첫번째 문자는 소문자 두번째 문자는 대문자인 경우
            return false;
        }
        return true;
    }

}
