package by.kapinskiy.carcatalog.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kapinskiy.carcatalog.R
import by.kapinskiy.carcatalog.ui.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_catalog) {

    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var carAdapter: CarAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        carAdapter = CarAdapter { car ->
            val action = FavoritesFragmentDirections.actionFavoriteFragmentToCarDetailFragment(car)
            findNavController().navigate(action)
        }

        recyclerView.adapter = carAdapter

        viewModel.favoriteCars.observe(viewLifecycleOwner) { carsMap ->
            val carsList = carsMap.values.toList()
            carAdapter.submitList(carsList)
        }


        val progressBar: ProgressBar = view.findViewById(R.id.loadingProgressBar)

        viewModel.favoriteCars.observe(viewLifecycleOwner) { cars ->
            progressBar.visibility = View.GONE
            if (!cars.isEmpty()) {
                val carsList = cars.values.toList()
                carAdapter.submitList(carsList)
            }
        }
        viewModel.loadFavorites()
    }
}
