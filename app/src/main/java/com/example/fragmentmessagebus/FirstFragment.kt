package com.example.fragmentmessagebus

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


// В любом другом месте, где нужно получать сообщения из EventBus, должна быть
// функция для приема сообщений, проаннотированная аннотацией @Subscribe

// Чтобы сберечь ресурсы, на EventBus желательно подписыватья
// когда фрагмент виден и отписываться когда он не виден.

// Подписываться удобно в onAttach, отписываться в onDetach.

class FirstFragment : Fragment() {
    private lateinit var receiveData: TextView
    private lateinit var dataToSend: EditText
    private lateinit var sendData: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_first, container, false)

        receiveData = view.findViewById<View>(R.id.first_data) as TextView
        dataToSend = view.findViewById<View>(R.id.first_text) as EditText
        sendData = view.findViewById<View>(R.id.first_post) as Button

        sendData.setOnClickListener {
            val data = dataToSend.text.toString()
            EventBus.getDefault().post(
                // Класс отправителя - используется в качестве тега
                // чтобы понимать, от кого пришли сообщения.
                Message("FIRST", data)
            )
        }
        return view
    }

    // Специальная аннотация
    // Сюда будут приходить сообщения
    @Subscribe
    fun onEvent(message: Message) {
        // Если нужный отправитель, меняем текст
        // Нужно чтобы не показывать и свои сообщения тоже
        if (message.sender == "SECOND") {
            receiveData.text = message.message
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Подписываемся на шину
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        // Отписываемся от шины
        EventBus.getDefault().unregister(this)
    }
}
