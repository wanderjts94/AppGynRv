package pe.edu.idat.appgynrv.Subinterfaces

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import pe.edu.idat.appgynrv.EjercicioProcesoFragment
import pe.edu.idat.appgynrv.R


class FlexionesEstiramientoProcesoFragment : Fragment() {
    private lateinit var btnStartPause2: Button
    private lateinit var txtTimeElapsed2: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var timer: CountDownTimer

    private var isTimerRunning = false
    private var timeElapsedInMillis: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       //return inflater.inflate(R.layout.fragment_flexiones_estiramiento_proceso, container, false)
        val view= inflater.inflate(R.layout.fragment_flexiones_estiramiento_proceso,container,false)
        btnStartPause2 = view.findViewById(R.id.btnStartPause2)
        txtTimeElapsed2 = view.findViewById(R.id.txtTimeElapsed2)
        progressBar = view.findViewById(R.id.progressBar)

        btnStartPause2.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }
        // Obtener los argumentos pasados desde RutinapornivelFragment
        val nombreEjercicio2 = arguments?.getString("nombreEjercicio2") ?: ""
        val numRepeticiones2 = arguments?.getInt("numRepeticiones2") ?: 0

        // Mostrar los valores en las cajas de texto correspondientes
        val tvNombreEjercicio2 = view.findViewById<TextView>(R.id.tvnombrejer2)
        val tvNumRepeticiones2 = view.findViewById<TextView>(R.id.tvnumrepeticiones2)
        tvNombreEjercicio2.text = nombreEjercicio2
        tvNumRepeticiones2.text = numRepeticiones2.toString()
        return view
    }
    private fun startTimer() {
        timer = object : CountDownTimer(
            MAX_TIME,
            INTERVAL
        ) {
            override fun onTick(millisUntilFinished: Long) {
                timeElapsedInMillis = MAX_TIME - millisUntilFinished
                updateTimerUI()
            }

            override fun onFinish() {
               findNavController().navigate(R.id.preFelicidadesEjer2Fragment)
            }
        }.start()

        isTimerRunning = true
        btnStartPause2.text = getString(R.string.pause)
    }
    private fun pauseTimer() {
        timer.cancel()
        isTimerRunning = false
        btnStartPause2.text = getString(R.string.start)
    }
    private fun updateTimerUI() {
        val seconds = (timeElapsedInMillis / 1000).toInt()
        val minutes = seconds / 60
        val hours = minutes / 60

        val timeString = String.format(
            "%02d:%02d:%02d",
            hours,
            minutes % 60,
            seconds % 60
        )

        txtTimeElapsed2.text = timeString

        val progress = ((MAX_TIME - timeElapsedInMillis) * 100 / MAX_TIME).toInt()
        progressBar.progress = progress
    }
    companion object {
        private const val MAX_TIME = 20000L
        private const val INTERVAL = 1000L // 1 second
    }

}