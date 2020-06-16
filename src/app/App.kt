package app

import ContentItem
import components.StepTitle
import components.chakra.*
import components.content.Step
import model.StepModel
import react.*
import util.*
import kotlin.browser.window

interface AppState : RState {
    var steps: MutableList<StepModel>
    var codelabName: String
    var generatedMarkdown: String
    var selectedStep: StepModel?
}

class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        steps = mutableListOf()
        generatedMarkdown = ""
        codelabName = "New Codelab"
        selectedStep = null
    }

    override fun RBuilder.render() {
        Stack {
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
            Stack {
                setProps {
                    isInline = true
                    width = "100%"
                }
                Stack {
                    setProps {
                        minWidth = "250px"
                        ml = "16px"
                    }

                    state.steps.forEach {
                        StepTitle(it) {
                            setState {
                                selectedStep = it
                            }
                        }
                    }

                    Button {
                        setProps {
                            variantColor = "green"
                        }
                        +"Add New Step"
                        this.setOnClickListener {
                            addStep()
                        }
                    }
                }
                Stack {
                    setProps {
                        spacing = "12px"
                        height = "100%"
                        width = "100%"
                        mx = "24px"
                    }
                    state.selectedStep?.let {
                        Step(it,
                                onDeleteStep = ::deleteStep,
                                onStepUpdated = ::onStepUpdated,
                                onContentUpdated = ::onContentUpdated)
                    }

                }
            }

            Button {
                setProps {
                    variantColor = "teal"
                    width = "250px"
                    alignSelf = "center"
                }
                +"Generate"
                this.setOnClickListener {
                    processMarkdown()
                }
            }

            TextArea {
                setProps {
                    disabled = state.generatedMarkdown.isBlank()
                    value = state.generatedMarkdown
                    minH = "400px"
                    height = "100%"
                    placeholder = "This is where the generated markdown will go"
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
            steps = steps.apply { add(StepModel(uuidv4(), 1, "Step ${steps.size + 1}", "header", mutableListOf())) }
        }
        setState {
            selectedStep = steps.last()
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

    private fun processMarkdown() {
        var markdownString = "#${state.codelabName}"
        state.steps.forEach { step ->
            markdownString += "\n\n"
            markdownString += "##${step.stepName}"
            markdownString += "\nDuration: ${step.duration}"
            step.contentItems.forEach { content ->
                markdownString += "\n\n"
                markdownString += content.getMarkdown()
            }
        }
        setState {
            generatedMarkdown = markdownString
        }
    }
}

fun RBuilder.app() = child(App::class) {}
