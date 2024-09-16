package me.cirosanchez.extendedEssay.method.implementation.ed


import me.cirosanchez.extendedEssay.ExtendedEssay
import me.cirosanchez.extendedEssay.method.UpdateMethod
import me.cirosanchez.extendedEssay.model.Profile
import org.bukkit.entity.Player
import org.checkerframework.checker.units.qual.m
import java.util.*
import kotlin.collections.HashMap
import kotlin.system.measureTimeMillis

class EnableDisableMethod(val plugin: ExtendedEssay) : UpdateMethod {
    val collection = plugin.collection

    val profiles: HashMap<UUID, Profile> = hashMapOf()
    val metrics = plugin.metrics

    override fun register() {
        plugin.server.pluginManager.registerEvents(Listener(this), plugin)
        metrics.trackCpuUsage {
            collection.find().get().forEach {
                val profile = it
                println(profile.name)
                profiles.put(profile.uuid, profile)
            }
        }
        metrics.queries++
        println("Finished loading profiles.")
    }


    override fun unregister() {
        metrics.trackCpuUsage {
            profiles.forEach { (uuid, profile) ->
                profile.save().get()
            }
        }
        println("Finished saving profiles.")
    }

    override fun getName(): String {
        return "Enable Disable Method"
    }


    fun add(player: Player) {
        if (profiles.keys.contains(player.uniqueId)) {
            profiles.get(player.uniqueId)!!.lastLogin = Date()
            return
        }
        val profile = Profile(player.uniqueId, player.name, 0, 0, Date(), mutableListOf())
        profiles.put(player.uniqueId, profile)
    }

    fun get(player: Player): Profile? {
        return profiles[player.uniqueId]
    }
}