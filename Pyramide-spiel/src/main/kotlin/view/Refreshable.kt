package view

import entity.Card
import entity.Player
/**
 * Das [Refreshable]-Interface definiert Methoden, die von Klassen implementiert werden können,
 * um ihre Benutzeroberfläche zu aktualisieren, basierend auf verschiedenen Ereignissen im Spiel.
 * Klassen, die dieses Interface implementieren, können aufgerufen werden, um nach dem Starten eines
 * neuen Spiels, dem Beenden des Spiels, der Durchführung von Spieleraktionen und anderen Ereignissen
 * ihre Oberfläche zu aktualisieren.
 */

interface Refreshable {
    /**
     * Aktualisiert die Benutzeroberfläche nach dem Starten eines neuen Spiels.
     */
    fun refreshAfterStartNewGame() {}
    /**
     * Aktualisiert die Benutzeroberfläche nach dem Ende eines Spiels.
     */
    fun refreshAfterGameEnd() {}
    /**
     * Aktualisiert die Benutzeroberfläche nach einer Spieleraktion.
     *
     * @param nextPlayer Der nächste Spieler, der an der Reihe ist.
     */
    fun refreshAfterPlayerAction(nextPlayer: Player) {}

    /**
     * Aktualisiert die Benutzeroberfläche nach dem Aufdecken einer Karte durch einen Spieler.
     *
     * @param player Der Spieler, der die Karte aufgedeckt hat.
     */

    fun refreshAfterPlayerkarteaufdeken(player: Player){}
    /**
     * Aktualisiert die Benutzeroberfläche nach dem Entfernen von Karten aus der Pyramide durch einen Spieler.
     *
     * @param player Der Spieler, der die Karten entfernt hat.
     * @param playerCard Die Nummer der entfernten Karte des Spielers.
     * @param playerCard2 Die Zeile der entfernten Karte des Spielers.
     * @param pyramid Die Anzahl der entfernten Karten aus der Stapil.
     */
    fun refreshAfterremove(player: Player, playerCard: Int,playerCard2: Int ,pyramid: Int) {}

    /**
     * Aktualisiert die Benutzeroberfläche nach dem Entfernen von Karten aus der Pyramide durch zwei Spieler.
     *
     * @param player Der Spieler, der die Karten entfernt hat.
     * @param playerCard Die Nummer der ersten entfernten Karte .
     * @param playerCard2 Die Zeile der ersten entfernten Karte.
     * @param pyramidDie Die Nummer der zweiten entfernten Karten aus der Pyramide.
     * @param pyramid2 Die Zeile der zweiten entfernten Karten aus der Pyramide.
     */
    fun refreshAfterremove2(player: Player, playerCard: Int, playerCard2: Int, pyramid: Int, pyramid2: Int){}


}