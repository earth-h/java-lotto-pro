package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LottoPrizeTest {

    @Test
    void 로또_번호가_4개_동일하고_보너스_숫자_맞추면_4등() {
        assertThat(LottoPrize.findLottoPrize(4, true)).isEqualTo(LottoPrize.FOURTH);
    }

    @Test
    void 로또_번호가_5개_동일하고_보너스_숫자_못_맞추면_3등() {
        assertThat(LottoPrize.findLottoPrize(5, false)).isEqualTo(LottoPrize.THIRD);
    }

    @Test
    void 로또_번호가_5개_동일하고_보너스_숫자_맞추면_2등() {
        assertThat(LottoPrize.findLottoPrize(5, true)).isEqualTo(LottoPrize.SECOND);
    }
}
