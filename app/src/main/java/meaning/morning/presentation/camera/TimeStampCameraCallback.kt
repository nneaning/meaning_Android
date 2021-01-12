package meaning.morning.presentation.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy

class TimeStampCameraCallback : ImageCapture.OnImageCapturedCallback() {

    private var onCaptureSuccessListener: ((image: Bitmap) -> Unit)? = null

    override fun onCaptureSuccess(image: ImageProxy) {
        onCaptureSuccessListener?.invoke(
            imageProxyToBitmap(image)
        )
    }

    fun setOnCaptureSuccessListener(listener: (image: Bitmap) -> Unit) {
        this.onCaptureSuccessListener = listener
    }

    private fun imageProxyToBitmap(image: ImageProxy): Bitmap {
        val buffer = image.planes[0].buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        val matrix = Matrix().apply {
            postRotate(image.imageInfo.rotationDegrees.toFloat())
        }
        return imageResult(bitmap, matrix)
    }

    private fun imageResult(bitmap: Bitmap, matrix: Matrix): Bitmap = Bitmap.createBitmap(
        bitmap,
        ((bitmap.width - bitmap.height) / 2),
        0,
        bitmap.height,
        bitmap.height,
        matrix,
        true
    )

}