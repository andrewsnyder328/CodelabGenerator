package util

import kotlinext.js.jsObject
import kotlinx.html.HtmlBlockInlineTag
import kotlinx.html.Tag
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.RElementBuilder
import react.RProps
import react.dom.RDOMBuilder

fun <T : Tag> RDOMBuilder<T>.addText(text: String) {
    +text
}

fun RElementBuilder<RProps>.addText(text: String) {
    +text
}

fun <T : Tag> RDOMBuilder<T>.setProps(block: dynamic.() -> Unit) {
    block(this.attrs.asDynamic())
}

fun RElementBuilder<RProps>.setProps(block: dynamic.() -> Unit) {
    block(this.attrs.asDynamic())
}

val RElementBuilder<RProps>.props: dynamic
    get() {
        return this.attrs.asDynamic()
    }

fun <T : Tag> RDOMBuilder<T>.setOnClickListener(block: (Event) -> Unit) {
    if (attrs is HtmlBlockInlineTag) {
        (attrs as HtmlBlockInlineTag).onClickFunction = block
    }
}

fun <T : RProps> RElementBuilder<T>.setOnChangeListener(block: (Event) -> Unit) {
    attrs.asDynamic().onChange = block
}

fun <T : RProps> RElementBuilder<T>.setOnClickListener(block: (Event) -> Unit) {
    attrs.asDynamic().onClick = block
}

fun RElementBuilder<RProps>.setOnHover(block: dynamic.() -> Unit) {
    val props = jsObject<Any> { }
    block(props)
    attrs.asDynamic()._hover = props
}

fun RElementBuilder<RProps>.setOnFocus(block: dynamic.() -> Unit) {
    val props = jsObject<Any> { }
    block(props)
    attrs.asDynamic()._focus = props
}

fun <T : RProps> RElementBuilder<T>.setOnMouseDown(block: (Event) -> Unit) {
    attrs.asDynamic().onMouseDown = block
}