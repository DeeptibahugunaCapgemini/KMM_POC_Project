package com.mcdonalds.kmmagentcore.data.mapper

import com.mcdonalds.kmmagentcore.data.dto.*
import com.mcdonalds.kmmagentcore.data.dto.SDUIComponent

/**
 * Converts wire-format DTOs into domain entities, resolving every design
 * token to a concrete value along the way (the layout schema resolution step).
 */
class LayoutMapper {

    fun map(response: OrderAgentResponse): OrderAgentResponse {
        return response.copy(
                screen = response.screen.copy(
                    components = response.screen.components.map { it.process() }
                )

        )
    }


    private fun SDUIComponent.process(): SDUIComponent {
            return when (this) {

                is Section -> copy(
                    components = components.map { it.process() }
                )

                is ContentSection -> copy(
                    components = components.map { it.process() }
                )


                is FooterSection -> copy(
                    components = components.map { it.process() }
                )

                is ListComponent -> copy(
                    props = props.copy(
                        items = props.items.map { it.process() }
                    )
                )

                else -> this
            }
        }

}

