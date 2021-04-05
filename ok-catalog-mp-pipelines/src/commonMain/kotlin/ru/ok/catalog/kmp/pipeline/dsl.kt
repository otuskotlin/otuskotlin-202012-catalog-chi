package ru.ok.catalog.kmp.pipeline

inline fun <T> pipeline(block: Pipeline.Builder<T>.() -> Unit): Pipeline<T> =
    Pipeline.Builder<T>().apply(block).build()

inline fun <T> operation(block: Operation.Builder<T>.() -> Unit): Operation<T> =
    Operation.Builder<T>().apply(block).build()

inline fun <T> Pipeline.Builder<T>.pipeline(block: Pipeline.Builder<T>.() -> Unit) {
    execute(Pipeline.Builder<T>().apply(block).build())
}

inline fun <T> Pipeline.Builder<T>.operation(block: Operation.Builder<T>.() -> Unit) {
    execute(Operation.Builder<T>().apply(block).build())
}
//fun example() {
//    pipeline<Unit> {
//        startIf { true }
//
//        operation<Unit> {
//            startIf { true }
//            run {}
//            onError {  }
//        }
//
//        run {}
//
//        onError {}
//    }
//}