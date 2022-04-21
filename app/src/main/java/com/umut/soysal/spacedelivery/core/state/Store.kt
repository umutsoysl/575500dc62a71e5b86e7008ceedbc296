package com.umut.soysal.spacedelivery.core.state

class Store {

    companion object {
        val shared = Store()
    }

    var map: MutableMap<String, Any> = mutableMapOf()

    fun findMemoryCacheValue(cacheID: String): Any? {
        return  shared.map[cacheID]
    }

    fun setMemoryCacheValue(cacheID: String, value: Any) {
        shared.map[cacheID] = value
    }
}