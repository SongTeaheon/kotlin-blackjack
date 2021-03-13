package blackjack.domain

import blackjack.enums.CardShape
import blackjack.enums.CardType

class Card(
    private val shape: CardShape,
    private val type: CardType
) {
    val point
        get() = type.point
}
