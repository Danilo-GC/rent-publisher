package com.danilo.infrastructure

import com.danilo.core.model.Rent
import com.danilo.infrastructure.client.RentClient
import com.danilo.infrastructure.model.RentEvent
import com.danilo.infrastructure.service.NatsService
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID

@MicronautTest
class NatsServiceTest : AnnotationSpec() {

    val rentClient = mockk<RentClient>()
    val natsService = NatsService(rentClient)

    lateinit var rent: Rent
    lateinit var rentEvent: RentEvent

    @BeforeEach
    fun setUp() {
        rent = Rent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
        rentEvent = RentEvent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
    }

    @Test
    fun `should send message 'save' with success`() {
        every {
            rentClient.send(any())
        } returns Unit

        val result = natsService.sendNats(rentEvent)
        result shouldBe rent
    }

    @Test
    fun `should send message 'delete' with success`() {
        every {
            rentClient.send(any())
        } returns Unit

        val result = natsService.deleteNats(rentEvent.id!!)
        result shouldBe Unit
    }

    @Test
    fun `should send message 'update' with success`() {
        every {
            rentClient.send(any())
        } returns Unit

        val result = natsService.updateNats(rentEvent)
        result shouldBe rent
    }
}
