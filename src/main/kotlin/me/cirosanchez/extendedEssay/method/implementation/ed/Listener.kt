package me.cirosanchez.extendedEssay.method.implementation.ed

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import java.util.*

class Listener(val method: EnableDisableMethod) : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        method.add(player)
    }

    @EventHandler
    fun onPlayerJump(event: PlayerJumpEvent){
        val player = event.player
        val profile = method.get(player)!!

        profile.jumps++

        printPlayer(player)

        // method.profiles[player.uniqueId] = profile
    }

    @EventHandler
    fun onPlayerCrouch(event: PlayerToggleSneakEvent){
        val player = event.player
        val profile = method.get(player)!!

        if (!player.isSneaking) return

        profile.crouches++

        printPlayer(player)

        // method.profiles[player.uniqueId] = profile
    }


    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent){
        val player = event.player
        val profile = method.get(player)!!

        profile.messages.add(event.message)

        printPlayer(player)

        // method.profiles[player.uniqueId] = profile
    }


    fun printPlayer(player: Player){
        println("action "+player.name)
    }
}