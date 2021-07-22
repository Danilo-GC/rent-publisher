package com.danilo.entrypoint.controller

import com.danilo.core.mapper.RentConverter
import com.danilo.core.ports.RentServicePort
import com.danilo.entrypoint.model.RentDto
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.PathVariable
import java.util.UUID

@Controller("/iupp/v1/rent")
class RentController(private val rentService: RentServicePort) {

    @Post
    fun saveRent(@Body rentDto: RentDto): HttpResponse<RentDto> {
        return HttpResponse.created(
            rentService.rentSave(RentConverter.rentDtoToRent(rentDto)))
    }

    @Delete ("/{id}")
    fun deleteRent(id: UUID): HttpResponse<Unit> {
        rentService.rentDelete(id)
        return HttpResponse.noContent()
    }

    @Put ("/{id}")
    fun updateRent(@PathVariable id:String, @Body rentDto: RentDto): HttpResponse<RentDto> {
        val updatedRent = RentDto(UUID.fromString(id), rentDto.price,rentDto.vehicle,rentDto.brand)
        return HttpResponse.ok<RentDto>().body(
            rentService.rentUpdate(RentConverter.rentDtoToRent(updatedRent)))
    }
}