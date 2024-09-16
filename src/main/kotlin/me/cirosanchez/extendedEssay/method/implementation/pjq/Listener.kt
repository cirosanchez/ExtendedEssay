package me.cirosanchez.extendedEssay.method.implementation.pjq

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import java.util.*

class Listener(val method: PlayerJoinQuitMethod) : Listener {


    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        method.find(event.player)
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        method.save(event.player)
    }

    @EventHandler
    fun onPlayerJump(event: PlayerJumpEvent){
        val player = event.player
        val profile = method.get(player)!!

        profile.jumps++

        // method.profiles[player.uniqueId] = profile
    }

    @EventHandler
    fun onPlayerCrouch(event: PlayerToggleSneakEvent){
        val player = event.player
        val profile = method.get(player)!!

        if (!player.isSneaking) return

        profile.crouches++

        // method.profiles[player.uniqueId] = profile
    }

    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent){
        val player = event.player
        val profile = method.get(player)!!

        profile.messages.add(event.message)

        // method.profiles[player.uniqueId] = profile
    }
}