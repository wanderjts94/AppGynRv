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



class EstiramientoCobraProcesoFragment : Fragment() {
    private lateinit var btnStartPause4: Button
    private lateinit var txtTimeElapsed4: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var timer: CountDownTimer

    private var isTimerRunning = false
    private var timeElapsedInMillis: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_estiramiento_cobra_proceso, container, false)
        val view = inflater.inflate(R.layout.fragment_estiramiento_cobra_proceso,container,false)
        btnStartPause4 = view.findViewById(R.id.btnStartPause4)
        txtTimeElapsed4 = view.findViewById(R.id.txtTimeElapsed4)
        progressBar = view.findViewById(R.id.progressBar)

        btnStartPause4.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }
        // Obtener los argumentos pasados desde RutinapornivelFragment
        val nombreEjercicio4 = arguments?.getString("nombreEjercicio4") ?: ""
        val numRepeticiones4 = arguments?.getInt("numRepeticiones4") ?: 0

        // Mostrar los valores en las cajas de texto correspondientes
        val tvNombreEjercicio4 = view.findViewById<TextView>(R.id.tvnombrejer4)
        val tvNumRepeticiones4 = view.findViewById<TextView>(R.id.tvnumrepeticiones4)
        tvNombreEjercicio4.text = nombreEjercicio4
        tvNumRepeticiones4.text = numRepeticiones4.toString()
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
                findNavController().navigate(R.id.preFelicidadesEjer4Fragment)
            }
        }.start()

        isTimerRunning = true
        btnStartPause4.text = getString(R.string.pause)
    }

    private fun pauseTimer() {
        timer.cancel()
        isTimerRunning = false
        btnStartPause4.text = getString(R.string.start)
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

        txtTimeElapsed4.text = timeString

        val progress = ((MAX_TIME - timeElapsedInMillis) * 100 / MAX_TIME).toInt()
        progressBar.progress = progress
    }
    companion object {
        private const val MAX_TIME = 20000L
        private const val INTERVAL = 1000L // 1 second
    }

}