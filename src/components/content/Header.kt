package components.content

import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.dom.button
import react.dom.div

fun RBuilder.Header(id: String, onDeleteItem: (String) -> Unit) {
    div {
        +"Header"
        button {
            +"Delete"
            attrs.onClickFunction = {
                onDeleteItem(id)
            }
        }
    }
}