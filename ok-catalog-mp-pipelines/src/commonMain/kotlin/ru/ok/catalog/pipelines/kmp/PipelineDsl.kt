package ru.ok.catalog.pipelines.kmp

@DslMarker
//ограничивает, какие методы в каком контексте доступны
//чтобы не вызвать случайно методы внешнего контекста во внутреннем
annotation class PipelineDsl()
