package com.techno.gapsi.data.model

import com.google.gson.annotations.SerializedName

data class ServiceResponse(
	@SerializedName("totalResults") val totalResults: Int? = null,
	@SerializedName("page") val page: Int? = null,
	@SerializedName("items") val items: List<Item>? = null
)


