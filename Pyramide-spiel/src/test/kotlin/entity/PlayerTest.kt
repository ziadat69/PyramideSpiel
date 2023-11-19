package entity

import entity.Player
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertFailsWith
/**
 * a class to test the Player class
 */
internal class PlayerTest {

    @Test
    fun testPlayerInitialization() {
        // Testet, ob der Konstruktor bei ungültigen Eingaben eine IllegalArgumentException auslöst.
        assertFailsWith<IllegalArgumentException> {
            Player("") // Leerer Spielername sollte eine Ausnahme auslösen.
        }
    }

    @Test
    fun testPlayerProperties() {
        // Überprüft, ob die Spieler-Eigenschaften korrekt gesetzt und abgerufen werden.
        val playerName = "Alice"
        val player = Player(playerName)

        // Prüft, ob der Spielername korrekt gesetzt wurde.
        assertEquals(playerName, player.playerName)

        // Überprüft, ob die anfängliche Punktzahl 0 ist.
        assertEquals(0, player.points)

        // Überprüft, ob das Spiel anfänglich null ist.
        assertNull(player.game)
    }
}