package me.cirosanchez.extendedEssay.method.implementation.pjq

import me.cirosanchez.extendedEssay.ExtendedEssay
import me.cirosanchez.extendedEssay.method.UpdateMethod
import me.cirosanchez.extendedEssay.model.Profile
import org.bson.BsonString
import org.bson.conversions.Bson
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashMap
import kotlin.system.measureTimeMillis

class PlayerJoinQuitMethod(val plugin: ExtendedEssay) : UpdateMethod {
    val collection = plugin.collection

    val profiles: HashMap<UUID, Profile> = hashMapOf()

    override fun register() {
        plugin.server.pluginManager.registerEvents(Listener(this), plugin)
    }

    override fun unregister() {
        for (player in plugin.server.onlinePlayers){
            save(player)
        }
    }

    override fun getName(): String {
        return "Player Join & Quit Method"
    }

    fun find(player: Player) {
        val uuid = player.uniqueId
        if (profiles.keys.contains(uuid)){
            val profile = profiles[uuid]!!
            profile.lastLogin = Date()
            return
        }

        plugin.metrics.trackCpuUsage {
            val profile = collection.findById(uuid).get().firstOrNull() ?: Profile(uuid, player.name, 0, 0, Date(), mutableListOf())
            profiles[uuid] = profile
        }
        plugin.metrics.queries++
    }


    fun save(player: Player){
        val profile = profiles[player.uniqueId]!!
        plugin.metrics.trackCpuUsage {
            profile.save().get()
        }
    }

    fun get(player: Player): Profile? {
        return profiles[player.uniqueId]
    }
}