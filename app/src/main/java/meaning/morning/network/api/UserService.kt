/*
 * Created by jinsu4755
 * DESC:
 */

package meaning.morning.network.api

import meaning.morning.data.entity.login.LoginData
import meaning.morning.data.entity.onboard.UserData
import meaning.morning.network.provideService
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {

    @POST("/user/signin")
    fun requestSignIn(
        @Body body: LoginData,
    ): Call<BaseResponse<LoginResponse>>

    @PUT("/user/onboard")
    fun requestPutUserData(
        @Header("token") token: String,
        @Body body: UserData,
    ): Call<BaseResponse<Unit>>

    @PUT("/user/refreshtoken")
    fun requestTokenRefresh(
        @Header("refreshtoken") refreshToken: String?,
    ): Call<BaseResponse<LoginResponse>>

    companion object {
        @Volatile
        private var instance: UserService? = null

        fun getInstance(): UserService = instance ?: synchronized(this) {
            instance ?: provideService(UserService::class.java)
                .apply { instance = this }
        }
    }
}
