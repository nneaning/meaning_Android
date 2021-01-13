package meaning.morning.presentation.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import meaning.morning.R
import meaning.morning.databinding.FragmentCameraResultBinding
import meaning.morning.utils.MeaningToast

class CameraResultFragment: Fragment() {

    private val cameraViewModel:CameraViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentCameraResultBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_camera_result,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = cameraViewModel
        initCameraResult(binding)
        initButtonView(binding)
        return binding.root
    }

    private fun initCameraResult(binding: FragmentCameraResultBinding) {
        binding.cameraResultImage.setImageBitmap(cameraViewModel.image)
    }

    private fun initButtonView(binding: FragmentCameraResultBinding) {
        binding.recaptureButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.useImageButton.setOnClickListener {
            TimeStampImageCreator(requireContext()).saveOf(binding.cameraResultPreviewFrame)
            MeaningToast(requireContext(),"사진 저장 완료").showToast()
        }
    }
}