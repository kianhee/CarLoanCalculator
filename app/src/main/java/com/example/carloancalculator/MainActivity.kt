package com.example.carloancalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calculate: Button = findViewById(R.id.buttonCalculate)
        val reset : Button = findViewById(R.id.buttonReset)
        calculate.setOnClickListener{calculate()}
        reset.setOnClickListener{reset()}
    }
    private fun calculate(){
        val carPrice : View = findViewById(R.id.editTextCarPrice)
        val downPayment : View = findViewById(R.id.editTextDownPayment)
        val loanPeriod : View = findViewById(R.id.editTextLoanPeriod)
        val interestRate : View = findViewById(R.id.editTextInterestRate)
        carPrice as EditText
        downPayment as EditText
        loanPeriod as EditText
        interestRate as EditText
        if(carPrice.text.isEmpty() || downPayment.text.isEmpty() || loanPeriod.text.isEmpty()|| interestRate.text.isEmpty()){
            Toast.makeText(this, getString(R.string.error_input),
                Toast.LENGTH_SHORT).show()
        }
        else{
            val carLoan : Double = carPrice.text.toString().toDouble() - downPayment.text.toString().toDouble()
            val interest : Double = carLoan * (interestRate.text.toString().toDouble()/100) * loanPeriod.text.toString().toDouble()
            val monthlyRepayment : Double = (carLoan + interest) / loanPeriod.text.toString().toDouble() / 12
            val textViewLoan : TextView = findViewById(R.id.textViewLoan)
            val textViewInterest : TextView = findViewById(R.id.textViewInterest)
            val textViewMonthlyRepayment : TextView = findViewById(R.id.textViewMonthlyRepayment)
            textViewLoan.text = String.format("%s %.2f",getString(R.string.loan),carLoan)
            textViewInterest.text = String.format("%s %.2f",getString(R.string.interest),interest)
            textViewMonthlyRepayment.text = String.format("%s %.2f",getString(R.string.monthly_repayment),monthlyRepayment)
        }
    }
    private fun reset(){
        val textViewLoan : TextView = findViewById(R.id.textViewLoan)
        val textViewInterest : TextView = findViewById(R.id.textViewInterest)
        val textViewMonthlyRepayment : TextView = findViewById(R.id.textViewMonthlyRepayment)
        val carPrice : EditText = findViewById(R.id.editTextCarPrice)
        val downPayment : EditText = findViewById(R.id.editTextDownPayment)
        val loanPeriod : EditText = findViewById(R.id.editTextLoanPeriod)
        val interestRate : EditText = findViewById(R.id.editTextInterestRate)
        carPrice.text.clear()
        downPayment.text.clear()
        loanPeriod.text.clear()
        interestRate.text.clear()
        textViewLoan.text = getString(R.string.loan)
        textViewInterest.text = getString(R.string.interest)
        textViewMonthlyRepayment.text = getString(R.string.monthly_repayment)
    }
}
