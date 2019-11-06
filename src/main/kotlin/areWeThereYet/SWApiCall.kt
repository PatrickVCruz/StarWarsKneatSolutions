package areWeThereYet

import mvc.ShipController
import mvc.ShipView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SWApiCall(var input: Int) {

    private var counter = 1

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

    fun printResults(response: Response<SWResponse>) {
        for (starShip in response.body()?.results!!) {
            val view = ShipView()
            val controller = ShipController(starShip, view, input)
            if (starShip.consumbles == "unknown" || starShip.mglt == "unknown") {
                controller.setResult("unknown")

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



