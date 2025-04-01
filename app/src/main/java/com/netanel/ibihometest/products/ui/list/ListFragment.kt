package com.netanel.ibihometest.products.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.netanel.ibihometest.R
import com.netanel.ibihometest.databinding.FragmentListBinding
import com.netanel.ibihometest.login.utils.shouldShow
import com.netanel.ibihometest.login.utils.showTopSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListViewModel by activityViewModels()

    private lateinit var adapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        viewModel.loadProducts()
    }

    private fun setupRecyclerView() {
        adapter = ProductListAdapter { product ->
            viewModel.selectProduct(product)
            findNavController().navigate(R.id.action_listFragment_to_detailsFragment)
        }
        binding.recyclerViewProducts.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.products.observe(viewLifecycleOwner) { productList ->
            adapter.submitList(productList)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.shouldShow(isLoading)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                requireActivity().showTopSnackbar(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
