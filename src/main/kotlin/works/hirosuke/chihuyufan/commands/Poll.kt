package works.hirosuke.chihuyufan.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

object Poll: Command() {

    init {
        this.name = "poll"
    }

    override fun execute(event: CommandEvent) {
        if (event.args.isEmpty()) event.reply("題名を入力してください")

        val arg = event.args.replaceFirstChar { it.toString() }

        val embed = EmbedBuilder()
            .setAuthor(event.author.name, event.message.jumpUrl, event.author.avatarUrl)
            .setTitle(arg)
            .setColor(event.member.color)

        if (event.message.attachments.isNotEmpty()) embed.setImage(event.message.attachments[0].url)

        event.textChannel.sendMessage(embed.build()).queue {
            it.addReaction("\uD83D\uDC4D").queue()
            it.addReaction("👎").queue()
        }
    }
}