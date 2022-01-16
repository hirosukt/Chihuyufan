package works.hirosuke.chihuyufan

import com.jagrosh.jdautilities.command.CommandClientBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.utils.cache.CacheFlag
import works.hirosuke.chihuyufan.commands.Neko
import works.hirosuke.chihuyufan.commands.VCNameChange
import works.hirosuke.chihuyufan.commands.VCNameReset

class Bot : ListenerAdapter() {
    companion object {
        lateinit var bot: JDA
    }

    fun main(token: String) {
        val commandListener = CommandClientBuilder()
            .setPrefix(".")
            .setActivity(Activity.playing("Lunar | .help"))
            .setOwnerId("743393055113216093")
            .setStatus(OnlineStatus.IDLE)
            .setServerInvite("discord.gg/gWTWVsqZB6")
            .build()

        listOf(Neko, VCNameChange, VCNameReset).forEach { commandListener.addCommand(it) }

        bot = JDABuilder.createLight(token)
            .enableCache(mutableListOf(CacheFlag.VOICE_STATE))
            .setAutoReconnect(true)
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