package com.roomproject.theater.control

import com.roomproject.theater.data.SeatRepository
import com.roomproject.theater.services.BookingService
import com.roomproject.theater.services.TheaterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController {

    @Autowired
    lateinit var theaterService: TheaterService

    @Autowired
    lateinit var bookingService: BookingService

    @Autowired
    lateinit var seatRepository: SeatRepository

    @RequestMapping("")
    fun homePage() : ModelAndView =
        ModelAndView("seatBooking", "bean", CheckAvailabilityBackingBean())

    @RequestMapping("checkAvailability", method = arrayOf(RequestMethod.POST))
    fun checkAvailability(bean: CheckAvailabilityBackingBean) : ModelAndView {
        val selectedSeat = theaterService.find(bean.selectedSeatNum, bean.selectedSeatRow)
        var result = bookingService.isSeatFree(selectedSeat)

        bean.result = "Seat $selectedSeat is " + if (result) "available" else "booked"
        return ModelAndView("seatBooking", "bean", bean)
    }

//    @RequestMapping("bootstrap")
//    fun createInitialData() : ModelAndView {
//
//        val seats = theaterService.seats
//        seatRepository.saveAll(seats)
//        return homePage()
//    }
}

class CheckAvailabilityBackingBean() {

    val seatNums = 1..36
    val seatRows = 'A'..'O'
    var selectedSeatNum: Int = 1
    var selectedSeatRow: Char = 'A'
    var result: String = ""



}