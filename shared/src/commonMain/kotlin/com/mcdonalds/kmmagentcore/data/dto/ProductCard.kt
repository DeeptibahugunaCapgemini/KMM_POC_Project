package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val description: String? = null,
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
    val original: Double? = null ,
    val discounted: Double?= null  ,
    val currency: String
)

@Serializable
data class Loyalty(
    val points: Int,
    val label: String
)