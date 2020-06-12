package model

import ContentItem

class ImageModel(id: String, var text: String, var link: String): ContentItem(id) {
    override fun getMarkdown(): String {
        return "[${text}](${link})"
    }
}