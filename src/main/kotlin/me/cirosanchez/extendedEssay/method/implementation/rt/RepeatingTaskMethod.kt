package me.cirosanchez.extendedEssay.method.implementation.rt

import me.cirosanchez.extendedEssay.ExtendedEssay
import me.cirosanchez.extendedEssay.method.UpdateMethod
import me.cirosanchez.extendedEssay.model.Profile
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.checkerframework.checker.units.qual.m
import java.util.*
import kotlin.collections.HashMap
import kotlin.system.measureTimeMillis

class RepeatingTaskMethod(val plugin: ExtendedEssay) : UpdateMethod {

    val profiles: HashMap<UUID, Profile> = hashMapOf()
    val collection = plugin.collection

    override fun register() {
        plugin.server.pluginManager.registerEvents(Listener(this), plugin)
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, Runnable {
            plugin.metrics.trackCpuUsage {
                profiles.forEach { (uuid, profile) ->
                    profile.save().get()
                }
            }
        }, 0, 20 * 30)

        plugin.metrics.trackCpuUsage {
            collection.find().get().forEach {
                val profile = it
                profiles[profile.uuid] = profile
            }
        }
        plugin.metrics.queries++
    }

    override fun unregister() {
        plugin.metrics.trackCpuUsage {
            profiles.forEach { (uuid, profile) ->
                profile.save().get()
            }
        }
    }

    override fun getName(): String {
        return "Repeating Task Method"
    }

    fun add(player: Player) {
        if (profiles.keys.contains(player.uniqueId)) {
            profiles[player.uniqueId]!!.lastLogin = Date()
            return
        }
        val profile = Profile(player.uniqueId, player.name, 0, 0, Date(), mutableListOf())
        profiles[player.uniqueId] = profile
    }

    fun get(player: Player): Profile? {
        return profiles[player.uniqueId]
    }
}