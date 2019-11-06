package areWeThereYet

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This interface will provides the functions needed for the programme. This interface will
 * be implemented by the retrofit library and only the return types needed.
 */
interface SWApiGET {

    /**
     * This method will return a {@link Call<SWResponse} which will be an implementation of the
     * api result
     *
     * The @GET is the relative url
     */
    @GET("starships/?format=json")
    fun getResponse(): Call<SWResponse>

    /**
     * Like the previous method it will be implemented by Retrofit
     * @param nextPage is the query for the next page, if there is a next page since not all
     * the ships are in the api endpoint in the Star Wars Api
     */
    @GET("starships/?format=json")
    fun getResponse(@Query("page") nextPage: String): Call<SWResponse>

}