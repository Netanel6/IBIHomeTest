package com.netanel.ibihometest.products.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.netanel.ibihometest.databinding.FragmentFavoritesBinding
import com.netanel.ibihometest.products.ui.list.ProductListAdapter
import com.netanel.ibihometest.products.ui.list.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListViewModel by activityViewModels()
    private lateinit var adapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ProductListAdapter(onFavoriteClick = {}, onItemClick = {})

        binding.recyclerViewFavorites.adapter = adapter

        viewModel.favorites.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loadFavorites()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
 