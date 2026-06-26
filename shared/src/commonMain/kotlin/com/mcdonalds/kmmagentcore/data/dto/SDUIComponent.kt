package com.mcdonalds.kmmagentcore.data.dto
import kotlinx.serialization.Serializable

/**
 * Wire-format DTOs that mirror the layout JSON schema produced by the
 * agent backend. These stay in the data layer; the domain never sees them.
 */

@Serializable
sealed class SDUIComponent {
    abstract val type: String
}



@Serializable
data class IconAction(
    val type: String,
    val name: String
)












