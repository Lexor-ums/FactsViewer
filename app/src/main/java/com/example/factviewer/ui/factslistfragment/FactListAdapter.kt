package com.example.factviewer.ui.factslistfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.factviewer.MainApplication
import com.example.factviewer.R
import com.example.factviewer.domain.animalfact.AnimalFact
import kotlinx.android.synthetic.main.fact_item.view.*

class FactListAdapter : RecyclerView.Adapter<FactListAdapter.FactListHolder>() {


    private var clickListener: (Int, String) -> Unit = { _: Int, _: String -> }

    private var likeClicker: (String, Boolean) -> Unit = { _: String, _: Boolean -> }

    private var facts: List<AnimalFact> = listOf()

    /**
     * уснатовка обработчика сибытия выбора элемента из списка
     * @param listener ссылка на функцию-обработчик
     */
    fun setOnClickListener(listener: (Int, String) -> Unit) {
        clickListener = listener
    }

    /**
     * установка обраьотчика события нажатия на кнопку лайка
     * @param clicker - ссылка на функцию -обработчик
     */
    fun setLikeClicker(clicker: (String, Boolean) -> Unit) {
        likeClicker = clicker
    }

    /**
     * загрузка списка фактов в адаптер
     * @param facts - список фактов
     */
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
        holder.time.text = factItem.id
        holder.setLikeState(factItem.isLiked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fact_item, parent, false)
        return FactListHolder(view, clickListener, likeClicker)
    }

    class FactListHolder(
        itemView: View, private val clickListener: (Int, String) -> Unit,
        private val clicker: (String, Boolean) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var isLiked = false
        var factTxt = itemView.textViewFact as TextView
        var time = itemView.editTextTime as TextView
        private var button: ImageButton = itemView.likeButton

        init {
            itemView.setOnClickListener(this)

            button.setOnClickListener {
                if (isLiked)
                    isLiked = false
                else if (!isLiked)
                    isLiked = true
                setLikeState(isLiked)
                clicker.invoke(time.text.toString(), isLiked)
            }
        }

        /**
         * установка состояния кнопки лайка
         * @param likeState - состояние нажатия
         */

        fun setLikeState(likeState: Boolean) {
            isLiked = likeState
            when (isLiked) {
                true -> button.setImageDrawable(
                    MainApplication.instance.getRsources().getDrawable(
                        android.R.drawable.star_big_on,
                        null
                    )
                )
                false -> button.setImageDrawable(
                    MainApplication.instance.getRsources().getDrawable(
                        android.R.drawable.star_big_off,
                        null
                    )
                )
            }
        }

        override fun onClick(v: View?) {
            clickListener.invoke(adapterPosition, time.text.toString())
        }
    }
}

