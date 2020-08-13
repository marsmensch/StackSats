package com.androdevlinux.percy.stackingsats.utils

import java.time.LocalDate

object TestUtils {

    @JvmStatic
    fun main(args: Array<String>) {
        val currentDate: LocalDate = LocalDate.now()
            println(currentDate.plusMonths(1).withDayOfMonth(1))

    }
}