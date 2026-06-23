package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Wire-format DTOs that mirror the layout JSON schema produced by the
 * agent backend. These stay in the data layer; the domain never sees them.
 */
import kotlinx.serialization.json.*
@Serializable
@SerialName("content_section")
data class ContentSection(
    override val type: String,
    val layout: String? = null,
    val components: List<UIComponent>
) : UIComponent()

@Serializable
@SerialName("section")
data class Section(
    override val type: String,
    val layout: String? = null,
    val components: List<UIComponent>
) : UIComponent()
@Serializable
sealed class UIComponent {
    abstract val type: String
}

@Serializable
data class Screen(
    val screen: PageComponent
)

@Serializable
data class PageComponent(
    val id: String,
    val type: String,
    val layout: String? = null,
    val components: List<UIComponent> = emptyList()
)

// -------------------- COMPONENTS --------------------

@Serializable
@SerialName("header_section")
data class HeaderSection(
    override val type: String,
    val components: List<UIComponent>
) : UIComponent()

@Serializable
@SerialName("header")
data class Header(
    override val type: String,
    val props: HeaderProps
) : UIComponent()

@Serializable
data class HeaderProps(
    val title: String,
    val actions: List<IconAction>
)

@Serializable
data class IconAction(
    val type: String,
    val name: String
)

@Serializable
@SerialName("text")
data class TextComponent(
    override val type: String,
    val props: TextProps
) : UIComponent()

@Serializable
data class TextProps(
    val value: String,
    val style: String? = null
)

@Serializable
@SerialName("chip")
data class ChipComponent(
    override val type: String,
    val props: ChipProps
) : UIComponent()

@Serializable
data class ChipProps(
    val label: String,
    val icon: String? = null,
    val action: String? = null,
    val payload: Map<String, String>? = null
)

@Serializable
@SerialName("list")
data class ListComponent(
    override val type: String,
    val props: ListProps
) : UIComponent()

@Serializable
data class ListProps(
    val items: List<UIComponent>
)

@Serializable
@SerialName("product_card")
data class ProductCard(
    override val type: String,
    val props: ProductProps
) : UIComponent()

@Serializable
data class ProductProps(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val image: String? = null,
    val icon: String? = null,
    val highlight: Boolean? = null,
    val price: Price,
    val loyalty: Loyalty? = null,
    val actions: List<ButtonAction> = emptyList()
)

@Serializable
data class Price(
    val value: Double? = null,
    val original: Double? = null,
    val discounted: Double? = null,
    val currency: String
)

@Serializable
data class Loyalty(
    val points: Int,
    val label: String
)

@Serializable
data class ButtonAction(
    val type: String,
    val label: String,
    val action: String,
    val payload: Map<String, String>? = null
)

@Serializable
@SerialName("quick_actions")
data class QuickActions(
    override val type: String,
    val layout: String? = null,
    val props: QuickActionsProps
) : UIComponent()

@Serializable
data class QuickActionsProps(
    val items: List<ChipProps>
)

@Serializable
@SerialName("input_bar")
data class InputBar(
    override val type: String,
    val props: InputBarProps
) : UIComponent()

@Serializable
data class InputBarProps(
    val placeholder: String,
    val actions: List<ButtonAction>
)

@Serializable
@SerialName("footer_section")
data class FooterSection(
    override val type: String,
    val layout: String? = null,
    val components: List<UIComponent>
) : UIComponent()



val module = SerializersModule {
    polymorphic(UIComponent::class) {
        subclass(HeaderSection::class)
        subclass(Header::class)
        subclass(ContentSection::class)   // ✅ added
        subclass(Section::class)          // ✅ added
        subclass(TextComponent::class)
        subclass(ChipComponent::class)
        subclass(ListComponent::class)
        subclass(ProductCard::class)
        subclass(QuickActions::class)
        subclass(InputBar::class)
        subclass(FooterSection::class)
    }
}

val json = Json {
    serializersModule = module
    classDiscriminator = "type"
}