package lotto.controller;

import static lotto.view.InputView.printBonusLottoDirection;
import static lotto.view.InputView.printPurchasingLottoDirection;
import static lotto.view.InputView.printWinningLottoDirection;
import static lotto.view.InputView.readLine;
import static lotto.view.ResultView.printExceptionErrorMessage;
import static lotto.view.ResultView.printLottoResults;
import static lotto.view.ResultView.printPurchasingLottoCount;
import static lotto.view.ResultView.printPurchasingLottos;

import common.utils.IntegerUtils;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.ReadLineLottoNumberGenerator;

public class LottoController {

    public LottoController() {
    }

    public void process() {
        Lottos lottos = createLottos(getMoney());
        Lotto winningLotto = getWinningLotto();
        LottoNumber bonusNumber = getBonusLottoNumber();
        getLottoResults(lottos, winningLotto, bonusNumber);
    }

    private Money getMoney() {
        printPurchasingLottoDirection();
        try {
            String readMoney = readLine();
            return new Money(IntegerUtils.parseInt(readMoney));
        } catch (IllegalArgumentException e) {
            printExceptionErrorMessage(e);
            return getMoney();
        }
    }

    private Lottos createLottos(Money money) {
        printPurchasingLottoCount(money.maxLottoCount());
        Lottos lottos = new Lottos(money);
        printPurchasingLottos(lottos.unmodifiedLottos());
        return lottos;
    }

    private Lotto getWinningLotto() {
        printWinningLottoDirection();
        try {
            return Lotto.generateLotto(new ReadLineLottoNumberGenerator(readLine()));
        } catch (IllegalArgumentException e) {
            printExceptionErrorMessage(e);
            return getWinningLotto();
        }
    }

    private LottoNumber getBonusLottoNumber() {
        printBonusLottoDirection();
        try {
            String readLottoNumber = readLine();
            return LottoNumber.from(IntegerUtils.parseInt(readLottoNumber));
        } catch (IllegalArgumentException e) {
            printExceptionErrorMessage(e);
            return getBonusLottoNumber();
        }
    }

    private void getLottoResults(Lottos lottos, Lotto winningLotto, LottoNumber bonumsNumber) {
        printLottoResults(lottos.createLottoResults(winningLotto, bonumsNumber), lottos.findTotalPrice());
    }
}
