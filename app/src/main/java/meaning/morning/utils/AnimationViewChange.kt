/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.utils

import android.app.Activity
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import meaning.morning.R

fun AppCompatActivity.replaceFragment(@IdRes layoutId: Int, initFragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(layoutId, initFragment)
        .commit()
}

fun AppCompatActivity.replaceFragment(
    @IdRes layoutId: Int,
    initFragment: Fragment,
    backStackName: String?
) {
    supportFragmentManager.beginTransaction()
        .replace(layoutId, initFragment)
        .addToBackStack(backStackName)
        .commit()
}

fun AppCompatActivity.replaceFragmentWithAnimation(
    @IdRes layoutId: Int,
    changeFragment: Fragment,
    backStackName: String?
) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(
            R.anim.enter_fade_slow,
            R.anim.fragment_fade_exit,
            R.anim.fragment_open_enter,
            R.anim.fragment_close_exit
        )
        .replace(layoutId, changeFragment)
        .addToBackStack(backStackName)
        .commit()
}

fun AppCompatActivity.replaceViewWithAnimation(
    @IdRes layoutId: Int,
    changeFragment: Fragment,
    backStackName: String?
) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(
            R.anim.enter_from_right_fragment,
            R.anim.fragment_fade_exit,
            R.anim.fragment_open_enter,
            R.anim.exit_to_right_fragment
        )
        .replace(layoutId, changeFragment)
        .addToBackStack(backStackName)
        .commit()
}

fun Activity.nextActivityAnimation() {
    this.overridePendingTransition(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
}