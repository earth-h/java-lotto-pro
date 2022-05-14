package camp.nextstep.edu.level1.lotto.lotto;

import java.util.Objects;

public class LottoNumber {
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;

    private final int number;

    public LottoNumber(int number) {
        checkValidLottoNumber(number);
        this.number = number;
    }

    private void checkValidLottoNumber(int number) {
        if (number < LOTTO_START_NUMBER || number > LOTTO_END_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 만 허용됩니다.");
        }
    }

    public boolean hasSameValue(LottoNumber number) {
        return this.number == number.number;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}