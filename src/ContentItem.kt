abstract class ContentItem(var id: String) {
    abstract fun getMarkdown(): String
}