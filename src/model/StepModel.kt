package model

import ContentItem

data class StepModel(var id: String, var duration: Int, var stepName: String, var selectedContentType: String, var contentItems: MutableList<ContentItem>)