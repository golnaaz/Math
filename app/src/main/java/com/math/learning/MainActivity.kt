package com.math.learning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_edit_box.view.*
import kotlinx.android.synthetic.main.layout_field_box.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var numUnitFirstRow: Editable
    private lateinit var numTensFirstRow: Editable
    private lateinit var numTensSecondRow: Editable
    private lateinit var numUnitSecondRow: Editable
    private var isAdd: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        numTensFirstRow = box1.editNumTens.text
        numUnitFirstRow = box1.editNumUnit.text
        numTensSecondRow = box2.editNumTens.text
        numUnitSecondRow = box2.editNumUnit.text

        operator.setOnClickListener {

            if (isAdd) {
                operator.text = "-"
                operator1.text = "-"
                isAdd = false
                clear()
            } else {
                operator.text = "+"
                operator1.text = "+"
                isAdd = true
                clear()
            }
        }

        btnClear.setOnClickListener {
            clear()
        }

        btnCalculate.setOnClickListener {

            if (numTensFirstRow.isNotEmpty() && numUnitFirstRow.isNotEmpty() &&
                numTensSecondRow.isNotEmpty() && numUnitSecondRow.isNotEmpty()
            ) {

                layout2.visibility = View.VISIBLE

                val first = numTensFirstRow.toString()
                val second = numTensSecondRow.toString()

                box3.textNumTens.text = operate(first.toInt(), second.toInt())
                box3.textNumUnit.text = numUnitFirstRow.toString()
                box4.textNumTens.text = ""
                box4.textNumUnit.text = numUnitSecondRow.toString()

                result.text = calculateResult()
            } else {
                Toast.makeText(this, "عدد ها را وارد کن", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun calculateResult(): String {

        return (operate(
            (numTensFirstRow.toString() + numUnitFirstRow.toString()).toInt(),
            (numTensSecondRow.toString() + numUnitSecondRow.toString()).toInt()
        ))
    }

    private fun operate(first: Int, second: Int): String {
        return if (isAdd) {
            first.plus(second).toString()
        } else {
            first.minus(second).toString()
        }
    }

    private fun clear() {
        layout2.visibility = View.INVISIBLE
        numTensSecondRow.clear()
        numUnitSecondRow.clear()
        numTensFirstRow.clear()
        numUnitFirstRow.clear()
        result.text = ""
    }
}