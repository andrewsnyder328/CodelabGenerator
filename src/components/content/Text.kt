package components.content

import components.chakra.Stack
import model.TextModel
import react.RBuilder
import util.addText
import util.setOnChangeListener
import util.setOnClickListener
import util.setProps
import components.chakra.Button
import components.chakra.Input
import components.chakra.TextArea

fun RBuilder.Text(textModel: TextModel, onUpdateItem: (TextModel) -> Unit, onDeleteItem: (String) -> Unit) {
    Stack {
        setProps {
            isInline = true
            alignItems = "center"
        }
        components.chakra.Text {
            addText("Text:")
        }
        TextArea {
            setProps {
                value = textModel.value
            }
            setOnChangeListener {
                onUpdateItem(textModel.apply {
                    value = it.target.asDynamic().value
                })
            }
        }

        Button {
            +"Delete"
            setOnClickListener {
                onDeleteItem(textModel.id)
            }
        }
    }
}