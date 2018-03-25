package com.example.vivek.justjava

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //update_price()
        orderButton.setOnClickListener{ update_price()}
        increase_qty_button.setOnClickListener{change_coffee_by_one(1)}
        decrease_qty_button.setOnClickListener{change_coffee_by_one(-1)}
    }

    fun Activity.change_coffee_by_one(x : Int){
        var qty: Int
        qty = Integer.parseInt(orderQuantityText.text.toString())
        qty += x
        if (qty<0)
        {
            toast("Can't order less than 0 coffee!")
            qty =0
        }
        orderQuantityText.setText(qty.toString())
    }


    fun Activity.decrease_coffee_by_one(){}
    fun Activity.update_price(){
        var quantity: Int=0
        var cost: Int=5
        var total: Int=0
        var cream: Boolean
        var chocolate: Boolean
        var qtyString: String = orderQuantityText.text.toString()
        var name: String
        var orderStr: String=""

        name = eTextName.text.toString()
        quantity = Integer.parseInt(qtyString)
        cream = cBoxCream.isChecked()
        chocolate = cBoxChocolate.isChecked()


        orderStr = getString(R.string.order_summary_name)+name+"\n"
        orderStr += getString(R.string.order_quantity)+quantity+"\n"

        orderStr += getString(R.string.order_ingredient1)
        if (cream)
        {
            orderStr += "Yes \n"
            cost += 1
        }
        else
        {orderStr += "No \n"}


        orderStr += getString(R.string.order_ingredient2)
        if (chocolate)
        {
            orderStr += "Yes \n"
            cost+=2
        }
        else
        {orderStr += "No \n"}

        total = cost*quantity

        orderStr += getString(R.string.total)+total+"\n"
        orderStr += getString(R.string.thanks)
        //price_text_view.text = orderStr

        var i = Intent( Intent.ACTION_SENDTO)
        i.setData(Uri.parse("mailto:"))
        i.putExtra(Intent.EXTRA_SUBJECT, "JustJava Coffe Order for "+name)
        i.putExtra(Intent.EXTRA_TEXT, orderStr)
        //i.setType("message/rfc822")
        startActivity(i)

    }
}

