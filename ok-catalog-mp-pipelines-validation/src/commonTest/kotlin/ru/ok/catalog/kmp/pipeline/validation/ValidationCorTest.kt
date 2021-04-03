package ru.ok.catalog.kmp.pipelines.validation

import ru.ok.catalog.common.mp.validation.validators.ValidatorStringNonEmpty
import ru.ok.catalog.common.mp.validation.IValidationError
import ru.ok.catalog.common.mp.validation.ValidationResult
import ru.ok.catalog.kmp.pipeline.pipeline
import ru.ok.catalog.kmp.pipeline.*
import ru.ok.catalog.kmp.pipeline.validation.validation
import runBlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ValidationTest {

    @Test
    fun pipelineValidation() {
        val pl = pipeline<TestContext> {

            validation {
                errorHandler { v: ValidationResult ->
                    if (v.isSuccess) return@errorHandler
                    errors.addAll(v.errors)
                }

                validate<String?> { validator(ValidatorStringNonEmpty()); on { x } }
                validate<String?> { validator(ValidatorStringNonEmpty()); on { y } }
            }
        }
        runBlockingTest {
            val ctx = TestContext()
            pl.execute(ctx)
            assertEquals(2, ctx.errors.size)
        }
    }

    data class TestContext(
        val x: String = "",
        val y: String = "",
        val errors: MutableList<IValidationError> = mutableListOf()
    )
}


