package ru.ok.catalog.transport.kmp.models.common

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.classification.*

fun prepareJson ():Json {
    return Json{
        prettyPrint = true
        //используем полиморфизм, указыаем базовый класс
        serializersModule = SerializersModule {
            polymorphic(MpMessage::class) {
                //TODO: не забываем добавлять сюда классы
                subclass(MpRequestCategoryCreate::class)
                subclass(MpRequestCategoryRead::class)
                subclass(MpRequestCategoryUpdate::class)
                subclass(MpRequestCategoryDelete::class)
                subclass(MpRequestCategoryList::class)
                subclass(MpResponseCategoryCreate::class)
                subclass(MpResponseCategoryRead::class)
                subclass(MpResponseCategoryUpdate::class)
                subclass(MpResponseCategoryDelete::class)
                subclass(MpResponseCategoryList::class)

                subclass(MpRequestClassificationCreate::class)
                subclass(MpRequestClassificationDelete::class)
                subclass(MpRequestClassificationList::class)
                subclass(MpResponseClassificationCreate::class)
                subclass(MpResponseClassificationDelete::class)
                subclass(MpResponseClassificationList::class)
            }
        }
        //указыаем поле по которому будет дескриминатор
        classDiscriminator = "type"
    }
}