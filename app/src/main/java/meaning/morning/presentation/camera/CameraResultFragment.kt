package meaning.morning.presentation.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.R
import meaning.morning.databinding.FragmentCameraResultBinding

class CameraResultFragment: Fragment() {
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

        return binding.root
    }
}