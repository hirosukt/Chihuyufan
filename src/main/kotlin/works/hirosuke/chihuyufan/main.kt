package works.hirosuke.chihuyufan

import com.jagrosh.jdautilities.command.CommandClientBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.utils.cache.CacheFlag
import works.hirosuke.chihuyufan.commands.Neko
import works.hirosuke.chihuyufan.commands.Poll
import works.hirosuke.chihuyufan.commands.VCNameChange
import works.hirosuke.chihuyufan.listener.Logger
import works.hirosuke.chihuyufan.listener.VCNameReset

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

        listOf(Neko, VCNameChange, Poll).forEach { commandListener.addCommand(it) }

        bot = JDABuilder.createLight(token)
            .enableCache(mutableListOf(CacheFlag.VOICE_STATE))
            .setAutoReconnect(true)
            .addEventListeners(commandListener)
            .addEventListeners(Logger, VCNameReset)
            .addEventListeners(this)
            .build()
    }

    override fun onReady(event: ReadyEvent) {
        println("Bot has started.")
    }

    override fun onGuildVoiceLeave(event: GuildVoiceLeaveEvent) {

        if (event.channelLeft.members.isNotEmpty()) return

        event.channelLeft.manager.setName(TempDatas.vc[event.channelLeft.idLong] ?: "null").queue()
    }
}

fun main(args: Array<String>) {
    val bot = Bot()
    bot.main(args[0])
}