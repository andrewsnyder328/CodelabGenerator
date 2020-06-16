package components

import components.chakra.Button
import model.StepModel
import react.RBuilder
import util.addText
import util.setOnClickListener

fun RBuilder.StepTitle(step: StepModel, onStepClicked: () -> Unit) {
    Button {
        setOnClickListener {
            onStepClicked()
        }
        addText(step.stepName)
    }
}