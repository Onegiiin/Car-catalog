package by.kapinskiy.carcatalog.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kapinskiy.carcatalog.R
import by.kapinskiy.carcatalog.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.isProfileComplete.observe(viewLifecycleOwner) { isComplete ->
            if (!isComplete) {
                findNavController().navigate(R.id.action_splashFragment_to_profileEditFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_profileViewFragment)
            }
        }


    }
}
