package pe.edu.idat.appgynrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.databinding.FragmentInfosaltoTBinding

class InfosaltoTFragment : Fragment() {


    private lateinit var binding: FragmentInfosaltoTBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using Data Binding
        binding = FragmentInfosaltoTBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar OnClickListener para el botón SIGUIENTE
        binding.btnSiguiente.setOnClickListener {
            // Navegar hacia el fragmento InfoFlexDFragment
            findNavController().navigate(R.id.action_infosaltoTFragment_to_infoFlexDFragment)
        }
    }
}