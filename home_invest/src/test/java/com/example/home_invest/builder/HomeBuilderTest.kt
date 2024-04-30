package com.example.home_invest.builder

import com.example.network.data.remote.service.BalanceService
import com.example.network.data.remote.service.ExtractService
import com.example.network.data.remote.service.InvestmentsService
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class HomeBuilderTest {

    private lateinit var balanceService: BalanceService
    private lateinit var investmentsService: InvestmentsService
    private lateinit var extractService: ExtractService

    @Before
    fun setUp() {
        // Mock dos serviços
        balanceService = mock(BalanceService::class.java)
        investmentsService = mock(InvestmentsService::class.java)
        extractService = mock(ExtractService::class.java)
    }

    @Test
    fun `test executeDepInject`() {
        // Executa a injeção de dependência
        HomeBuilder.executeDepInject()

        // Verifica se os repositórios foram criados corretamente
        assertNotNull(HomeBuilder.getBalanceRepository())
        assertNotNull(HomeBuilder.getInvestmentsRepository())
        assertNotNull(HomeBuilder.getExtractRepository())
    }

    @Test
    fun `test provideBalanceService`() {
        // Cria o repositório de saldo
        val balanceRepository = HomeBuilder.provideBalanceService()

        // Verifica se o repositório não é nulo
        assertNotNull(balanceRepository)
    }

    @Test
    fun `test provideInvestmentsService`() {
        // Cria o repositório de investimentos
        val investmentsRepository = HomeBuilder.provideInvestmentsService()

        // Verifica se o repositório não é nulo
        assertNotNull(investmentsRepository)
    }

    @Test
    fun `test provideExtractService`() {
        // Cria o repositório de extrato
        val extractRepository = HomeBuilder.provideExtractService()

        // Verifica se o repositório não é nulo
        assertNotNull(extractRepository)
    }
}
