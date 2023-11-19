package entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
/**
 * a class to test the CardValue class
 */
internal class CardValueTest {

    @Test
    fun testToString() {
        // Überprüft, ob die Methode toString() die erwarteten Zeichenfolgen zurückgibt.
        assertEquals("2", CardValue.TWO.toString())
        assertEquals("3", CardValue.THREE.toString())
        assertEquals("4", CardValue.FOUR.toString())
        assertEquals("5", CardValue.FIVE.toString())
        assertEquals("6", CardValue.SIX.toString())
        assertEquals("7", CardValue.SEVEN.toString())
        assertEquals("8", CardValue.EIGHT.toString())
        assertEquals("9", CardValue.NINE.toString())
        assertEquals("10", CardValue.TEN.toString())
        assertEquals("J", CardValue.JACK.toString())
        assertEquals("Q", CardValue.QUEEN.toString())
        assertEquals("K", CardValue.KING.toString())
        assertEquals("A", CardValue.ACE.toString())
    }

    @Test
    fun testShortDeck() {
        // Überprüft, ob die Methode shortDeck() die erwartete Menge von Werten für ein reduziertes Kartenset zurückgibt.
        val shortDeck = CardValue.deck()
        assertTrue(shortDeck.contains(CardValue.TWO))
        assertTrue(shortDeck.contains(CardValue.THREE))
        assertTrue(shortDeck.contains(CardValue.FOUR))
        assertTrue(shortDeck.contains(CardValue.ACE))
        assertTrue(shortDeck.contains(CardValue.SEVEN))
        assertTrue(shortDeck.contains(CardValue.EIGHT))
        assertTrue(shortDeck.contains(CardValue.NINE))
        assertTrue(shortDeck.contains(CardValue.TEN))
        assertTrue(shortDeck.contains(CardValue.JACK))
        assertTrue(shortDeck.contains(CardValue.QUEEN))
        assertTrue(shortDeck.contains(CardValue.KING))
    }
}
