package pe.edu.idat.appgynrv

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.databinding.FragmentDetalleCompraBinding

class DetalleCompraFragment : Fragment() {
    private lateinit var binding: FragmentDetalleCompraBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleCompraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve data from SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val data = sharedPreferences.getString("guardardatos", "")

        // Split the data string
        val dataArray = data?.split(",") ?: emptyList()

        // Set data to views
        if (dataArray.size >= 4) {
            binding.ivimagenproduct.setImageResource(dataArray[0].toInt())
            binding.etnombreproducto.text = dataArray[1]
            binding.etnunprecio.text = dataArray[2]
            binding.etinfodescript.text = dataArray[3]
        }

        binding.btnAgregarC.setOnClickListener {
            // Save product data to SharedPreferences
            val nombreProducto = binding.etnombreproducto.text.toString()
            val precio = binding.etnunprecio.text.toString()
            val cantidad = binding.etnumcantidad.text.toString()
            val img = dataArray[0].toInt()

            val editor = sharedPreferences.edit()
            val dataToSave = "$img,$nombreProducto,$precio,$cantidad"
            editor.putString("datosparacarrito", dataToSave)
            editor.apply()

            // Navigate to CarritoCompraFragment
            view.findNavController().navigate(R.id.carritoCompraFragment)
        }

        binding.btncarrito.setOnClickListener {
            findNavController().navigate(R.id.carritoCompraFragment)
        }
    }
}