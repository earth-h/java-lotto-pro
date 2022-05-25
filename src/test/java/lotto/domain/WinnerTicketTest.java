package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinnerTicketTest {

    private final LottoNumber bonusNumber = new LottoNumber(45);

    private static final int purchasePrice = 1000;

    @Test
    void 지난주_당첨_번호_5개만_입력() {
        assertThatThrownBy(() -> new WinnerTicket("1,2,3,4,5", bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 지난주_당첨_번호_공백_입력() {
        List<LottoTicket> tickets = new ArrayList<>();
        tickets.add(new LottoTicket(new TestNumberGenerator()));
        LottoGame game = new LottoGame(tickets);
        game.generateGameResult(new WinnerTicket("4, 5, 6, 7, 8, 9", bonusNumber), purchasePrice);
        assertThat(game.getScore()).containsEntry(Rank.FIFTH, 1);
    }

    @Test
    void 지난주_당첨_번호_콤마_뒤_공백() {
        WinnerTicket winnerTicket = new WinnerTicket("1, 2, 3, 4, 5, 6", bonusNumber);
        assertNotNull(winnerTicket);
    }

    @Test
    void 지난주_당첨_번호_중복_입력() {
        assertThatThrownBy(() -> new WinnerTicket("1,2,3,4,6,6", bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 지난주_당첨_번호_숫자_범위_초과() {
        LottoGame game = new LottoGame(14000, new LottoNumberGenerator());
        assertThatThrownBy(() -> new WinnerTicket("-1,2,3,4,5,6", bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스볼_당첨번호_중복() {
        assertThrows(IllegalArgumentException.class, () -> new WinnerTicket("1,2,3,4,5,45", bonusNumber));
    }
}