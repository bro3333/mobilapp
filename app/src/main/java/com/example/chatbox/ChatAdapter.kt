import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbox.R
import com.example.chatbox.MessageModel
class ChatAdapter(private var messages: List<MessageModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_USER = 0
        const val TYPE_BOT = 1
    }

    // Mesaj tipine göre viewType belirler
    override fun getItemViewType(position: Int): Int {
        return if (messages[position].role == "user") TYPE_USER else TYPE_BOT
    }

    // ViewHolder oluşturur
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_USER -> {
                val view = inflater.inflate(R.layout.chatresponse, parent, false)
                UserMessageViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.chatresponse1, parent, false)
                BotMessageViewHolder(view)
            }
        }
    }

    // ViewHolder'a veriyi bağlar
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder) {
            is UserMessageViewHolder -> holder.bind(message)
            is BotMessageViewHolder -> holder.bind(message)
        }
    }

    override fun getItemCount(): Int = messages.size

    // Kullanıcı mesajı için ViewHolder
    class UserMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText: TextView = itemView.findViewById(R.id.show_message)

        fun bind(message: MessageModel) {
            messageText.text = message.message
        }
    }

    // Bot mesajı için ViewHolder
    class BotMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText: TextView = itemView.findViewById(R.id.response)

        fun bind(message: MessageModel) {
            messageText.text = message.message
        }
    }

    // Yeni mesajlar geldiğinde RecyclerView'u güncelleyen fonksiyon
    fun updateMessages(newMessages: List<MessageModel>) {
        messages = newMessages
        notifyDataSetChanged()  // Adapter'a verilerin değiştiğini bildiriyoruz
    }
}
