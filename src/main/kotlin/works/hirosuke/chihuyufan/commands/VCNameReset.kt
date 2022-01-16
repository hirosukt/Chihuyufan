package works.hirosuke.chihuyufan.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import works.hirosuke.chihuyufan.TempDatas

object VCNameReset: Command() {

    init {
        this.name = "reset"
    }

    override fun execute(event: CommandEvent) {
        val channel = event.guild.voiceChannels.find { channel -> channel.members.contains(event.member) }

        if (channel == null) {
            event.reply("ボイスチャンネルに参加してから実行してください")
            return
        }

        channel.manager.setName(TempDatas.vc[channel.idLong] ?: "null").queue()
        event.reply("チャンネル名を変更しました")
    }
}