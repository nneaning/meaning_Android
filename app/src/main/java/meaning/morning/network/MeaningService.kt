/*
 * Created by jinsu4755
 * DESC: 서버 연결 interface
 * MeaningService.getInstance().apiMethod 로 사용이 가능하다
 */

package meaning.morning.network

import meaning.morning.network.request.CardBookReadingRequest
import meaning.morning.network.request.CardDailyDiaryRequest
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.CalendarResponse
import meaning.morning.network.response.CardDailyDiaryResponse
import meaning.morning.network.response.CardTodayPromise
import meaning.morning.network.response.GroupListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MeaningService {

    // 캘린더 연결
    @GET("/timestamp/calendar")
    fun getCalendar(
        @Header("token") token: String?
    ): Call<BaseResponse<CalendarResponse>>

    // 미션 2. 오늘 하루 다짐
    @GET("/user/daypromise")
    fun requestDayPromise(
        @Header("token") token: String
    ): Call<BaseResponse<CardTodayPromise>>

    // 그룹 리스트 연결
    @GET("/group?offset=0")
    fun getGroupList(
        @Header("token") token: String?
    ): Call<BaseResponse<GroupListResponse>>

    // 미션 3. 회고 일기 작성
    @POST("/user/dailydiary")
    fun requestDailyDaiary(
        @Header("token") token: String,
        @Body body: CardDailyDiaryRequest
    ): Call<BaseResponse<CardDailyDiaryResponse>>

    // 미션 4. 짧은 독서 작성
    @POST("/user/bookreview")
    fun requestBookReading(
        @Header("token") token: String,
        @Body body: CardBookReadingRequest
    ): Call<BaseResponse<CardBookReadingRequest>>

    companion object {
        @Volatile
        private var instance: MeaningService? = null

        const val meaningToken =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTcsIm5hbWUiOiLrsJXtmqjshqEiLCJpYXQiOjE2MTA0NjgyMTIsImV4cCI6MTYxMjI4MjYxMiwiaXNzIjoiU2VydmVyQmFkIn0.sVKcyYHYkEe3nq5xi36hQDLn1XWpxI6l_ermMBt3aYE"

        fun getInstance(): MeaningService = instance ?: synchronized(this) {
            instance ?: provideService(MeaningService::class.java)
                .apply { instance = this }
        }
    }
}
