package com.example.home_invest.builder

import com.example.network.NetworkConstants
import com.example.network.data.create_service.ServiceGenerator
import com.example.network.data.remote.repository.balance.BalanceRepository
import com.example.network.data.remote.repository.balance.BalanceRepositoryImp
import com.example.network.data.remote.repository.extract.ExtractRepository
import com.example.network.data.remote.repository.extract.ExtractRepositoryImp
import com.example.network.data.remote.repository.investments.InvestmentsRepository
import com.example.network.data.remote.repository.investments.InvestmentsRepositoryImp
import com.example.network.data.remote.service.BalanceService
import com.example.network.data.remote.service.ExtractService
import com.example.network.data.remote.service.InvestmentsService

object HomeBuilder {

    private var balanceRepository: BalanceRepository? = null
    private var investmentsRepository: InvestmentsRepository? = null
    private var extractRepository: ExtractRepository? = null

    fun executeDepInject() {
        provideBalanceService()
        provideInvestmentsService()
        provideExtractService()
    }


    internal fun provideBalanceService(): BalanceRepository? {
        val retrofit = ServiceGenerator.createService(
            BalanceService::class.java,
            url = NetworkConstants.BASE_URL
        )
        balanceRepository = BalanceRepositoryImp(retrofit)
        return balanceRepository
    }

    internal fun provideInvestmentsService(): InvestmentsRepository? {
        val retrofit = ServiceGenerator.createService(
            InvestmentsService::class.java,
            url = NetworkConstants.BASE_URL
        )
        investmentsRepository = InvestmentsRepositoryImp(retrofit)
        return investmentsRepository
    }

    internal fun provideExtractService(): ExtractRepository? {
        val retrofit = ServiceGenerator.createService(
            ExtractService::class.java,
            url = NetworkConstants.BASE_URL
        )
        extractRepository = ExtractRepositoryImp(retrofit)
        return extractRepository
    }

    fun getBalanceRepository() = balanceRepository
    fun getInvestmentsRepository() = investmentsRepository
    fun getExtractRepository() = extractRepository
}