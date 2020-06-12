package components.content

import ContentItem
import kotlinx.html.js.onClickFunction
import model.HeaderModel
import model.StepModel
import react.*
import react.dom.button
import util.setOnChangeListener
import util.setProps
import view.components.chakra.Input
import components.chakra.Stack
import util.uuidv4

fun RBuilder.Step(step: StepModel,
                  onDeleteStep: (String) -> Unit,
                  onStepUpdated: (StepModel) -> Unit,
                  onContentUpdated: (String, MutableList<ContentItem>) -> Unit) {

    Stack {
        setProps {
            marginTop = "12px"
            backgroundColor = "#E2E2E2"
        }
        Stack {
            setProps {
                isInline = true
            }
            Input {
                setProps {
                    value = step.stepName
                }
                setOnChangeListener {
                    val newValue = it.target.asDynamic().value
                    onStepUpdated(step.apply {
                        stepName = newValue
                    })
                }
            }
            button {
                +"Add Content Item"
                this.attrs.onClickFunction = {
                    onContentUpdated(step.id, step.contentItems.apply {
                        add(HeaderModel(uuidv4(), "Header"))
                    })
                }
            }
            button {
                +"Delete Step"
                this.attrs.onClickFunction = {
                    onDeleteStep(step.id)
                }
            }
        }
        step.contentItems.forEach {
            when(it) {
                is HeaderModel -> {
                    Header(it.id) { id ->
                        onContentUpdated(step.id, step.contentItems.apply {
                            removeAll { it.id == id }
                        })
                    }
                }
            }
        }
    }
}

