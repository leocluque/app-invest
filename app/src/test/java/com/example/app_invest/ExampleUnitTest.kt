//package com.example.app_invest
//
///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * See [testing documentation](http://d.android.com/tools/testing).
// */
//import android.os.Bundle
//import androidx.navigation.NavController
//import com.example.app_invest.ui.home.HostActivity
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.verify
//import org.mockito.MockitoAnnotations
//import org.robolectric.RobolectricTestRunner
//
//@RunWith(RobolectricTestRunner::class)
//class HostActivityTest {
//
//    private lateinit var hostActivity: HostActivity
//
//    @Mock
//    private lateinit var navController: NavController
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        hostActivity = HostActivity()
//        hostActivity.binding = mock()
//        navController = mock()
//    }
//
//
//    @Test
//    fun `test navigation when clicking on navigation menu item`() {
//        // Inicializar o hostActivity
//        hostActivity = HostActivity()
//
//        // Criar e definir o mock do NavController
////        val navController: NavController = mock()
////        hostActivity.setNavController(navController)
//
//        // Criar e definir o mock do BottomNavigationView
//        val bottomNavigationView: BottomNavigationView = mock()
//        hostActivity.setBottomNavigation(bottomNavigationView)
//
//        // Simular o clique em um item do menu de navegação
//        hostActivity.onNavigationItemSelected(bottomNavigationView.menu.getItem(0))
//
//        // Verificar se a navegação ocorreu corretamente
//        verify(navController).navigate(R.id.stockAlertFragment)
//    }
//
//
//    @Test
//    fun `test onBackPressed`() {
//        // Verifica se onBackPressed não faz nada
//    }
//
//    // Adicione mais testes conforme necessário para cobrir outras funcionalidades da classe
//}
