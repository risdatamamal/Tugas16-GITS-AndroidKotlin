package com.example.tugas15

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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

        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val judul = intent.getStringExtra(EXTRA_TITLE)
        tvTitle.text = judul
        supportActionBar!!.title = judul

        val tvPopularity = findViewById<TextView>(R.id.tv_popularity)
        val popularitas = intent.getStringExtra(EXTRA_POPULARITY)
        tvPopularity.text = popularitas

        val tvOverview = findViewById<TextView>(R.id.tv_overview)
        val deskripsi = intent.getStringExtra(EXTRA_OVERVIEW)
        tvOverview.text = deskripsi

        val imgPoster = findViewById<ImageView>(R.id.poster_path_movies)
        val poster = intent.getIntExtra(EXTRA_POSTER, 1)
        imgPoster.setImageResource(poster)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}