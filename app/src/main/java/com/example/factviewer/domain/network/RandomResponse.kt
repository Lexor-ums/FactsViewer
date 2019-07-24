package com.example.factviewer.domain.network

data class Fact (
    val _id :String,
    val __v  :Int,
    val text :String,
    val delete :Boolean,
    val source :String,
    val used : Boolean,
    val type: String,
    val updatedAt : String,
    val createdAt : String
)
