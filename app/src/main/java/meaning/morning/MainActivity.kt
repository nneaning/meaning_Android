package meaning.morning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import meaning.morning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
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