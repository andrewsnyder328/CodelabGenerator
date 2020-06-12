package model

import ContentItem

class TextModel(id: String, var value: String): ContentItem(id) {
    override fun getMarkdown(): String {
        return value
    }
}