package com.appspot.pistatium.unluckymeter.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream


class BitmapUtils {
    companion object {
        fun save(bitmap: Bitmap, filename: String, context: Context,
                 format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
                 quality: Int = 95): File {
            val cachePath = File(context.cacheDir, "images");
            cachePath.mkdirs()
            val filePath = File(cachePath, filename)
            val fos = FileOutputStream(filePath.absolutePath)
            bitmap.compress(format, quality, fos)
            fos.close()
            return filePath
        }
    }
}