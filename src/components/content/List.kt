package components.content

import components.chakra.Input
import components.chakra.Stack
import model.ListModel
import react.RBuilder
import util.setProps

class List<T: ListModel>(builder: RBuilder,
           item: T,
           onDeleteItem: (String) -> Unit) : BaseContentComponent<T>(builder, item, "List", item.id, onDeleteItem) {

    override fun getContent(builder: RBuilder, item: T) {
        with(builder) {
            //TODO make this dynamic
            Stack {
                setProps {
                    width = "100%"
                }
                Input {

                }
                Input {

                }
                Input {

                }
            }
        }
    }
}

//Use this rather than passing builder in for each class
//fun RBuilder.list(contentName: String, item: ContentItem, onDeleteItem: (String) -> Unit): List {
//    return (List(this, contentName, item, onDeleteItem))
//}