package meaning.morning.network.request

data class GroupAddRequest(
    val groupName: String,
    val maximumMemberNumber : Int,
    val introduction : String
)