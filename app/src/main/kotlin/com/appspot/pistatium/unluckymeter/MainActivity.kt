package com.appspot.pistatium.unluckymeter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.NativeExpressAdView

class MainActivity : AppCompatActivity() {

    private var adView: NativeExpressAdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adView = findViewById(R.id.adView) as NativeExpressAdView?
        val adRequest = AdRequest.Builder()
                .addTestDevice("DC94E0945AA4C0A087C454509D6A3617")
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView?.loadAd(adRequest)
    }
}
