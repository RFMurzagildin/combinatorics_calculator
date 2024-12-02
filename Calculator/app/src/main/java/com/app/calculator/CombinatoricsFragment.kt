package com.app.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.app.calculator.databinding.FragmentCombinatoricsBinding
import com.app.calculator.databinding.FragmentMenuBinding
import java.math.BigInteger
import kotlin.time.times

//нужно ввести интервал допустимых значений
//
class CombinatoricsFragment : Fragment(R.layout.fragment_combinatorics) {

    private var binding: FragmentCombinatoricsBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCombinatoricsBinding.bind(view)
        binding?.run {
            btnCalculate.setOnClickListener {
                hideKeyboard(editTextN)
                if(editTextN.text?.isNotEmpty() == true){
                    val n = editTextN.text.toString().toBigInteger()
                    tvCountPermutationsWithoutRepetitions.text = factorial(n).toString()
                    if(editTextK.text?.isNotEmpty() == true){
                        val k = editTextK.text.toString().toBigInteger()
                        if(n >= k){
                            tvCountPlacementWithoutRepetitions.text = countPlacementWithoutRepetitions(n, k).toString()
                            tvCountCombinationsWithoutRepetitions.text = countCombinationsWithoutRepetitions(n,k).toString()
                            tvCountCombinationsWithRepetitions.text = countCombinationsWithRepetitions(n, k).toString()
                            tvCountPlacementWithRepetitions.text = countPlacementWithRepetitions(n, k).toString()
                        }else{
                            tvCountCombinationsWithRepetitions.text = countCombinationsWithRepetitions(n, k).toString()
                            tvCountPlacementWithRepetitions.text = countPlacementWithRepetitions(n, k).toString()
                            tvCountPlacementWithoutRepetitions.text = "Ошибка!"
                            tvCountCombinationsWithoutRepetitions.text = "Ошибка!"
                        }
                        if(editTextNumbers.text?.isNotEmpty() == true){
                            val numbers = editTextNumbers.text.toString()
                            tvCountPermutationsWithRepetitions.text = countPermutationsWithRepetitions(n, k, numbers).toString()
                        }
                    }else{
                        tvCountPermutationsWithRepetitions.text = "Введите k и n1,n2,...,nk!"
                        tvCountCombinationsWithoutRepetitions.text = "Введите k!"
                        tvCountCombinationsWithRepetitions.text = "Введите k!"
                        tvCountPlacementWithoutRepetitions.text = "Введите k!"
                        tvCountPlacementWithRepetitions.text = "Введите k!"
                    }

                }else{
                    Toast.makeText(context, "Enter numbers", Toast.LENGTH_SHORT).show()
                    tvCountPermutationsWithoutRepetitions.text = "Ошибка!"
                    tvCountPermutationsWithRepetitions.text = "Ошибка!"
                    tvCountCombinationsWithoutRepetitions.text = "Ошибка!"
                    tvCountCombinationsWithRepetitions.text = "Ошибка!"
                    tvCountPlacementWithoutRepetitions.text = "Ошибка!"
                    tvCountPlacementWithRepetitions.text = "Ошибка!"
                }
            }
        }



    }
    //функция для вычисления количества перестановок без повторений
    fun factorial(n: BigInteger): BigInteger {
        return if (n <= BigInteger.ONE) BigInteger.ONE else BigInteger.valueOf(n.toLong()) * factorial(n - BigInteger.ONE)
    }
    //функция для вычисления количества перестановок с повторениями
    fun countPermutationsWithRepetitions(n: BigInteger, k: BigInteger, numbers: String): BigInteger {
        val numberList = numbers.split(",").map { it.trim().toBigInteger() }
        val totalSum = numberList.reduce { acc, i -> acc + i }

        if(numberList != k){
            Toast.makeText(context, "Количество всех n_k должна равняться k.", Toast.LENGTH_SHORT).show()
            return BigInteger.ZERO
        }

        if (totalSum != n) {
            Toast.makeText(context, "Сумма всех n_k должна равняться n.", Toast.LENGTH_SHORT).show()
            return BigInteger.ZERO
        }

        val factorialN = factorial(n)
        val denominator = numberList.map { factorial(it) }.reduce { acc, i -> acc * i }
        return factorialN / denominator
    }
    //функция для вычисления количества размещений без повторений
    fun countPlacementWithoutRepetitions(n: BigInteger, k: BigInteger): BigInteger{
        return factorial(n) / factorial(n-k)
    }
    //функция для вычисления количества размещений с повторениями
    fun countPlacementWithRepetitions(n: BigInteger, k: BigInteger): BigInteger{
        var result = BigInteger.ONE
        var exponent = k
        while (exponent > BigInteger.ZERO) {
            result *= n
            exponent -= BigInteger.ONE
        }
        return result
    }
    //функция для вычисления количества сочетаний без повторений
    fun countCombinationsWithoutRepetitions(n: BigInteger, k: BigInteger): BigInteger {
        return factorial(n) / (factorial(k) * factorial(n - k))
    }
    //функция для вычисления количества сочетаний с повторениями
    fun countCombinationsWithRepetitions(n: BigInteger, k: BigInteger): BigInteger{
        return factorial(n + k - BigInteger.ONE) / (factorial(k) * factorial(n - BigInteger.ONE))
    }

    fun numberToDigitList(number: Long): MutableList<Long> {
        val digitList = mutableListOf<Long>()

        // Преобразуем число в строку, затем извлекаем цифры и добавляем в список
        number.toString().forEach { char ->
            digitList.add(char.toString().toLong())
        }
        return digitList
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    fun hideKeyboard(view: View) {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}