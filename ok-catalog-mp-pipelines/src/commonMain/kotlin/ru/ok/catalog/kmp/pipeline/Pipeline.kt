package ru.ok.catalog.kmp.pipeline

class Pipeline<T>
private constructor(
    private val operations: Collection<IOperation<T>>,
    private val checkPrecondition: Predicate<T>,
    private val handleError: ErrorHandler<T>,
) : IOperation<T> {
    override suspend fun execute(context: T) {
        try {
            if (checkPrecondition(context))
                operations.forEach { it.execute(context) }
        } catch (t: Throwable) {
            handleError(context, t)
        }
    }

    @PipelineDsl
    class Builder<T>: IOperationBuilder<T> {
        private val operations = mutableListOf<IOperation<T>>()
        private var checkPrecondition: Predicate<T> = { true }
        private var handleError: ErrorHandler<T> = { throw it }

        fun execute(operation: IOperation<T>) {
            operations.add(operation)
        }

        fun execute(block: Runnable<T>) {
            operations.add(
                Operation.Builder<T>()
                    .apply { execute(block) }
                    .build()
            )
        }

        fun startIf(block: Predicate<T>) {
            checkPrecondition = block
        }

        fun onError(block: ErrorHandler<T>) {
            handleError = block
        }

//        fun <C> Builder<C>.validation(block: ValidationBuilder<C>.() -> Unit) {
//            execute(ValidationBuilder<C>().apply(block).build())
//        }

        override fun build(): Pipeline<T> =
            Pipeline(operations.toList(), checkPrecondition, handleError)
    }
}