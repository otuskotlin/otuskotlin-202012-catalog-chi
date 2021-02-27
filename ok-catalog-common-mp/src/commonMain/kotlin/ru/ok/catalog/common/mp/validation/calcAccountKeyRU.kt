package ru.ok.catalog.common.mp.validation

fun calcAccountKeyRU(party: String, accnbr: String): String {
    //party - код участника, accnbr - номер счета
    val k = intArrayOf(7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1)
    //буквы в кодах драг. металлов требуют замены на цифры
    val str = party + translate(accnbr.toUpperCase(),"ABCEHKMPTXАВСЕНКМРТХ","01234567890123456789")
    var sum  = 0
    var dig: Int
    for ( i in 0 .. 22 ) {
        dig = if ( i != 8+3 ) {
            str.substring(i, i + 1).toInt()
        } else {
            0
        }
        sum += (dig * k[i]) % 10
    }

    return (((sum % 10) * 3) % 10).toString()
}

fun getPartyCode(bic: String): String {
    val re = Regex(pattern = "^....(..)00[012]$")
    var res = re.find(bic)
    return if ( res != null ) {
        "0" + res.groupValues.get(1)
    } else {
        bic.substring(6,9)
    }
}

fun translate(str: String, src: String, dst: String): String {
    var res: String = ""
    for ( i in 0 .. str.length-1 ) {
        val idx = src.indexOf(str.substring(i, i + 1))
        if ( idx >= 0 ) {
            if ( idx <= dst.length - 1) {
                res += dst.substring(idx,idx+1)
            } else {
                //если за пределами - ничего не делаем, т.е. удаляем
            }
        } else {
            res += str.substring(i, i + 1)
        }
    }
    return res
}