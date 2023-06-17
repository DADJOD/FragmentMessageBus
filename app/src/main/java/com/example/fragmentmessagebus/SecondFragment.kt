package com.example.fragmentmessagebus

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
import org.jetbrains.annotations.Nullable

// Комментарии в первом фрагменте
class SecondFragment : Fragment() {
    private lateinit var receiveData: TextView
    private lateinit var dataToSend: EditText
    private lateinit var sendData: Button

    /*
    public interface SecondFragmentReceiver {
        public void secondReceive(String data);
    }
    */
    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?,
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_second, container, false)

        receiveData = view.findViewById<View>(R.id.second_data) as TextView
        dataToSend = view.findViewById<View>(R.id.second_text) as EditText
        sendData = view.findViewById<View>(R.id.second_post) as Button

        sendData.setOnClickListener { /*
                    if(secondReceiver != null)
                    {
                        String text = dataToSend.getText().toString();
                        secondReceiver.secondReceive(text);
                    }
                    */

            val data = dataToSend.text.toString()
            EventBus.getDefault().post(
                Message("SECOND", data )
            )
        }
        return view
    }

    /*
    void dataReceived(String data)
    {
        receiveData.setText(data);
    }
    */
    @Subscribe
    fun onEvent(message: Message) {
        // Если нужный получатель, меняем текст
        // Нужно чтобы не показывать и свои сообщения тоже
        if(message.sender == "FIRST") {
            receiveData.text = message.message
        }
    }

    // private SecondFragmentReceiver secondReceiver;
    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*
        if(context instanceof FirstFragmentReceiver)
        {
            firstReceiver = (FirstFragmentReceiver) context;
        }
        */
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        // firstReceiver = null;
        EventBus.getDefault().unregister(this)
    }
}
