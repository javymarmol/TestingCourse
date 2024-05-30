package com.plcoding.testingcourse.part1.domain

/**
 * Created by Heyner Javier Marmol @javymarmol on 29/05/24.
 * javymarmol.com
 * Copyright (c) 2024 JavyMarmol. All rights reserved.
 **/
interface AnalyticsLogger {
    fun logEvent(key: String, vararg params: LogParam<Any>)
}

data class LogParam<T>(
    val key: String,
    val value: T
)