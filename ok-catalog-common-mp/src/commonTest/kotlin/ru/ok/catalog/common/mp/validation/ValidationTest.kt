package ru.ok.catalog.common.mp.validation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ValidationTest {
    @Test

    fun stringValidationTest() {
        val validator = ValidatorStringNotEmpty()
        var res = validator.validate("")
        assertEquals(false, res.isSuccess)
        assertTrue("Must contain \"empty\"") {
            res.errors.map { it.message }.first().contains("empty")
        }
        res = "".validate(validator)
        assertEquals(false, res.isSuccess)
        assertTrue("Must contain \"empty\"") {
            res.errors.map { it.message }.first().contains("empty")
        }
    }

    @Test

    fun rangeValidationTest() {
        val validator = ValidatorInRange("Age",2,5)
        var res = validator.validate(8)
        assertEquals(false, res.isSuccess)
        assertTrue{
            res.errors.map { it.message }.first().contains("must be in range")
        }
        res = 8.validate(validator)
        assertEquals(false, res.isSuccess)
        assertTrue{
            res.errors.map { it.message }.first().contains("must be in range")
        }
    }

    @Test
    fun validatorsListTest() {
        val validator = ValidatorChild()

        //отрицательный сценарий
        var child = Child(10,"")
        var res = validator.validate(child)
        assertEquals(false, res.isSuccess)
        assertTrue("must not be empty"){
            res.errors.filter { it.message.contains("empty") }.isNotEmpty()
        }
        assertTrue("must be in range"){
            res.errors.filter { it.message.contains("must be in range") }.isNotEmpty()
        }

        //положительный сценарий
        child = Child(3,"Petr")
        res = validator.validate(child)
        assertEquals(true, res.isSuccess)
    }

    @Test
    //проверки БИК банка
    fun validatorsBICTest() {
        val validator = ValidatorBIC()
        var test: String
        var res: ValidationResult

        //позитивный сценарий

        test = "БИК корректный"
        res = validator.validate("044030653")
        assertEquals(true, res.isSuccess, test)

        //негативные сценарии

        test = "БИК не пустой"
        res = validator.validate("")
        assertEquals(false, res.isSuccess, test)
        assertTrue(test){
            res.errors.filter { it.message.contains("быть пустым") }.isNotEmpty()
        }

        test = "Длина БИК и начало с 04"
        res = validator.validate("44030653")
        assertEquals(false, res.isSuccess, test)
        assertTrue(test){
            res.errors.filter { it.message.contains("Длина БИК") }.isNotEmpty()
        }
        assertTrue(test){
            res.errors.filter { it.message.contains("должен начинаться") }.isNotEmpty()
        }

        test = "В БИК только цифры"
        res = validator.validate("044O3O653")
        assertEquals(false, res.isSuccess, test)
        assertTrue(test){
            res.errors.filter { it.message.contains("Длина БИК") }.isNotEmpty()
        }
    }

    @Test
    //проверки номера счета по правилам РФ
    fun validatorsAccountRUTest() {
        val validator = ValidatorAccountRU()
        var test: String
        var res: ValidationResult
        var acc: Account

        //позитивный сценарий

        test = "Счет корректный рубли"
        acc = Account("044030653", "40702810455000000123")
        res = validator.validate(acc)
        assertEquals(true, res.isSuccess, test)

        test = "Счет корректный драг. мет."
        acc = Account("044030653", "40702A76655000000123")
        res = validator.validate(acc)
        assertEquals(true, res.isSuccess, test)


        //негативные сценарии

        test = "Счет пустой"
        acc = Account("044030653", "")
        res = validator.validate(acc)
        assertEquals(false, res.isSuccess, test)
        assertTrue(test){
            res.errors.filter { it.message.contains("быть пустым") }.isNotEmpty()
        }

        test = "Ошибка ключа счет в рублях"
        acc = Account("044030653", "40702810155000000123")
        res = validator.validate(acc)
        assertEquals(false, res.isSuccess, test)
        assertTrue(test){
            res.errors.filter { it.message.contains("Некорректный ключевой разряд") }.isNotEmpty()
        }

        test = "Ошибка ключа счет в драг. мет."
        acc = Account("044030653", "40702A76155000000123")
        res = validator.validate(acc)
        assertEquals(false, res.isSuccess, test)
        assertTrue(test){
            res.errors.filter { it.message.contains("Некорректный ключевой разряд") }.isNotEmpty()
        }
    }
}


