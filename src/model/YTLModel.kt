package model

import ContentItem

class YTLModel(var text: String, var link: String): ContentItem() {
    override fun getMarkdown(): String {
        return "![${text}](${link})"
    }
}