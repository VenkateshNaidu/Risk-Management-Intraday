package com.venkygithub.riskmanagementmis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val percentage =19.5f
    var onepercent =19.5f
    var twopercent =19.5f

    var amountwithMargin=19.5f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        edtQty.addTextChangedListener(object : TextWatcher{

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if( !edtQty.text.toString().isEmpty() && !edtStockPrice.text.toString().isEmpty()){

                    edtAmountwithMargin.text ="Amount with Margin : "+ (edtStockPrice.text.toString().toDouble()
                            * Integer.parseInt(edtQty.text.toString())).toString()

                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        btnBUY.setOnClickListener {

            if(validate()){

                // calclation for Stop loss
                amountwithMargin =
                    edtAmountwithMargin.text.toString().split(":")[1].trim().toDouble().toFloat()
                onepercent = (amountwithMargin / 100).toFloat()
                Log.d("#onepercent(Buy)",onepercent.toString())

                var amt= amountwithMargin-onepercent
                var SL= amt/Integer.parseInt(edtQty?.text.toString())
                Log.d("#SL(Buy)",SL?.toString())

                textViewSL.text = "Stop Loss = ${SL?.toString()} (1%)"

                twopercent = onepercent * 2
                Log.d("#twopercent(Buy)",onepercent.toString())
                var amt1= amountwithMargin+twopercent
                var target= amt1/Integer.parseInt(edtQty?.text.toString())
                Log.d("#Target(Buy)",target?.toString())

                textViewTarget.text = "Target       = ${target?.toString()} (2%)"

                txtselection.text = "BUY Side"

            }
        }

        btnSELL.setOnClickListener {

            if(validate()){
                amountwithMargin =
                    edtAmountwithMargin.text.toString().split(":")[1].trim().toDouble().toFloat()
                onepercent = (amountwithMargin / 100).toFloat()
                Log.d("#onepercent(Sell)",onepercent.toString())
                var amt= amountwithMargin+onepercent
                var SL= amt/Integer.parseInt(edtQty?.text.toString())
                Log.d("#SL(Sell)",SL?.toString())
                textViewSL.text = "Stop Loss = ${SL?.toString()} (1%)"

                twopercent = onepercent * 2
                Log.d("#twopercent(Sell)",onepercent.toString())
                var amt1= amountwithMargin-twopercent
                var target= amt1/Integer.parseInt(edtQty?.text.toString())
                Log.d("#Target(Sell)",target?.toString())

                textViewTarget.text = "Target       = ${target?.toString()} (2%)"
                txtselection.text = "SELL Side"
            }
        }


    }

    private fun validate(): Boolean{
        if(edtStockPrice.text.toString().trim().isEmpty()){
            edtStockPrice.error = "Required"
            edtStockPrice.requestFocus()
            return false

        }else if(edtQty.text.toString().trim().isEmpty()){
            edtQty.error = "Required"
            edtQty.requestFocus()
            return false

        }
        return true
    }
}
