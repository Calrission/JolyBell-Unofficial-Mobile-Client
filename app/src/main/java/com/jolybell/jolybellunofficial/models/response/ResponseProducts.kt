package com.jolybell.jolybellunofficial.models.response

import com.jolybell.jolybellunofficial.models.ModelProductCategory

data class ResponseProducts(
    val `data`: List<ModelProductCategory>,
    val links: Links,
    val meta: Meta,
    override val result: Boolean
): ModelResponse()

data class Meta(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val links: List<Link>,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
)

data class Links(
    val first: String,
    val last: String,
    val next: Any,
    val prev: Any
)

data class Link(
    val active: Boolean,
    val label: String,
    val url: String
)
