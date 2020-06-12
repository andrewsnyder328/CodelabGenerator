package app

import ContentItem
import components.chakra.Stack
import components.content.Step
import model.StepModel
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import util.setProps
import util.uuidv4

interface AppState : RState {
    var steps: MutableList<StepModel>
}

class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        steps = mutableListOf()
    }

    override fun RBuilder.render() {
        Stack {
            setProps {
                spacing = "12px"
            }
            state.steps.forEachIndexed { index, stepModel ->
                Step(stepModel,
                        onDeleteStep = ::deleteStep,
                        onStepUpdated = ::onStepUpdated,
                        onContentUpdated = ::onContentUpdated)
            }

            button {
                +"Add New Step"
                this.attrs.onClickFunction = {
                    addStep()
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
            steps = steps.apply { add(StepModel(uuidv4(), "New Step", mutableListOf())) }
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
