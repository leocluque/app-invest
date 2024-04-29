package com.example.stock_alert.builder

import com.example.network.NetworkConstants
import com.example.network.data.create_service.ServiceGenerator
import com.example.network.data.remote.repository.stock.StockAlertRepository
import com.example.network.data.remote.repository.stock.StockAlertRepositoryImp
import com.example.network.data.remote.service.StockAlertService

object StockAlertBuilder {

    private var stockAlertRepository: StockAlertRepository? = null

    fun executeDepInject() {
        provideStockAlertService()
    }


    private fun provideStockAlertService(): StockAlertRepository? {
        val retrofit = ServiceGenerator.createService(
            StockAlertService::class.java,
            url = NetworkConstants.BASE_URL
        )
        stockAlertRepository = StockAlertRepositoryImp(retrofit)
        return stockAlertRepository
    }

    fun getStockAlertRepository() = stockAlertRepository
}