package works.hirosuke.chihuyufan

import com.jagrosh.jdautilities.command.CommandClientBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.cache.CacheFlag
import works.hirosuke.chihuyufan.commands.Neko
import works.hirosuke.chihuyufan.commands.Poll
import works.hirosuke.chihuyufan.commands.VCNameChange
import works.hirosuke.chihuyufan.commands.Vote
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

        listOf(Neko, VCNameChange, Poll, Vote).forEach { commandListener.addCommand(it) }

        bot = JDABuilder.createDefault(token)
            .enableCache(mutableListOf(CacheFlag.VOICE_STATE, CacheFlag.EMOTE, CacheFlag.MEMBER_OVERRIDES))
            .enableIntents(listOf(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES))
            .setAutoReconnect(true)
            .addEventListeners(commandListener)
            .addEventListeners(VCNameReset)
            .addEventListeners(this)
            .build()
            .awaitReady()
    }

    override fun onReady(event: ReadyEvent) {
        println("Bot has started.")
    }
}

fun main(args: Array<String>) {
    val bot = Bot()
    bot.main(args[0])
}