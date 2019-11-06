package areWeThereYet

import com.google.gson.annotations.SerializedName

/**
 * This is an object that holds the details for a ship from the Star Wars Api
 * This class is auto populated with Retrofit
 */
class StarShip {
    /**
     *  The Megalight of the ship. The serialized name is so that Retrofit knows which
     *  json element it will populate since the name of the parameter will be the key but
     *  since mglt is all in lowercase @SerializedName must be called to correct it
     *
     */
    @SerializedName("MGLT")
    lateinit var mglt: String
    /**
     * This is the consumables of the ship
     */
    @SerializedName("consumables")
    lateinit var consumables: String
    /**
     * Name of the Ship
     */
    lateinit var name: String

    /**
     * Number of resupplies needed in terms of {@link ShipController} input
     */
    var noOfResupplies: String = ""
    /**
     * The {@link StarShip#consumables} converted into hours
     */
    var consumablesInHours: Int = 0


}