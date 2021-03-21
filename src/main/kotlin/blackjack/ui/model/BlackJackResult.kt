package blackjack.ui.model

import blackjack.domain.Player

class BlackJackResult(
    player: Player
) {
    val name: String = player.name
    val cardNames: List<String> = player.cardNames
    val point: Int = player.calculateCardSum()
}
