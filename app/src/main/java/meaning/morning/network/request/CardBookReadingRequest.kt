package meaning.morning.network.request

data class CardBookReadingRequest(
    val bookCommentContents: String,
    val bookTitle: String
)