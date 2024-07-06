package com.plcoding.testingcourse.shopping.core.domain

import com.plcoding.testingcourse.core.domain.Product
import com.plcoding.testingcourse.core.domain.ProductRepository

class ProductRepositoryFake: ProductRepository {

    private val purchaseProducts = mutableListOf<Product>()
    var shouldReturnError = false

    override suspend fun purchaseProducts(products: List<Product>): Result<Unit> {
        products.forEach {
            purchaseProducts.add(it)
        }
        return if (shouldReturnError){
            Result.failure(Exception())
        } else Result.success(Unit)
    }

    override suspend fun cancelPurchase(purchaseId: Int): Result<Unit> {
        purchaseProducts.removeIf { it.id == purchaseId }
        return if (shouldReturnError){
            Result.failure(Exception())
        } else Result.success(Unit)
    }
}