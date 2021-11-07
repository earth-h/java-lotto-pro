package study.lotto.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryFactoryTest {
    @Test
    void 로또생성기_를_통해서_로또복권_을_생성할_수_있다() {
        final LotteryFactory lotteryFactory = LotteryFactory.getInstance();
        final TicketLottery ticketLottery = lotteryFactory.generateTicketLottery();
        assertThat(ticketLottery).isNotNull();
    }

}