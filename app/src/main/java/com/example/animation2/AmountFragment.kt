package com.example.animation2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar


class AmountFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var amountTV: TextView
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_amount, container, false)

        toolbar = view.findViewById(R.id.toolbar)
        toolbar.apply {
            setTitle("Просрочечка")
            setNavigationIcon(R.drawable.ic_exit)
            setNavigationOnClickListener {
                requireActivity().finishAffinity()
                requireActivity().finish()
            }
        }
        amountTV = view.findViewById(R.id.amountTV)
        listView = view.findViewById(R.id.listView)
        val list = arguments?.getSerializable("buyList") as ArrayList<Product>
        val checkList = getCheckList(list)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, checkList)
        listView.adapter = adapter
        var amount = 0
        for (i in list){
            amount += i.price
        }

        amountTV.text = "Общая сумма покупок = $amount"

        return view
    }

    private  fun getCheckList(list: ArrayList<Product>): MutableList<String>{
        val newList = mutableListOf<String>()
        for (i in list){
            val text = "${i.name}, цена: ${i.price}"
            newList.add(text)
        }
        return newList
    }

}