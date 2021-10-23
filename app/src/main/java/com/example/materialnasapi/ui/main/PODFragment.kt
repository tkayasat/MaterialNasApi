package com.example.materialnasapi.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlin.reflect.KProperty

class PODFragment : Fragment() {

    private val root: View
        get() {}
    private var _binding: PODFragment? = null
    private val binding: PODFragment
        get() {
            return _binding!!
        }

    companion object {
        fun newInstance(): PODFragment {
            return PODFragment()
        }

        fun inflate(inflater: LayoutInflater) {
        }
    }
    
    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData()
            .observe(this@PODFragment, Observer<PODDATA> { renderData(it) })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PODFragment.inflate(inflater)
        return binding.root
    }


    private fun renderData(PODDATA: PODDATA) {
        when (PODDATA) {
            is PODDATA.Error ->
                Snackbar.make(binding.root, PODDATA.error.toString(), Snackbar.LENGTH_SHORT).show()
            is PODDATA.Loading -> {
                Snackbar.make(binding.root, "Loading", Snackbar.LENGTH_LONG)
            }
            is PODDATA.Success -> {
                setData(PODDATA)
            }
        }
    }

    private fun setData(PODDATA: PODDATA) {

    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

    private fun ViewModelProvider.get(java: Class<PODViewModel>) {
    }
}

private operator fun <T> Lazy<T>.getValue(podFragment: PODFragment, property: KProperty<T?>): PODViewModel {

}
