package pe.edu.idat.appgynrv

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment

class EjercicioProcesoFragment : Fragment() {

    private lateinit var btnStartPause: Button
    private lateinit var txtTimeElapsed: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var timer: CountDownTimer

    private var isTimerRunning = false
    private var timeElapsedInMillis: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ejercicio_proceso, container, false)

        btnStartPause = view.findViewById(R.id.btnStartPause)
        txtTimeElapsed = view.findViewById(R.id.txtTimeElapsed)
        progressBar = view.findViewById(R.id.progressBar)

        btnStartPause.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        return view
    }

    private fun startTimer() {
        timer = object : CountDownTimer(MAX_TIME, INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                timeElapsedInMillis = MAX_TIME - millisUntilFinished
                updateTimerUI()
            }

            override fun onFinish() {
                timeElapsedInMillis = MAX_TIME
                updateTimerUI()
            }
        }.start()

        isTimerRunning = true
        btnStartPause.text = getString(R.string.pause)
    }

    private fun pauseTimer() {
        timer.cancel()
        isTimerRunning = false
        btnStartPause.text = getString(R.string.start)
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

        txtTimeElapsed.text = timeString

        val progress = ((MAX_TIME - timeElapsedInMillis) * 100 / MAX_TIME).toInt()
        progressBar.progress = progress
    }

    companion object {
        private const val MAX_TIME = 600000L // 10 minutes
        private const val INTERVAL = 1000L // 1 second
    }
}