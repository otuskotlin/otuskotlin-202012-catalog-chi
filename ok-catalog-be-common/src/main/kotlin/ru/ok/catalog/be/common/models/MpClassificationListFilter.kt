package ru.ok.catalog.transport.kmp.models.classification


data class MpClassificationListFilter (
    //id продукта
    val productId: String = ""
) {
    companion object {
        val NONE = MpClassificationListFilter()
    }
}
