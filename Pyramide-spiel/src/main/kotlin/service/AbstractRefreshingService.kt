package service
import view.*


/**
 * Abstrakte Service-Klasse, die mehrere [Refreshable]-Objekte (normalerweise UI-Elemente) behandelt und diese über
 * Änderungen zur Aktualisierung benachrichtigt, indem die Methode [onAllRefreshables] verwendet wird.
 */

abstract class AbstractRefreshingService {

    private val refreshables = mutableListOf<Refreshable>()


    /**
     * Fügt ein [Refreshable]-Objekt zur Liste hinzu, das aufgerufen wird, wenn [onAllRefreshables] verwendet wird.
     *
     * @param newRefreshable Das [Refreshable]-Objekt, das zur Liste hinzugefügt werden soll.
     */
    fun addRefreshable(newRefreshable : Refreshable) {
        refreshables += newRefreshable
    }
    /**
     * Führt die übergebene Methode (normalerweise eine Lambda-Funktion) auf allen
     * mit der Service-Klasse registrierten [Refreshable]-Objekten aus, die von
     * einer Klasse erben, die [AbstractRefreshingService] erweitert.
     *
     * Beispielverwendung (aus einer Methode innerhalb des Dienstes):
     * ```
     * onAllRefreshables {
     *   refreshPlayerStack(p1, p1.playStack)
     *   refreshPlayerStack(p2, p2.playStack)
     *   refreshPlayerStack(p1, p1.collectedCardsStack)
     *   refreshPlayerStack(p2, p2.collectedCardsStack)
     * }
     * ```
     *
     * @param method Eine Lambda-Funktion, die auf den [Refreshable]-Objekten ausgeführt werden soll.
     */


    fun onAllRefreshables(method: Refreshable.() -> Unit) =
        refreshables.forEach { it.method() }

}