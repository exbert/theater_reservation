package com.roomproject.theater.services

import com.roomproject.theater.domain.Seat
import org.springframework.stereotype.Service

@Service
class BookingService {

    fun isSeatFree(seat: Seat) : Boolean {
        return true
    }

}