package ru.ok.catalog.transport.kmp.models.common

import kotlinx.serialization.Serializable

@Serializable
abstract class MpMessage ()
//MpMessage - workaround для обхода неких проблем с тем, что JS
//не дружит с полиморфизмом интерфейсов
//MpMessage должен использоваться в каждом классе запроса и ответа
