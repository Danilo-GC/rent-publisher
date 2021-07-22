package com.danilo.core.model

import java.math.BigDecimal
import java.util.UUID

data class Rent(

    val id: UUID? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val vehicle: String? = "",
    val brand: String? = "",
)