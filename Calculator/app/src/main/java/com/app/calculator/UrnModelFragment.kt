package com.app.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.calculator.databinding.FragmentUrnModelBinding

class UrnModelFragment : Fragment() {

    private var binding: FragmentUrnModelBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_urn_model, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUrnModelBinding.bind(view)
        binding?.run {
            clAll.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                    .replace(R.id.fragment_container, AllMarkedFragment(), "ToAllMarkedFragmentFromUrnModelFragment")
                    .addToBackStack("ToAllMarkedFragmentFromUrnModelFragment")
                    .commit()
            }
            clNotAll.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                    .replace(R.id.fragment_container, NotAllMarkedFragment(), "ToNotAllMarkedFragmentFromUrnModelFragment")
                    .addToBackStack("ToNotAllMarkedFragmentFromUrnModelFragment")
                    .commit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}