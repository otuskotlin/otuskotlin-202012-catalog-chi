package ru.ok.catalog.be.common.models

enum class MpStubCase {
    NONE,
    INVALID,
    CATEGORY_CREATE_SUCCESS,
    CATEGORY_READ_SUCCESS,
    CATEGORY_READ_EXCEPTION,
    CATEGORY_READ_ERROR,
    CATEGORY_UPDATE_SUCCESS,
    CATEGORY_DELETE_SUCCESS,
    CATEGORY_LIST_SUCCESS,

    CLASSIFICATION_FILTER_SUCCESS,
    CLASSIFICATION_CREATE_SUCCESS,
    CLASSIFICATION_DELETE_SUCCESS,
}