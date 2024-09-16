package me.cirosanchez.extendedEssay.method.implementation.rt

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerToggleSneakEvent

class Listener(val method: RepeatingTaskMethod) : Listener {

    @EventHandler
    fun join(event: PlayerJoinEvent){
        method.add(event.player)
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
    fun onPlayerLogin(event: PlayerJoinEvent){
        val player = event.player
        method.add(player)
    }

    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent){
        val player = event.player
        val profile = method.get(player)!!

        profile.messages.add(event.message)

        // method.profiles[player.uniqueId] = profile
    }
}