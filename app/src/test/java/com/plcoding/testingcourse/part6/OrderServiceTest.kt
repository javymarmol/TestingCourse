package com.plcoding.testingcourse.part6

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrderServiceTest {
    private lateinit var orderService: OrderService
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var emailClient: EmailClient

    @BeforeEach
    fun setUp() {
        emailClient = mockk(relaxed = true)
        val firebaseUser = mockk<FirebaseUser>(relaxed = true) {
            every { isAnonymous } returns false
        }
        firebaseAuth = mockk(relaxed = true) {
            every { currentUser } returns firebaseUser
        }
        orderService = OrderService(
            auth = firebaseAuth,
            emailClient = emailClient
        )
    }

    @Test
    fun `is not anonymous user place order, sent email`() {

        val customerEmail = "test@test.com"
        val productName = "product name"

        orderService.placeOrder(
            customerEmail = customerEmail,
            productName = productName
        )

        verify {
            emailClient.send(Email(
                subject = "Order Confirmation",
                content ="Thank you for your order of $productName.",
                recipient = customerEmail
            ))
        }
    }
}