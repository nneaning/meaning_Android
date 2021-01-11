package meaning.morning.presentation.camera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import meaning.morning.R
import meaning.morning.databinding.ActivityTimeStampCameraBinding

class TimeStampCameraActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityTimeStampCameraBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_time_stamp_camera)
        binding.lifecycleOwner = this
    }

}