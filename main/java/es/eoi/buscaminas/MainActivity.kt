package es.eoi.buscaminas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lista = arrayListOf<Mina>()
    var mAdapter: MinasAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rellenarTablero()
        loadAdapter()

        title = "Busca Minas"

        buttonStart.setOnClickListener{
            buttonStart.isEnabled = false
            lista.clear()
            rellenarTablero()
            loadAdapter()
        }
    }

    fun rellenarTablero(){
        val ver = "3"
        for (num in 1..25){
            lista.add(Mina((1..ver.toInt()).random() == 1, false))
        }
    }

    fun loadAdapter(){
        mAdapter = MinasAdapter(lista) {
            buttonStart.isEnabled = true
        }
        recyclerMina.layoutManager = GridLayoutManager(this, 5)
        recyclerMina.adapter = mAdapter
    }
}