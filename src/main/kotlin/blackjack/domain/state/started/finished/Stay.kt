package blackjack.domain.state.started.finished

import blackjack.domain.card.Cards
import blackjack.domain.state.State
import java.math.BigDecimal

class Stay(
    cards: Cards
) : Finished(cards) {
    override fun findEarningRatio(dealerState: State): BigDecimal {
        if (dealerState.isBust || cardPointSum() > dealerState.cardPointSum()) {
            return EARNING_RATIO
        }

        if (dealerState.isBlackJack || cardPointSum() < dealerState.cardPointSum()) {
            return LOSING_RATIO
        }

        return NO_EARNING_RATIO
    }
}
