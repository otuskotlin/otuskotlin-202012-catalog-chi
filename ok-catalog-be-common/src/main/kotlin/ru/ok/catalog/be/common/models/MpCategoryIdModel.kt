package ru.ok.catalog.be.common.models

inline class MpCategoryIdModel (
    override val id: String

) : IMpIdModel<String> {
    companion object {
        val NONE = MpCategoryIdModel("")
    }
}