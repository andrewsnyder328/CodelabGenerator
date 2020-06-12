package components.content

import components.chakra.Stack
import model.HeaderModel
import react.RBuilder
import util.setOnChangeListener
import util.setOnClickListener
import util.setProps
import view.components.chakra.Button
import view.components.chakra.Input

fun RBuilder.Header(headerModel: HeaderModel, onUpdateItem: (HeaderModel) -> Unit, onDeleteItem: (String) -> Unit) {
    Stack {
        setProps {
            isInline = true
            alignItems = "center"
        }
        Input {
            setProps {
                value = headerModel.title
            }
            setOnChangeListener {
                onUpdateItem(headerModel.apply {
                    title = it.target.asDynamic().value
                })
            }
        }

        Button {
            +"Delete"
            setOnClickListener {
                onDeleteItem(headerModel.id)
            }
        }
    }
}