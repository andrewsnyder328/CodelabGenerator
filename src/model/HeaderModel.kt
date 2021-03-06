package model

import ContentItem

class HeaderModel(var title: String): ContentItem() {
    override fun getMarkdown(): String {
        return "###${title}"
    }
}