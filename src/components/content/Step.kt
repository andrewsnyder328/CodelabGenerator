package components.content

import ContentItem
import components.chakra.Select
import react.*
import components.chakra.Input
import components.chakra.Stack
import react.dom.option
import util.*
import components.chakra.Button
import model.*

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
                    w = "300px"
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
                        when (step.selectedContentType) {
                            "header" -> {
                                add(HeaderModel(uuidv4(), "Header"))
                            }
                            "text" -> {
                                add(TextModel(uuidv4(), "Text"))
                            }
                            "youtube_link" -> {
                                add(YTLModel(uuidv4(), "link", "https://www.youtube.com"))
                            }
                            "image" -> {
                                add(ImageModel(uuidv4(), "link", ""))
                            }
                        }
                    })
                }
            }
            Select {
                setProps {
                    value = step.selectedContentType
                    w = "150px"
                }
                ContentType.values().forEach {
                    option {
                        setProps {
                            value = it.value
                        }
                        addText(it.Text)
                    }
                }
                setOnChangeListener {
                    onStepUpdated(step.apply {
                        selectedContentType = it.target.asDynamic().value
                    })
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
                is TextModel -> {
                    Text(
                            it,
                            onUpdateItem = {textModel ->
                                onContentUpdated(step.id, step.contentItems.apply {
                                    (find { it.id == textModel.id } as TextModel).value = textModel.value
                                })
                            },
                            onDeleteItem = { id ->
                                onContentUpdated(step.id, step.contentItems.apply {
                                    removeAll { it.id == id }
                                })
                            }
                    )
                }
                is YTLModel -> {
                    YoutubeLink(
                            it,
                            onUpdateItem = {ytlModel ->
                                onContentUpdated(step.id, step.contentItems.apply {
                                    (find { it.id == ytlModel.id } as YTLModel).text = ytlModel.text
                                })
                            },
                            onDeleteItem = { id ->
                                onContentUpdated(step.id, step.contentItems.apply {
                                    removeAll { it.id == id }
                                })
                            }
                    )
                }
                is ImageModel -> {
                    Image(
                            it,
                            onUpdateItem = {imageModel ->
                                onContentUpdated(step.id, step.contentItems.apply {
                                    (find { it.id == imageModel.id } as YTLModel).text = imageModel.text
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
        Button {
            +"Delete Step"
            this.setOnClickListener {
                onDeleteStep(step.id)
            }
        }
    }
}

enum class ContentType(val value: String, val Text: String) {
    HEADER("header", "Header"),
    TEXT("text", "Text"),
    YOUTUBE_LINK("youtube_link", "YouTube Link"),
    IMAGE("image", "Image")
}

