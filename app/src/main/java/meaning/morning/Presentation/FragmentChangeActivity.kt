/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import meaning.morning.R

abstract class FragmentChangeActivity : AppCompatActivity() {
    protected fun changeFragment(initFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.fragment_camera, initFragment)
            commit()
        }
    }
}