package model

import ContentItem

class ImageModel(var text: String, var link: String): ContentItem() {
    override fun getMarkdown(): String {
        return "[${text}](${link})"
    }
}