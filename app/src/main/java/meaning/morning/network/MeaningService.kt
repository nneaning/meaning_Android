/*
 * Created by jinsu4755
 * DESC: 서버 연결 interface
 * MeaningService.getInstance().apiMethod 로 사용이 가능하다
 */

package meaning.morning.network

import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.CalendarResponse
import meaning.morning.network.response.CardTodayPromise
import meaning.morning.network.response.GroupListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MeaningService {
    /*TODO 서버 연결을 위한 함수는 여기
    * Ex)
    * @Annotaion
    * fun request0000():Call<BaseResponse<DataClass or any >>
    * */

    //캘린더 연결
    @GET("/timestamp/calendar")
    fun getCalendar(
        @Header("token") token: String?
    ): Call<BaseResponse<CalendarResponse>>

    /*오늘 하루 다짐*/
    @GET("/user/daypromise")
    fun requestDayPromise(
        @Header("token") token: String
    ): Call<BaseResponse<CardTodayPromise>>

    //그룹 리스트 연결
    @GET("/group?offset=0")
    fun getGroupList(
        @Header("token") token: String?
    ): Call<BaseResponse<GroupListResponse>>

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