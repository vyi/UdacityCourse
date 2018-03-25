package com.example.vivek.nutritionquiz

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var array_question_views = arrayOf<View?>(null, null, null, null, null, null, null, null, null, null, null)
    val array_questions_radio_type = arrayOf("WHO recommended minimum water intake for healthy adults is","Fluid loss at 4% of body weight impacts in","Which food is a good source of dietary fiber (roughage)?","Grains belongs to the which food category?","A healthy diet for an adult should contain this much of fruit and vegetables","Recommended limit on daily salt intake for an healthy individual is","WHO identifies ....... as a global risk to health apart from 'Unhealthy diet'?", "WHO recommended sugar consumption is .... % of total energy intake", "World health day is observed on this day annually : ","Choose the food items that are a good source of omega-3", "Identify the vegetable shown in picture (hint: its name has 7 characters)")
    val array_options = arrayOf(arrayOf("1000 ml","2000 ml","2500 ml","4000 ml"), arrayOf("No effect","Impaired thermoregulation", "Thirst", "20%-30% reduction in work capacity"), arrayOf("Dark green Veggies","Potato chips","Eggs","Grains"), arrayOf("Protein","Fats","Carbohydrates","Fiber"), arrayOf("400 ","100 "," 50 ","0"), arrayOf("5 grams", "10 grams", "15 grams", "20 grams"), arrayOf("Lack of education","Lack of physical activity","Lack of sleep","Lack of medicine"), arrayOf("2","10","15","25"), arrayOf("22 March", "5 June", "20 March", "7 April"), arrayOf("Fish (salmon, mackerel, sardines)", "Walnuts", "Flaxseed oil", "Oyster"))
    //http://www.who.int/water_sanitation_health/dwq/nutwaterrequir.pdf (page 2,15), https://en.wikipedia.org/wiki/Dietary_fiber, general, http://www.who.int/elena/healthy_diet_fact_sheet_394.pdf, Guideline: Sugars intake for adults and children. Geneva: World Health Organization; 2015.

    val answers = arrayOf(2,4,1,3,1,1,2,2,4,2,0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        for (i in 0 until array_questions_radio_type.size) {
            val radio_question_view: View
            val checkbox_question_view: View
            val text_type_question_view: View
            val layoutInflater = LayoutInflater.from(this)
            val parentContainer = container_parent

            // Reference to various layouts
            radio_question_view = layoutInflater.inflate(R.layout.radio_question, null, false)
            checkbox_question_view = layoutInflater.inflate(R.layout.checkbox_question, null, false)
            text_type_question_view = layoutInflater.inflate(R.layout.text_question, null, false)


            if(i<9) {
                val title = radio_question_view.findViewById<TextView>(R.id.problemText)
                val optionA = radio_question_view.findViewById<TextView>(R.id.option_A)
                val optionB = radio_question_view.findViewById<TextView>(R.id.option_B)
                val optionC = radio_question_view.findViewById<TextView>(R.id.option_C)
                val optionD = radio_question_view.findViewById<TextView>(R.id.option_D)

                title.text = array_questions_radio_type.get(i)
                optionA.text = array_options.get(i).get(0)
                optionB.text = array_options.get(i).get(1)
                optionC.text = array_options.get(i).get(2)
                optionD.text = array_options.get(i).get(3)



                parentContainer.addView(radio_question_view)
                array_question_views[i] = radio_question_view
            }

            if(i==9){
                val title = checkbox_question_view.findViewById<TextView>(R.id.problemText)
                val optionA = checkbox_question_view.findViewById<CheckBox>(R.id.option_A)
                val optionB = checkbox_question_view.findViewById<CheckBox>(R.id.option_B)
                val optionC = checkbox_question_view.findViewById<CheckBox>(R.id.option_C)
                val optionD = checkbox_question_view.findViewById<CheckBox>(R.id.option_D)

                title.text = array_questions_radio_type.get(i)
                optionA.text = array_options.get(i).get(0)
                optionB.text = array_options.get(i).get(1)
                optionC.text = array_options.get(i).get(2)
                optionD.text = array_options.get(i).get(3)



                parentContainer.addView(checkbox_question_view)
                array_question_views[i] = checkbox_question_view
            }

            if (i==10){
                val title = text_type_question_view.findViewById<TextView>(R.id.problemText)
                title.text = array_questions_radio_type.get(i)

                val q_image = text_type_question_view.findViewById<ImageView>(R.id.question_visual_que)
                q_image.setBackgroundResource(R.drawable.apricot)

                parentContainer.addView(text_type_question_view)
                array_question_views[i] = text_type_question_view
            }
        }


        evaluate_quiz.setOnClickListener {
            evaluate()
        }
    }


    private fun evaluate() {
        var score =0
        var q_index =0
        var actual_answer =0
        var user_answer =0
        for (question_view in array_question_views)
        {

            if(q_index<9) {
                actual_answer = answers[q_index]

                val optionA = question_view?.findViewById<TextView>(R.id.option_A)
                val optionB = question_view?.findViewById<TextView>(R.id.option_B)
                val optionC = question_view?.findViewById<TextView>(R.id.option_C)
                val optionD = question_view?.findViewById<TextView>(R.id.option_D)
                val radioGroup = question_view?.findViewById<RadioGroup>(R.id.RG1)
                var id = radioGroup?.checkedRadioButtonId

                if (id == -1) {
                    question_view?.findViewById<TextView>(R.id.result_text)?.text = "Not tried"
                } else {
                    question_view?.findViewById<TextView>(R.id.result_text)?.text = "Incorrect"
                }

                // Highlight wrong answers
                if (optionA?.id == id) {
                    optionA?.setBackgroundColor(Color.parseColor("#E57373"))
                    user_answer = 1
                }
                if (optionB?.id == id) {
                    optionB?.setBackgroundColor(Color.parseColor("#E57373"))
                    user_answer = 2
                }
                if (optionC?.id == id) {
                    optionC?.setBackgroundColor(Color.parseColor("#E57373"))
                    user_answer = 3
                }
                if (optionD?.id == id) {
                    optionD?.setBackgroundColor(Color.parseColor("#E57373"))
                    user_answer = 4
                }
                when (actual_answer) { //Make Green the correct answer

                    1 -> {
                        optionA?.setBackgroundColor(Color.parseColor("#DCE775"))
                        if (user_answer == 1) {
                            score += 1
                            question_view?.findViewById<TextView>(R.id.result_text)?.text = "Correct"
                        }
                    }
                    2 -> {
                        optionB?.setBackgroundColor(Color.parseColor("#DCE775"))
                        if (user_answer == 2) {
                            score += 1
                            question_view?.findViewById<TextView>(R.id.result_text)?.text = "Correct"
                        }

                    }
                    3 -> {
                        optionC?.setBackgroundColor(Color.parseColor("#DCE775"))
                        if (user_answer == 3) {
                            score += 1
                            question_view?.findViewById<TextView>(R.id.result_text)?.text = "Correct"
                        }
                    }
                    4 -> {
                        optionD?.setBackgroundColor(Color.parseColor("#DCE775"))
                        if (user_answer == 4) {
                            score += 1
                            question_view?.findViewById<TextView>(R.id.result_text)?.text = "Correct"
                        }
                    }
                }
            }
            if(q_index==9)
            {
                actual_answer = answers[q_index]

                val optionA = question_view?.findViewById<CheckBox>(R.id.option_A)
                val optionB = question_view?.findViewById<CheckBox>(R.id.option_B)
                val optionC = question_view?.findViewById<CheckBox>(R.id.option_C)
                val optionD = question_view?.findViewById<CheckBox>(R.id.option_D)

                if (optionA?.isChecked!! && optionB?.isChecked!! && optionC?.isChecked!! && optionD?.isChecked!! ) {
                    score += 1
                    optionA?.setBackgroundColor(Color.parseColor("#DCE775"))
                    optionB?.setBackgroundColor(Color.parseColor("#DCE775"))
                    optionC?.setBackgroundColor(Color.parseColor("#DCE775"))
                    optionD?.setBackgroundColor(Color.parseColor("#DCE775"))
                    question_view?.findViewById<TextView>(R.id.result_text)?.text = "Correct"
                }
                else{
                    question_view?.findViewById<TextView>(R.id.result_text)?.text = "Incorrect"
                }
            }
            if(q_index==10)
            {
                    val answer_text = question_view?.findViewById<EditText>(R.id.answer_textview)
                    var ans_text: String = answer_text?.text.toString().decapitalize()
                if (ans_text == ""){
                    question_view?.findViewById<TextView>(R.id.result_text)?.text = "Not tried"
                }
                if ("apricot" in ans_text.split(" ")){
                    question_view?.findViewById<TextView>(R.id.result_text)?.text = "Correct"
                    score += 1
                    }

            }


            //Log.d("Selected :", selected.toString())

            q_index += 1
        }
        score_text.text = "Your score is: " + score.toString() +"/" + array_questions_radio_type.size.toString()
    }


}
