package com.example.a00016560_mad.data.remote


import com.example.a00016560_mad.data.Product

data class Response(
    // HTTP-код ответа (например, 200, 404, 500 и т.д.)
    // Позволяет клиенту определить успешность запроса
    val code: Int,
    // Текстовый статус ответа (например, "success", "error")
    // Дублирует информацию кода в человекочитаемом формате
    val status: String,
    val message: String,
    // Основные данные ответа - список объектов Product
    // В случае ошибки может быть пустым или содержать null
    // (если нужно поддерживать null, следует изменить на List<Product>?)

    val data: List<Product>
)