package me.cirosanchez.extendedEssay.model

import com.mongodb.client.result.UpdateResult
import gg.flyte.twilight.data.Id
import gg.flyte.twilight.data.MongoSerializable
import me.cirosanchez.extendedEssay.ExtendedEssay
import java.util.*
import java.util.concurrent.CompletableFuture

data class Profile(
    @field:Id
    var uuid: UUID,
    var name: String,
    var jumps: Int,
    var crouches: Int,
    var lastLogin: Date,
    var messages: MutableList<String>
) : MongoSerializable {
    override fun save(): CompletableFuture<UpdateResult> {
        ExtendedEssay.get().metrics.updates++
        return super.save()
    }
}