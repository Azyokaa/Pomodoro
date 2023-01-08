package com.exo.pomodoro
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import org.bson.Document

fun main() {
    val client: MongoClient = MongoClients.create("mongodb://localhost")
    val database: MongoDatabase = client.getDatabase("myDatabase")
    val users: MongoCollection<Document> = database.getCollection("users")

    // Registration function
    fun register(username: String, password: String) {
        val user = Document("username", username)
            .append("password", password)
        users.insertOne(user)
    }

    // Login function
    fun login(username: String, password: String): Boolean {
        val user = users.find(Filters.eq("username", username)).first()
        return user != null && user["password"] == password
    }
}
