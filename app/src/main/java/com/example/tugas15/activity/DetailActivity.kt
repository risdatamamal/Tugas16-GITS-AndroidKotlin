package com.example.tugas15.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas15.R
import kotlinx.android.synthetic.main.detail_activity.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_POPULARITY = "extra_popularity"
        const val EXTRA_OVERVIEW = "extra_overview"
        const val EXTRA_POSTER = "extra_poster"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = tv_title.toString()

        tv_title.text = intent.getStringExtra(EXTRA_TITLE)
        tv_popularity.text = intent.getStringExtra(EXTRA_POPULARITY)
        tv_overview.text = intent.getStringExtra(EXTRA_OVERVIEW)

        val poster = intent.getIntExtra(EXTRA_POSTER, 1)
        poster_path_movies.setImageResource(poster)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}