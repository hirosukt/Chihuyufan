package works.hirosuke.chihuyufan.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

object Neko: Command() {

    init {
        this.name = "neko"
    }

    override fun execute(event: CommandEvent) {
        event.reply("にゃーん")
    }
}