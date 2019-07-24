package com.example.factviewer.ui.factslistfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.factviewer.R
import com.example.factviewer.domain.animalfact.AnimalFact
import kotlinx.android.synthetic.main.fact_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class FactListAdapter : RecyclerView.Adapter<FactListAdapter.FactListHolder>() {
    private var clickListener : (Int, String) -> Unit = { i: Int, s: String -> }

    fun setOnClickListener(listener : (Int, String) -> Unit){
        clickListener = listener
    }
    private var facts: List<AnimalFact> = listOf()
    fun addFacts(facts: List<AnimalFact>) {
        this.facts = facts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    override fun onBindViewHolder(holder: FactListHolder, position: Int) {
        val factItem = facts[position]
        holder.factTxt.text = factItem.content
        holder.time.text = factItem.time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fact_item, parent, false)
        return FactListHolder(view, clickListener)
    }

    class FactListHolder(itemView: View, private val clickListener: (Int, String) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            clickListener.invoke(adapterPosition, time.text.toString())
        }

        var factTxt = itemView.textViewFact as TextView
        var time = itemView.editTextTime as TextView
    }
}

