package com.jmcaldera.roomexpenses.data.converter

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

class DateTypeConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): LocalDateTime? =
            if (timestamp == null) {
                null
            } else {
                LocalDateTime.ofEpochSecond(timestamp, 0,
                        ZoneOffset.ofTotalSeconds(0))
            }

    @TypeConverter
    fun toTimestamp(date: LocalDateTime?): Long? =
            date?.toInstant(ZoneOffset.ofTotalSeconds(0))?.epochSecond
}