package com.example.fragmentmessagebus

// Сообщение, которыми будут обмениваться фрагменты
// Содержит таг отправителя и текст сообщения
class Message(
// Будет использоваться класс отправителя
    var sender: String, var message: String,
)