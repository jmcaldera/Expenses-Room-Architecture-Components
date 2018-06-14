package com.jmcaldera.roomexpenses.features.transactions.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jmcaldera.roomexpenses.R
import com.jmcaldera.roomexpenses.core.extensions.inflate
import com.jmcaldera.roomexpenses.features.model.TransactionCategoryView
import kotlinx.android.synthetic.main.item_transaction.view.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import javax.inject.Inject
import kotlin.properties.Delegates

class TransactionsCategoryAdapter
@Inject constructor(): RecyclerView.Adapter<TransactionsCategoryAdapter.ViewHolder>() {

    internal var items: List<TransactionCategoryView> by Delegates.observable(emptyList()) { property, oldValue, newValue ->
        notifyChanges(oldValue, newValue)
    }

    internal var clickListener: (TransactionCategoryView) -> Unit = {}

    private fun notifyChanges(oldValue: List<TransactionCategoryView>, newValue: List<TransactionCategoryView>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    oldValue[oldItemPosition].transactionId == newValue[newItemPosition].transactionId

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

        fun bind(transaction: TransactionCategoryView, itemClick: (TransactionCategoryView) -> Unit) {
            with(transaction) {
                itemView.name.text = transactionName
                itemView.categoryName.text = categoryName
                itemView.amount.text = transactionAmount.toString()
                itemView.date.text = transactionDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}