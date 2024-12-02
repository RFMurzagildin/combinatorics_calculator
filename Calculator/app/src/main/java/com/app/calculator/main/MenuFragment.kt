package com.app.calculator.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.calculator.CombinatoricsFragment
import com.app.calculator.R
import com.app.calculator.UrnModelFragment
import com.app.calculator.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var binding: FragmentMenuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)
        binding?.run {
            combinatorics.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                    .replace(R.id.fragment_container, CombinatoricsFragment(), "ToCombinatoricsFragmentFromMenuFragment")
                    .addToBackStack("ToCombinatoricsFragmentFromMenuFragment")
                    .commit()
            }
            urnModel.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                    .replace(R.id.fragment_container, UrnModelFragment(), "ToUrnModelFragmentFromMenuFragment")
                    .addToBackStack("ToUrnModelFragmentFromMenuFragment")
                    .commit()
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}