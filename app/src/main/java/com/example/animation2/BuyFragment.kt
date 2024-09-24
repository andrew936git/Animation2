package com.example.animation2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BuyFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private var productList = arrayListOf<Product>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var buyButton: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_buy, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.apply {
            setTitle("Просрочечка")
            setNavigationIcon(R.drawable.ic_exit)
            setNavigationOnClickListener {
                requireActivity().finishAffinity()
                requireActivity().finish()
            }
        }
        buyButton = view.findViewById(R.id.buyButtonFB)
        recyclerView = view?.findViewById(R.id.recyclerView)!!
        recyclerView.layoutManager = LinearLayoutManager(context)
        productList = arguments?.getSerializable("list") as ArrayList<Product>
        val adapter = CustomAdapter(productList)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        adapter.setOnProductClickListener(object:
            CustomAdapter.OnProductClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onProductClick(product: Product, position: Int) {
                val dialog = AlertDialog.Builder(requireContext())
                dialog.setTitle("Удалить из корзины?")
                dialog.setPositiveButton("Удалить") { _, _ ->
                    productList.remove(Product.list[position])
                    adapter.notifyDataSetChanged()
                }
                dialog.setNegativeButton("Нет") { _, _ -> }
                dialog.create().show()
            }
        })
        if (productList.size > 0){
            val animationImage = AnimationUtils.loadAnimation(context, R.anim.fade)
            buyButton.startAnimation(animationImage)
            buyButton.visibility = View.VISIBLE
        }

        buyButton.setOnClickListener{
            val bundle = Bundle()
            bundle.putSerializable("buyList", productList)

            val transaction = fragmentManager?.beginTransaction()
            val amountFragment = AmountFragment()
            amountFragment.arguments = bundle

            transaction?.replace(R.id.fragment_container, amountFragment)
            transaction?.addToBackStack(null)
            transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction?.commit()
        }


        return view
    }

}