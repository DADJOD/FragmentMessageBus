package com.example.fragmentmessagebus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


// Активити совершенно пуст
// Для диспетчеризации фрагменты используют
// MessageBus

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
