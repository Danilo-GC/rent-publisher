package com.danilo.infrastructure.service

import com.danilo.core.mapper.RentConverter
import com.danilo.core.model.Rent
import com.danilo.core.ports.NatsServicePort
import com.danilo.infrastructure.client.RentClient
import com.danilo.infrastructure.model.Events
import com.danilo.infrastructure.model.EventsInformationDto
import com.danilo.infrastructure.model.RentEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID
import javax.inject.Singleton

@Singleton
class NatsService(private val rentClient: RentClient) : NatsServicePort {

    val log: Logger = LoggerFactory.getLogger(NatsService::class.java)

    override fun deleteNats(id: UUID) {
        val eventsInformation = EventsInformationDto(
            Events.DELETE_RENT,
            RentEvent(id)
        )
        rentClient.send(eventsInformation)
        log.info("message 'delete' successfully sent to the broker", eventsInformation.events.event)
    }

    override fun sendNats(rentEvent: RentEvent): Rent {
        val eventsInformation = EventsInformationDto(
            Events.SAVE_RENT,
            RentEvent(null, rentEvent.price, rentEvent.vehicle, rentEvent.brand)
        )
        rentClient.send(eventsInformation)
        log.info("message 'save' successfully sent to the broker", eventsInformation.events.event)
        return RentConverter.rentEventToRent(rentEvent)
    }

    override fun updateNats(rentEvent: RentEvent): Rent {
        val eventsInformation = EventsInformationDto(
            Events.UPDATE_RENT,
            rentEvent
        )
        rentClient.send(eventsInformation)
        log.info("message 'update' successfully sent to the broker", eventsInformation.events.event)
        return RentConverter.rentEventToRent(rentEvent)
    }

}