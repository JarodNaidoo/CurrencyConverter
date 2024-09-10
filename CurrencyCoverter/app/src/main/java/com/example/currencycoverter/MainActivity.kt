import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var fromCurrencySpinner: Spinner
    private lateinit var toCurrencySpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountEditText = findViewById(R.id.amountEditText)
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner)
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner)
        convertButton = findViewById(R.id.convertButton)
        resultTextView = findViewById(R.id.resultTextView)

        // Populate the spinners with currency options
        val currencies = arrayOf("USD", "EUR", "GBP")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        fromCurrencySpinner.adapter = adapter
        toCurrencySpinner.adapter = adapter

        convertButton.setOnClickListener {
            val amountString = amountEditText.text.toString()
            if (amountString.isEmpty()) {
                resultTextView.text = "Please enter an amount"
                return@setOnClickListener
            }

            val amount = try {
                amountString.toDouble()
            } catch (e: NumberFormatException) {
                resultTextView.text = "Invalid amount"
                return@setOnClickListener
            }

            val fromCurrency = fromCurrencySpinner.selectedItem.toString()
            val toCurrency = toCurrencySpinner.selectedItem.toString()

            convertCurrency(amount, fromCurrency, toCurrency)
        }
    }

    private fun convertCurrency(amount: Double, from: String, to: String) {
        // Use hardcoded conversion rates for simplicity
        val conversionRate = when (from to to) {
            "USD" to "EUR" -> 0.85
            "USD" to "GBP" -> 0.75
            "EUR" to "USD" -> 1.18
            "EUR" to "GBP" -> 0.88
            "GBP" to "USD" -> 1.33
            "GBP" to "EUR" -> 1.14
            else -> 1.0 // Same currency
        }

        val result = amount * conversionRate
        resultTextView.text = "$amount $from = $result $to"
    }
}
