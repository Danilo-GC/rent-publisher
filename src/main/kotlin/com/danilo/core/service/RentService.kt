package com.danilo.core.service

import com.danilo.core.mapper.RentConverter
import com.danilo.core.model.Rent
import com.danilo.core.ports.NatsServicePort
import com.danilo.core.ports.RentServicePort
import com.danilo.entrypoint.model.RentDto
import java.util.*
import javax.inject.Singleton

@Singleton
class RentService(private val service: NatsServicePort) : RentServicePort {

    override fun rentSave(rent: Rent): RentDto =
        RentConverter.rentToRentDto(
            service.sendNats(RentConverter.rentToRentEvent(rent))
        )

    override fun rentDelete(id: UUID) =
            service.deleteNats(id)


    override fun rentUpdate(rent: Rent): RentDto =
        RentConverter.rentToRentDto(
            service.updateNats(RentConverter.rentToRentEvent(rent))
        )
}


