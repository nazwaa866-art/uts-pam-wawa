package lat.pam.uts_wawa

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import lat.pam.uts_wawa.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        updateHeader()

        val currentName = UserSessionManager.getUserName(this)
        binding.etProfileName.setText(currentName)

        binding.btnSaveProfile.setOnClickListener {
            val newName = binding.etProfileName.text.toString().trim()
            if (newName.isNotEmpty()) {
                UserSessionManager.saveUserName(this, newName)
                updateHeader()
                Toast.makeText(this, "Profile berhasil disimpan!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        setupBottomNav()
    }

    private fun updateHeader() {
        val currentName = UserSessionManager.getUserName(this)
        binding.tvHaloNama.text = getString(R.string.halo_nama, currentName)
    }

    private fun setupBottomNav() {
        val navBinding = binding.layoutBottomNav

        navBinding.tvNavHome.background = null
        navBinding.tvNavHome.setTextColor(ContextCompat.getColor(this, R.color.text_dark))

        navBinding.tvNavOrder.background = null
        navBinding.tvNavOrder.setTextColor(ContextCompat.getColor(this, R.color.text_dark))

        // Active tab: Profile
        navBinding.tvNavProfile.setBackgroundResource(R.drawable.bg_nav_active)
        navBinding.tvNavProfile.setTextColor(ContextCompat.getColor(this, R.color.white))

        navBinding.navHomeContainer.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        navBinding.navOrderContainer.setOnClickListener {
            startActivity(Intent(this, OrderAddressActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        updateHeader()
        setupBottomNav()
    }
}