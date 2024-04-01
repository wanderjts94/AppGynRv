package pe.edu.idat.appgynrv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.databinding.FragmentHaztePremiumBinding


class HaztePremiumFragment : Fragment() {
    private var _binding:FragmentHaztePremiumBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_hazte_premium, container, false)
        _binding= FragmentHaztePremiumBinding.inflate(inflater,container,false)
        binding.btnpagomensual.setOnClickListener{
            findNavController().navigate(R.id.realizarPagoFragment)
        }
        return binding.root
    }

}