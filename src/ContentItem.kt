import util.uuidv4

abstract class ContentItem(var id: String = uuidv4()) {
    abstract fun getMarkdown(): String
}