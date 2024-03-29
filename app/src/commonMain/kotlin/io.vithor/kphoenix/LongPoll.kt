package io.vithor.kphoenix

import io.vithor.kphoenix.facades.Transport
import io.vithor.kphoenix.facades.TransportFactory

abstract class LongPoll(val endPoint: String) : Transport {
    override val transportPath: String
        get() = path

    companion object : TransportFactory {
        override val path: String
            get() = TRANSPORTS.Longpoll.path

        override fun invoke(endPoint: String, longpollerTimeout: Long): LongPoll? {
            return TRANSPORT_BAG.find { transportClass ->
                transportClass.supertypes.any { it.classifier == LongPoll::class }
            }?.constructors?.firstOrNull()?.call(endPoint) as? LongPoll
        }
    }
}