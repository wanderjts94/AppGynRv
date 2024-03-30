package pe.edu.idat.appgynrv

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import pe.edu.idat.appgynrv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.ejercicioFragment, R.id.informeFragment, R.id.tiendaFragment,
                R.id.perfilFragment,R.id.infosaltoTFragment, R.id.rutinapornivelFragment,
                R.id.infosaltoTFragment,R.id.infoFlexDFragment, R.id.infoFlexIncFragment,
                R.id.infoEstiramientoFragment,R.id.infoEstiraPechoFragment,R.id.carritoCompraFragment,
                R.id.detalleCompraFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}