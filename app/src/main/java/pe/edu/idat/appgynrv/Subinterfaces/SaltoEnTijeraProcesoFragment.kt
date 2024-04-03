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
import pe.edu.idat.appgynrv.R


class SaltoEnTijeraProcesoFragment : Fragment() {
    private lateinit var btnStartPause3: Button
    private lateinit var txtTimeElapsed3: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var timer: CountDownTimer

    private var isTimerRunning = false
    private var timeElapsedInMillis: Long = 0L
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_salto_en_tijera_proceso, container, false)
        val view = inflater.inflate(R.layout.fragment_salto_en_tijera_proceso,container,false)
        btnStartPause3 = view.findViewById(R.id.btnStartPause3)
        txtTimeElapsed3 = view.findViewById(R.id.txtTimeElapsed3)
        progressBar = view.findViewById(R.id.progressBar)

        btnStartPause3.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }
        // Obtener los argumentos pasados desde RutinapornivelFragment
        val nombreEjercicio3 = arguments?.getString("nombreEjercicio3") ?: ""
        val numRepeticiones3 = arguments?.getInt("numRepeticiones3") ?: 0

        // Mostrar los valores en las cajas de texto correspondientes
        val tvNombreEjercicio3 = view.findViewById<TextView>(R.id.tvnombrejer3)
        val tvNumRepeticiones3 = view.findViewById<TextView>(R.id.tvnumrepeticiones3)
        tvNombreEjercicio3.text = nombreEjercicio3
        tvNumRepeticiones3.text = numRepeticiones3.toString()
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
                findNavController().navigate(R.id.preFelicidadesEjer3Fragment)
            }
        }.start()

        isTimerRunning = true
        btnStartPause3.text = getString(R.string.pause)
    }

    private fun pauseTimer() {
        timer.cancel()
        isTimerRunning = false
        btnStartPause3.text = getString(R.string.start)
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

        txtTimeElapsed3.text = timeString

        val progress = ((MAX_TIME - timeElapsedInMillis) * 100 / MAX_TIME).toInt()
        progressBar.progress = progress
    }
    companion object {
        private const val MAX_TIME = 20000L
        private const val INTERVAL = 1000L // 1 second
    }

}