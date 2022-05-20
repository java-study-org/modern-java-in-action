import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomQuestions {

    @Nested
    @DisplayName("김현진 문제")
    class Kim{
        final List<CustomAnswer.Student> students = new ArrayList<>(Arrays.asList(
                new CustomAnswer.Student("kim", 90, 70, 60),
                new CustomAnswer.Student("jang", 100, 50, 30),
                new CustomAnswer.Student("lee", 50, 60, 30),
                new CustomAnswer.Student("park", 30, 60, 100)
        ));

        @Test
        @DisplayName("1번")
        void Q1() {
            //(이름, 총점)으로 변환하기 - List<String[]>의 형태

            List<String[]> list = new ArrayList<>();

            assertThat(list.get(0)[0]).isEqualTo("kim");
            assertThat(list.get(0)[1]).isEqualTo("220");
            assertThat(list.get(1)[0]).isEqualTo("jang");
            assertThat(list.get(1)[1]).isEqualTo("180");
            assertThat(list.get(2)[0]).isEqualTo("lee");
            assertThat(list.get(2)[1]).isEqualTo("140");
            assertThat(list.get(3)[0]).isEqualTo("park");
            assertThat(list.get(3)[1]).isEqualTo("190");
        }

        @Test
        @DisplayName("2번")
        void Q2(){
            //학생들의 이름에 쓰인 알파벳을 오름차순으로 정렬(5개만)

            List<String> result = new ArrayList<>();

            assertThat(result.get(0)).isEqualTo("a");
            assertThat(result.get(1)).isEqualTo("e");
            assertThat(result.get(2)).isEqualTo("g");
            assertThat(result.get(3)).isEqualTo("i");
            assertThat(result.get(4)).isEqualTo("j");
        }

        @Test
        @DisplayName("3번")
        void Q3() {
            //String 배열이 주어졌을 때, 중복된 단어가 제거된 4글자 이상의 단어 중 사전상 3번째 나오는 단어를 선택

            String[] input = {"red","blue","green","red","orange","purple"};

            String result = null;

            assertThat(result).isEqualTo("orange");

        }
    }

    @AllArgsConstructor
    @Getter
    static class Student{
        private String name;
        private Integer mathScore;
        private Integer korScore;
        private Integer enScore;

    }
}
