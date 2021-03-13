package ru.ok.catalog.be.common.models

inline class MpCategoryIdModel (
    override val id: String

) : IMpIdModel {
    companion object {
        val NONE = MpCategoryIdModel("")
    }
}