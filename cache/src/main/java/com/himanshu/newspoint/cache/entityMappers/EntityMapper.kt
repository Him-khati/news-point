package com.himanshu.newspoint.cache.entityMappers

interface EntityMapper<FROM, TO> {

    fun mapFromCached(type: FROM): TO

    fun mapToCached(type: TO): FROM
}








