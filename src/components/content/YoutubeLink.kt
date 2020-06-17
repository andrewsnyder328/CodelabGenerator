package components.content

import react.RBuilder
import util.addText
import util.setOnChangeListener
import util.setProps
import components.chakra.Input
import model.YTLModel

class YoutubeLink<T: YTLModel>(builder: RBuilder,
                         item: T,
                         val onUpdateItem: (T) -> Unit,
                         onDeleteItem: (String) -> Unit): BaseContentComponent<T>(builder, item, "YouTube Text", item.id, onDeleteItem) {

    override fun getContent(builder: RBuilder, item: T) {
        with(builder) {
            Input {
                setProps {
                    value = item.text
                }
                setOnChangeListener {
                    onUpdateItem(item.apply {
                        text = it.target.asDynamic().text
                    })
                }
            }
            components.chakra.Text {
                addText("YouTube Link:")
            }
            Input {
                setProps {
                    value = item.link
                }
                setOnChangeListener {
                    onUpdateItem(item.apply {
                        link = it.target.asDynamic().text
                    })
                }
            }
        }
    }
}