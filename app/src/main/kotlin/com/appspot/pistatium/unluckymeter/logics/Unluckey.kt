package com.appspot.pistatium.unluckymeter.logics


fun calcUnluckey(p: Double, trial_count: Int): Double {
    return 1.0 / Math.pow(1.0 - p, trial_count.toDouble())
}