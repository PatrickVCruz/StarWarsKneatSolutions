package areWeThereYet

import com.google.gson.annotations.SerializedName

class StarShip {

    @SerializedName("MGLT")
    lateinit var mglt: String
    @SerializedName("consumables")
    lateinit var consumbles: String

    lateinit var name: String

    var noOfResupplies: String = ""
    var consumablesInHours: Int = 0


}