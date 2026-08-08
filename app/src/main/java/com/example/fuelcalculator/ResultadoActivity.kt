package com.example.fuelcalculator

import android.R.attr.defaultValue
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Removemos o padding do TOP para o background subir
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets

        }
        //retrieve infos from textfields
        val tvPreco = findViewById<TextView>(R.id.tvPrecoViagem)
        val tvPrecoLitro = findViewById<TextView>(R.id.tvPrecoLitroValor)
        val tvConsumoLitro = findViewById<TextView>(R.id.tvConsumoLitroValor)
        val tvDistancia = findViewById<TextView>(R.id.tvDistanciaValor)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarInicio)

        //retrieve infos from before screen
        val precoTotal = intent.getDoubleExtra("EXTRA_RESULTADO", 0.0)
        val precoCombustivel = intent.getDoubleExtra("EXTRA_PRECO_LITRO", 0.0)
        val consumoPorLitro = intent.getDoubleExtra("EXTRA_CONSUMO", 0.0)
        val distancia = intent.getDoubleExtra("EXTRA_DISTANCIA", 0.0)

        //put text on new screen
        tvPreco.text = "R$ %.2f".format(precoTotal)
        tvPrecoLitro.text = "R$ %.2f".format(precoCombustivel)
        tvConsumoLitro.text = "%.2f KM/L".format(consumoPorLitro)
        tvDistancia.text = "%.2f KM".format(distancia)

        btnVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
        }
    }
}