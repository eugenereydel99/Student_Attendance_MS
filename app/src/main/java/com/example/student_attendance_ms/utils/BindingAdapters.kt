package com.example.student_attendance_ms.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.student_attendance_ms.R
import com.google.android.material.snackbar.Snackbar

@BindingAdapter("app:onNoteClick")
fun onNoteClick(imageView: ImageView, note: String?){
    if (note != null){
        imageView.setOnClickListener{
            Snackbar.make(imageView, note, Snackbar.LENGTH_SHORT).apply {
                view.setBackgroundColor(
                        ContextCompat.getColor(
                                imageView.context,
                                R.color.blue
                        )
                )
            }.show()
        }
    }
}