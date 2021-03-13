package ru.ok.catalog.be.common

import java.util.*

//FUID - Friendly unique id
class FUID {
    companion object {
        public fun id(): String {
            val u = UUID.randomUUID()
            return lb64(u.mostSignificantBits)+"-"+lb64(u.leastSignificantBits)
        }

        public fun lb64(v: Long): String {
            val chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@\$"
            var vr = v
            var res = ""
            for ( j in 1 .. 11 ) {
                val i = (vr and 63).toInt()
                vr = vr shr 6
                res = chars.substring(i..i) + res
            }
            return res
        }
    }

}