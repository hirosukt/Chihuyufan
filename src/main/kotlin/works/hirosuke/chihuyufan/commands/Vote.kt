package works.hirosuke.chihuyufan.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.internal.JDAImpl
import net.dv8tion.jda.internal.entities.GuildImpl
import net.dv8tion.jda.internal.utils.config.AuthorizationConfig
import works.hirosuke.chihuyufan.Bot

object Vote: Command() {

    init {
        this.name = "voite"
    }

    override fun execute(event: CommandEvent) {
        if (event.args.isEmpty()) event.reply("題目と選択肢を入力してください")

        val args = event.args.replaceFirstChar { it.toString() }.split(" ")

        val embed = EmbedBuilder().apply {
            setAuthor(event.author.name, event.message.jumpUrl, event.author.avatarUrl)
            setTitle(args[0])
            setColor(event.member.color)
        }

        val map = mapOf(
            Pair(0, "\uD83C\uDDE6"),
            Pair(1, "\uD83C\uDDE7"),
            Pair(2, "\uD83C\uDDE8"),
            Pair(3, "\uD83C\uDDE9"),
            Pair(4, "\uD83C\uDDEA"),
            Pair(5, "\uD83C\uDDEB"),
            Pair(6, "\uD83C\uDDEC"),
            Pair(7, "\uD83C\uDDED"),
            Pair(8, "\uD83C\uDDEE"),
            Pair(9, "\uD83C\uDDEF"),
            Pair(10, "\uD83C\uDDF0"),
            Pair(11, "\uD83C\uDDF1"),
            Pair(12, "\uD83C\uDDF2"),
            Pair(13, "\uD83C\uDDF3"),
            Pair(14, "\uD83C\uDDF4"),
            Pair(15, "\uD83C\uDDF5"),
            Pair(16, "\uD83C\uDDF6"),
            Pair(17, "\uD83C\uDDF7"),
            Pair(18, "\uD83C\uDDF8"),
            Pair(19, "\uD83C\uDDF9"),
            Pair(20, "\uD83C\uDDFA"),
            Pair(21, "\uD83C\uDDFB"),
            Pair(22, "\uD83C\uDDFC"),
            Pair(23, "\uD83C\uDDFD"),
            Pair(24, "\uD83C\uDDFE"),
            Pair(25, "\uD83C\uDDFF")
        )


        args.drop(0).forEach {
            embed.addField("${map[args.indexOf(it)]}  $it", "", false)
        }

        if (event.message.attachments.isNotEmpty()) embed.setImage(event.message.attachments[0].url)

        event.textChannel.sendMessage(embed.build()).queue()
    }
}