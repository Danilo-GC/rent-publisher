package com.danilo.core.ports

import com.danilo.core.model.Rent
import com.danilo.infrastructure.model.RentEvent
import java.util.*
import javax.inject.Singleton

@Singleton
interface NatsServicePort {
    fun sendNats (rentEvent: RentEvent): Rent
    fun deleteNats (id: UUID)
    fun updateNats (rentEvent: RentEvent): Rent
}