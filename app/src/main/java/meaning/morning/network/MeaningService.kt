/*
 * Created by jinsu4755
 * DESC: 서버 연결 interface
 * MeaningService.getInstance().apiMethod 로 사용이 가능하다
 */

package meaning.morning.network

interface MeaningService {
    /*TODO 서버 연결을 위한 함수는 여기
    * Ex)
    * @Annotaion
    * fun request0000():Call<BaseResponse<DataClass or any >>
    * */

    

    companion object {
        @Volatile
        private var instance: MeaningService? = null

        fun getInstance(): MeaningService = instance ?: synchronized(this) {
            instance ?: provideService(MeaningService::class.java)
                .apply { instance = this }
        }
    }
}