package com.mcdonalds.kmmagentcore.data.mapper

import com.mcdonalds.kmmagentcore.data.dto.*
import com.mcdonalds.kmmagentcore.data.dto.HeaderSection
import com.mcdonalds.kmmagentcore.data.dto.Screen
import com.mcdonalds.kmmagentcore.data.dto.UIComponent

/**
 * Converts wire-format DTOs into domain entities, resolving every design
 * token to a concrete value along the way (the layout schema resolution step).
 */
class LayoutMapper {

        fun map(screen: Screen): Screen {
            return screen.copy(
                screen = screen.screen.copy(
                    components = screen.screen.components.map { it.process() }
                )
            )
        }

        private fun UIComponent.process(): UIComponent {
            return when (this) {

                is Section -> copy(
                    components = components.map { it.process() }
                )

                is ContentSection -> copy(
                    components = components.map { it.process() }
                )

                is HeaderSection -> copy(
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

