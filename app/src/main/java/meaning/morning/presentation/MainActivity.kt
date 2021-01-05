package meaning.morning.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.R
import meaning.morning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this

        initNavigationBar()
    }

    private fun initNavigationBar(){
        binding.bottomNavigation.run {
            setOnNavigationItemSelectedListener {
               when(it.itemId){
                   R.id.bottom_bar_home ->{
                       val fragment = HomeFragment()
                       changeFragment(fragment)
                       return@setOnNavigationItemSelectedListener true
                   }
                   R.id.bottom_bar_group -> {
                       val fragment = GroupFragment()
                       changeFragment(fragment)
                       return@setOnNavigationItemSelectedListener  true
                   }
                   else -> false
               }
            }
        }
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,fragment)
            .commit()
    }
}