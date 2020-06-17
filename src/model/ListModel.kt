package model

import ContentItem
import util.uuidv4

class ListModel: ContentItem() {
    override fun getMarkdown(): String {
        return "List"
    }
}