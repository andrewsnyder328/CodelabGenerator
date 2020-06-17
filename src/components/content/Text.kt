package components.content

import model.TextModel
import react.RBuilder
import util.setOnChangeListener
import util.setProps
import components.chakra.TextArea

class Text<T: TextModel>(builder: RBuilder,
                             item: T,
                             val onUpdateItem: (T) -> Unit,
                             onDeleteItem: (String) -> Unit): BaseContentComponent<T>(builder, item, "Text", item.id, onDeleteItem) {

    override fun getContent(builder: RBuilder, item: T) {
        with(builder) {
            TextArea {
                setProps {
                    value = item.value
                }
                setOnChangeListener {
                    onUpdateItem(item.apply {
                        value = it.target.asDynamic().value
                    })
                }
            }
        }
    }
}