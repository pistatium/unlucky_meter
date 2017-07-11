package com.appspot.pistatium.unluckymeter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class MainActivity : AppCompatActivity() {

    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adView = findViewById(R.id.adView) as AdView?
        val adRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)
    }
}
