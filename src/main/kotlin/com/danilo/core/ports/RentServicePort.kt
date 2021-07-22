package com.danilo.core.ports

import com.danilo.core.model.Rent
import com.danilo.entrypoint.model.RentDto
import java.util.*
import javax.inject.Singleton

@Singleton
interface RentServicePort {
    fun rentSave (rent: Rent): RentDto
    fun rentDelete (id: UUID)
    fun rentUpdate (rent: Rent): RentDto
}