import areWeThereYet.SWApiGET
import areWeThereYet.SWResponse
import mvc.ShipController
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.junit.rules.ExpectedException
import java.util.*
import java.io.ByteArrayInputStream
import java.io.InputStream


class StarShipTest {
    @Test
    fun checkAPIResponse() {

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
                assertEquals(response.code(), 200)
            }
        })
    }

    @Test
    fun convertToTime() {
        var result = 0
        var testVariable = arrayOf("1","day")
        //24 hours in a day
        var expectedResult = 24

        when (testVariable[1]) {
            "month", "months" -> {
                result = ((testVariable[0].toInt() * 730.0008).toInt())
            }
            "day", "days" -> {
                result = (testVariable[0].toInt() * 24)
            }
            "week", "weeks" -> {
                result = (testVariable[0].toInt() * 168)
            }
            "year", "years" -> {
                result = (testVariable[0].toInt() * 8760)
            }
        }

        assertEquals(expectedResult,result)
        assertNotEquals(expectedResult,testVariable[0])
    }

    @Test
    fun checkDistance(){
        // Y wing = 74 from Coding Challenge Doc
        //Sample output for 1000000 input
        val expectedResult = 74
        //

        //Input / MGLT * Consumables as hours
        var result = 1000000 / (80 * 168)

        assertEquals(expectedResult,result)

    }

}