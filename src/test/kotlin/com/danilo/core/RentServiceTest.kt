package com.danilo.core

import com.danilo.core.model.Rent
import com.danilo.core.ports.NatsServicePort
import com.danilo.core.service.RentService
import com.danilo.entrypoint.model.RentDto
import com.danilo.infrastructure.model.RentEvent
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID

@MicronautTest
class RentServiceTest : AnnotationSpec() {

    val natsServicePort = mockk<NatsServicePort>()
    val rentService = RentService(natsServicePort)

    lateinit var rent: Rent
    lateinit var rentDto: RentDto
    lateinit var rentEvent: RentEvent

    @BeforeEach
    fun setUp() {
        rent = Rent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
        rentDto = RentDto(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
        rentEvent = RentEvent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")


    }

    @Test
    fun `should save rent with success`() {
        every {
            natsServicePort.sendNats(any())
        } returns rent

        val result = rentService.rentSave(rent)
        result shouldBe rentDto
    }

    @Test
    fun `should delete rent with success`() {
        every {
            natsServicePort.deleteNats(rentEvent.id!!)
        } returns Unit
        val result =
            rentService.rentDelete(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"))
        result shouldBe Unit

    }

    @Test
    fun `should edit rent with success`() {
        every {
            natsServicePort.updateNats(any())
        } returns rent
        val result = rentService.rentUpdate(rent)
        result shouldBe rentDto
    }
}