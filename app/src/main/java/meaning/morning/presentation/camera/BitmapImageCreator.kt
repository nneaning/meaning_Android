/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.presentation.camera

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import androidx.constraintlayout.widget.ConstraintLayout
import meaning.morning.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class TimeStampImageCreator(private val context: Context) {

    fun saveOf(viewGroup: ConstraintLayout) {
        val width = viewGroup.width
        val height = viewGroup.height
        removeViewEvent(viewGroup)
        val bitmapBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmapBuffer)
        viewGroup.draw(canvas)
        saveImage(bitmapBuffer)
    }

    private fun removeViewEvent(viewGroup: ConstraintLayout) {
        viewGroup.apply {
            clearFocus()
            isPressed = false
            invalidate()
        }
    }

    private fun getOutputDirectory(): File {
        //FIXME Deprecated 30 방법 변환이 필요
        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
            File(it, context.resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
    }

    private fun saveImage(bitmapBuffer: Bitmap) {
        val photoFile = getPhotoFile()
        try {
            val outputStream = FileOutputStream(photoFile)
            bitmapBuffer.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.close()
            galleryAddPicture()
        } catch (errorMessage: FileNotFoundException) {
            errorMessage.stackTrace
        } catch (errorMessage: IOException) {
            errorMessage.stackTrace
        } finally {
            bitmapBuffer.recycle()
        }
    }

    private fun getPhotoFile() = File(
        getOutputDirectory(),
        SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss",
            Locale.KOREA
        ).format(System.currentTimeMillis()) + ".jpeg"
    )

    private fun galleryAddPicture() {
        //FIXME : Deprecated 30 방법을 바꿔야함.
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also {
            it.data  = Uri.fromFile(getOutputDirectory())
            context.sendBroadcast(it)
        }
    }


}