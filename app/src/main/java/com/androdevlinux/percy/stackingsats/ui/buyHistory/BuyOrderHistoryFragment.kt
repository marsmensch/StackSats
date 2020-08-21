package com.androdevlinux.percy.stackingsats.ui.buyHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.androdevlinux.percy.stackingsats.R
import com.androdevlinux.percy.stackingsats.adapters.BuyOrderHistoryAdapter
import com.androdevlinux.percy.stackingsats.pojo.ErrorResponseBean
import com.androdevlinux.percy.stackingsats.pojo.contract.Contract
import com.androdevlinux.percy.stackingsats.pojo.contract.ListingContractResponseBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_buy_order_history.*

class BuyOrderHistoryFragment: Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var buyOrderHistoryViewModel: BuyOrderHistoryViewModel
    private var contractResponseBeanArrayList: ArrayList<Contract>? = null
    private var buyOrderHistoryAdapter: BuyOrderHistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buyOrderHistoryViewModel =
            ViewModelProvider(this).get(BuyOrderHistoryViewModel::class.java)
        (context as AppCompatActivity).supportActionBar!!.title = requireContext().getString(R.string.title_buy_order_history)
    }

    override fun onResume() {
        super.onResume()
        (context as AppCompatActivity).supportActionBar!!.title = requireContext().getString(R.string.title_buy_order_history)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buy_order_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contractResponseBeanArrayList = ArrayList()
        buyOrderHistoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        buyOrderHistoryRecyclerView.itemAnimator = null
        buyOrderHistoryRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        buyOrderHistoryAdapter = BuyOrderHistoryAdapter(contractResponseBeanArrayList!!, requireContext())
        buyOrderHistoryRecyclerView.adapter = buyOrderHistoryAdapter

        buyOrderHistoryViewModel.listingAllContracts()
            .observe(viewLifecycleOwner, {
                if (null != it) {
                    if (it.code() == 200) {
                        val myType = object : TypeToken<ListingContractResponseBean>() {}.type
                        val responseList =
                            Gson().fromJson<ListingContractResponseBean>(
                                it.body()!!.charStream(),
                                myType
                            )
                        contractResponseBeanArrayList!!.clear()
                        contractResponseBeanArrayList!!.addAll(responseList.contracts!!)
                        buyOrderHistoryAdapter!!.notifyDataSetChanged()
                    } else {
                        val errorBody = Gson().fromJson(
                            it.errorBody()!!.charStream(),
                            ErrorResponseBean::class.java
                        )
                        Toasty.error(
                            requireContext(),
                            errorBody.status!!,
                            Toast.LENGTH_SHORT,
                            true
                        ).show()
                    }
                }
                progressBar.visibility = View.GONE
            })
        swipeContainer.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        swipeContainer.isRefreshing = true
        progressBar.visibility = View.VISIBLE
        buyOrderHistoryViewModel.listingAllContracts()
            .observe(viewLifecycleOwner, {
                if (it.code() == 200) {
                    val myType = object : TypeToken<ListingContractResponseBean>() {}.type
                    val responseList =
                        Gson().fromJson<ListingContractResponseBean>(it.body()!!.charStream(), myType)
                    contractResponseBeanArrayList!!.clear()
                    contractResponseBeanArrayList!!.addAll(responseList.contracts!!)
                    buyOrderHistoryAdapter!!.notifyDataSetChanged()
                } else {
                    val errorBody = Gson().fromJson(
                        it.errorBody()!!.charStream(),
                        ErrorResponseBean::class.java
                    )
                    Toasty.error(
                        requireContext(),
                        errorBody.status!!,
                        Toast.LENGTH_SHORT,
                        true
                    ).show()
                }
                progressBar.visibility = View.GONE
                swipeContainer.isRefreshing = false
            })
    }
}