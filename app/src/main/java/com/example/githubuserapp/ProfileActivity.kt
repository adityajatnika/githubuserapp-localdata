package com.example.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.ActivityProfileBinding
import com.example.githubuserapp.model.User
import java.lang.StringBuilder
import java.util.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.title = getString(R.string.detail_user)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        binding.apply {
            tvNama.text = user.name
            tvCompany.text = StringBuilder(getString(R.string.company)).append(": ").append(user.company)
            tvLocation.text = StringBuilder("Lokasi: ").append(user.location)
            tvFollowers.text = StringBuilder(user.followers).append(" ").append(getString(R.string.followers).lowercase(
                Locale.getDefault()
            ))
            tvFollowing.text = StringBuilder(user.following).append(" diikuti")
            tvRepository.text = StringBuilder(user.repository).append(" repositori")
            tvUsername.text = StringBuilder("ID: ").append(user.username)
            Glide.with(baseContext)
                .load(user.avatar)
                .circleCrop()
                .into(imgUser)
        }

    }

    companion object{
        const val EXTRA_USER = "extra_person"
    }
}