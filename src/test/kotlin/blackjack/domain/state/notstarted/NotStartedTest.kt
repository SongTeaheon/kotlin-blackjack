package blackjack.domain.state.notstarted

import blackjack.domain.card.CardType
import blackjack.domain.card.Cards
import blackjack.domain.cards
import blackjack.domain.state.started.Running.Hit
import blackjack.domain.state.started.finished.BlackJack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NotStartedTest {

    @Test
    fun `takeFirstTwoCards 블랙잭인 경우`() {
        val state = NotStarted()
        val cards = Cards(cards(CardType.QUEEN, CardType.ACE))
        val nextState = state.takeFirstTwoCards(cards)

        assertThat(nextState).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `takeFirstTwoCards 블랙잭이 아닌 경우`() {
        val state = NotStarted()
        val cards = Cards(cards(CardType.QUEEN, CardType.TWO))
        val nextState = state.takeFirstTwoCards(cards)

        assertThat(nextState).isInstanceOf(Hit::class.java)
    }
}
