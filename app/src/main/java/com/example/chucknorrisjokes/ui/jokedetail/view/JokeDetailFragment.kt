package com.example.chucknorrisjokes.ui.jokedetail.view


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.databinding.FragmentJokeDetailBinding
import com.example.chucknorrisjokes.extension.getErrorStringOrNull
import com.example.chucknorrisjokes.ui.jokedetail.viewmodel.JokeDetailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_joke_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class JokeDetailFragment : Fragment() {

    private val viewModel: JokeDetailViewModel by viewModel()
    private val args: JokeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentJokeDetailBinding>(
            inflater, R.layout.fragment_joke_detail, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)?.supportActionBar?.title = args.category

        viewModel.let { vm ->

            vm.errorHandlerLiveData.observe(this, Observer {
                requireContext().getErrorStringOrNull(it)?.let { msg ->
                    Snackbar.make(rootLayout, msg, Snackbar.LENGTH_LONG).show()
                }
            })

            vm.getJokeByCategory(args.category)

            refreshFab.setOnClickListener {
                vm.getJokeByCategory(args.category)
            }
        }

    }

}
