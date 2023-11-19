package service

import view.Refreshable
import kotlin.test.*

class RootServiceTest {

    /**
     * Testing the add refreshable function of the rootService
     */
    @Test
    fun addRefreshableTest(){
        val rootService = RootService()
        val gameService = rootService.gameService

        var testVar = false
        val testRefreshable = object : Refreshable {
            override fun refreshAfterGameEnd() {
                testVar = true


            }
        }

        rootService.addRefreshable(testRefreshable)
        gameService.onAllRefreshables { refreshAfterGameEnd() }
        assertEquals(true, testVar)
    }

    /**
     * Testing the rootService function addRefreshables
     */
    @Test
    fun testAddRefreshables(){
        val rootService = RootService()
        val gameService = rootService.gameService

        var testVar1 = false
        val testRefreshable1 = object : Refreshable {
            override fun refreshAfterGameEnd() {
                super.refreshAfterGameEnd()

                testVar1 = true
            }
        }

        var testVar2 = false
        val testRefreshable2 = object: Refreshable {
            override fun refreshAfterStartNewGame() {
                testVar2 = true
            }
        }
        rootService.addRefreshables(testRefreshable1, testRefreshable2)

        gameService.onAllRefreshables { refreshAfterGameEnd() }
        gameService.onAllRefreshables { refreshAfterStartNewGame() }
        assertEquals(true, testVar1)
        assertEquals(true, testVar2)
    }
}