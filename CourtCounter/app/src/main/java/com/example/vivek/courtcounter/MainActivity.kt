package com.example.vivek.courtcounter

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var scoreA: Int=0
    var scoreB: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reset_button.setOnClickListener{
            reset()
            displayScore()
        }

        point2_buttonA.setOnClickListener{
            add_to_scoreA(2)
            displayScore()
        }
        point3_buttonA.setOnClickListener {
            add_to_scoreA(3)
            displayScore()
        }
        freethrow_buttonA.setOnClickListener {
            add_to_scoreA(1)
            displayScore()
        }

        point2_buttonB.setOnClickListener{
            add_to_scoreB(2)
            displayScore()
        }
        point3_buttonB.setOnClickListener {
            add_to_scoreB(3)
            displayScore()}
        freethrow_buttonB.setOnClickListener {
            add_to_scoreB(1)
            displayScore()}

    }

    fun Activity.add_to_scoreA(x: Int){
        scoreA += x;

    }
    fun Activity.add_to_scoreB(x: Int){
        scoreB += x;

    }
    fun Activity.reset(){
        scoreA = 0;
        scoreB = 0;
    }

    fun Activity.displayScore(){
        score_textA.text = ""+scoreA
        score_textB.text = ""+scoreB
    }
}
