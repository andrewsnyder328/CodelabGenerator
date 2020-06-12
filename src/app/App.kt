package app

import ContentItem
import components.chakra.Stack
import components.content.Step
import model.StepModel
import react.*
import util.setOnChangeListener
import util.setOnClickListener
import util.setProps
import util.uuidv4
import components.chakra.Button
import components.chakra.Input

interface AppState : RState {
    var steps: MutableList<StepModel>
    var codelabName: String
}

class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        steps = mutableListOf()
        codelabName = "New Codelab"
    }

    override fun RBuilder.render() {
        Stack {
            setProps {
                spacing = "12px"
            }
            Input {
                setProps {
                    value = state.codelabName
                }
                setOnChangeListener { event ->
                    event.target.let {
                        setState {
                            codelabName = it.asDynamic().value
                        }
                    }

                }
            }
            state.steps.forEachIndexed { index, stepModel ->
                Step(stepModel,
                        onDeleteStep = ::deleteStep,
                        onStepUpdated = ::onStepUpdated,
                        onContentUpdated = ::onContentUpdated)
            }

            Button {
                +"Add New Step"
                this.setOnClickListener {
                    addStep()
                }
            }

            Button {
                +"Generate"
                this.setOnClickListener {
                    console.log("Generate")
                }
            }
        }
    }

    private fun onContentUpdated(stepId: String, contentItems: MutableList<ContentItem>) {
        setState {
            steps.apply {
                find { it.id == stepId }?.contentItems = contentItems
            }
        }
    }

    private fun onStepUpdated(stepModel: StepModel) {
        setState {
            steps.apply {
                set(indexOfFirst { it.id == stepModel.id }, stepModel)
            }
        }
    }

    private fun addStep() {
        setState {
            steps = steps.apply { add(StepModel(uuidv4(), "New Step", "header", mutableListOf())) }
        }
    }

    private fun deleteStep(id: String) {
        setState {
            steps = state.steps.apply {
                removeAll {
                    it.id == id
                }
            }
        }
    }
}

fun RBuilder.app() = child(App::class) {}
