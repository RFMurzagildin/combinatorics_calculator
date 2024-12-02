package com.app.calculator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.app.calculator.databinding.FragmentAllMarkedBinding
import java.math.BigInteger
import androidx.appcompat.app.AppCompatActivity

class AllMarkedFragment : Fragment() {

    private var binding: FragmentAllMarkedBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_marked, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAllMarkedBinding.bind(view)
        binding?.run {
            btnCalculate.setOnClickListener {
                hideKeyboard(editTextN)
                if(editTextN.text?.isNotEmpty() == true
                    &&
                    editTextM.text?.isNotEmpty() == true
                    &&
                    editTextK.text?.isNotEmpty() == true
                    ){
                    val n = editTextN.text.toString().toBigInteger()
                    val m = editTextM.text.toString().toBigInteger()
                    val k = editTextK.text.toString().toBigInteger()
                    if(n >= m && n >= k && k <= m){
                        tvAnswer.text = urnProbability(n,m,k).toString()
                    }else{
                        Toast.makeText(context, "The data was entered incorrectly", Toast.LENGTH_SHORT).show()
                        tvAnswer.text = "Ошибка!"
                    }
                }else{
                    Toast.makeText(context, "Enter numbers", Toast.LENGTH_SHORT).show()
                    tvAnswer.text = "Ошибка!"
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    fun factorial(n: BigInteger): BigInteger {
        return if (n <= BigInteger.ONE) BigInteger.ONE else BigInteger.valueOf(n.toLong()) * factorial(n - BigInteger.ONE)
    }
    fun combinations(n: BigInteger, k: BigInteger): BigInteger {
        return factorial(n) / (factorial(k) * factorial(n - k))
    }

    fun urnProbability(n: BigInteger, m: BigInteger, k: BigInteger): Double {
        val c_mk = combinations(m, k)
        val c_nk = combinations(n, k)
        return c_mk.toDouble() / c_nk.toDouble()
    }
    fun hideKeyboard(view: View) {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}