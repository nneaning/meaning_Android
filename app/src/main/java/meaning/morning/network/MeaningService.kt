/*
 * Created by jinsu4755
 * DESC: 서버 연결 interface
 * MeaningService.getInstance().apiMethod 로 사용이 가능하다
 */

package meaning.morning.network

import meaning.morning.network.request.CardBookReadingRequest
import meaning.morning.network.request.CardDailyDiaryRequest
import meaning.morning.network.request.GroupAddRequest
import meaning.morning.network.request.GroupJoinApproveRequest
import meaning.morning.network.response.BaseResponse
import meaning.morning.network.response.CalendarResponse
import meaning.morning.network.response.CardDailyDiaryResponse
import meaning.morning.network.response.CardTodayPromise
import meaning.morning.network.response.GroupAddResponse
import meaning.morning.network.response.GroupDetailResponse
import meaning.morning.network.response.GroupJoinApproveResponse
import meaning.morning.network.response.GroupListResponse
import meaning.morning.network.response.GroupSettingResponse
import meaning.morning.network.response.MyFeedResponse
import meaning.morning.network.response.MyGroupResponse
import meaning.morning.network.response.TimeStampResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query

interface MeaningService {
    // 캘린더 연결
    @GET("/timestamp/calendar")
    fun getCalendar(
        @Header("token") token: String?,
    ): Call<BaseResponse<CalendarResponse>>

    // 그룹 리스트 연결
    @GET("/group?offset=0")
    fun getGroupList(
        @Header("token") token: String?,
    ): Call<BaseResponse<GroupListResponse>>

    // 가입 그룹 조회
    @GET("/group/my")
    fun getMyGroup(
        @Header("token") token: String?,
    ): Call<BaseResponse<MyGroupResponse>>

    // 그룹 상세보기 연결
    @GET("/group/{groupid}")
    fun getGroupDetail(
        @Header("token") token: String?,
        @Path("groupid") groupid: Int,
    ): Call<BaseResponse<GroupDetailResponse>>

    // 그룹 설정 연결
    @GET("/group/{groupId}/edit")
    fun getGroupSetting(
        @Header("token") token: String?,
        @Path("groupId") groupid: Int,
    ): Call<BaseResponse<GroupSettingResponse>>

    // 그룹 참가 신청 연결
    @POST("/group/join")
    fun getApproveJoinGroup(
        @Header("token") token: String?,
        @Body body: GroupJoinApproveRequest,
    ): Call<GroupJoinApproveResponse>

    // 그룹 생성 연결
    @POST("/group")
    fun addGroup(
        @Header("token") token: String?,
        @Body body: GroupAddRequest
    ): Call<GroupAddResponse>

    // 미션 2. 오늘 하루 다짐
    @GET("/user/daypromise")
    fun requestDayPromise(
        @Header("token") token: String?,
    ): Call<BaseResponse<CardTodayPromise>>

    // 미션 3. 회고 일기 작성
    @POST("/user/dailydiary")
    fun requestDailyDaiary(
        @Header("token") token: String?,
        @Body body: CardDailyDiaryRequest,
    ): Call<BaseResponse<CardDailyDiaryResponse>>

    // 미션 4. 짧은 독서 작성
    @POST("/user/bookreview")
    fun requestBookReading(
        @Header("token") token: String?,
        @Body body: CardBookReadingRequest,
    ): Call<BaseResponse<CardBookReadingRequest>>

    // 마이 피드 연결
    @GET("/user/mypage")
    fun requestMyFeed(
        @Header("token") token: String?,
        @Query("offset") offset: Int,
    ): Call<BaseResponse<MyFeedResponse>>

    // 타임스템프 카메라 업로드
    @Multipart
    @POST("/timestamp")
    fun requestPostTimestamp(
        @Header("token") token: String?,
        @PartMap params: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): Call<BaseResponse<TimeStampResponse>>

    companion object {
        @Volatile
        private var instance: MeaningService? = null

        fun getInstance(): MeaningService = instance ?: synchronized(this) {
            instance ?: provideService(MeaningService::class.java)
                .apply { instance = this }
        }
    }
}
