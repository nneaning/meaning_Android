package meaning.morning.network.response

data class GroupFeedResponse(
    val `data`: List<Data>,
) {
    data class Data(
        val user: List<User>,
        val createdAt: String,
        val id: Int,
        val status: Int,
        val timeStampContents: String,
        val timeStampImageUrl: String
    ) {
        data class User(
            val id: Int,
            val nickName: String,
            val userName: String,
            val wakeUpTime: String
        )
    }
}