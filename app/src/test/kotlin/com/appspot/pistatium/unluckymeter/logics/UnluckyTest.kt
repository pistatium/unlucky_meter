package com.appspot.pistatium.unluckymeter.logics

import org.junit.Assert.*
import org.junit.Test

class UnluckyTest {

    val DELTA = 1.0e-5

    @Test
    fun testUnlucky() {
        val un = Unlucky(0.5, false)
        assertEquals(un.p, 0.5, DELTA)
        assertEquals(un.per, 2.0, DELTA)
        assertEquals(un.is_lucky, false)
    }

    @Test
    fun testCalcCoin() {
        assertEquals(calcUnlucky(0.5, 1, 0).per, 2.0, DELTA)
        assertEquals(calcUnlucky(0.5, 2, 0).per, 4.0, DELTA)
        assertEquals(calcUnlucky(0.5, 3, 0).per, 8.0, DELTA)
        assertEquals(calcUnlucky(0.5, 4, 0).per, 16.0, DELTA)
        assertEquals(calcUnlucky(0.5, 4, 1).per, 4.0, DELTA)
    }

    @Test
    fun testCalcDice() {
        assertEquals(calcUnlucky(1.0/6.0, 1, 0).per, 1.2, DELTA)
        assertEquals(calcUnlucky(1.0/6.0, 2, 0).per, 1.44, DELTA)
    }

    @Test
    fun testGacha() {
        val un = calcUnlucky(1.0/100.0, 100, 0)
        assertEquals(un.p, 0.36603, DELTA)
        assertEquals(un.is_lucky, false)

        val lk = calcUnlucky(1.0/100.0, 100, 2)
        assertEquals(lk.p, 0.003734, DELTA)
        assertEquals(lk.is_lucky, true)
    }
}