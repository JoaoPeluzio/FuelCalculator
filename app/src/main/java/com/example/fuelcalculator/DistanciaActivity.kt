package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DistanciaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_distancia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Removemos o padding do TOP para o background subir
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets

        }

        val precoCombustivel = intent.getDoubleExtra("PRECO_COMBUSTIVEL", 0.0)
        val consumoPorLitro = intent.getDoubleExtra("CONSUMO_LITRO", 0.0)

        val edtDistancia = findViewById<EditText>(R.id.edtDistancia)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val btnDistanciaCalcular = findViewById<Button>(R.id.btnDistanciaCalcular)

        btnDistanciaCalcular.setOnClickListener {
            val sDistancia = edtDistancia.text.toString().replace(",", ".")
            if (sDistancia.isNotEmpty()) {
                val distancia = sDistancia.toDouble()

                // Cálculo: (Distância / Consumo) * Preço
                // Evita divisão por zero caso o consumo seja 0
                if (consumoPorLitro > 0) {
                    val resultado = (distancia / consumoPorLitro) * precoCombustivel
                    tvResultado.text = "O custo total será: R$ %.2f".format(resultado)
                    tvResultado.visibility = View.VISIBLE
                    
                    // Abrir a tela de Resultado
                    val intent = Intent(this, ResultadoActivity::class.java)
                    intent.putExtra("EXTRA_RESULTADO", resultado)
                    intent.putExtra("EXTRA_PRECO_LITRO", precoCombustivel)
                    intent.putExtra("EXTRA_CONSUMO", consumoPorLitro)
                    intent.putExtra("EXTRA_DISTANCIA", distancia)
                    startActivity(intent)
                } else {
                    tvResultado.text = "Consumo deve ser maior que zero"
                    tvResultado.visibility = View.VISIBLE
                }
            }
        }
    }
}