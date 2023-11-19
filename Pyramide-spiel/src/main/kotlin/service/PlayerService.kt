package service

import entity.CardValue
import entity.Player

/**
 * Die `PlayerService`-Klasse ist für die Verwaltung von Spielern und ihren Aktionen im Kartenspiel verantwortlich.
 * Dieser Service ermöglicht die Erstellung von Spielern,
 * das Passieren von Zügen, das Aufdecken von Karten und das Entfernen von Karten aus der Pyramide.
 * @property rootService Der `RootService`, der diesen `PlayerService` verwendet.
 */
class PlayerService(private val rootService: RootService): AbstractRefreshingService(){
    /**
     * Die Anzahl der pyrmide karte
     */
       var endspiel = 28 // das damit es beendet werden weenn pyrmide leer ist


    /**
     * Erstellt einen neuen Spieler mit dem angegebenen Namen.
     *
     * @param name Der Name des Spielers.
     * @return Der erstellte Spieler.
     * @throws IllegalArgumentException, wenn der Name leer ist.
     */
    fun createPlayer ( name :String ) : Player {
        if (name.isEmpty()){
            throw IllegalArgumentException()
        }else{
            return Player(name)
        }
    }
    /**
     * Führt einen Zug "Pass" aus, und wechseln zum nächsten Spieler
     *
     */
    fun pass() {
        val game = rootService.currentGame
        checkNotNull(game)

        game.passCounter++ //  den Wert von passCounter um 1 jedes Mal
        //Wenn der Pass-Counter 2 erreicht, wechsle den Spieler und löse eine Aktualisierung aus.
        if (game.passCounter == 2) {

            onAllRefreshables { refreshAfterGameEnd() } // Aktualisiere die Ansicht nach dem Spielende
        } else {
            // Setze den Pass-Counter auf 0, wechsle den Spieler und löse eine Aktualisierung aus.
            game.passCounter == 0 // Dies sollte ein Zuweisungsoperator sein, um den Counter zurückzusetzen.
            switchSpieler() // Wechsle den Spieler
            onAllRefreshables { refreshAfterPlayerAction(game.currentPlayer) } // Aktualisiere die Ansicht
        }
    }
    /**
     * Deckt eine Karte auf und fügt sie zur Ablage hinzu.
     *  wechseln zum nächsten Spieler
     */

    fun karteaufdecken() {
        val game = rootService.currentGame
        checkNotNull(game)
        val currentPlayer :Player =game.currentPlayer
        checkNotNull(game)
        val cardStack = game.cardStack
        if (game.cardStack.cardimStack()) {
            game.collectedStoragePile.add(cardStack.drawCard())
            game.passCounter = 0
            onAllRefreshables { refreshAfterPlayerkarteaufdeken(currentPlayer) }
        }

    }
    /**
     * Entfernt eine Karte aus der Pyramide und der Ablage, wenn die Bedingungen erfüllt sind.
     *
     * @param chosenCard1   Die Zeile der ausgewählten Karte in der Pyramide.
     * @param chosenCard3 Die Position der ausgewählten Karte in der Pyramide.
     * @param chosenCard2 Die Position der ausgewählten Karte in der Ablage.
     */

       /* diese methode benutzen wir um ein karte von stapel und ein karte von pyrmid zu entfernen
  beispiel pyrmid[chosenCard1][chosenCard3] -> pyrmid[0][1]
            collectedStoragePile[chosenCard2]-> collectedStoragePile[0]
     */
    fun karte(chosenCard1: Int ,chosenCard3: Int,chosenCard2: Int ){
        val game = rootService.currentGame
        checkNotNull(game)
        val currentPlayer = game.currentPlayer
         if ( chosenCard1 !=-1 && chosenCard3 !=-1 && chosenCard2 !=-1){
        val pCard1 = game.pyramid[chosenCard1][chosenCard3]
        val tCard = game.collectedStoragePile.last()

        if (((tCard.value == CardValue.ACE) && (pCard1 != null && pCard1.value != CardValue.ACE ))
            || (pCard1.value == CardValue.ACE) && (tCard != null && tCard.value != CardValue.ACE )) {
            currentPlayer.points++
            game.passCounter=0
            onAllRefreshables { refreshAfterremove(currentPlayer,chosenCard1,chosenCard3,chosenCard2) }

            switchSpieler()
            onAllRefreshables { refreshAfterPlayerAction(currentPlayer) }
            endspiel --
        }
        if   (tCard.getPoints()+pCard1.getPoints()==15) {

            currentPlayer.points=currentPlayer.points+2
            game.passCounter=0
            onAllRefreshables { refreshAfterremove(currentPlayer,chosenCard1,chosenCard3,chosenCard2) }

            switchSpieler()
            onAllRefreshables { refreshAfterPlayerAction(currentPlayer) }
            endspiel --
    }

        if( endspiel == 0){
            onAllRefreshables { refreshAfterGameEnd() }
        } }

    }
    /**
     * Entfernt zwei Karten aus der Pyramide und der Ablage, wenn die Bedingungen erfüllt sind.
     *
     * @param chosenCard1 Die Zeile der ersten ausgewählten Karte in der Pyramide.
     * @param chosenCard3 Die Position der ersten ausgewählten Karte in der Pyramide.
     * @param chosenCard4 Die Zeile der zweiten ausgewählten Karte in der Pyramide.
     * @param chosenCard5 Die Position der zweiten ausgewählten Karte in der Pyramide.
     */
    //diese methode wirbenutzen um zwei karten pyrmide zu entfernen
    fun karte2(chosenCard1: Int ,chosenCard3: Int,chosenCard4: Int ,chosenCard5: Int ){

        val game = rootService.currentGame
        checkNotNull(game)
        val currentPlayer = game.currentPlayer
        if ( chosenCard1 != -1 && chosenCard3 !=-1 && chosenCard4 !=-1 && chosenCard5 !=-1){

            val pCard1 = game.pyramid[chosenCard1][chosenCard3]
            val pCard2 = game.pyramid[chosenCard4][chosenCard5]

            if (((pCard2.value == CardValue.ACE) && ((pCard1 != pCard2) &&
                        (pCard1 != null && pCard1.value != CardValue.ACE)))
                || (pCard1.value == CardValue.ACE) && ((pCard1 != pCard2) &&
                        (pCard2 != null && pCard2.value != CardValue.ACE))) {

                currentPlayer.points++
                game.passCounter = 0
                onAllRefreshables {
                    refreshAfterremove2(
                        currentPlayer,
                        chosenCard1,
                        chosenCard3,
                        chosenCard4,
                        chosenCard5
                    )
                }
                switchSpieler()
                onAllRefreshables { refreshAfterPlayerAction(currentPlayer) }
                endspiel = endspiel - 2
            }
            if (pCard2.getPoints() + pCard1.getPoints() == 15) {

                currentPlayer.points = currentPlayer.points + 2
                game.passCounter = 0
                onAllRefreshables {
                    refreshAfterremove2(
                        currentPlayer,
                        chosenCard1,
                        chosenCard3,
                        chosenCard4,
                        chosenCard5
                    )
                }
                switchSpieler()
                onAllRefreshables { refreshAfterPlayerAction(currentPlayer) }
                endspiel = endspiel - 2
            }

            if (endspiel == 0) {
                onAllRefreshables { refreshAfterGameEnd() }
            }
        }

    }

    /**
     * Wechselt zum nächsten Spieler im Spiel.
     * Dies wird aufgerufen, um den aktuellen Spieler zu wechseln und sicherzustellen,
      dass die Spieler in der richtigen Reihenfolge spielen.
     */
    fun switchSpieler() {
        val game = rootService.currentGame
        checkNotNull(game)
        val players = game.players
        val currentPlayerIndex = players.indexOf(game.currentPlayer)

        // Den aktuellen Spieler auf den nächsten Spieler setzen, und dabei sicherstellen,
        // dass wir bei der letzten Position auf den ersten Spieler zurückspringen.
        game.currentPlayer = players[(currentPlayerIndex + 1) % players.size]
    }
}