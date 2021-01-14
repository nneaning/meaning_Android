package meaning.morning.network.response

data class MyFeedResponse(
    val getMyPage: List<GetMyPage>,
    val successDays: Int
) {
    data class GetMyPage(
        val createdAt: String,
        val id: Int,
        val timeStampContents: String,
        val timeStampImageUrl: String
    )
}