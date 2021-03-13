package ru.ok.catalog.transport.kmp.models.common

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.classification.MpClassificationCreateDto
import ru.ok.catalog.transport.kmp.models.classification.MpRequestClassificationCreate
import ru.ok.catalog.transport.kmp.models.classification.MpRequestClassificationDelete
import ru.ok.catalog.transport.kmp.models.classification.MpRequestClassificationList

//Модификатор доступа internal означает, что этот член видно в рамках его модуля.
// Модуль - это набор скомпилированных вместе Kotlin файлов:
//
// - модуль в IntelliJ IDEA;
// - Maven или Gradle проект;
// - набор скомпилированных вместе файлов с одним способом вызова <kotlinc> задачи в Ant.
internal class SerializationTestCategory {
    @Test
    fun requestSerialTestCategory() {
        val json = Json{
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
                }
            }
            //указыаем поле по которому будет дескриминатор
            classDiscriminator = "type"
        }


        //
        // проверка Read
        //

        val reqRead = MpRequestCategoryRead(
            categoryId =  "cat-2"
        )
        val reqRead2 = MpRequestCategoryRead(
            categoryId =  "cat-3"
        )
        //val reqStr = json.encodeToString(MpRequestClassificationRead.serializer(), request)
        //этот вариант с полиформизмом:
        val reqReadStr = json.encodeToString(MpMessage.serializer(), reqRead)
        println(reqReadStr)
        assertTrue{ reqReadStr.contains("cat-2")}

        //val dto = json.decodeFromString(MpRequestClassificationRead.serializer(), reqStr)

        //при использовании полиформизма десериализатор целевого класса
        //использовать нельзя, он будет ломаться
        //нужно использовать сериализатор и десириализатор от базового класса
        //MpMessage должен использоваться в каждом классе запроса и ответа
        val dtoRead = json.decodeFromString(MpMessage.serializer(), reqReadStr)

        //безопасное приведение к модели запроса через as?
        assertEquals("cat-2", (dtoRead as? MpRequestCategoryRead)?.categoryId)
        assertEquals(reqRead, (dtoRead as? MpRequestCategoryRead))
        //assertEquals(reqRead2, (dtoRead as? MpRequestCategoryRead))

        //
        // проверка Create
        //

        val reqCreate = MpRequestCategoryCreate(
            createData = MpCategoryCreateDto(
                type = "PRODUCTION",
                title = "Производство металлообрабатывающего оборудования",
                code = "28.41",
                upRefId = "cat-1"
            )
        )
        val reqCreateStr = json.encodeToString(MpMessage.serializer(), reqCreate)
        println(reqCreateStr)
        assertTrue{ reqCreateStr.contains("cat-1")}
        val dtoCreate = json.decodeFromString(MpMessage.serializer(), reqCreateStr)
        assertEquals(reqCreate, (dtoCreate as? MpRequestCategoryCreate))

    }
}