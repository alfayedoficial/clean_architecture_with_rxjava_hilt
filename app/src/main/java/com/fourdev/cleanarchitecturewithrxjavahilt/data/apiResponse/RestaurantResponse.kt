package com.fourdev.cleanarchitecturewithrxjavahilt.data.apiResponse

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("response")
	val response: Response? = null
)

data class Response(

	@field:SerializedName("venues")
	val venues: List<VenuesItem?>? = null
)

data class Icon(

	@field:SerializedName("prefix")
	val prefix: String? = null,

	@field:SerializedName("suffix")
	val suffix: String? = null
)

data class LabeledLatLngsItem(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)

data class VenuePage(

	@field:SerializedName("id")
	val id: String? = null
)

data class CategoriesItem(

	@field:SerializedName("pluralName")
	val pluralName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("shortName")
	val shortName: String? = null,

	@field:SerializedName("primary")
	val primary: Boolean? = null
)

data class VenuesItem(

	@field:SerializedName("venuePage")
	val venuePage: VenuePage? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem?>? = null
)

data class Location(

	@field:SerializedName("cc")
	val cc: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("labeledLatLngs")
	val labeledLatLngs: List<LabeledLatLngsItem?>? = null,

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("distance")
	val distance: Int? = null,

	@field:SerializedName("formattedAddress")
	val formattedAddress: List<String?>? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("postalCode")
	val postalCode: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("crossStreet")
	val crossStreet: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)

data class Meta(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("requestId")
	val requestId: String? = null
)
