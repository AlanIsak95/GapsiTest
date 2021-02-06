package com.techno.gapsi.data.model


import com.google.gson.annotations.SerializedName


data class Item(
	@SerializedName("id")  	val id:    String,
	@SerializedName("rating")	val rating:Double? = null,
	@SerializedName("price") 	val price: Double? = null,
	@SerializedName("image")	val image: String? = null,
	@SerializedName("title")	val title: String? = null
)
