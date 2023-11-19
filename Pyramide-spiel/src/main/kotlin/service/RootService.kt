package service

import entity.Game
import view.Refreshable
/**
 * Hauptklasse der Service-Schicht für das Kartenspiel. Bietet Zugriff
 * auf alle anderen Service-Klassen und hält den Spielzustand [currentGame] für diese
 * Services zum Zugriff.
 */
class RootService {

    var gameService= GameService(this)
    val playerService =PlayerService(this)

    /**
     * Das aktuell laufende Spiel. Kann `null` sein, wenn noch kein Spiel gestartet wurde.
     */
    var currentGame: Game?=null
    /**
     * Fügt das bereitgestellte [newRefreshable] zu allen Services hinzu, die mit diesem
     * Root-Service verbunden sind.
     */

    fun addRefreshable(newRefreshable: Refreshable) {
        gameService.addRefreshable(newRefreshable)
        playerService.addRefreshable(newRefreshable)
    }

    /**
     * Fügt jedes der bereitgestellten [newRefreshables] zu allen Services hinzu,
     * die mit diesem Root-Service verbunden sind.
     */

    fun addRefreshables(vararg newRefreshables: Refreshable) { //variable arguments"
        newRefreshables.forEach { addRefreshable(it) }
    }



}