package components.content

import components.chakra.Stack
import model.HeaderModel
import react.RBuilder
import util.addText
import util.setOnChangeListener
import util.setOnClickListener
import util.setProps
import components.chakra.Button
import components.chakra.Input

//TODO refactor out into generic base component
fun RBuilder.Header(headerModel: HeaderModel, onUpdateItem: (HeaderModel) -> Unit, onDeleteItem: (String) -> Unit) {
    Stack {
        setProps {
            isInline = true
            alignItems = "center"
        }
        components.chakra.Text {
            addText("Header:")
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