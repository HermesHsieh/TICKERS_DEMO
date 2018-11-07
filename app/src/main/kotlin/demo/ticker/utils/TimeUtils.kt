package demo.ticker.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    companion object {
        /**
         * timeStampString in SECONDS.
         */
        @JvmStatic
        fun getTimeFormat(timeStampString: String): String {
            if (timeStampString.isNotEmpty()) {
                val timeStamp = java.lang.Long.parseLong(timeStampString)
                return getFormatTimeInDefaultLocale(timeStamp)
            } else {
                return ""
            }
        }

        /**
         * time in MILLISECONDS.
         */
        @JvmStatic
        fun getFormatTimeInDefaultLocale(time: Long): String {
            val cal = Calendar.getInstance(Locale.getDefault())
            cal.timeInMillis = time
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return sdf.format(cal.time)
        }

        /**
         * Timestamp in MILLISECONDS.
         */
        @JvmStatic
        fun getNowTimestamp(): Long {
            val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+00:00"))
            return cal.time.time
        }

        /**
         * Timestamp in MILLISECONDS.
         */
        @JvmStatic
        fun getTodayTimestamp(): Long {
            val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+00:00"))
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)
            return cal.time.time
        }

        /**
         * Long in SECS.
         */
        @JvmStatic
        fun getLongOfDays(days: Int): Long {
            return (days * 24 * 3600).toLong()
        }

        @JvmStatic
        fun compareTimestamp(
                timeStamp1: String,
                timeStamp2: String
        ): Long {
            val time1 = if (timeStamp1.isEmpty()) {
                0L
            } else {
                timeStamp1.toLong()
            }
            val time2 = if (timeStamp2.isEmpty()) {
                0L
            } else {
                timeStamp2.toLong()
            }
            return time1 - time2
        }
    }
}