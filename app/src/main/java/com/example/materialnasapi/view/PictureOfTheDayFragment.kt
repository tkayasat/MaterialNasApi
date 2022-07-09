package com.example.materialnasapi.view

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialnasapi.R
import com.example.materialnasapi.api.ApiActivity
import com.example.materialnasapi.api.ApiBottomActivity
import com.example.materialnasapi.api.CoordinatorLayout
import com.example.materialnasapi.databinding.FragmentMainBinding
import com.example.materialnasapi.databinding.FragmentMainStartBinding
import com.example.materialnasapi.picture.PictureOfTheDayViewModel
import com.example.materialnasapi.ui.BottomNavigationDrawerFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_main.*


class PictureOfTheDayFragment : Fragment() {

    private var isExpanded = false

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var _binding: FragmentMainStartBinding? = null
    val binding: FragmentMainStartBinding
        get() {
            return _binding!!
        }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentMainStartBinding.inflate(inflater)
        setActionBar()

        return binding.root
    }

    private var isMain = true
    private fun setActionBar() {
        (context as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_hamburger_menu_bottom_bar
                    )
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest()

        binding.inputLayout.setEndIconOnClickListener {
            val i = Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            }
            startActivity(i)
        }
        bottomSheetBehavior = BottomSheetBehavior.from(binding.includeLayout.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        animationImage()

    }

    private fun animationImage() {
        image_view.setOnClickListener {
            isExpanded = !isExpanded
            TransitionManager.beginDelayedTransition(
                main, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )

            val params: ViewGroup.LayoutParams = image_view.layoutParams
            params.height = if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else
                ViewGroup.LayoutParams.WRAP_CONTENT
            image_view.layoutParams = params
            image_view.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else
                    ImageView.ScaleType.FIT_CENTER
        }
    }


    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> {
                Toast.makeText(context, "AppState error", Toast.LENGTH_LONG).show()
            }
            is AppState.Loading -> {

            }
            is AppState.Success -> {
                binding.imageView.load(data.serverResponseData.url) {
                    kotlin.error(R.drawable.ic_load_error_vector)
                }
                data.serverResponseData.explanation?.let {
                    val spannable = SpannableStringBuilder(it)

                    spannable.setSpan(
                        ForegroundColorSpan(resources.getColor(R.color.colorPrimary)),
                        0,
                        spannable.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    binding.includeLayoutTv.textViewForFonts.text = spannable
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.app_bar_fav -> {
                Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, ApiActivity::class.java))
            }

            R.id.app_bar_change_style -> {
                Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, ApiBottomActivity::class.java))
            }
            R.id.app_bar_coordinator_layout -> {
                startActivity(Intent(context, CoordinatorLayout::class.java))
            }

            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}