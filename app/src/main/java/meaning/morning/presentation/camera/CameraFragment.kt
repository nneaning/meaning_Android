package meaning.morning.presentation.camera

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.common.util.concurrent.ListenableFuture
import meaning.morning.R
import meaning.morning.databinding.FragmentCameraBinding
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment : Fragment() {

    private val cameraViewModel: CameraViewModel by activityViewModels()

    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var binding: FragmentCameraBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_camera,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = cameraViewModel
        initView(binding)
        cameraViewModel.runCurrentTimeThread()
        return binding.root
    }

    private fun initView(binding: FragmentCameraBinding) {
        startCamera()
        binding.cameraTakePhoto.setOnClickListener {
            takePhoto()
        }
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(
            cameraProvideFutureListener(cameraProviderFuture),
            getMainExecutor()
        )
    }

    private fun cameraProvideFutureListener(
        cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    ) = Runnable {
        val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
        val preview = getCameraPreview()
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        setImageCapture()
        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
        } catch (failBindException: Exception) {
            Log.e(TAG, "Use case binding failed", failBindException)
        }
    }

    private fun getCameraPreview(): Preview = Preview.Builder()
        .build()
        .also {
            it.setSurfaceProvider(binding.cameraViewFinder.surfaceProvider)
        }

    private fun setImageCapture() {
        imageCapture = ImageCapture.Builder()
            .build()
    }

    private fun getMainExecutor() = ContextCompat.getMainExecutor(requireContext())

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        imageCapture.takePicture(
            getMainExecutor(),
            getImageCapturedCallback()
        )
    }

    private fun getImageCapturedCallback(): TimeStampCameraCallback =
        TimeStampCameraCallback().apply {
            setOnCaptureSuccessListener { imageCaptureEvent(it) }
        }

    private fun imageCaptureEvent(image: Bitmap) {
        cameraViewModel.image = image
        (requireActivity() as TimeStampCameraActivity).changeFragment(
            CameraResultFragment(),
            null
        )
    }

    companion object {
        private const val TAG = "Meaning-Camera"
    }

}