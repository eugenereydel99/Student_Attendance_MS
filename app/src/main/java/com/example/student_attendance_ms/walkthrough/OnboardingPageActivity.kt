package com.example.student_attendance_ms.walkthrough

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
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.student_attendance_ms.login.LoginActivity
import com.example.student_attendance_ms.R
import kotlinx.android.synthetic.main.pager_onboarding.*

private const val INTRO = "INTRO"

class WalkthroughActivity : AppCompatActivity() {

    // задаём контент страниц
    private var pagesAdapter = OnboardingPageAdapter(
            listOf(
                    OnboardingPage(
                            title = "Личный кабинет",
                            description = "Доступ в личный кабинет ТУСУР",
                            animation = R.raw.profile
                    ),
                    OnboardingPage(
                            title = "Посещаемость",
                            description = "Помогайте снизить нагрузку с преподавателей и старост групп, сканируя QR-код во время проверки посещаемости",
                            animation = R.raw.scan_qr_code1
                    ),

                    OnboardingPage(
                            title = "Расписание",
                            description = "Теперь вам не нужно заходить в браузер, чтобы посмотреть, когда начнётся пара",
                            animation = R.raw.timetable
                    )
            )
    )

    private lateinit var nextButton: Button
    private lateinit var prevButton: Button

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // получаем настройки приложения
        preferences = getSharedPreferences(INTRO, Context.MODE_PRIVATE)
        if (!preferences.getBoolean(INTRO, true)){
            startActivity(Intent(this@WalkthroughActivity, LoginActivity::class.java))
        }

        setContentView(R.layout.pager_onboarding)
        viewPager.adapter = pagesAdapter

        nextButton = findViewById(R.id.btn_next)
        prevButton = findViewById(R.id.btn_prev)

        setupIndicators()
        setCurrentIndicator(0)

        // работаем с кнопками для слайдинга viewPager
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)

                // меняем отображение кнопок навигации
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
                    }
                }

            }
        })

        nextButton.setOnClickListener {
            if (viewPager.currentItem + 1 < pagesAdapter.itemCount){
                viewPager.currentItem += 1
            } else {
                // запускаем новую активити и убиваем старую
                Intent(applicationContext, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
                // устанавливаем настройки sharedPreference
                preferences.edit()
                        .putBoolean(INTRO, false)
                        .apply()
            }
        }
        prevButton.setOnClickListener {
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
