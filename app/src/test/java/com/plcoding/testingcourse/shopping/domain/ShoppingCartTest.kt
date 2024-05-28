package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

/**
 * Created by Heyner Javier Marmol @javymarmol on 27/05/24.
 * javymarmol.com
 * Copyright (c) 2024 JavyMarmol. All rights reserved.
 */
internal class ShoppingCartTest {

    private lateinit var cart: ShoppingCart

    @BeforeEach
    fun setUp() {
        cart = ShoppingCart()
    }

    @ParameterizedTest
    @CsvSource(
        "3,15.0",
        "0,0.0",
        "6,30.0",
        "20,100.0",
    )
    fun `Add multiple products, total price sum is correct`(
        quantity: Int,
        expectedPriceSum: Double
    ) {
        // GIVEN
        val product = Product(
            id = 0,
            name = "Ice cream",
            price = 5.0
        )
        cart.addProduct(product, quantity)

        //ACTION
        val priceSum = cart.getTotalCost()

        //ASSERTION
        assertThat(priceSum).isEqualTo(expectedPriceSum)
    }

    @RepeatedTest(100)
    fun `Ad product with negative quantity, throws Exception`() {
        val product = Product(
            id = 0,
            name = "Ice cream",
            price = 5.0
        )

        assertFailure {
            cart.addProduct(product, -5)
        }
    }

    @Test
    fun `isValidProduct returns invalid for not existing product`() {
        val product = Product(
            id = 1234,
            name = "Ice cream",
            price = 5.0
        )

        cart.addProduct(product, 4)

        val totalPriceSum = cart.getTotalCost()

        assertThat(totalPriceSum).isEqualTo(0.0)
    }

}