package com.muratgul.colorapp

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

import androidx.cardview.widget.CardView
import com.google.android.material.slider.Slider
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var sldR: Slider
    private lateinit var sldG: Slider
    private lateinit var sldB: Slider
    private lateinit var sldA: Slider
    private lateinit var textR: TextView
    private lateinit var textG: TextView
    private lateinit var textB: TextView
    private lateinit var textA: TextView
    private lateinit var randomBtn: TextView

    private var cR: Int = 0
    private var cG: Int = 0
    private var cB: Int = 0
    private var cA: Int = 255

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        randomBtn = findViewById(R.id.btnRandom)

        sldR = findViewById(R.id.sliderR)
        sldG = findViewById(R.id.sliderG)
        sldB = findViewById(R.id.sliderB)
        sldA = findViewById(R.id.sliderA)

        textR = findViewById(R.id.textR)
        textG = findViewById(R.id.textG)
        textB = findViewById(R.id.textB)
        textA = findViewById(R.id.textA)

        randomBtn.setOnClickListener {
            cR = (0..255).random()
            cG = (0..255).random()
            cB = (0..255).random()
            setSliderValue(cR, cG, cB)
            setColor()
        }


        sldR.addOnChangeListener { slider, value, fromUser ->
            textR.text = value.toInt().toString()
            cR = value.toInt()
            setColor()
        }

        sldG.addOnChangeListener { slider, value, fromUser ->
            textG.text = value.toInt().toString()
            cG = value.toInt()
            setColor()
        }

        sldB.addOnChangeListener { slider, value, fromUser ->
            textB.text = value.toInt().toString()
            cB = value.toInt()
            setColor()
        }

        sldA.addOnChangeListener { slider, value, fromUser ->
            textA.text = value.toInt().toString()
            cA = value.toInt()
            setColor()
        }


    }

    private fun setSliderValue(r: Int, g: Int, b: Int) {
        sldR.value = r.toFloat()
        sldG.value = g.toFloat()
        sldB.value = b.toFloat()
        textR.text = r.toString()
        textG.text = g.toString()
        textB.text = b.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun setColor() {
        val cView = findViewById<CardView>(R.id.panelColor)
        val hex = findViewById<TextView>(R.id.hexCode)

        hex.text = String.format("#%02x%02x%02x", cR, cG, cB)
            .toUpperCase(Locale.ROOT) + "\n\n" + String.format("#%02x%02x%02x%02x", cA, cR, cG, cB)
            .toUpperCase(Locale.ROOT)

        cView.setBackgroundColor(Color.argb(cA, cR, cG, cB))
    }
}