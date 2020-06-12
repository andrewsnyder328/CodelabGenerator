package model

import ContentItem

class HeaderModel(id: String, var title: String): ContentItem(id) {
    override fun getMarkdown(): String {
        return "###${title}"
    }
}