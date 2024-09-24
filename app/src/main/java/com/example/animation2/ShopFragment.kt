package com.example.animation2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ShopFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private val basketList = arrayListOf<Product>()
    private lateinit var basketButtonFB: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.apply {
            setTitle("Просрочечка")
            setNavigationIcon(R.drawable.ic_exit)
            setNavigationOnClickListener {
                requireActivity().finishAffinity()
                requireActivity().finish()
            }
        }
        recyclerView = view?.findViewById(R.id.recyclerView)!!
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = CustomAdapter(Product.list)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        adapter.setOnProductClickListener(object:
            CustomAdapter.OnProductClickListener {
            override fun onProductClick(product: Product, position: Int) {
                val dialog = AlertDialog.Builder(requireContext())
                dialog.setTitle("Добавить в корзину?")
                dialog.setPositiveButton("В корзину") { _, _ ->
                    basketList.add(Product.list[position])
                }
                dialog.setNegativeButton("Отмена") { _, _ -> }
                dialog.create().show()
            }
        })

        basketButtonFB = view.findViewById(R.id.basketButtonFB)
        basketButtonFB.setOnClickListener{
            val bundle = Bundle()
            bundle.putSerializable("list", basketList)

            val transaction = fragmentManager?.beginTransaction()
            val buyFragment = BuyFragment()
            buyFragment.arguments = bundle

            transaction?.replace(R.id.fragment_container, buyFragment)
            transaction?.addToBackStack(null)
            transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction?.commit()
        }

        return view
    }

}