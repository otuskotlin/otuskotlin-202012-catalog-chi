package ru.ok.catalog.common.mp.validation

data class Account(
    val BIC:String,
    val number:String
)

//валидатор БИК - Банковского идентификационного кода
class ValidatorBIC: IValidator<String> {
    override fun validate(sample: String): ValidationResult {
        var errFound: MutableList<ValidationDefaultError> = mutableListOf<ValidationDefaultError>()
        val bicRe = Regex(pattern = "^[0-9]{9}$")

        fun addErr(errText: String) {
            errFound.add(
                ValidationDefaultError(
                    message = errText
                )
            )
        }

        if ( sample.isNullOrBlank() ) {
            addErr("БИК не должен быть пустым")
        } else {
            if ( sample.length != 9) {
                addErr("Длина БИК должна быть равна 9")
            }
            if ( ! bicRe.matches(sample) ) {
                addErr("Длина БИК должен состоять из цифр")
            }
            if ( ! sample.startsWith("04") ) {
                addErr("БИК должен начинаться с \"04\"")
            }
        }

        if ( errFound.count() > 0 ) {
            return ValidationResult(errors = errFound)
        } else {
            return ValidationResult.SUCCESS
        }
    }
}


//валидатор ключа счета по правилам РФ
class ValidatorAccountRU: IValidator<Account> {
    override fun validate(sample: Account): ValidationResult {
        var errFound: MutableList<ValidationDefaultError> = mutableListOf<ValidationDefaultError>()
        val accRe = Regex("^\\d{5}[0-9ABCEHKMPTXАВСЕНКМРТХ]\\d{14}$")

        fun addErr(errText: String) {
            errFound.add(
                ValidationDefaultError(
                    message = errText
                )
            )
        }

        if ( sample.number.isNullOrBlank() ) {
            addErr("Счет не должен быть пустым")
        } else {
            if ( sample.number.length != 20) {
                addErr("Длина счета должна быть равна 20")
            }
            if ( ! accRe.matches(sample.number) ) {
                addErr("Недопустимые символы в номере счета \"${sample.number}\". Допустимы цифры 0-9 и буквы ABCEHKMPTX.")
            }
            val party = getPartyCode(sample.BIC)
            val key = calcAccountKeyRU(party,sample.number)
            if ( key != sample.number.substring(8,9) ) {
                addErr("Некорректный ключевой разряд в номере счета \"${sample.number}\". Правильное значение \"$key\".")
            }
        }

        if ( errFound.count() > 0 ) {
            return ValidationResult(errors = errFound)
        } else {
            return ValidationResult.SUCCESS
        }
    }
}