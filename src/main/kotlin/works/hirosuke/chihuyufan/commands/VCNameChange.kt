package works.hirosuke.chihuyufan.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import works.hirosuke.chihuyufan.TempDatas

object VCNameChange: Command() {

    init {
        this.name = "vc"
    }

    override fun execute(event: CommandEvent) {
        val channel = event.guild.voiceChannels.find { channel -> channel.members.contains(event.member) }
        val arg = event.args.replaceFirstChar { it.toString() }

        if (channel == null) {
            event.reply("ボイスチャンネルに参加してから実行してください")
            return
        }

        if (arg.isEmpty()) {
            event.reply("引数が空です")
            return
        }

        if (TempDatas.vc[channel.idLong] == null) TempDatas.vc[channel.idLong] = channel.name
        channel.manager.setName(arg).queue()
        event.reply("チャンネル名を変更しました")
    }
}