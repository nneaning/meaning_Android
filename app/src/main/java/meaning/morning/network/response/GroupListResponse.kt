package meaning.morning.network.response

data class GroupListResponse(
    val hasImageGroupList: List<HasImageGroup>,
    val noImageGroupList: List<NoImageGroup>
) {
    data class HasImageGroup(
        val countMember: Int,
        val groupId: Int,
        val groupName: String,
        val imageUrl: String
    )

    data class NoImageGroup(
        val countMember: Int,
        val groupId: Int,
        val groupName: String,
        val maximumMemberNumber: Int
    )
}
