/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import meaning.morning.R
import meaning.morning.databinding.FragmentLoginInitBinding

class LoginInitFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginInitBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login_init, container, false
        )
        return binding.root
    }
}