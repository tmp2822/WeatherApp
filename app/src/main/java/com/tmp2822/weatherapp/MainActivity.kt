package com.tmp2822.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEditText: EditText = findViewById(R.id.zipcodeEditText)
        val enterButton: Button = findViewById(R.id.enterButton)

        enterButton.setOnClickListener {
            val zipcode: String = zipcodeEditText.text.toString()

            if(zipcode.length != 5){
                Toast.makeText(this, R.string.zipcode_entry_error, Toast.LENGTH_SHORT).show()
            }else{
                forecastRepository.loadForecast(zipcode)
            }

            val forecastList: RecyclerView = findViewById(R.id.forecastList)

            val weeklyForecastObserver = Observer<List<DailyForecast>>{ forcastItems ->
                // update our list adapter
                Toast.makeText(this, "Loaded Items", Toast.LENGTH_SHORT).show()
            }
            forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
        }
    }



}