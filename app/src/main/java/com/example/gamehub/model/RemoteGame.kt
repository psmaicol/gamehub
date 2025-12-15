package com.example.gamehub.model

import com.google.gson.annotations.SerializedName

data class RemoteGame(

    @SerializedName("id")
    val id: Int,

    @SerializedName("nombre")
    val title: String,

    @SerializedName("imagen")
    val thumbnail: String,

    @SerializedName("descripcion")
    val short_description: String?
)
