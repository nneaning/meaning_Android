/*
 * Created by jinsu4755
 * DESC: 서버 연결 interface
 * MeaningService.getInstance().apiMethod 로 사용이 가능하다
 */

package meaning.morning.network

import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.CalendarResponse
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

    companion object {
        @Volatile
        private var instance: MeaningService? = null

        fun getInstance(): MeaningService = instance ?: synchronized(this) {
            instance ?: provideService(MeaningService::class.java)
                .apply { instance = this }
        }
    }
}