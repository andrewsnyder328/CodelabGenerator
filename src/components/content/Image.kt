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
import model.ImageModel
import model.YTLModel

//TODO refactor out into generic base component
fun RBuilder.Image(imageModel: ImageModel, onUpdateItem: (ImageModel) -> Unit, onDeleteItem: (String) -> Unit) {
    Stack {
        setProps {
            isInline = true
            alignItems = "center"
        }
        components.chakra.Text {
            addText("Image Text:")
        }
        Input {
            setProps {
                value = imageModel.text
            }
            setOnChangeListener {
                onUpdateItem(imageModel.apply {
                    text = it.target.asDynamic().text
                })
            }
        }
        components.chakra.Text {
            addText("Image Link:")
        }
        Input {
            setProps {
                value = imageModel.link
            }
            setOnChangeListener {
                onUpdateItem(imageModel.apply {
                    text = it.target.asDynamic().text
                })
            }
        }

        Button {
            +"Delete"
            setOnClickListener {
                onDeleteItem(imageModel.id)
            }
        }
    }
}