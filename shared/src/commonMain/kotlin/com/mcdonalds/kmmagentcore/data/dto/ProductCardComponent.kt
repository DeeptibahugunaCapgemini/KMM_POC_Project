package com.mcdonalds.kmmagentcore.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("product_card")
data class ProductCard(
    override val type: String,
    val props: ProductProps
) : SDUIComponent()

@Serializable
data class ProductProps(
    val productID: String,
    val productName: String,
    val productCategory: String? = null,
    val productSubtitle: String? = null,
    val productImage: String? = null,
    val productSize: String? = null,
    val icon: String? = null,
    val productQuantity : Double? = null,
    val highlight: Boolean? = null,
    val productPrice: ProductPrice,
    val productEarnPoints: Double? = null,
    val actions: List<ButtonActionComponent> = emptyList()
)

@Serializable
data class ProductPrice(
    val price: Double? = null,
    val discountedPrice: Double?= null  ,
    val currency: String
)

@Serializable
data class Loyalty(
    val points: Int,
    val label: String
)