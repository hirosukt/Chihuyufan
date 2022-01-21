package works.hirosuke.chihuyufan.listener

import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import works.hirosuke.chihuyufan.TempDatas

object VCNameReset: ListenerAdapter() {

//    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent) {
//        super.onGuildVoiceUpdate(event)
//    }

    override fun onGuildVoiceLeave(event: GuildVoiceLeaveEvent) {
        val channel = event.guild.getGuildChannelById(ChannelType.VOICE, event.channelLeft.idLong) ?: return
        val name = TempDatas.vc[channel.idLong] ?: "VC"

        if (channel.members.isNotEmpty()) return

        if (channel.name == name) return

        channel.manager.setName(name).complete()
    }
}