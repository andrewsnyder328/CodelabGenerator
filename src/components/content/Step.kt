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
import util.setOnClickListener
import util.uuidv4
import view.components.chakra.Button

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
                    onStepUpdated(step.apply {
                        stepName = it.target.asDynamic().value
                    })
                }
            }
            Button {
                +"Add Content Item"
                this.setOnClickListener {
                    onContentUpdated(step.id, step.contentItems.apply {
                        add(HeaderModel(uuidv4(), "Header"))
                    })
                }
            }
            Button {
                +"Delete Step"
                this.setOnClickListener {
                    onDeleteStep(step.id)
                }
            }
        }
        step.contentItems.forEach {
            when(it) {
                is HeaderModel -> {
                    Header(
                            it,
                            onUpdateItem = {headerModel ->
                                onContentUpdated(step.id, step.contentItems.apply {
                                    (find { it.id == headerModel.id } as HeaderModel).title = headerModel.title
                                })
                            },
                            onDeleteItem = { id ->
                                onContentUpdated(step.id, step.contentItems.apply {
                                    removeAll { it.id == id }
                                })
                            }
                    )
                }
            }
        }
    }
}

