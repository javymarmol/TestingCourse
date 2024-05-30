package com.plcoding.testingcourse.part1.data

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.plcoding.testingcourse.part1.domain.AnalyticsLogger
import com.plcoding.testingcourse.part1.domain.LogParam

/**
 * Created by Heyner Javier Marmol @javymarmol on 29/05/24.
 * javymarmol.com
 * Copyright (c) 2024 JavyMarmol. All rights reserved.
 **/
class FirebaseAnalyticsLogger(
    private val analytics: FirebaseAnalytics = Firebase.analytics
): AnalyticsLogger {
    override fun logEvent(key: String, vararg params: LogParam<Any>) {

        analytics.logEvent("save_profile") {
            params.forEach { parameters ->
                param(parameters.key, parameters.value.toString())
                param(parameters.key, parameters.value.toString())
            }
        }
    }
}