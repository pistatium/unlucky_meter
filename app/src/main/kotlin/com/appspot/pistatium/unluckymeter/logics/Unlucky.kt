package com.appspot.pistatium.unluckymeter.logics


data class Unlucky(val p: Double, val is_lucky: Boolean = false) {
    val per: Double
        get() {
            if (p == 0.0) {
                return 1.0 / 1e10
            }
            return 1.0 / p
        }

    override fun toString(): String {
        when (is_lucky) {
            true -> return "(Luckey: 1 / $per)"
            false -> return "(Unluckey: 1 / $per)"
        }
    }
}

fun calcUnlucky(p: Double, trial_count: Int, hit_count: Int): Unlucky {
    val p1 = Math.pow(p, hit_count.toDouble())
    val p2 = Math.pow(1.0 - p, (trial_count - hit_count).toDouble())
    val c = when (hit_count) {
        0 -> 1.0
        else -> org.apache.commons.math3.util.Combinations(trial_count, hit_count).n.toDouble()
    }
    val is_lucky = hit_count > calcExpo(p, trial_count)
    return Unlucky(p1 * p2 * c, is_lucky)
}

fun calcExpo(p: Double, trial_count: Int): Double {
    return p * trial_count.toDouble()
}
