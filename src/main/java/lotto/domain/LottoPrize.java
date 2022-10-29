package lotto.domain;

import java.util.Arrays;

public enum LottoPrize {

    NO_PRIZE(0, 0, "0~2개 일치 (0원)"),
    FIFTH(3, 5000, "3개 일치 (5000원)"),
    FOURTH(4, 50000, "4개 일치 (50000원)"),
    THIRD(5, 1500000, "5개 일치 (1500000원)"),
    SECOND(5, 30000000, "5개 일치, 보너스 볼 일치(30000000원)"),
    FIRST(6, 2000000000, "6개 일치 (2000000000원)")
    ;

    private final int matchCount;
    private final int lottoPrizeMoney;
    private final String lottoPrizeMessage;

    LottoPrize(int matchCount, int lottoPrizeMoney, String lottoPrizeMessage) {
        this.matchCount = matchCount;
        this.lottoPrizeMoney = lottoPrizeMoney;
        this.lottoPrizeMessage = lottoPrizeMessage;
    }

    public static LottoPrize findLottoPrize(int matchCount) {
        return Arrays.stream(LottoPrize.values()).filter(prize -> prize.getMatchCount() == matchCount)
                .findFirst()
                .orElse(NO_PRIZE);
    }

    public static LottoPrize findLottoPrize(int matchCount, boolean isMatchBonusLottoNumber) {
        if(isThirdMatchCount(matchCount)) {
            return findBonusLottoPrize(isMatchBonusLottoNumber);
        }
        return Arrays.stream(LottoPrize.values()).filter(prize -> prize.getMatchCount() == matchCount)
                .findFirst()
                .orElse(NO_PRIZE);
    }

    private static LottoPrize findBonusLottoPrize(boolean isMatchBonusLottoNumber) {
        if(isMatchBonusLottoNumber) {
            return LottoPrize.SECOND;
        }
        return LottoPrize.THIRD;
    }

    private static boolean isThirdMatchCount(int matchCount) {
        return LottoPrize.THIRD.matchCount == matchCount;
    }

    public static boolean isNoPrize(LottoPrize lottoPrize) {
        return LottoPrize.NO_PRIZE.equals(lottoPrize);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getLottoPrizeMoney() {
        return lottoPrizeMoney;
    }

    public String getLottoPrizeMessage() {
        return lottoPrizeMessage;
    }
}
