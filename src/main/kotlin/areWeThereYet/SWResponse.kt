package areWeThereYet


/**
 * This class will be an implementation of the Star Wars Api endpoint /api/starships
 * The parameters outlined within will be populated from the json elements from the
 * endpoint result.
 *
 * The names of these variables correspond to the json elements found in the json
 * result from the api.
 */
class SWResponse {

    lateinit var count: String
    val next: String ?= null
    lateinit var previous: String
    lateinit var results: List<StarShip>
}