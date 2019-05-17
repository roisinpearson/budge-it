package com.example.budgeit.income

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.budgeit.BudgetItApp
import com.example.budgeit.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_display_pay.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseViews()

    }

    private fun initialiseViews() {
        button.setOnClickListener {
            updateIncome()
//            finish()
            saveIncomeToDB(income)
        }

        switch1.setOnCheckedChangeListener() { switch1, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked times the amount by 4
                updateIncome()
            }
        }
        switch2.setOnCheckedChangeListener() { switch2, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked times the amount by 4
                updateIncome()
            }
        }

    }

    var income: Double = 0.0

    private fun updateIncome() {
        // Get income
        val enteredIncome = editText.text.toString().toDoubleOrNull() ?: 0.0

        if (switch1.isChecked) {
            income = enteredIncome * 52 / 12

        }
        if (switch2.isChecked) {
            income = enteredIncome * 26 / 12

        }
    }

        // Save income to DB

    private fun saveIncomeToDB(income: Double) {
        val incomeForDb = Income(PRIMARY_INCOME_ID, income)
        (application as BudgetItApp).database.incomeDao().putIncome(incomeForDb)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                finish()
            }, {
                throw it
            })
    }



//    private fun checkSwitches() {
//        switch1.setOnCheckedChangeListener { switch1, isChecked ->
//            if (isChecked) {
//                // The switch is enabled/checked times the amount by 4
//                updateIncome()
//
//
//
//            } else {
//                // The switch is disabled
//
//
//                // Set the app background color to light gray
//            }
//        }
//    }


//    fun sendMessage(view: View) {
//        val editText = findViewById<EditText>(R.id.editText)
//        val message = editText.text.toString()
//        val intent = Intent(this, DisplayPayActivity::class.java).apply {
//            putExtra(EXTRA_MESSAGE, message)
//
//        }
//        startActivity(intent)
//    }
}
