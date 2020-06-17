package model

import ContentItem

class TextModel(var value: String): ContentItem() {
    override fun getMarkdown(): String {
        return value
    }
}