package com.appspot.pistatium.unluckymeter.logics

import org.junit.Assert.*
import org.junit.Test

class UnluckeyKtTest {

    val DELTA = 1.0e-5

    @Test
    fun testCalcCoin() {
        assertEquals(calcUnluckey(0.5, 1), 2.0, DELTA)
        assertEquals(calcUnluckey(0.5, 2), 4.0, DELTA)
        assertEquals(calcUnluckey(0.5, 3), 8.0, DELTA)
        assertEquals(calcUnluckey(0.5, 4), 16.0, DELTA)
    }

    @Test
    fun testCalcDice() {
        assertEquals(calcUnluckey(1.0/6.0, 1), 1.2, DELTA)
        assertEquals(calcUnluckey(1.0/6.0, 2), 1.44, DELTA)
    }
}