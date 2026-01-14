package com.example.todoapp.data

import com.google.gson.*
import java.lang.reflect.Type

class TodoDeserializer : JsonDeserializer<Todo> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Todo {
        val obj = json.asJsonObject
        
        // Konversi is_done dari integer (0 atau 1) ke Boolean
        val isDone = when (obj.get("is_done")?.asInt ?: 0) {
            1 -> true
            else -> false
        }
        
        return Todo(
            id = obj.get("id")?.asInt ?: 0,
            title = obj.get("title")?.asString ?: "",
            description = obj.get("description")?.asString ?: "",
            deadline = obj.get("deadline")?.asString ?: "",
            priority = obj.get("priority")?.asInt ?: 1,
            category = obj.get("category")?.asString ?: "",
            isDone = isDone
        )
    }
}
