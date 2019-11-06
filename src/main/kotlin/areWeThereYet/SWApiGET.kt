package areWeThereYet

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SWApiGET {

    @GET("starships/?format=json")
    fun getResponse(): Call<SWResponse>

    @GET("starships/?format=json")
    fun getResponse(@Query("page") nextPage: String): Call<SWResponse>

}