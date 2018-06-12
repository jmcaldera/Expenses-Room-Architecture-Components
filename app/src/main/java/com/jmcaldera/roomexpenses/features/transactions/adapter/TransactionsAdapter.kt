package com.jmcaldera.roomexpenses.features.transactions.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jmcaldera.roomexpenses.R
import com.jmcaldera.roomexpenses.core.extensions.inflate
import com.jmcaldera.roomexpenses.features.model.TransactionView
import kotlinx.android.synthetic.main.item_transaction.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class TransactionsAdapter
@Inject constructor(): RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    internal var items: List<TransactionView> by Delegates.observable(emptyList()) { property, oldValue, newValue ->
        notifyChanges(oldValue, newValue)
    }

    internal var clickListener: (TransactionView) -> Unit = {}

    private fun notifyChanges(oldValue: List<TransactionView>, newValue: List<TransactionView>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    oldValue[oldItemPosition].id == newValue[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    oldValue[oldItemPosition] == newValue[newItemPosition]

            override fun getOldListSize(): Int = oldValue.size

            override fun getNewListSize(): Int = newValue.size

        })
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_transaction))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position], clickListener)

    override fun getItemCount(): Int = items.size


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(transaction: TransactionView, itemClick: (TransactionView) -> Unit) {
            with(transaction) {
                itemView.name.text = name
                itemView.categoryName.text = categoryId
                itemView.amount.text = amount.toString()
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}