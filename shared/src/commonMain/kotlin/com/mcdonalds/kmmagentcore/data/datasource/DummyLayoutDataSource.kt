package com.mcdonalds.kmmagentcore.data.datasource

/**
 * Dummy data source that returns a hard-coded layout JSON payload so the
 * UI and orchestration can be developed before the real backend exists.
 */
class DummyLayoutDataSource : LayoutRemoteDataSource {

    override suspend fun fetchLayoutJson(userId: String,input: String): String = SAMPLE_JSON

    companion object {
//        private val SAMPLE_JSON = "{\n" +
//                "  \"screen\": {\n" +
//                "    \"id\": \"personalised_order_agent\",\n" +
//                "    \"type\": \"page\",\n" +
//                "    \"layout\": \"vertical\",\n" +
//                "    \"components\": [\n" +
//                "\n" +
//                "      {\n" +
//                "        \"type\": \"header_section\",\n" +
//                "        \"components\": [\n" +
//                "          {\n" +
//                "            \"type\": \"header\",\n" +
//                "            \"props\": {\n" +
//                "              \"title\": \"McDonald's\",\n" +
//                "              \"actions\": [\n" +
//                "                { \"type\": \"icon\", \"name\": \"cart\" },\n" +
//                "                { \"type\": \"icon\", \"name\": \"profile\" }\n" +
//                "              ]\n" +
//                "            }\n" +
//                "          }\n" +
//                "        ]\n" +
//                "      },\n" +
//                "\n" +
//                "      {\n" +
//                "        \"type\": \"content_section\",\n" +
//                "        \"layout\": \"vertical\",\n" +
//                "        \"components\": [\n" +
//                "          {\n" +
//                "            \"type\": \"section\",\n" +
//                "            \"layout\": \"vertical\",\n" +
//                "            \"components\": [\n" +
//                "              {\n" +
//                "                \"type\": \"text\",\n" +
//                "                \"props\": {\n" +
//                "                  \"value\": \"Good afternoon\",\n" +
//                "                  \"style\": \"subtle\"\n" +
//                "                }\n" +
//                "              },\n" +
//                "              {\n" +
//                "                \"type\": \"text\",\n" +
//                "                \"props\": {\n" +
//                "                  \"value\": \"Welcome, Abdul\",\n" +
//                "                  \"style\": \"title\"\n" +
//                "                }\n" +
//                "              },\n" +
//                "              {\n" +
//                "                \"type\": \"chip\",\n" +
//                "                \"props\": {\n" +
//                "                  \"icon\": \"square\",\n" +
//                "                  \"label\": \"Tuesday lunch • Chicago\"\n" +
//                "                }\n" +
//                "              }\n" +
//                "            ]\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"section\",\n" +
//                "            \"components\": [\n" +
//                "              {\n" +
//                "                \"type\": \"text\",\n" +
//                "                \"props\": {\n" +
//                "                  \"value\": \"PICKED FOR YOU\",\n" +
//                "                  \"style\": \"section_header\"\n" +
//                "                }\n" +
//                "              }\n" +
//                "            ]\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"list\",\n" +
//                "            \"props\": {\n" +
//                "              \"items\": [\n" +
//                "                {\n" +
//                "                  \"type\": \"product_card\",\n" +
//                "                  \"props\": {\n" +
//                "                    \"id\": \"item_1\",\n" +
//                "                    \"highlight\": true,\n" +
//                "                    \"image\": \"meal\",\n" +
//                "                    \"title\": \"McSpicy Chicken Meal\",\n" +
//                "                    \"subtitle\": \"Your usual · Large\",\n" +
//                "                    \"price\": {\n" +
//                "                      \"original\": 10.49,\n" +
//                "                      \"discounted\": 8.49,\n" +
//                "                      \"currency\": \"\$\"\n" +
//                "                    },\n" +
//                "                    \"loyalty\": {\n" +
//                "                      \"points\": 420,\n" +
//                "                      \"label\": \"Earns 420 pts\"\n" +
//                "                    },\n" +
//                "                    \"actions\": [\n" +
//                "                      {\n" +
//                "                        \"type\": \"button\",\n" +
//                "                        \"label\": \"Add\",\n" +
//                "                        \"action\": \"add_to_cart\",\n" +
//                "                        \"payload\": { \"item_id\": \"item_1\" }\n" +
//                "                      }\n" +
//                "                    ]\n" +
//                "                  }\n" +
//                "                },\n" +
//                "                {\n" +
//                "                  \"type\": \"product_card\",\n" +
//                "                  \"props\": {\n" +
//                "                    \"id\": \"item_2\",\n" +
//                "                    \"icon\": \"meal\",\n" +
//                "                    \"title\": \"McWrap + McCafé\",\n" +
//                "                    \"subtitle\": \"Popular right now\",\n" +
//                "                    \"price\": {\n" +
//                "                      \"value\": 10.49,\n" +
//                "                      \"currency\": \"\$\"\n" +
//                "                    },\n" +
//                "                    \"loyalty\": {\n" +
//                "                      \"points\": 380,\n" +
//                "                      \"label\": \"Earns 380 pts\"\n" +
//                "                    },\n" +
//                "                    \"actions\": [\n" +
//                "                      {\n" +
//                "                        \"type\": \"button\",\n" +
//                "                        \"label\": \"Add\",\n" +
//                "                        \"action\": \"add_to_cart\",\n" +
//                "                        \"payload\": { \"item_id\": \"item_2\" }\n" +
//                "                      }\n" +
//                "                    ]\n" +
//                "                  }\n" +
//                "                },\n" +
//                "                {\n" +
//                "                  \"type\": \"product_card\",\n" +
//                "                  \"props\": {\n" +
//                "                    \"id\": \"item_3\",\n" +
//                "                    \"icon\": \"meal\",\n" +
//                "                    \"title\": \"Filet-O-Fish Meal\",\n" +
//                "                    \"subtitle\": \"Fast prep · Ready now\",\n" +
//                "                    \"price\": {\n" +
//                "                      \"value\": 9.29,\n" +
//                "                      \"currency\": \"\$\"\n" +
//                "                    },\n" +
//                "                    \"loyalty\": {\n" +
//                "                      \"points\": 340,\n" +
//                "                      \"label\": \"Earns 340 pts\"\n" +
//                "                    },\n" +
//                "                    \"actions\": [\n" +
//                "                      {\n" +
//                "                        \"type\": \"button\",\n" +
//                "                        \"label\": \"Add\",\n" +
//                "                        \"action\": \"add_to_cart\",\n" +
//                "                        \"payload\": { \"item_id\": \"item_3\" }\n" +
//                "                      }\n" +
//                "                    ]\n" +
//                "                  }\n" +
//                "                }\n" +
//                "              ]\n" +
//                "            }\n" +
//                "          }\n" +
//                "        ]\n" +
//                "      },\n" +
//                "\n" +
//                "      {\n" +
//                "        \"type\": \"footer_section\",\n" +
//                "        \"layout\": \"vertical\",\n" +
//                "        \"components\": [\n" +
//                "          {\n" +
//                "            \"type\": \"quick_actions\",\n" +
//                "            \"layout\": \"horizontal_scroll\",\n" +
//                "            \"props\": {\n" +
//                "              \"items\": [\n" +
//                "                {\n" +
//                "                  \"type\": \"chip\",\n" +
//                "                  \"label\": \"Desserts\",\n" +
//                "                  \"action\": \"navigate_category\",\n" +
//                "                  \"payload\": { \"category\": \"desserts\" }\n" +
//                "                },\n" +
//                "                {\n" +
//                "                  \"type\": \"chip\",\n" +
//                "                  \"label\": \"Chicken\",\n" +
//                "                  \"action\": \"navigate_category\",\n" +
//                "                  \"payload\": { \"category\": \"chicken\" }\n" +
//                "                },\n" +
//                "                {\n" +
//                "                  \"type\": \"chip\",\n" +
//                "                  \"label\": \"View Cart\",\n" +
//                "                  \"action\": \"view_cart\"\n" +
//                "                }\n" +
//                "              ]\n" +
//                "            }\n" +
//                "          },\n" +
//                "          {\n" +
//                "            \"type\": \"input_bar\",\n" +
//                "            \"props\": {\n" +
//                "              \"placeholder\": \"Type your order...\",\n" +
//                "              \"actions\": [\n" +
//                "                {\n" +
//                "                  \"type\": \"button\",\n" +
//                "                  \"style\": \"floating\",\n" +
//                "                  \"label\": \"Preview\",\n" +
//                "                  \"action\": \"preview_order\"\n" +
//                "                }\n" +
//                "              ]\n" +
//                "            }\n" +
//                "          }\n" +
//                "        ]\n" +
//                "      }\n" +
//                "\n" +
//                "    ]\n" +
//                "  }\n" +
//                "}".trimIndent()

        private val SAMPLE_JSON = "{\n" +
                "    \"username\": \"Siddheshwar\",\n" +
                "    \"usertype\": \"Gold\",\n" +
                "    \"userEarnedPoints\": \"1200\",\n" +
                "    \"clv\": \"High\",\n" +
                "    \"daypart\": \"Lunch\",\n" +
                "    \"screen\": {\n" +
                "        \"id\": \"personalised_order_agent\",\n" +
                "        \"type\": \"page\",\n" +
                "        \"components\": [\n" +
                "            {\n" +
                "                \"type\": \"message\",\n" +
                "                \"label\": \"Hey Siddheshwar, craving a quick bite for lunch? Let's find some delicious fast food! \uD83C\uDF54\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"type\": \"content_section\",\n" +
                "                \"components\": [\n" +
                "                    {\n" +
                "                        \"type\": \"list\",\n" +
                "                        \"props\": {\n" +
                "                            \"items\": [\n" +
                "                                {\n" +
                "                                    \"type\": \"product_card\",\n" +
                "                                    \"props\": {\n" +
                "                                        \"productID\": \"P005\",\n" +
                "                                        \"productImage\": \"https://storage.cloud.google.com/product_image_mcd/ProductImage/Peri%20Peri%20Fries.jpg\",\n" +
                "                                        \"productName\": \"Peri Peri Fries\",\n" +
                "                                        \"productSubtitle\": \"Fast prep · Ready now\",\n" +
                "                                        \"productSize\": \"Large\",\n" +
                "                                        \"productCategory\": \"FastFood\",\n" +
                "                                        \"productQuantity\": 121,\n" +
                "                                        \"productEarnPoints\": 26,\n" +
                "                                        \"productPrice\": {\n" +
                "                                            \"price\": 112,\n" +
                "                                            \"discountedPrice\": 52,\n" +
                "                                            \"currency\": \"\$\"\n" +
                "                                        },\n" +
                "                                        \"actions\": [\n" +
                "                                            {\n" +
                "                                                \"type\": \"button\",\n" +
                "                                                \"label\": \"Add\",\n" +
                "                                                \"action\": \"add_to_cart\"\n" +
                "                                            }\n" +
                "                                        ]\n" +
                "                                    }\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"type\": \"product_card\",\n" +
                "                                    \"props\": {\n" +
                "                                        \"productID\": \"P007\",\n" +
                "                                        \"productImage\": \"https://storage.cloud.google.com/product_image_mcd/ProductImage/Cheeseburger.webp\",\n" +
                "                                        \"productName\": \"Cheeseburger\",\n" +
                "                                        \"productSubtitle\": \"Popular right now \uD83D\uDD25\",\n" +
                "                                        \"productSize\": \"Medium\",\n" +
                "                                        \"productCategory\": \"FastFood\",\n" +
                "                                        \"productQuantity\": 196,\n" +
                "                                        \"productEarnPoints\": 28,\n" +
                "                                        \"productPrice\": {\n" +
                "                                            \"price\": 128,\n" +
                "                                            \"discountedPrice\": 89,\n" +
                "                                            \"currency\": \"\$\"\n" +
                "                                        },\n" +
                "                                        \"actions\": [\n" +
                "                                            {\n" +
                "                                                \"type\": \"button\",\n" +
                "                                                \"label\": \"Add\",\n" +
                "                                                \"action\": \"add_to_cart\"\n" +
                "                                            }\n" +
                "                                        ]\n" +
                "                                    }\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"type\": \"product_card\",\n" +
                "                                    \"props\": {\n" +
                "                                        \"productID\": \"P012\",\n" +
                "                                        \"productImage\": \"https://storage.cloud.google.com/product_image_mcd/ProductImage/Filet-O-Fish.jpg\",\n" +
                "                                        \"productName\": \"Filet-O-Fish\",\n" +
                "                                        \"productSubtitle\": \"Popular right now \uD83D\uDD25\",\n" +
                "                                        \"productSize\": \"Medium\",\n" +
                "                                        \"productCategory\": \"FastFood\",\n" +
                "                                        \"productQuantity\": 178,\n" +
                "                                        \"productEarnPoints\": 20,\n" +
                "                                        \"productPrice\": {\n" +
                "                                            \"price\": 194,\n" +
                "                                            \"discountedPrice\": 134,\n" +
                "                                            \"currency\": \"\$\"\n" +
                "                                        },\n" +
                "                                        \"actions\": [\n" +
                "                                            {\n" +
                "                                                \"type\": \"button\",\n" +
                "                                                \"label\": \"Add\",\n" +
                "                                                \"action\": \"add_to_cart\"\n" +
                "                                            }\n" +
                "                                        ]\n" +
                "                                    }\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"type\": \"product_card\",\n" +
                "                                    \"props\": {\n" +
                "                                        \"productID\": \"P020\",\n" +
                "                                        \"productImage\": \"https://storage.cloud.google.com/product_image_mcd/ProductImage/McChicken.jpeg\",\n" +
                "                                        \"productName\": \"McChicken\",\n" +
                "                                        \"productSubtitle\": \"Fast prep · Ready now\",\n" +
                "                                        \"productSize\": \"Medium\",\n" +
                "                                        \"productCategory\": \"FastFood\",\n" +
                "                                        \"productQuantity\": 91,\n" +
                "                                        \"productEarnPoints\": 46,\n" +
                "                                        \"productPrice\": {\n" +
                "                                            \"price\": 94,\n" +
                "                                            \"discountedPrice\": 48,\n" +
                "                                            \"currency\": \"\$\"\n" +
                "                                        },\n" +
                "                                        \"actions\": [\n" +
                "                                            {\n" +
                "                                                \"type\": \"button\",\n" +
                "                                                \"label\": \"Add\",\n" +
                "                                                \"action\": \"add_to_cart\"\n" +
                "                                            }\n" +
                "                                        ]\n" +
                "                                    }\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"type\": \"product_card\",\n" +
                "                                    \"props\": {\n" +
                "                                        \"productID\": \"P026\",\n" +
                "                                        \"productImage\": \"https://storage.cloud.google.com/product_image_mcd/ProductImage/McVeggie.webp\",\n" +
                "                                        \"productName\": \"McVeggie\",\n" +
                "                                        \"productSubtitle\": \"Fast prep · Ready now\",\n" +
                "                                        \"productSize\": \"Medium\",\n" +
                "                                        \"productCategory\": \"FastFood\",\n" +
                "                                        \"productQuantity\": 111,\n" +
                "                                        \"productEarnPoints\": 21,\n" +
                "                                        \"productPrice\": {\n" +
                "                                            \"price\": 320,\n" +
                "                                            \"discountedPrice\": 305,\n" +
                "                                            \"currency\": \"\$\"\n" +
                "                                        },\n" +
                "                                        \"actions\": [\n" +
                "                                            {\n" +
                "                                                \"type\": \"button\",\n" +
                "                                                \"label\": \"Add\",\n" +
                "                                                \"action\": \"add_to_cart\"\n" +
                "                                            }\n" +
                "                                        ]\n" +
                "                                    }\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"type\": \"product_card\",\n" +
                "                                    \"props\": {\n" +
                "                                        \"productID\": \"P027\",\n" +
                "                                        \"productImage\": \"https://storage.cloud.google.com/product_image_mcd/ProductImage/Double%20Cheeseburger.jpeg\",\n" +
                "                                        \"productName\": \"Double Cheeseburger\",\n" +
                "                                        \"productSubtitle\": \"Fast prep · Ready now\",\n" +
                "                                        \"productSize\": \"Medium\",\n" +
                "                                        \"productCategory\": \"FastFood\",\n" +
                "                                        \"productQuantity\": 102,\n" +
                "                                        \"productEarnPoints\": 47,\n" +
                "                                        \"productPrice\": {\n" +
                "                                            \"price\": 250,\n" +
                "                                            \"discountedPrice\": 235,\n" +
                "                                            \"currency\": \"\$\"\n" +
                "                                        },\n" +
                "                                        \"actions\": [\n" +
                "                                            {\n" +
                "                                                \"type\": \"button\",\n" +
                "                                                \"label\": \"Add\",\n" +
                "                                                \"action\": \"add_to_cart\"\n" +
                "                                            }\n" +
                "                                        ]\n" +
                "                                    }\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}".trimIndent()
    }
}

