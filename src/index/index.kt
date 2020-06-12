package index

import app.*
import kotlinext.js.*
import react.dom.*
import util.setProps
import components.chakra.CSSReset
import components.chakra.Theme
import components.chakra.ThemeProvider
import kotlin.browser.*

fun main(args: Array<String>) {
    requireAll(require.context("src", true, js("/\\.css$/")))

    render(document.getElementById("root")) {
        ThemeProvider {
            setProps {
                theme = Theme
            }
            CSSReset {}
            child(App::class) {}
        }
    }
}
