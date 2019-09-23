package me.ibrahimsn.library

import java.util.*

object Interval {

    val today: TimeInterval
        get() {
            val calendar = Calendar.getInstance()

            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.clear(Calendar.MINUTE)
            calendar.clear(Calendar.SECOND)
            calendar.clear(Calendar.MILLISECOND)
            val start = calendar.timeInMillis

            calendar.add(Calendar.DATE, 1)
            val end = calendar.timeInMillis

            return TimeInterval(start, end)
        }

    val yesterday: TimeInterval
        get() {
            val calendar = Calendar.getInstance()

            calendar.add(Calendar.DATE, -1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.clear(Calendar.MINUTE)
            calendar.clear(Calendar.SECOND)
            calendar.clear(Calendar.MILLISECOND)
            val start = calendar.timeInMillis

            calendar.add(Calendar.DATE, 1)
            val end = calendar.timeInMillis

            return TimeInterval(start, end)
        }


    val last7days: TimeInterval
        get() {
            val calendar = Calendar.getInstance()

            val end = calendar.timeInMillis

            calendar.add(Calendar.DATE, -7)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.clear(Calendar.MINUTE)
            calendar.clear(Calendar.SECOND)
            calendar.clear(Calendar.MILLISECOND)
            val start = calendar.timeInMillis

            return TimeInterval(start, end)
        }

    val last30days: TimeInterval
        get() {
            val calendar = Calendar.getInstance()

            calendar.set(Calendar.AM_PM, Calendar.AM)
            val end = calendar.timeInMillis

            calendar.add(Calendar.DATE, -30)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.clear(Calendar.MINUTE)
            calendar.clear(Calendar.SECOND)
            calendar.clear(Calendar.MILLISECOND)
            val start = calendar.timeInMillis

            return TimeInterval(start, end)
        }

    val week: TimeInterval
        get() {
            val calendar = Calendar.getInstance()

            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.clear(Calendar.MINUTE)
            calendar.clear(Calendar.SECOND)
            calendar.clear(Calendar.MILLISECOND)
            val start = calendar.timeInMillis

            calendar.add(Calendar.DATE, 6)
            val end = calendar.timeInMillis

            return TimeInterval(start, end)
        }

    val month: TimeInterval
        get() {
            val calendar = Calendar.getInstance()

            calendar.set(Calendar.DAY_OF_MONTH, 1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.clear(Calendar.MINUTE)
            calendar.clear(Calendar.SECOND)
            calendar.clear(Calendar.MILLISECOND)
            val start = calendar.timeInMillis

            calendar.add(Calendar.MONTH, 1)
            val end = calendar.timeInMillis

            return TimeInterval(start, end)
        }

    fun monthlyPlan(startDay: Int): TimeInterval {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_MONTH, startDay)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.clear(Calendar.MINUTE)
        calendar.clear(Calendar.SECOND)
        calendar.clear(Calendar.MILLISECOND)
        val start = calendar.timeInMillis

        calendar.add(Calendar.MONTH, 1)
        val end = calendar.timeInMillis

        return TimeInterval(start, end)
    }

    fun weeklyPlan(startDay: Int): TimeInterval {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_WEEK, startDay)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.clear(Calendar.MINUTE)
        calendar.clear(Calendar.SECOND)
        calendar.clear(Calendar.MILLISECOND)
        val start = calendar.timeInMillis

        calendar.add(Calendar.DATE, 6)
        val end = calendar.timeInMillis

        return TimeInterval(start, end)
    }
}