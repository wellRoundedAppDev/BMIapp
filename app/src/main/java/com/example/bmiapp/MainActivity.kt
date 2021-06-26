package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bmiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.calculateBMIButton.setOnClickListener{

            if(userInputIsNotEmpty()){

                val bmi = calculateBMI()
                displayBodySize(bmi)
            }
        }
    }



    private fun calculateBMI(): Float{

        val weightInKg = binding.weightTextView.text.toString().toFloat()
        val heightInMeter = binding.heightTextView.text.toString().toFloat()
        val bmi = weightInKg / (heightInMeter * heightInMeter)

        binding.BMITextView.text = bmi.toString()

        return bmi

    }

    private fun displayBodySize(bmi: Float){

        if(bmi > 0 && bmi < 18.5){
            binding.bodySizeImageView.setImageResource(R.drawable.ic_underweight)
            Toast.makeText(this, "You're Underweight", Toast.LENGTH_LONG).show()
        }
        else if(bmi >= 18.5 && bmi < 25){
            binding.bodySizeImageView.setImageResource(R.drawable.ic_healthy)
            Toast.makeText(this, "You're Healthy", Toast.LENGTH_LONG).show()
        }
        else if(bmi >= 25 && bmi < 30) {
            binding.bodySizeImageView.setImageResource(R.drawable.ic_overweight)
            Toast.makeText(this, "You're Overweight", Toast.LENGTH_LONG).show()

        }
        else if(bmi >= 30){
            binding.bodySizeImageView.setImageResource(R.drawable.ic_obesity)
            Toast.makeText(this, "You're Obese", Toast.LENGTH_LONG).show()

        }
        else{
            Toast.makeText(this, "Wrong Input", Toast.LENGTH_LONG).show()
        }

    }

    private fun userInputIsNotEmpty(): Boolean{

        if (binding.weightTextView.text.isEmpty() ||
                binding.heightTextView.text.isEmpty()){

                    Toast.makeText(this, "Missing Input, Please Enter Values", Toast.LENGTH_LONG).show()
                    return false
        }

        return true
    }
}