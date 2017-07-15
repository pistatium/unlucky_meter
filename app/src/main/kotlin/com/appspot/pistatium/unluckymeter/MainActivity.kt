package com.appspot.pistatium.unluckymeter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.view.KeyEvent.KEYCODE_BACK
import android.widget.Toast
import com.appspot.pistatium.unluckymeter.logics.calcUnlucky
import com.appspot.pistatium.unluckymeter.utils.BitmapUtils
import kotlinx.android.synthetic.main.main_card.*
import kotlinx.android.synthetic.main.result.*
import java.lang.String.format
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAds()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && resultLayer.visibility == View.VISIBLE) {
            resultLayer.visibility = View.GONE
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun onClickCalc(view: View) {
        val p = etProbability.text.toString().toDouble() / 100.0
        val tryCount = etTryCount.text.toString().toInt()
        val hitCount = etHitCount.text.toString().toInt()

        if (hitCount > tryCount) {
            Toast.makeText(this, "当たり回数が回した回数より多いです", Toast.LENGTH_SHORT).show()
            return
        }
        resultLayer.visibility = View.VISIBLE
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)


        val result = calcUnlucky(p, tryCount, hitCount)

        tvResultUnlucky.text = if (result.is_lucky) "幸運な人" else "不幸な人"
        val textColor = if (result.is_lucky) R.color.luckyLable else R.color.unluckyLable
        tvResultUnlucky.setTextColor(ContextCompat.getColor(applicationContext, textColor))
        tvResultDesc.text = "${etProbability.text.toString()}％のガチャを${tryCount}回まわして${hitCount}回しか当たらなかったあなたは…"
        tvResultPos.text = "${result.per.formatted()}人に1人の"
    }

    fun onClickShare(view: View) {
        resultDialog.isDrawingCacheEnabled = true
        resultDialog.destroyDrawingCache()

        val bmp = resultDialog.drawingCache
        val path = BitmapUtils.save(bmp, "unlucky.png", applicationContext)
        val contentUri = FileProvider.getUriForFile(applicationContext, "$packageName.fileprovider", path)

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "image/png"
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        shareIntent.setDataAndType(contentUri, contentResolver.getType(contentUri))
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
        shareIntent.putExtra(Intent.EXTRA_TEXT, "#不幸計算機")
        startActivity(Intent.createChooser(shareIntent, ""))
    }

    private fun initAds() {
        val adRequest = AdRequest.Builder()
                .addTestDevice("DC94E0945AA4C0A087C454509D6A3617")
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        adView.loadAd(adRequest)
    }

    private fun Double.formatted(): String {
        if (this > 10) {
            return this.toInt().toString()
        }
        return BigDecimal.valueOf(this).setScale(2, RoundingMode.HALF_UP).toPlainString()
    }

}
