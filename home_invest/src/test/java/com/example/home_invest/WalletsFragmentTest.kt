import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.home_invest.R
import com.example.home_invest.ui.components.ProgressItem
import com.example.home_invest.ui.home.wallets.WalletsFragment
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WalletsFragmentTest {

    private lateinit var scenario: FragmentScenario<WalletsFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_AppInvest)
    }

    @Test
    fun testFragmentInitialization() {
        scenario.onFragment { fragment ->
            // Verificar se a visualização não é nula após a criação do fragmento
            val expectedVisibility = View.VISIBLE // ou View.GONE ou View.INVISIBLE, dependendo do XML
            assertEquals(expectedVisibility, fragment.binding?.circularProgressBar?.visibility)
        }
    }

    @Test
    fun testCircularProgressBarConfiguration() {
        val testData = "Abril 2024"
        val totalInvested = "Total Investido"
        val balance = "R$ 160.000,00"
        val progressItems = listOf(
            ProgressItem("1", 20f, "#f28500"),
            ProgressItem("2", 20f, "#7A124E"),
            ProgressItem("3", 20f, "#5786B9"),
            ProgressItem("4", 20f, "#FFBB86FC"),
            ProgressItem("5", 20f, "#FF6200EE")
        )

        scenario.onFragment { fragment ->

            // Verificar se os textos são configurados corretamente
            assertEquals(testData, fragment.binding?.circularProgressBar?.getTitle())
            assertEquals(totalInvested, fragment.binding?.circularProgressBar?.getSubtitle())
            assertEquals(balance, fragment.binding?.circularProgressBar?.getBalance())

            // Verificar se os itens de progresso são configurados corretamente
            assertEquals(progressItems.size, fragment.binding?.circularProgressBar?.getItems()?.size)
            progressItems.forEachIndexed { index, progressItem ->
                val item = fragment.binding?.circularProgressBar?.getItems()?.get(index)
                assertEquals(progressItem.productName, item?.productName)
                assertEquals(progressItem.percentage, item?.percentage ?: 0f, 0.01f)
                assertEquals(progressItem.color, item?.color)
            }
        }
    }
}
