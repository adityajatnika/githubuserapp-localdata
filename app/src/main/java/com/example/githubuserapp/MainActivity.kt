package com.example.githubuserapp

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.adapter.ListUserAdapter
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.example.githubuserapp.model.User

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<User>()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.list_user)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUser.setHasFixedSize(true)

        list.addAll(listUser)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvUser.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvUser.layoutManager = LinearLayoutManager(this)
        }
        val listHeroAdapter = ListUserAdapter(list)
        binding.rvUser.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private val listUser: ArrayList<User>
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataPhoto = resources.obtainTypedArray(R.array.avatar)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataRepository = resources.getStringArray(R.array.repository)
            val listUser = ArrayList<User>()
            for (i in dataName.indices) {
                val user = User(dataName[i],dataUsername[i], dataPhoto.getResourceId(i, -1),
                dataCompany[i],dataLocation[i], dataFollowers[i], dataFollowing[i], dataRepository[i])
                listUser.add(user)
            }
            dataPhoto.recycle()
            return listUser
        }

    private fun showSelectedUser(user: User) {
        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.EXTRA_USER, user)
        startActivity(intent)

    }
}