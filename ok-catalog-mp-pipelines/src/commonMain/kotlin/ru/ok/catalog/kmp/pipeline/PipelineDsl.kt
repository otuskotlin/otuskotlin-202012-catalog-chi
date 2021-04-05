package ru.ok.catalog.kmp.pipeline

@DslMarker
//ограничивает, какие методы в каком контексте доступны
//чтобы не вызвать случайно методы внешнего контекста во внутреннем
annotation class PipelineDsl()
