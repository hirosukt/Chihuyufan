package works.hirosuke.chihuyufan.listener

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import works.hirosuke.chihuyufan.TempDatas

object VCNameReset: ListenerAdapter() {

    override fun onGuildVoiceMove(event: GuildVoiceMoveEvent) {
        if (event.channelLeft.members.isNotEmpty()) return

        if (event.channelLeft.name == TempDatas.vc[event.channelLeft.idLong]) return

        event.channelLeft.manager.setName(TempDatas.vc[event.channelLeft.idLong] ?: "VC").queue()
    }
}