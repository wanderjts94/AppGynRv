package pe.edu.idat.appgynrv
import android.content.Context
import android.os.Bundle
import android.util.Log
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
        val applicationContext = requireActivity().applicationContext
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val data = sharedPreferences.getString("guardardatos", "")

        val dataArray = data?.split(",") ?: emptyList()

        if (dataArray.size >= 4) {

            val resourceId = applicationContext.resources.getIdentifier(
                dataArray[0],
                "drawable",
                applicationContext.packageName
            )
            if(resourceId != 0){
                binding.ivimagenproduct.setImageResource(resourceId)
            } else {
                binding.ivimagenproduct.setImageResource(R.drawable.carga)
            }

            binding.etnombreproducto.text = dataArray[1]
            binding.etnunprecio.text = dataArray[2]
            binding.etinfodescript.text = dataArray[3]
            Log.d("DetalleCompraFragment", "Datos del producto: $dataArray")
        }

        binding.btnAgregarC.setOnClickListener {
            val nombreProducto = binding.etnombreproducto.text.toString()
            val precio = binding.etnunprecio.text.toString()
            val cantidad = binding.etnumcantidad.text.toString()
            val resourceId = applicationContext.resources.getIdentifier(
                dataArray[0],
                "drawable",
                applicationContext.packageName
            )
            val img = resourceId

            val editor = sharedPreferences.edit()

            // Obtener los datos existentes y agregar el nuevo producto
            val datosCarritoActual = sharedPreferences.getString("datosparacarrito", "")
            val nuevoProducto = "$img,$nombreProducto,$precio,$cantidad"

            // Concatenar el nuevo producto al final de los datos existentes
            val nuevosDatos = if (datosCarritoActual.isNullOrEmpty()) {
                nuevoProducto
            } else {
                "$datosCarritoActual;$nuevoProducto"
            }

            editor.putString("datosparacarrito", nuevosDatos)
            editor.apply()

            Log.d("DetalleCompraFragment", "Datos guardados en SharedPreferences (datosparacarrito): $nuevosDatos")

            view.findNavController().navigate(R.id.carritoCompraFragment)
        }

        binding.btncarrito.setOnClickListener {
            findNavController().navigate(R.id.carritoCompraFragment)
        }
    }
}