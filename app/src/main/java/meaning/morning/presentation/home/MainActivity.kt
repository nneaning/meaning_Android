/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.R
import meaning.morning.databinding.ActivityMainBinding
import meaning.morning.presentation.camera.TimeStampCameraActivity
import meaning.morning.presentation.group.GroupFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val homeFragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this

        changeFragment(homeFragment)
        initNavigationBar()
        initFloatingButtonEvent()
    }

    private fun initNavigationBar() {
        binding.bottomNavigation.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_bar_home -> {
                        homeFragment.goCardHomeFragment()
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.bottom_bar_group -> {
                        val fragment = GroupFragment()
                        changeFragment(fragment)
                        return@setOnNavigationItemSelectedListener true
                    }
                    else -> false
                }
            }
        }
    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }

    private fun initFloatingButtonEvent() {
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, TimeStampCameraActivity::class.java)
            startActivity(intent)
        }
    }

}