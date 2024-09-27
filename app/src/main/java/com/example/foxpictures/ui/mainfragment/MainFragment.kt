package com.example.foxpictures.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.foxpictures.R
import com.example.foxpictures.databinding.FragmentMainBinding
import com.example.foxpictures.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserve()
        setupClick()
    }

    private fun setupObserve() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentImage.collect { imageResponseState ->
                    when (imageResponseState) {
                        is Resource.Successful -> onSuccess(url = imageResponseState.data.url)
                        is Resource.Loading -> onLoading()
                        is Resource.Error -> onError(message = imageResponseState.message)
                        is Resource.Default -> onDefault()
                    }
                }
            }
        }
    }

    private fun onSuccess(url: String) = views {
        showPicture(url)
        removeLoadingState()
        fadeIn()
    }

    private fun showPicture(url: String) = views {
        Glide.with(this@MainFragment).load(url).into(foxImage)
    }

    private fun fadeIn() = views {
        foxImage.alpha = 1f
        foxImage.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.fade_in
            )
        )
    }

    private fun onLoading() = views {
        setLoadingState()
    }

    private fun setLoadingState() = views {
        foxImage.alpha = 0.5f
        progressBar.visibility = View.VISIBLE
        getImageButton.isEnabled = false
    }

    private fun onError(message: String?) = views {
        foxImage.alpha = 0.5f
        removeLoadingState()
        showErrorMessage(message)
    }

    private fun showErrorMessage(message: String?) {
        Toast.makeText(context, message ?: "Something went wrong..Try again", Toast.LENGTH_LONG)
            .show()
    }

    private fun onDefault() = views {
        removeLoadingState()
    }

    private fun removeLoadingState() = views {
        progressBar.visibility = View.INVISIBLE
        getImageButton.isEnabled = true
    }

    private fun setupClick() = views {
        getImageButton.setOnClickListener {
            viewModel.getFoxPicture()
        }
    }

    private fun <T> views(block: FragmentMainBinding.() -> T): T? = binding?.block()
}