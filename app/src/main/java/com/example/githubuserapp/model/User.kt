package com.example.githubuserapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String,
    var username: String,
    var avatar: Int,
    var company: String,
    var location:String,
    var followers: String,
    var following: String,
    var repository: String

) : Parcelable
