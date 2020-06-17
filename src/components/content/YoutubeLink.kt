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
import model.YTLModel

//TODO refactor out into generic base component
fun RBuilder.YoutubeLink(ytlModel: YTLModel, onUpdateItem: (YTLModel) -> Unit, onDeleteItem: (String) -> Unit) {
    Stack {
        setProps {
            isInline = true
            alignItems = "center"
        }
        components.chakra.Text {
            addText("YouTube Text:")
        }
        Input {
            setProps {
                value = ytlModel.text
            }
            setOnChangeListener {
                onUpdateItem(ytlModel.apply {
                    text = it.target.asDynamic().text
                })
            }
        }
        components.chakra.Text {
            addText("YouTube Link:")
        }
        Input {
            setProps {
                value = ytlModel.link
            }
            setOnChangeListener {
                onUpdateItem(ytlModel.apply {
                    text = it.target.asDynamic().text
                })
            }
        }

        Button {
            +"Delete"
            setOnClickListener {
                onDeleteItem(ytlModel.id)
            }
        }
    }
}