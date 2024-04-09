package pe.edu.idat.appgynrv.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import pe.edu.idat.appgynrv.R
import pe.edu.idat.appgynrv.databinding.FragmentRealizarPagoBinding

class RealizarPagoFragment : Fragment() {
    private var _binding:FragmentRealizarPagoBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_realizar_pago, container, false)
        _binding=FragmentRealizarPagoBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val opcionesPago= resources.getStringArray(R.array.opciones_pago)

        val adapter= ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,opcionesPago)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spFormaPago.adapter=adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}