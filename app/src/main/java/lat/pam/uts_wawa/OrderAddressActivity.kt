package lat.pam.uts_wawa

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import lat.pam.uts_wawa.databinding.ActivityOrderAddressBinding

class OrderAddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOrderAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val selectedFood = UserSessionManager.getSelectedFood(this)
        binding.tvSelectedItem.text = selectedFood

        val currentFullName = UserSessionManager.getFullName(this)
        val currentAddress = UserSessionManager.getAddress(this)
        val currentPatokan = UserSessionManager.getPatokan(this)

        binding.etFullName.setText(currentFullName)
        binding.etAlamat.setText(currentAddress)
        binding.etPatokan.setText(currentPatokan)

        binding.btnOrderKirim.setOnClickListener {
            val name = binding.etFullName.text.toString().trim()
            val address = binding.etAlamat.text.toString().trim()
            val patokan = binding.etPatokan.text.toString().trim()

            if (name.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Nama Lengkap dan Alamat wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            UserSessionManager.saveAddress(this, name, address, patokan)

            val intent = Intent(this, OrderConfirmationActivity::class.java)
            startActivity(intent)
        }

        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navBinding = binding.layoutBottomNav

        navBinding.tvNavHome.background = null
        navBinding.tvNavHome.setTextColor(ContextCompat.getColor(this, R.color.text_dark))

        // Active tab: Order
        navBinding.tvNavOrder.setBackgroundResource(R.drawable.bg_nav_active)
        navBinding.tvNavOrder.setTextColor(ContextCompat.getColor(this, R.color.white))

        navBinding.tvNavProfile.background = null
        navBinding.tvNavProfile.setTextColor(ContextCompat.getColor(this, R.color.text_dark))

        navBinding.navHomeContainer.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        navBinding.navProfileContainer.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        setupBottomNav()
    }
}