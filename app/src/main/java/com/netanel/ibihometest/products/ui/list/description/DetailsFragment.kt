package com.netanel.ibihometest.products.ui.list.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.netanel.ibihometest.databinding.FragmentDetailsBinding
import com.netanel.ibihometest.products.MainActivity
import com.netanel.ibihometest.products.ui.list.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedProduct.observe(viewLifecycleOwner) { product ->
            product?.let {
                binding.textTitle.text = it.title
                binding.textDescription.text = it.description
                binding.textPrice.text = "₪${it.price}"

                Glide.with(requireContext())
                    .load(it.thumbnail)
                    .into(binding.imageThumbnail)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
