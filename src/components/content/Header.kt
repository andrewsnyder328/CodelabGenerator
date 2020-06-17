package components.content

import model.HeaderModel
import react.RBuilder
import util.setOnChangeListener
import util.setProps
import components.chakra.Input

class Header<T: HeaderModel>(builder: RBuilder,
             item: T,
             val onUpdateItem: (T) -> Unit,
             onDeleteItem: (String) -> Unit): BaseContentComponent<T>(builder, item, "Header", item.id, onDeleteItem) {

    override fun getContent(builder: RBuilder, item: T) {
        with(builder) {
            Input {
                setProps {
                    value = item.title
                }
                setOnChangeListener {
                    onUpdateItem(item.apply {
                        title = it.target.asDynamic().value
                    })
                }
            }
        }
    }
}