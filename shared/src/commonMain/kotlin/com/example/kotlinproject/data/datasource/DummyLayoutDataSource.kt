package com.example.kotlinproject.data.datasource

/**
 * Dummy data source that returns a hard-coded layout JSON payload so the
 * UI and orchestration can be developed before the real backend exists.
 */
class DummyLayoutDataSource : LayoutRemoteDataSource {

    override suspend fun fetchLayoutJson(screenId: String): String = when (screenId) {
        "restaurant_menu" -> RESTAURANT_JSON
        else -> SAMPLE_JSON
    }

    companion object {
        private val SAMPLE_JSON = """
            {
              "version": "1.0",
              "screenId": "home_dashboard",
              "title": "Welcome Back",
              "tokens": {
                "colors": {
                  "primary": "#3366FF",
                  "onPrimary": "#FFFFFF",
                  "surface": "#F5F5F5",
                  "textPrimary": "#1A1A1A"
                },
                "spacing": { "small": 8, "medium": 16, "large": 24 },
                "typography": {
                  "heading": { "fontSize": 24, "fontWeight": "bold" },
                  "body": { "fontSize": 16, "fontWeight": "normal" }
                }
              },
              "components": [
                {
                  "id": "root_column",
                  "type": "column",
                  "spacingToken": "medium",
                  "children": [
                    {
                      "id": "title_text",
                      "type": "text",
                      "text": "Your Daily Summary",
                      "colorToken": "textPrimary",
                      "typographyToken": "heading"
                    },
                    {
                      "id": "body_text",
                      "type": "text",
                      "text": "You have 3 new tasks today.",
                      "colorToken": "textPrimary",
                      "typographyToken": "body"
                    },
                    {
                      "id": "cta_button",
                      "type": "button",
                      "text": "View Tasks",
                      "colorToken": "primary",
                      "spacingToken": "small",
                      "action": {
                        "type": "navigate",
                        "payload": { "destination": "tasks_screen" }
                      }
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        private val RESTAURANT_JSON = """
            {
              "version": "1.0",
              "screenId": "restaurant_menu",
              "title": "Tasty Bites Restaurant",
              "tokens": {
                "colors": {
                  "primary": "#E8552D",
                  "onPrimary": "#FFFFFF",
                  "surface": "#FFFFFF",
                  "cardBackground": "#F7F7F7",
                  "textPrimary": "#1A1A1A",
                  "textSecondary": "#6B6B6B",
                  "price": "#E8552D"
                },
                "spacing": { "small": 8, "medium": 16, "large": 24 },
                "typography": {
                  "heading": { "fontSize": 24, "fontWeight": "bold" },
                  "cardTitle": { "fontSize": 20, "fontWeight": "bold" },
                  "productName": { "fontSize": 18, "fontWeight": "bold" },
                  "price": { "fontSize": 16, "fontWeight": "bold" },
                  "body": { "fontSize": 14, "fontWeight": "normal" }
                }
              },
              "components": [
                {
                  "id": "menu_card",
                  "type": "column",
                  "spacingToken": "medium",
                  "colorToken": "cardBackground",
                  "children": [
                    {
                      "id": "menu_card_title",
                      "type": "text",
                      "text": "Popular Dishes",
                      "colorToken": "textPrimary",
                      "typographyToken": "cardTitle"
                    },
                    {
                      "id": "product_card_1",
                      "type": "column",
                      "spacingToken": "small",
                      "colorToken": "surface",
                      "children": [
                        {
                          "id": "product_1_name",
                          "type": "text",
                          "text": "Margherita Pizza",
                          "colorToken": "textPrimary",
                          "typographyToken": "productName"
                        },
                        {
                          "id": "product_1_price",
                          "type": "text",
                          "text": "${'$'}12.99",
                          "colorToken": "price",
                          "typographyToken": "price"
                        },
                        {
                          "id": "product_1_add_button",
                          "type": "button",
                          "text": "Add",
                          "colorToken": "primary",
                          "spacingToken": "small",
                          "action": {
                            "type": "addToCart",
                            "payload": {
                              "productId": "pizza_margherita",
                              "name": "Margherita Pizza",
                              "price": "12.99"
                            }
                          }
                        }
                      ]
                    },
                    {
                      "id": "product_card_2",
                      "type": "column",
                      "spacingToken": "small",
                      "colorToken": "surface",
                      "children": [
                        {
                          "id": "product_2_name",
                          "type": "text",
                          "text": "Classic Cheeseburger",
                          "colorToken": "textPrimary",
                          "typographyToken": "productName"
                        },
                        {
                          "id": "product_2_price",
                          "type": "text",
                          "text": "${'$'}9.49",
                          "colorToken": "price",
                          "typographyToken": "price"
                        },
                        {
                          "id": "product_2_add_button",
                          "type": "button",
                          "text": "Add",
                          "colorToken": "primary",
                          "spacingToken": "small",
                          "action": {
                            "type": "addToCart",
                            "payload": {
                              "productId": "burger_classic",
                              "name": "Classic Cheeseburger",
                              "price": "9.49"
                            }
                          }
                        }
                      ]
                    }
                  ]
                }
              ]
            }
        """.trimIndent()
    }
}

