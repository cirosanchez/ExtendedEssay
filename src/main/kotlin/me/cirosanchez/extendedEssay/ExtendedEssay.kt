package me.cirosanchez.extendedEssay

import gg.flyte.twilight.data.MongoDB
import gg.flyte.twilight.twilight
import me.cirosanchez.extendedEssay.method.UpdateMethod
import me.cirosanchez.extendedEssay.method.implementation.ed.EnableDisableMethod
import me.cirosanchez.extendedEssay.model.Profile
import me.cirosanchez.extendedEssay.util.DatabaseCredential
import org.bukkit.plugin.java.JavaPlugin
import gg.flyte.twilight.data.TwilightMongoCollection
import me.cirosanchez.extendedEssay.method.implementation.pjq.PlayerJoinQuitMethod
import me.cirosanchez.extendedEssay.method.implementation.rt.RepeatingTaskMethod
import org.bukkit.Bukkit
import org.checkerframework.checker.units.qual.m

class ExtendedEssay : JavaPlugin() {

    companion object {
        fun get() = getPlugin(ExtendedEssay::class.java)
    }


    lateinit var method: UpdateMethod
    lateinit var collection: TwilightMongoCollection<Profile>
    val metrics = Metrics()




    override fun onEnable() {
        twilight(this) {
            mongo {
                uri = DatabaseCredential.URI
                database = "Database"
            }
        }

        collection = MongoDB.collection<Profile>("profiles")

        method = EnableDisableMethod(this)


        method.register()
        Bukkit.getScheduler().runTaskLater(this, Runnable {
            logger.info("PLAYERS ONLINE: ${Bukkit.getOnlinePlayers().size}")
            Bukkit.getConsoleSender().server.dispatchCommand(Bukkit.getConsoleSender(), "stop")
        }, 20 * 60 * 5)
    }

    override fun onDisable() {
        method.unregister()
        println("Method used: ${method.getName()}")
        metrics.printMetrics()
    }


}











