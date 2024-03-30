package pe.edu.idat.appgynrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.databinding.FragmentInfoFlexDBinding


class InfoFlexDFragment : Fragment() {
    private lateinit var binding: FragmentInfoFlexDBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using Data Binding
        binding = FragmentInfoFlexDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar OnClickListener para el botón SIGUIENTE
        binding.btnSiguiente.setOnClickListener {
            // Navegar hacia el fragmento
            findNavController().navigate(R.id.action_infoFlexDFragment_to_InfoFlexIncFragment)
        }

        binding.btncerrar.setOnClickListener {
            // Navegar hacia el fragmento RutinapornivelFragment
            findNavController().navigate(R.id.to_rutinapornivelFragment)
        }
    }
}