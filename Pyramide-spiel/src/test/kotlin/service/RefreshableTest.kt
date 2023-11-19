package service

import entity.Player
import view.Refreshable


/**
 * [RefreshableTest] implementation that refreshes nothing, but remembers
 * if a refresh method has been called (since last [reset])
 */
class RefreshableTest: Refreshable {

    var refreshAfterStartGame: Boolean = false
        private set

    var refreshAfterGameEnd: Boolean = false
        private set



    var refreshAfterPlayerAction: Boolean = false
        private set

    var refreshAfterPlayerkarteaufdeken: Boolean = false
    private set

    var refreshAfterremove: Boolean = false
        private set

    var refreshAfterremove2: Boolean = false
        private set


    /**
     * resets all *Called properties to false
     */
    fun reset() {

        refreshAfterGameEnd = false
        refreshAfterPlayerkarteaufdeken =false
        refreshAfterremove = false
        refreshAfterPlayerAction = false
        refreshAfterStartGame = false
        refreshAfterremove2 = false
    }


    override fun refreshAfterremove(player: Player, playerCard: Int, playerCard2: Int, pyramid: Int) {

        refreshAfterremove = true
    }



    override fun refreshAfterGameEnd() {
        refreshAfterGameEnd = true
    }

    override fun refreshAfterPlayerkarteaufdeken(player: Player) {

        refreshAfterPlayerkarteaufdeken = true
    }



    override fun refreshAfterStartNewGame() {

        refreshAfterStartGame = true
    }


    override fun refreshAfterremove2(player: Player, playerCard: Int, playerCard2: Int, pyramid: Int, pyramid2: Int) {
     refreshAfterremove2 = true
    }

    override fun refreshAfterPlayerAction(nextPlayer: Player) {


            refreshAfterPlayerAction = true


    }



}