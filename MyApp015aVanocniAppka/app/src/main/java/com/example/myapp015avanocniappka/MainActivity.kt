package com.example.myapp015avanocniappka
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp015avanocniappka.databinding.ActivityMainBinding
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var countdownTimer: CountDownTimer? = null  // Flag pro běžící odpočítávání

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MainActivity", "Aplikace byla spuštěna")

        val calendar = Calendar.getInstance()

        // Listener pro Vánoce
        binding.btnChristmas.setOnClickListener {
            Log.d("MainActivity", "Kliknuto na Vánoce")
            try {
                val christmas = Calendar.getInstance()
                christmas.set(Calendar.MONTH, Calendar.DECEMBER)
                christmas.set(Calendar.DAY_OF_MONTH, 24)
                christmas.set(Calendar.HOUR_OF_DAY, 0)
                christmas.set(Calendar.MINUTE, 0)
                christmas.set(Calendar.SECOND, 0)

                val christmasTimeInMillis = christmas.timeInMillis

                // Pokud běží odpočítávání, zastavíme ho
                countdownTimer?.cancel()

                // Nastavení odpočítávání
                startCountdown(christmasTimeInMillis)
            } catch (e: Exception) {
                Log.e("MainActivity", "Chyba při výpočtu odpočtu na Vánoce: ${e.message}")
            }
        }

        // Listener pro Silvestr
        binding.btnNewYear.setOnClickListener {
            Log.d("MainActivity", "Kliknuto na Silvestr")
            try {
                val newYear = Calendar.getInstance()
                newYear.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1)
                newYear.set(Calendar.MONTH, Calendar.JANUARY)
                newYear.set(Calendar.DAY_OF_MONTH, 1)
                newYear.set(Calendar.HOUR_OF_DAY, 0)
                newYear.set(Calendar.MINUTE, 0)
                newYear.set(Calendar.SECOND, 0)

                val newYearTimeInMillis = newYear.timeInMillis

                // Pokud běží odpočítávání, zastavíme ho
                countdownTimer?.cancel()

                // Nastavení odpočítávání
                startCountdown(newYearTimeInMillis)
            } catch (e: Exception) {
                Log.e("MainActivity", "Chyba při výpočtu odpočtu na Silvestr: ${e.message}")
            }
        }
    }

    // Funkce pro spuštění odpočítávání
    private fun startCountdown(targetTimeInMillis: Long) {
        val currentTimeInMillis = System.currentTimeMillis()

        // Pokud je cílový čas v minulosti, odpočítávání nebude fungovat
        if (targetTimeInMillis < currentTimeInMillis) {
            binding.tvCountdown.text = "Událost již nastala!"
            return
        }

        // Spuštění nového odpočítávání
        countdownTimer = object : CountDownTimer(targetTimeInMillis - currentTimeInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                // Výpočet dní, hodin, minut a sekund
                val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60

                // Nastavení textu pro zobrazení odpočtu
                binding.tvCountdown.text = String.format(
                    "Zbývá: %d dní %02d:%02d:%02d",
                    days, hours, minutes, seconds
                )
            }

            override fun onFinish() {
                binding.tvCountdown.text = "Štědrý den je tady!" // Nebo "Šťastný nový rok!"
            }
        }

        countdownTimer?.start()
    }
}
