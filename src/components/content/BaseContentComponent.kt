package components.content

import ContentItem
import components.chakra.Stack
import react.RBuilder
import util.addText
import util.setOnClickListener
import util.setProps
import components.chakra.Button

abstract class BaseContentComponent<T: ContentItem>(
        builder: RBuilder,
        item: T,
        contentName: String,
        id: String,
        onDeleteItem: (String) -> Unit) {

    init {
        with(builder) {
            Stack {
                setProps {
                    isInline = true
                    alignItems = "center"
                }
                components.chakra.Text {
                    addText("${contentName}:")
                }

                getContent(this, item)

                Button {
                    +"Delete"
                    setOnClickListener {
                        onDeleteItem(id)
                    }
                }
            }
        }
    }

    abstract fun getContent(builder: RBuilder, item: T)
}