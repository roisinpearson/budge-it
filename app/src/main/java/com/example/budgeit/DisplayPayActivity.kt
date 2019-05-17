package com.example.budgeit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.budgeit.income.MainActivity
import com.example.budgeit.income.PRIMARY_INCOME_ID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_display_pay.*

class DisplayPayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_pay)

        initialiseViews()
    }

    private fun initialiseViews() {
        enterIncomeButton.setOnClickListener {
            openIncomeActivity()
        }
        enterExpensesButton.setOnClickListener {
        }
    }

    private fun openIncomeActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()

        (application as BudgetItApp).database.incomeDao().getIncome(PRIMARY_INCOME_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // When income has been entered
                textView2.text = "$" + String.format("%.2f", it.income)
            }, {
                // When no income has been entered
                textView2.text = "No income"
            })
    }

    // Get the Intent that started this activity and extract the string
//    val message = intent.getStringExtra(EXTRA_MESSAGE)

    // Capture the layout's TextView and set the string as its text
//    val textView = findViewById<TextView>(R.id.textView).apply {
//        text = message
//    }
}
