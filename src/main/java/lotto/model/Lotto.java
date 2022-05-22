package lotto.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lotto.generator.LottoNumberGenerator;

public class Lotto {
    public static final int SIZE = 6;
    private static final int COUNT_OF_MATCH_ZERO = 0;
    private static final int COUNT_OF_MATCH_ONE = 1;
    public static final int PRICE = 1000;
    private final Set<LottoNumber> lotto;

    private Lotto(Set<LottoNumber> lotto) {
        validateLotto(lotto);
        this.lotto = lotto;
    }

    public static Lotto draw(LottoNumberGenerator lottoNumberGenerator) {
        return new Lotto(new HashSet<>(lottoNumberGenerator.generate()));
    }

    public LottoRanking lottoRanking(WinningLotto lotto) {
        int countOfMatch = COUNT_OF_MATCH_ZERO;
        for (LottoNumber lottoNumber : this.lotto) {
            countOfMatch += countIfContainLottoNumber(lotto.winningLotto(), lottoNumber);
        }
        validateCountOfMatch(countOfMatch);
        final boolean isBonusMatched = containLottoNumber(lotto.bonusLottoNumber());
        final int finalCountOfMatch = countOfMatch;
        return LottoRanking.findLottoRankingByCountOfMatchAndBonusMatched(finalCountOfMatch, isBonusMatched);
    }

    private static void validateCountOfMatch(int countOfMatch) {
        if (isNotLottoCountOfMatchRange(countOfMatch)) {
            throw new IllegalArgumentException("로또번호 일치 갯수가 유효하지 않습니다.");
        }
    }

    private static boolean isNotLottoCountOfMatchRange(int countOfMatch) {
        return countOfMatch < LottoRanking.COUNT_OF_MATCH_MIN_NUM || countOfMatch > LottoRanking.COUNT_OF_MATCH_MAX_NUM;
    }

    private int countIfContainLottoNumber(Lotto winningLotto, LottoNumber lottoNumber) {
        if (winningLotto.containLottoNumber(lottoNumber)) {
            return COUNT_OF_MATCH_ONE;
        }
        return COUNT_OF_MATCH_ZERO;
    }

    public boolean containLottoNumber(LottoNumber lottoNumber) {
        return this.lotto.contains(lottoNumber);
    }

    private void validateLotto(Set<LottoNumber> lotto) {
        validateLottoSize(lotto);
    }

    private void validateLottoSize(Set<LottoNumber> lotto) {
        if (isNotLottoSize(lotto.size())) {
            throw new IllegalArgumentException("로또 번호 갯수가 올바르지 않습니다.");
        }
    }

    private boolean isNotLottoSize(int size) {
        return size != SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lotto, lotto1.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}