package service

import entity.*

/**
 * Die `GameService`-Klasse ist für die Verwaltung und Steuerung eines Kartenspiels verantwortlich.
 * Dieser Service ermöglicht das Starten eines neuen Spiels mit einer bestimmten Anzahl von Spielern und das Verwalten
   des Spielzustands.
 *
 * @property rootService Der `RootService`, der diesen `GameService` verwendet.
 */
class GameService(private val rootService: RootService) : AbstractRefreshingService() {

    /**
     * Startet ein neues Spiel mit den angegebenen Spieler-Namen.
     *
     * @param playerNames Eine Liste von Spieler-Namen, die am Spiel teilnehmen.
     * @throws IllegalArgumentException, wenn die Anzahl der Spieler nicht zwischen 1 und 2 liegt.
     */

    fun startNewGame(playerNames :List<String>){
        // überprüfe die Anzahl der Spieler
        require(playerNames.size in 1..2) {"Anzahl der zugelassenen Spieler 2."}

        // erstelle eine Liste von Spielern.
        val players = List(playerNames.size){
            rootService.playerService.createPlayer(playerNames[it]) /* In Kotlin ist [it] eine Konvention für den
         impliziten Index in einer Lambda-Funktion, wenn Sie eine Schleife oder eine ähnliche Funktion verwenden.
         Es ist äquivalent zur Verwendung von playerNames[index],
          wobei index der aktuelle Index in der Schleife ist. */
        }
        //erstelle ein neues Game für die gegebenen Spielern..
        rootService.currentGame= Game(players)
        val game = rootService.currentGame
        checkNotNull(game)
        val cardStack = game.cardStack

        //setze die pyramidKarten.

        game.pyramid = mutableListOf(
            mutableListOf(cardStack.drawCard()), // 1. Reihe
            mutableListOf(cardStack.drawCard(), cardStack.drawCard()), // 2. Reihe
            mutableListOf(cardStack.drawCard(), cardStack.drawCard(), cardStack.drawCard()), // 3. Reihe
            mutableListOf(cardStack.drawCard(), cardStack.drawCard(), cardStack.drawCard(), cardStack.drawCard()),
            // 4. Reihe
            mutableListOf(cardStack.drawCard(), cardStack.drawCard(), cardStack.drawCard(), cardStack.drawCard(),
                cardStack.drawCard()), // 5. Reihe
            mutableListOf(cardStack.drawCard(), cardStack.drawCard(), cardStack.drawCard(), cardStack.drawCard(),
                cardStack.drawCard(), cardStack.drawCard()), // 6. Reihe
            mutableListOf(cardStack.drawCard(), cardStack.drawCard(), cardStack.drawCard(), cardStack.drawCard(),
                cardStack.drawCard(), cardStack.drawCard(), cardStack.drawCard()) // 7. Reihe
        )

        rootService.currentGame= game // Aktualisiere erneut die Referenz auf das aktuelle Spiel
        onAllRefreshables {  refreshAfterStartNewGame() }
    }






}