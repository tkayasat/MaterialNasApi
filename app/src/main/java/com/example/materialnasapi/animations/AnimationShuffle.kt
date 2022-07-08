package com.example.materialnasapi.animations

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.materialnasapi.R
import kotlinx.android.synthetic.main.activity_animation.*
import java.util.ArrayList

class AnimationShuffle : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_animations_shuffle)
        val titles: MutableList<String> = ArrayList()
        for (i in 0..4) {
            titles.add(String.format("Item %d", i + 1))
        }

        createViews(transitions_container, titles)
        button.setOnClickListener {
            TransitionManager.beginDelayedTransition(transitions_container, ChangeBounds())
            titles.shuffle()
            createViews(transitions_container, titles)
        }
    }


    private fun createViews(layout: ViewGroup, titles: List<String>) {
        layout.removeAllViews()
        for (title in titles) {
            val textView = TextView(this)
            textView.text = title
            textView.gravity = Gravity.CENTER_HORIZONTAL
            ViewCompat.setTransitionName(textView, title)
            layout.addView(textView)
        }
    }
}