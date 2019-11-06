package areWeThereYet

import mvc.ShipController
import mvc.ShipView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * This class calls the Star Wars Api using the Retrofit library
 *
 * @param input this is the user input
 */
class SWApiCall(var input: Int) {


    /**
     *  The counter that checks which page should be called
     */
    private var counter = 1

    /**
     * This function will be called if the json element in the results next is not null
     * This function calls to the Star Wars Api using retrofit. I create a Retrofit object which uses the builder design
     * pattern I add the necessary parameters needed to create the object. The {@link Retrofit#baseUrl} is the base url for
     * the Star Wars Api, the {@link Retrofit#addConverterFactory} I instantiate what format I wish to the result to be in.
     *
     * After that I create an object of {@link SWApiGet} which is an implementation of the Star Wars Api endpoint.
     * After enqueuing the api endpoint is called and response is received. If the response code is a error {@link Callback#onFailure}
     * will be called and the api call would be a failure if not {@link Callback#onSuccess} will be
     * called and from there {@link SWApiCall#printResults} to send the response to be printed
     */
    private fun nextPage() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val swApiGET = retrofit.create(SWApiGET::class.java)

        val ship = swApiGET.getResponse(counter.toString())

        ship.enqueue(object : Callback<SWResponse> {
            override fun onFailure(call: Call<SWResponse>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<SWResponse>, response: Response<SWResponse>) {
                printResults(response)
            }

        })
    }

    /**
     * Like {@link SWApiCall} this function makes a call to the endpoint
     */

    fun start() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val swApiGET = retrofit.create(SWApiGET::class.java)

        val ship = swApiGET.getResponse()

        ship.enqueue(object : Callback<SWResponse> {
            override fun onFailure(call: Call<SWResponse>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<SWResponse>, response: Response<SWResponse>) {
                printResults(response)
            }

        })
    }


    /**
     * This function creates the {@link ShipController} and {@link ShipView} and receives the model {@link StarShip}
     * from the api call. This function receives an {@link SWResponse} which is an implementation of the endpoint results
     * in this particular implementation it has a json array of the ships but the {@link SWResponse} will have an array list
     * of {@link StarShip}. From there it will check if each ship has a valid mglt and consumables if not, the number of
     * resupplies cannot be calculated thus its result is deemed unknown. If the ship does have the valid parameters
     * {@link ShipController} will calculate the number of resupplies needed.
     *
     * @param response It is an implementation of the json object received from the endpoint
     */
    fun printResults(response: Response<SWResponse>) {
        for (starShip in response.body()?.results!!) {
            val view = ShipView()
            val controller = ShipController(starShip, view, input)
            if (starShip.consumables == "unknown" || starShip.mglt == "unknown") {
                controller.setUnknown("unknown")

            } else {
                controller.convertToHours()
                controller.getNoOfResupplies()
            }
            controller.updateView()
        }

        if (response.body()!!.next != null) {
            counter++
            nextPage()
        }
        else{
            System.exit(0)
        }
    }
}



