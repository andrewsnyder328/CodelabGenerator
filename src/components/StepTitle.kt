package components

import components.chakra.Button
import components.chakra.Icon
import components.chakra.Stack
import model.StepModel
import react.RBuilder
import util.addText
import util.setOnClickListener
import util.setProps

fun RBuilder.StepTitle(step: StepModel, onStepClicked: () -> Unit, onMoveUp: () -> Unit, onMoveDown: () -> Unit) {

    Stack {
        setProps {
            isInline = true
        }
        Stack {
            Icon {
                setProps {
                    cursor = "pointer"
                    name = "chevron-up"
                }
                setOnClickListener {
                    onMoveUp()
                }
            }
            Icon {
                setProps {
                    cursor = "pointer"
                    name = "chevron-down"
                }
                setOnClickListener {
                    onMoveDown()
                }
            }

        }
        Button {
            setProps {
                width = "100%"
            }
            setOnClickListener {
                onStepClicked()
            }
            addText(step.stepName)
        }
    }
}