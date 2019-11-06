package areWeThereYet

import com.google.gson.annotations.SerializedName

class SWResponse {
    @SerializedName("String")
    lateinit var count: String
    val next: String ?= null
    lateinit var previous: String
    lateinit var results: List<StarShip>
}