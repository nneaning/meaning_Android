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
import androidx.fragment.app.activityViewModels
import meaning.morning.R
import meaning.morning.databinding.FragmentLoginContentBinding

class LoginContentFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginContentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login_content, container, false
        )
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}