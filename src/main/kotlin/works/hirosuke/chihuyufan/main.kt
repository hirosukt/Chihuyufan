package works.hirosuke.chihuyufan

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent

class BotClient : ListenerAdapter() {
    lateinit var jda: JDA

    fun main(token: String) { //トークンを使ってBotを起動する部分
        jda = JDABuilder.createLight(token,
            GatewayIntent.GUILD_MESSAGES)
            .addEventListeners(this)
            .build()
    }

    override fun onReady(event: ReadyEvent) { //Botがログインしたときの処理
        println("Bot has started.")
    }

    override fun onMessageReceived(e: MessageReceivedEvent) {
        if (!e.message.contentDisplay.startsWith(".")) return
        val command = e.message.contentDisplay.substringAfter(".")
        if(command == "neko"){
            e.channel.sendMessageFormat("にゃーん").queue()
        }
    }
}

fun main(args: Array<String>) {
    val bot = BotClient()
    bot.main(args[0])
}