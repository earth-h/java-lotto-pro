package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SetTest {

    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @DisplayName("요구사항1 - Set의 size() 메소드를 활용해 Set의 크기를 확인하는 학습테스트")
    @Test
    void size() {
        // given & when & then
        assertThat(numbers).hasSize(3);
    }

    @DisplayName("요구사항2 - Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인하는 학습테스트")
    @ParameterizedTest(name = "{displayName} -> number: {0}")
    @ValueSource(ints = {1, 2, 3})
    void contains(int number) {
        // given & when & then
        assertThat(numbers.contains(number)).isTrue();
    }

    @DisplayName("요구사항3 - Set의 contains() 메소드를 활용해 입력 값에 따라 결과 값이 다른 경우에 대한 테스트")
    @ParameterizedTest(name = "{displayName} -> number: {0}, expected: {1}")
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
    void contains_result(int number, boolean expected) {
        // given & when & then
        assertThat(numbers.contains(number)).isEqualTo(expected);
    }
}