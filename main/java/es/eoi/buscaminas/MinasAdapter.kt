package es.eoi.buscaminas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_mina.view.*

class MinasAdapter(private val mDataSet: List<Mina>?, val function:() -> Unit) :
        RecyclerView.Adapter<MinasAdapter.MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_mina, parent, false)
            return MainViewHolder(v)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val data = mDataSet?.get(position)
            data?.let {
                holder.bindItems(data)
                holder.itemView.setOnClickListener {
                    data.pulsado = true
                    holder.bindItems(data)
                    if (data.bomba){
                        mostrarTodos()
                        notifyDataSetChanged()
                        function()
                    }

                    if (mDataSet?.count { it.pulsado } == mDataSet?.count { !it.bomba }) {
                        Snackbar.make(holder.itemView, "YOU WIN", Snackbar.LENGTH_LONG).show()
                        mostrarTodos()
                        notifyDataSetChanged()
                        function()
                    }
                }
            }
        }

        fun mostrarTodos(){
            mDataSet?.forEach{
                it.pulsado = true
            }
        }

        override fun getItemCount(): Int {
            return mDataSet?.size ?: 0
        }

        inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
            fun bindItems(data: Mina?) {
                if (data?.pulsado!!){
                        if (data.bomba) v.tvMina.text = "\uD83D\uDCA3"
                    else v.tvMina.text = "\uD83D\uDEA9"
                }
            }
        }
    }