package ru.ok.catalog.be.common.models

inline class MpClassificationIdModel (
    override val id: String

) : IMpIdModel {
    companion object {
        val NONE = MpClassificationIdModel("")
    }
}