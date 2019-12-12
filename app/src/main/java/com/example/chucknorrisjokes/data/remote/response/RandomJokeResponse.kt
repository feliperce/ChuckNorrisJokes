package com.example.chucknorrisjokes.data.remote.response

import com.google.gson.annotations.SerializedName

data class RandomJokeResponse(
    val categories: List<String>? = arrayListOf(),
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("icon_url") val iconUrl: String?,
    val id: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val url: String?,
    val value: String?
)