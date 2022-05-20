import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Answer {

    @Test
    @DisplayName("1번")
    void Q1() {
        //숫자 리스트가 주어졌을때 각 숫자의 제곱근으로 이루어진 리스트를 반환하시오
        // [1,2,3,4] -> [1,4,9,16]

        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);

        List<Integer> result = numbers.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());

        assertThat(result).containsExactly(1, 9, 25, 49, 81);
    }

    @Test
    @DisplayName("2번")
    void Q2() {
        //1. 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환하시오.
        // [1,2,3], [3,4] -> [(1,3),(1,4),(2,3),...,(3,4)]
        //2. 위의 예제에서 두 쌍의 합이 3의 배수인 리스트를 반환하시오
        // 결과 값은 List<int[]>의 형태

        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);

        List<int[]> result1 = nums1.stream()
                .flatMap(num1 -> nums2.stream()
                        .map(num2 -> new int[]{num1, num2}))
                .collect(Collectors.toList());

        List<int[]> result2 = nums1.stream()
                .flatMap(num1 -> nums2.stream()
                        .filter(num2 -> (num1 + num2) % 3 == 0)
                        .map(num2 -> new int[]{num1, num2}))
                .collect(Collectors.toList());

        assertThat(result1).containsExactly(
                new int[]{1, 3},
                new int[]{1, 4},
                new int[]{2, 3},
                new int[]{2, 4},
                new int[]{3, 3},
                new int[]{3, 4});

        assertThat(result2).containsExactly(
                new int[]{2, 4},
                new int[]{3, 3});
    }

    @Nested
    @DisplayName("3번")
    class Q3 {
        final Trader raoul = new Trader("Raoul", "Cambridge");
        final Trader mario = new Trader("Mario", "Milan");
        final Trader alan = new Trader("Alan", "Cambridge");
        final Trader brian = new Trader("Brian", "Cambridge");

        final List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        @Test
        @DisplayName("3-1번")
        void Q3_1() {
            //1. 2011에 일어난 모든 트랜잭션을 찾아 오름차순으로 정렬하시오(value 오름차순)
            List<Transaction> result1 = transactions.stream()
                    .filter(transaction -> transaction.getYear() == 2011)
                    .sorted((t1, t2) -> t1.getValue() > t2.getValue() ? 1 : -1)
                    .collect(Collectors.toList());

            assertThat(result1).containsExactly(
                    transactions.get(0),
                    transactions.get(2)
            );
        }

        @Test
        @DisplayName("3-2번")
        void Q3_2() {
            //2. 거래자가 근무하는 모든 도시를 중복없이 나열하시오
            List<String> result2 = transactions.stream()
                    .map(transaction -> transaction.getTrader().getCity())
                    .distinct()
                    .collect(Collectors.toList());

            assertThat(result2).containsExactly("Cambridge", "Milan");
        }

        @Test
        @DisplayName("3-3번")
        void Q3_3() {
            //3. 케임브리지에서 근무하는 모든 거래자를 찾아 이름순으로 정렬하시오
            List<String> result3 = transactions.stream()
                    .filter(transaction ->
                            "Cambridge".equals(transaction.getTrader().getCity()))
                    .map(transaction -> transaction.getTrader().getName())
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            assertThat(result3).containsExactly("Alan", "Brian", "Raoul");
        }

        @Test
        @DisplayName("3-4번")
        void Q3_4() {
            //4. 모든 거래자의 이름을 알파벳 순으로 정렬하시오
            List<String> result4 = transactions.stream()
                    .map(transaction -> transaction.getTrader().getName())
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            assertThat(result4).containsExactly("Alan", "Brian", "Mario", "Raoul");
        }

        @Test
        @DisplayName("3-5번")
        void Q3_5() {
            //5. 밀라노에 거래자가 있는지 판별하시오
            boolean result5 = transactions.stream()
                    .filter(transaction ->
                            "Milan".equals(transaction.getTrader().getCity()))
                    .findFirst()
                    .isPresent();

            assertThat(result5).isTrue();
        }

        @Test
        @DisplayName("3-6번")
        void Q3_6() {
            //6. 케임브리지에 거주하는 거래자의 모든 트랜잭션 값을 구하시오
            List<Integer> result6 = transactions.stream()
                    .filter(transaction ->
                            "Cambridge".equals(transaction.getTrader().getCity()))
                    .map(transaction -> transaction.getValue())
                    .collect(Collectors.toList());

            assertThat(result6).containsOnly(300, 400, 950, 1000);
        }

        @Test
        @DisplayName("3-7번")
        void Q3_7() {
            //7. 전체 트랜잭션 중 최댓값은?
            int max = transactions.stream()
                    .map(transaction -> transaction.getValue())
                    .reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);

            assertThat(max).isEqualTo(1000);
        }

        @Test
        @DisplayName("3-8번")
        void Q3_8() {
            //8. 전체 트랜잭션 중 최솟값은?
            int min = transactions.stream()
                    .map(transaction -> transaction.getValue())
                    .reduce(Integer.MAX_VALUE, (a, b) -> a > b ? b : a);

            assertThat(min).isEqualTo(300);
        }

    }

    @Getter
    @AllArgsConstructor
    static class Trader {
        private final String name;
        private final String city;

        @Override
        public String toString() {
            return "Trader: " + this.name + " in " + this.city;
        }
    }

    @Getter
    @AllArgsConstructor
    static class Transaction {
        private final Trader trader;
        private final int year;
        private final int value;

        @Override
        public String toString() {
            return "{" + this.trader + ", " +
                    "year: " + this.year + ", " +
                    "value:" + this.value + "}";
        }
    }
}
