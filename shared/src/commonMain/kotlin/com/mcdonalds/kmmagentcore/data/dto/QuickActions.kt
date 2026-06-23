package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("quick_actions")
data class QuickActions(
    override val type: String,
    val layout: String? ,
    val props: QuickActionsProps
) : UIComponent()

@Serializable
data class QuickActionsProps(
    val items: List<ChipProps>
)