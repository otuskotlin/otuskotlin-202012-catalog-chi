package ru.ok.catalog.be.common.models

inline class MpClassificationIdModel (
    override val id: String

) : IMpIdModel<String> {
    companion object {
        val NONE = MpClassificationIdModel("")
    }
}