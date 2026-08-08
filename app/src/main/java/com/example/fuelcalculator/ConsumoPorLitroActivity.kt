package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConsumoPorLitroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_consumo_por_litro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Removemos o padding do TOP para o background subir
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        val precoCombustivel = intent.getDoubleExtra("PRECO_COMBUSTIVEL", 0.0) //recupera dados da tela anterior
        val edtConsumoPorLitro = findViewById<EditText>(R.id.edtConsumoLitro)
        val btnConsumoPorLitro = findViewById<Button>(R.id.btnConsumoLitroProximo)

        btnConsumoPorLitro.setOnClickListener {
            val sConsumo = edtConsumoPorLitro.text.toString().replace(",", ".")
            if (sConsumo.isNotEmpty()) {
                val consumo = sConsumo.toDouble()
                val intent = Intent(this, DistanciaActivity::class.java)
                intent.putExtra("CONSUMO_LITRO", consumo)
                intent.putExtra("PRECO_COMBUSTIVEL", precoCombustivel)
                startActivity(intent)
            }
        }

    }
}