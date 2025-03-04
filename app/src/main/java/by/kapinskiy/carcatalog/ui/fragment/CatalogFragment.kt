package by.kapinskiy.carcatalog.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kapinskiy.carcatalog.R
import by.kapinskiy.carcatalog.ui.viewmodel.CatalogViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var carAdapter: CarAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        carAdapter = CarAdapter { car ->
            val action = CatalogFragmentDirections.actionCatalogFragmentToCarDetailFragment(car)
            findNavController().navigate(action)
        }

        recyclerView.adapter = carAdapter

        val progressBar: ProgressBar = view.findViewById(R.id.loadingProgressBar)

        viewModel.cars.observe(viewLifecycleOwner) { cars ->
            if (cars.isEmpty()) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                carAdapter.submitList(cars)
            }
        }
        viewModel.loadCars()
    }
}
