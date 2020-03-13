package com.example.student_attendance_ms.walkthrough

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendance_ms.R

class OnboardPageAdapter (private val pages: List<OnboardPage>): RecyclerView.Adapter<OnboardPageAdapter.PageViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.onboarding_page_content,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int = pages.size

    override fun onBindViewHolder(holder: PageViewHolder, position: Int){
        holder.onCreatePages(pages[position])
    }


    inner class PageViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val title = view.findViewById<TextView>(R.id.page_title)
        private val description = view.findViewById<TextView>(R.id.page_desc)
        private val image = view.findViewById<ImageView>(R.id.page_icon)

        fun onCreatePages(pages: OnboardPage){
            title.text = pages.title
            description.text = pages.description
            image.setImageResource(pages.image)
        }

    }
}