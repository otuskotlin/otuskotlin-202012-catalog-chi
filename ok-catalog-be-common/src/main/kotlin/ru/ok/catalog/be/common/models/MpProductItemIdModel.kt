package ru.ok.catalog.be.common.models

inline class MpProductItemIdModel (
    override val id: String

) : IMpIdModel {
    companion object {
        val NONE = MpProductItemIdModel("")
    }
}