package com.danilo.core.mapper

import com.danilo.core.model.Rent
import com.danilo.entrypoint.model.RentDto
import com.danilo.infrastructure.model.RentEvent

class RentConverter {
    companion object {
        fun rentEventToRent(rentEvent: RentEvent) =
            Rent(rentEvent.id, rentEvent.price, rentEvent.vehicle, rentEvent.brand)

        fun rentToRentDto(rent: Rent) =
            RentDto(rent.id, rent.price, rent.vehicle, rent.brand)

        fun rentToRentEvent(rent: Rent) =
            RentEvent(rent.id, rent.price, rent.vehicle, rent.brand)

        fun rentDtoToRent(rentDto: RentDto) =
            Rent(rentDto.id, rentDto.price, rentDto.vehicle, rentDto.brand)
    }
}
