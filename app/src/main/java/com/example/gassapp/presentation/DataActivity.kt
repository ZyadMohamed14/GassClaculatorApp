package com.example.gassapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gassapp.MainActivity
import com.example.gassapp.R
import com.example.gassapp.databinding.ActivityDataBinding
import com.example.gassapp.domain.models.model.CarsModel

class DataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBinding
    private lateinit var carsModel: CarsModel
    private lateinit var brandAdapter: ArrayAdapter<String>
    private lateinit var modelAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        carsModel = CarsModel()

        // Set up the brand adapter
        brandAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carsModel.brands)
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.brandSpinner.adapter = brandAdapter

        // Set up the listener for brand selection
        binding.brandSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedBrand = binding.brandSpinner.selectedItem.toString()
                getModels(selectedBrand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing here
            }
        }

        // Optionally, trigger the listener manually to set the models for the initially selected brand
        binding.brandSpinner.selectedItem?.toString()?.let {
            getModels(it)
        }

        binding.confirmButton.setOnClickListener {
            confirm()
        }
    }
    private fun getModels(brand: String) {
        modelAdapter = when (brand) {
            "Audi" -> ArrayAdapter(this, android.R.layout.simple_spinner_item, carsModel.audiModels)
            "BMW" -> ArrayAdapter(this, android.R.layout.simple_spinner_item, carsModel.bmwModels)
            "Chevrolet" -> ArrayAdapter(this, android.R.layout.simple_spinner_item, carsModel.chevroletModels)
            "Ford" -> ArrayAdapter(this, android.R.layout.simple_spinner_item, carsModel.fordModels)
            "Datsun" -> ArrayAdapter(this, android.R.layout.simple_spinner_item, carsModel.datsunModels)
            else -> ArrayAdapter(this, android.R.layout.simple_spinner_item, emptyList())
        }
        binding.modelSpinner.adapter = modelAdapter
    }
    private fun confirm() {
        val speed = binding.speedEt.text.toString()
        val ccEngine = binding.ccEt.text.toString()

        if (speed.isEmpty() || ccEngine.isEmpty()) {
            Toast.makeText(this, "Please enter valid data", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isNumeric(speed) || !isNumeric(ccEngine)) {
            Toast.makeText(this, "Please enter valid data", Toast.LENGTH_SHORT).show()
            return
        }

        val speedInt = speed.toInt()
        val ccEngineInt = ccEngine.toInt()

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("speed", speedInt)
            putExtra("cc", ccEngineInt)
        }
        startActivity(intent)
    }

    companion object {
        fun isNumeric(str: String): Boolean {
            return str.all { it.isDigit() }
        }
    }
}