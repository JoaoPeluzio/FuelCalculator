package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class PrecoCombustivelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preco_combustivel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Removemos o padding do TOP para o background subir
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        //FindViewByID

        //Essa findview de toolbar serve para pegar todas as infos dela:
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_preco_combustivel) //1
        setSupportActionBar(toolbar)//2

        supportActionBar?.setDisplayHomeAsUpEnabled(true)//3
        supportActionBar?.setDisplayShowHomeEnabled(true)//4


        val edtPrecoCombustivel = findViewById<EditText>(R.id.edtPrecoCombustivel)
        val btnCombustivelProximo = findViewById<Button>(R.id.btnPrecoCombustivelProximo)

        btnCombustivelProximo.setOnClickListener {
            val sPreco = edtPrecoCombustivel.text.toString().replace(",", ".")
            if (sPreco.isNotEmpty()) {
                val preco = sPreco.toDouble()
                val intent = Intent(this, ConsumoPorLitroActivity::class.java)
                intent.putExtra("PRECO_COMBUSTIVEL", preco)
                startActivity(intent)
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}