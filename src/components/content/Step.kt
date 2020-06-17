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
            padding = "16px"
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
                                add(HeaderModel("Header"))
                            }
                            "text" -> {
                                add(TextModel("Text"))
                            }
                            "youtube_link" -> {
                                add(YTLModel("Text", "https://www.youtube.com"))
                            }
                            "image" -> {
                                add(ImageModel("Text", "Link"))
                            }
                            "list" -> {
                                add(ListModel())
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
                            this,
                            item = it,
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
                            this,
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
                            this,
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
                            this,
                            it,
                            onUpdateItem = {imageModel ->
                                onContentUpdated(step.id, step.contentItems.apply {
                                    (find { it.id == imageModel.id } as ImageModel).text = imageModel.text
                                })
                            },
                            onDeleteItem = { id ->
                                onContentUpdated(step.id, step.contentItems.apply {
                                    removeAll { it.id == id }
                                })
                            }
                    )
                }
                is ListModel -> {
                    List(
                            this,
                            it,
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
            setProps {
                variantColor = "red"
            }
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
    IMAGE("image", "Image"),
    LIST("list", "List")
}

