package com.plcoding.testingcourse.part4.presentation

import com.plcoding.testingcourse.part1.domain.AnalyticsLogger
import com.plcoding.testingcourse.part1.domain.LogParam
import org.junit.jupiter.api.BeforeEach

/**
 * Created by Heyner Javier Marmol @javymarmol on 29/05/24.
 * javymarmol.com
 * Copyright (c) 2024 JavyMarmol. All rights reserved.
 */
class GoodProfileViewModelTest {
    private lateinit var viewModel: GoodProfileViewModel

    @BeforeEach
    fun setUp() {
        viewModel = GoodProfileViewModel(
            analytics = object : AnalyticsLogger {
                override fun logEvent(key: String, vararg params: LogParam<Any>) = Unit
            }
        )
    }
}