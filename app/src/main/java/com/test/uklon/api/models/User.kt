package com.test.uklon.api.models

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("username") val username: String,
        @SerializedName("email") val email: String,
        @SerializedName("phone") val phone: String,
        @SerializedName("website") val website: String,
        @SerializedName("address") val address: Address,
        @SerializedName("geo") val geo: Geo
)