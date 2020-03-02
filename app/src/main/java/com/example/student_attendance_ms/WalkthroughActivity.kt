package com.example.student_attendance_ms

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.onboarding_page_layout.*

private const val INTRO = "INTRO"

class WalkthroughActivity : AppCompatActivity() {

    // задаём контент страниц
    private val pagesAdapter = OnboardPageAdapter(
            listOf(
                    OnboardPage(
                            title = "Личный кабинет",
                            description = "Доступ в личный кабинет ТУСУР",
                            image = R.drawable.tusur
                    ),
                    OnboardPage(
                            title = "Мини-тесты",
                            description = "Проверяйте свои знания, проходя мини-тесты в конце пары",
                            image = R.drawable.tusur
                    ),

                    OnboardPage(
                            title = "Посещаемость",
                            description = "Помогайте снизить нагрузку с преподавателей и старост групп, сканируя QR-код во время проверки посещаемости",
                            image = R.drawable.tusur
                    )
            )
    )

    private lateinit var nextButton: Button
    private lateinit var prevButton: Button

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_page_layout)

        // получаем настройки приложения
        preferences = getSharedPreferences(INTRO, Context.MODE_PRIVATE)
        if (!preferences.getBoolean(INTRO, true)){
            startActivity(Intent(this@WalkthroughActivity, LoginActivity::class.java))
            finish()
        }

        viewPager.adapter = pagesAdapter

        nextButton = findViewById(R.id.btn_next)
        prevButton = findViewById(R.id.btn_prev)

        setupIndicators()
        setCurrentIndicator(0)

        // работаем с кнопками для слайдинга viewPager
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)

                when (position){
                    0 -> {
                        prevButton.visibility = View.GONE
                        nextButton.text = resources.getString(R.string.next_button_text)
                    }
                    1 -> {
                        prevButton.visibility = View.VISIBLE
                        nextButton.text = resources.getString(R.string.next_button_text)
                    }
                    2 -> {
                        prevButton.visibility = View.VISIBLE
                        nextButton.text = resources.getString(R.string.finish_button_text)

                        // устанавливаем настройки sharedPreference
                        // для однократного отображения приветственного окна
                        // и вызываем новую активити
                        nextButton.setOnClickListener {
                            startActivity(Intent(this@WalkthroughActivity, LoginActivity::class.java))
                            finish()
                            preferences.edit()
                                    .putBoolean(INTRO, false)
                                    .apply()
                        }
                    }
                }

            }
        })

        btn_next.setOnClickListener {
            viewPager.currentItem += 1
        }

        btn_prev.setOnClickListener {
            viewPager.currentItem -= 1
        }
    }

    // установка индикаторов
    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(pagesAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(5,0,5,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                        ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.indicator_inactive
                        )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    //установка текущего индикатора
    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.indicator_active
                        )
                )
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.indicator_inactive
                        )
                )
            }
        }
    }
}
