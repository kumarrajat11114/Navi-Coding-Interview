package com.rajatkumar.navicodinginterview.pullRequest.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rajatkumar.navicodinginterview.R
import com.rajatkumar.navicodinginterview.databinding.FragmentPullRequestDetailBinding
import com.rajatkumar.navicodinginterview.common.domain.BindingFragment
import com.rajatkumar.navicodinginterview.common.domain.toDateString

class PullRequestDetailFragment : BindingFragment<FragmentPullRequestDetailBinding>() {

    private val args: PullRequestDetailFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPullRequestDetailBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateView()

        binding.apply {
            ibBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun populateView() {
        binding.apply {
            context?.let {
                Glide.with(it)
                    .load(args.pullRequest.user?.avatarUrl)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(ivProfile)
            }

            tvTitle.text = args.pullRequest.title
            tvDescription.text = args.pullRequest.body
            args.pullRequest.user?.login?.let {
                tvUser.text = context?.getString(R.string.by_, it)
            }
            args.pullRequest.createdAt?.toDateString()?.let {
                tvCreatedAt.text = context?.getString(R.string.create_at, it)
            }
            args.pullRequest.closedAt?.toDateString()?.let {
                tvClosedAt.text = context?.getString(R.string.closed_at, it)
            }
        }
    }
}
