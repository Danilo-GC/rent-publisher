package com.danilo.entrypoint

import com.danilo.core.model.Rent
import com.danilo.core.ports.RentServicePort
import com.danilo.entrypoint.controller.RentController
import com.danilo.entrypoint.model.RentDto
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID

@MicronautTest
class RentControllerTest : AnnotationSpec() {

    val rentServicePort = mockk<RentServicePort>()
    val rentController = RentController(rentServicePort)

    lateinit var rent: Rent
    lateinit var rentDto: RentDto

    @BeforeEach
    fun setUp() {
        rent = Rent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
        rentDto = RentDto(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
    }

    @Test
    fun `should save rent`() {
        every {
            rentServicePort.rentSave(rent)
        } returns rentDto

        val result = rentController.saveRent(rentDto)
        result.status shouldBe HttpStatus.CREATED
    }

    @Test
    fun `should delete rent`() {
        every {
            rentServicePort.rentDelete(rent.id!!)
        } returns Unit

        val result =
            rentController.deleteRent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"))
    }

    @Test
    fun `should update rent`() {
        every {
            rentServicePort.rentUpdate(any())
        } returns rentDto

        val result =
            rentController.updateRent("d5e493a3-61a0-4bbb-9128-a4c893d929b6", rentDto)
        result.status shouldBe HttpStatus.OK
    }
}