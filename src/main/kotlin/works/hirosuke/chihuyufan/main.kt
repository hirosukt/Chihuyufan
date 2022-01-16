package works.hirosuke.chihuyufan

import com.jagrosh.jdautilities.command.CommandClientBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import works.hirosuke.chihuyufan.commands.Neko

class Bot : ListenerAdapter() {
    lateinit var bot: JDA

    fun main(token: String) {
        val commandListener = CommandClientBuilder()
            .setPrefix(".")
            .setActivity(Activity.playing("Lunar | .help"))
            .setOwnerId("743393055113216093")
            .setStatus(OnlineStatus.IDLE)
            .setServerInvite("discord.gg/gWTWVsqZB6")
            .addCommand(Neko)
            .build()

        bot = JDABuilder.createLight(token)
            .addEventListeners(commandListener)
            .build()
    }

    override fun onReady(event: ReadyEvent) {
        println("Bot has started.")
    }
}

fun main(args: Array<String>) {
    val bot = Bot()
    bot.main(args[0])
}