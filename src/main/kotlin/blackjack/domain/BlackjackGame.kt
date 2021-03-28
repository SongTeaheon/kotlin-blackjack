package blackjack.domain

import blackjack.ui.model.PlayerCardResult
import blackjack.ui.model.PlayerDto
import blackjack.ui.model.PlayerWinTypes

class BlackjackGame(private val players: Players, dealerCards: Set<Card> = emptySet()) {

    private val cardPack = CardPack()
    private val dealer = Dealer(dealerCards)

    val dealerDto: PlayerDto
        get() = dealer.toPlayerDto()
    val playerDtos: List<PlayerDto>
        get() = players.toPlayerDtos()
    val playerCardResults: List<PlayerCardResult>
        get() = players.toPlayerCardResults()
    val dealerCardResults: PlayerCardResult
        get() = dealer.cardResult
    val addedDealerCardNumber: Int
        get() = dealer.cardSize - FIRST_GIVEN_CARD_SIZE

    fun giveTwoCardsToAllPlayers() {
        repeat(FIRST_GIVEN_CARD_SIZE) {
            players.giveToAllPlayers(cardPack)
            dealer.takeCard(cardPack.poll())
        }
    }

    fun giveCard(player: Player, hasAccepted: Boolean) {
        if (hasAccepted) {
            player.takeCard(cardPack.poll())
        }
    }

    fun giveCardsToDealer() {
        while (dealer.isUnderSixteen) {
            dealer.takeCard(cardPack.poll())
        }
    }

    fun findPlayerWinTypes(): PlayerWinTypes {
        val winTypeMap = mutableMapOf<String, PlayerWinType>()
        val dealerPoint = dealer.cardPointSum()
        players.forEach { winTypeMap[it.name] = PlayerWinType.findPlayerWinType(it.cardPointSum(), dealerPoint) }
        return PlayerWinTypes(winTypeMap)
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
