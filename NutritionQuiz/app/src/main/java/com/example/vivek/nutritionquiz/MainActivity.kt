package com.example.vivek.nutritionquiz

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var bigV = arrayOf<View?>(null, null, null, null, null, null, null, null, null, null)
    val questions = arrayOf("WHO recommended minimum water intake for healthy adults is","Fluid loss at 4% of body weight impacts in","Which food is a good source of dietary fiber (roughage)?","Grains belongs to the which food category?","A healthy diet for an adult should contain how many grams of fruit and vegetables","Recommended limit on daily salt intake for an healthy individual is","WHO identifies ....... as a global risk to health apart from 'Unhealthy diet'?", "WHO recommended sugar consumption is .... % of total energy intake", "World health day is observed on ")
    val options = arrayOf(arrayOf("1000 ml","2000 ml","2500 ml","4000 ml"), arrayOf("No effect","Impaired thermoregulation", "Thirst", "20%-30% reduction in work capacity"), arrayOf("Dark green Veggies","Potato chips","Eggs","Grains"), arrayOf("Protein","Fats","Carbohydrates","Fiber"), arrayOf("400 ","100 "," 50 ","0"), arrayOf("5 grams", "10 grams", "15 grams", "20 grams"), arrayOf("Lack of education","Lack of physical activity","Lack of sleep","Lack of medicine"), arrayOf("2","10","15","25"), arrayOf("22 March", "5 June", "20 March", "7 April"))
    //http://www.who.int/water_sanitation_health/dwq/nutwaterrequir.pdf (page 2,15), https://en.wikipedia.org/wiki/Dietary_fiber, general, http://www.who.int/elena/healthy_diet_fact_sheet_394.pdf, Guideline: Sugars intake for adults and children. Geneva: World Health Organization; 2015.

    val answers = arrayOf(2,4,1,3,1,1,2,2,4,2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        for (i in 0 until questions.size) {
            val question: View
            val layoutInflater = LayoutInflater.from(this)
            question = layoutInflater.inflate(R.layout.question, null, false)

            val title = question.findViewById<TextView>(R.id.problemText)
            val optionA = question.findViewById<TextView>(R.id.option_A)
            val optionB = question.findViewById<TextView>(R.id.option_B)
            val optionC = question.findViewById<TextView>(R.id.option_C)
            val optionD = question.findViewById<TextView>(R.id.option_D)

            title.text = questions.get(i)
            optionA.text = options.get(i).get(0)
            optionB.text = options.get(i).get(1)
            optionC.text = options.get(i).get(2)
            optionD.text = options.get(i).get(3)



            val parentContainer = container_parent
            parentContainer.addView(question)
            bigV[i] = question
        }

        evaluate_quiz.setOnClickListener {
            evaluate()
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun evaluate() {
        var score = 0
        var q =0
        var ans =0
        var selected=0
        for (question in bigV)
        {
            ans = answers[q]

            val optionA = question?.findViewById<TextView>(R.id.option_A)
            val optionB = question?.findViewById<TextView>(R.id.option_B)
            val optionC = question?.findViewById<TextView>(R.id.option_C)
            val optionD = question?.findViewById<TextView>(R.id.option_D)
            val radioGroup = question?.findViewById<RadioGroup>(R.id.RG1)
            var id = radioGroup?.checkedRadioButtonId

            if (id==-1){
                question?.findViewById<TextView>(R.id.result_text)?.text="Not tried"
            }else{
                question?.findViewById<TextView>(R.id.result_text)?.text="Incorrect"
            }
            // Highlight if wrong selected
            if(optionA?.id == id){
                optionA?.setBackgroundColor(Color.parseColor("#E57373"))
                selected = 1
            }
            if (optionB?.id == id ) {
                optionB?.setBackgroundColor(Color.parseColor("#E57373"))
                selected = 2
            }
            if (optionC?.id == id ) {
                optionC?.setBackgroundColor(Color.parseColor("#E57373"))
                selected = 3
            }
            if (optionD?.id == id ) {
                optionD?.setBackgroundColor(Color.parseColor("#E57373"))
                selected = 4
            }
            when (ans){ //Make Green the correct answer

                1 -> {
                    optionA?.setBackgroundColor(Color.parseColor("#DCE775"))
                    if(selected == 1) {
                        score += 1
                        question?.findViewById<TextView>(R.id.result_text)?.text = "Correct"
                    }
                }
                2 -> {
                    optionB?.setBackgroundColor(Color.parseColor("#DCE775"))
                    if(selected == 2) {
                        score += 1
                        question?.findViewById<TextView>(R.id.result_text)?.text = "Correct"
                    }

                }
                3 -> {optionC?.setBackgroundColor(Color.parseColor("#DCE775"))
                    if(selected == 3){score+=1
                    question?.findViewById<TextView>(R.id.result_text)?.text="Correct"}
                }
                4 -> {
                    optionD?.setBackgroundColor(Color.parseColor("#DCE775"))
                    if(selected == 4){score+=1
                    question?.findViewById<TextView>(R.id.result_text)?.text="Correct"}
                }
            }





            Log.d("Selected :", selected.toString())

            q += 1
        }
        score_text.text = "Your score is: " + score.toString() +"/9"
    }


}
