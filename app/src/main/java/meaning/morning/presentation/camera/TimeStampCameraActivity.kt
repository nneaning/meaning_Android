package meaning.morning.presentation.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.R
import meaning.morning.databinding.ActivityTimeStampCameraBinding

class TimeStampCameraActivity : AppCompatActivity() {

    private val cameraViewModel: CameraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTimeStampCameraBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_time_stamp_camera)
        binding.lifecycleOwner = this
        initTimeStampCamera()
        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
    }

    private fun initTimeStampCamera() {
        if (allPermissionGranted()) {
            loadCameraView()
            return
        }
        requestPermission()
    }

    private fun allPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(applicationContext,it) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            REQUIRED_PERMISSIONS,
            CameraViewModel.REQUEST_CODE_PERMISSIONS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CameraViewModel.REQUEST_CODE_PERMISSIONS) {
            permissionResponseEvent()
        }
    }

    private fun permissionResponseEvent() {
        if (allPermissionGranted()) {
            loadCameraView()
            return
        }
        permissionDeniedEvent()
    }

    private fun permissionDeniedEvent() {
        //TODO 커스텀 토스트로 변경하기.
        Toast.makeText(
            this,
            "권한을 승인하지 않으면 당신의 미라클 모닝을 기록할 수 없어요!",
            Toast.LENGTH_LONG
        ).show()
        finish()
    }

    private fun loadCameraView() {
        changeFragment(CameraFragment())
    }

    private fun changeFragment(initFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.fragment_camera, initFragment)
            commit()
        }
    }

    fun changeFragment(changeFragment: Fragment, backStackName: String?) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            setCustomAnimations(
                R.anim.enter_from_right_fragment,
                R.anim.fragment_fade_exit,
                R.anim.fragment_open_enter,
                R.anim.fragment_close_exit
            )
            replace(R.id.fragment_camera, changeFragment)
            addToBackStack(backStackName)
            commit()
        }
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_camera)
        if (currentFragment is CameraResultFragment) {
            cameraViewModel.isEnableTimer = true
            cameraViewModel.runCurrentTimer()
        }
        super.onBackPressed()
    }

    companion object {
        private val REQUIRED_PERMISSIONS =
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

}