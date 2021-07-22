package com.danilo.infrastructure.client

import com.danilo.infrastructure.model.EventsInformationDto
import io.micronaut.nats.annotation.NatsClient
import io.micronaut.nats.annotation.Subject
import javax.inject.Singleton

@NatsClient
@Singleton
interface RentClient {

    @Subject("store.rent")
    fun send (eventsInformationDto: EventsInformationDto)
}