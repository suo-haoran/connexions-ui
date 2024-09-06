package com.cs206g3.connexions.services


import android.util.Log
import com.cs206g3.connexions.models.ListingRequest
import com.cs206g3.connexions.models.ListingResponse
import com.cs206g3.connexions.models.OnboardingAnswer
import com.cs206g3.connexions.models.OnboardingQuestionResponse
import com.cs206g3.connexions.models.OnboardingResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiTrait {
    @GET("onboarding")
    fun getOnboarding(): Call<List<OnboardingAnswer>>


    @POST("onboarding")
    fun postOnboarding(
        @Body onboardingQuestionResponse: OnboardingQuestionResponse
    ):Call<OnboardingResponse>

    @POST("listing/recommended")
    fun getListing(
        @Body listingRequest: ListingRequest
    ): Call<List<ListingResponse>>
}

object ApiService {
    const val BASEURL = "http://cs206-app-lb-361256259.ap-southeast-1.elb.amazonaws.com/backend/v1/"
    const val IMAGE_BASEURL = "http://cs206-app-lb-361256259.ap-southeast-1.elb.amazonaws.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ApiTrait::class.java)

    /**
     *  Get Onboarding questions from backend
     */
    suspend fun getAnswers(): List<OnboardingAnswer> =
        withContext(Dispatchers.IO) {
            Log.d("Answers", "Sending Request")
            service.getOnboarding().execute().also { println(it.code()) }.body().also { println(it?.toString()) }.orEmpty().map {
                Log.d("Answers", it.image_link?:"")
                if (it.image_link == null) {
                    return@map it
                }
                it.copy(image_link = "${IMAGE_BASEURL}${it.image_link}")
            }.also { println(it) }
        }


    /**
     *  After user answers the questions, send it to backend to store it.
     */
    suspend fun postQuestions(questions: OnboardingQuestionResponse): Boolean =
        withContext(Dispatchers.IO) {
            service.postOnboarding(
                questions
            ).also {
                Log.d("Send Answer", "Sending")
            }.execute().also {
                Log.d("Send Answer", it.code().toString())
                Log.d("Send Answer", it.message().toString())
                Log.d("Send Answer", it.raw().body?.toString().orEmpty())
            }.isSuccessful
        }

    /**
     * Get recommendation listing from backend
     * Note: User must be onboarded first
     */
    suspend fun getRecommendationListing(listingRequest: ListingRequest): List<ListingResponse> =
        withContext(Dispatchers.IO) {
            Log.d("Get Listing", "${listingRequest.personId}")

            service.getListing(listingRequest).execute()
                .also {
                    Log.d("Get Listing", it.code().toString())
                    Log.d("Get Listing", it.message().toString())
                    Log.d("Get Listing", it.raw().body?.toString().orEmpty())
                }
                .body().orEmpty()
                .map {
                    Log.d("Listing image", it.experience.image_link?:"")
                    if (it.experience.image_link == null) {
                        return@map it
                    }
                    it.copy(experience = it.experience.copy(image_link = "${IMAGE_BASEURL}${it.experience.image_link}"))
                }
        }


}