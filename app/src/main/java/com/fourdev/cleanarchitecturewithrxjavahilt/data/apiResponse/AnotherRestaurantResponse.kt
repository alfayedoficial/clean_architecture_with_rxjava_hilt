package com.fourdev.cleanarchitecturewithrxjavahilt.data.apiResponse

import com.google.gson.annotations.SerializedName

data class AnotherRestaurantResponse(

	@field:SerializedName("fsq_results")
	val fsqResults: List<FsqResultsItem>? = null,

	@field:SerializedName("results_geojson")
	val resultsGeojson: ResultsGeojson? = null,

	@field:SerializedName("new_map_center")
	val newMapCenter: NewMapCenter? = null,

	@field:SerializedName("no_results")
	val noResults: Boolean? = null
)

data class RegularItem(

	@field:SerializedName("close")
	val close: String? = null,

	@field:SerializedName("day")
	val day: Int? = null,

	@field:SerializedName("open")
	val open: String? = null
)

data class SocialMedia(

	@field:SerializedName("twitter")
	val twitter: String? = null,

	@field:SerializedName("facebook_id")
	val facebookId: String? = null,

	@field:SerializedName("instagram")
	val instagram: String? = null
)

data class Hours(

	@field:SerializedName("open_now")
	val openNow: Boolean? = null,

	@field:SerializedName("seasonal")
	val seasonal: List<Any?>? = null,

	@field:SerializedName("display")
	val display: String? = null,

	@field:SerializedName("is_local_holiday")
	val isLocalHoliday: Boolean? = null,

	@field:SerializedName("regular")
	val regular: List<RegularItem?>? = null
)

data class TipsItem(

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)

data class Rich(

	@field:SerializedName("hours")
	val hours: Hours? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("tastes")
	val tastes: List<String?>? = null,

	@field:SerializedName("hours_popular")
	val hoursPopular: List<HoursPopularItem?>? = null,

	@field:SerializedName("stats")
	val stats: Stats? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("verified")
	val verified: Boolean? = null,

	@field:SerializedName("tel")
	val tel: String? = null,

	@field:SerializedName("photos")
	val photos: List<PhotosItem?>? = null,

	@field:SerializedName("tips")
	val tips: List<TipsItem?>? = null,

	@field:SerializedName("social_media")
	val socialMedia: SocialMedia? = null,

	@field:SerializedName("menu")
	val menu: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("fax")
	val fax: String? = null
)

data class Stats(

	@field:SerializedName("total_ratings")
	val totalRatings: Int? = null,

	@field:SerializedName("total_photos")
	val totalPhotos: Int? = null,

	@field:SerializedName("total_tips")
	val totalTips: Int? = null
)

data class PhotosItem(

	@field:SerializedName("classifications")
	val classifications: List<String?>? = null,

	@field:SerializedName("prefix")
	val prefix: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("suffix")
	val suffix: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class Main(

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)

data class CategoriesItem(

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class NewMapCenter(

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)

data class FsqResultsItem(

	@field:SerializedName("fsq_id")
	val fsqId: String? = null,

	@field:SerializedName("distance")
	val distance: Int? = null,

	@field:SerializedName("chains")
	val chains: List<Any?>? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("related_places")
	val relatedPlaces: RelatedPlaces? = null,

	@field:SerializedName("rich")
	val rich: Rich? = null,

	@field:SerializedName("geocodes")
	val geocodes: Geocodes? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem?>? = null
)

data class Geocodes(

	@field:SerializedName("roof")
	val roof: Roof? = null,

	@field:SerializedName("main")
	val main: Main? = null
)

data class ChildrenItem(

	@field:SerializedName("fsq_id")
	val fsqId: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class Roof(

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)

data class FeaturesItem(

	@field:SerializedName("geometry")
	val geometry: Geometry? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("properties")
	val properties: Properties? = null
)

data class ResultsGeojson(

	@field:SerializedName("features")
	val features: List<FeaturesItem?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class Icon(

	@field:SerializedName("prefix")
	val prefix: String? = null,

	@field:SerializedName("suffix")
	val suffix: String? = null
)

data class Properties(

	@field:SerializedName("venuename")
	val venuename: String? = null,

	@field:SerializedName("venueid")
	val venueid: String? = null,

	@field:SerializedName("location")
	val location: String? = null
)

data class Location(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("formatted_address")
	val formattedAddress: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("post_town")
	val postTown: String? = null,

	@field:SerializedName("locality")
	val locality: String? = null,

	@field:SerializedName("postcode")
	val postcode: String? = null,

	@field:SerializedName("cross_street")
	val crossStreet: String? = null,

	@field:SerializedName("address_extended")
	val addressExtended: String? = null,

	@field:SerializedName("neighborhood")
	val neighborhood: List<String?>? = null,

	@field:SerializedName("region")
	val region: String? = null
)

data class RelatedPlaces(

	@field:SerializedName("parent")
	val parent: Parent? = null,

	@field:SerializedName("children")
	val children: List<ChildrenItem?>? = null
)

data class Geometry(

	@field:SerializedName("coordinates")
	val coordinates: List<Double?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class HoursPopularItem(

	@field:SerializedName("close")
	val close: String? = null,

	@field:SerializedName("day")
	val day: Int? = null,

	@field:SerializedName("open")
	val open: String? = null
)

data class Parent(

	@field:SerializedName("fsq_id")
	val fsqId: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)
