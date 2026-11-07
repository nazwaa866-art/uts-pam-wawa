package lat.pam.uts_wawa

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import lat.pam.uts_wawa.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        updateHeader()

        val foodItems = listOf(
            FoodItem(
                id = 1,
                name = "Kopi Susu Gula Aren",
                description = "Espresso, susu, gula aren",
                price = 22000,
                formattedPrice = "Rp 22.000",
                iconResId = R.drawable.ic_food_kopi,
            ),
            FoodItem(
                id = 2,
                name = "Donat Cokelat",
                description = "Donat empuk, glaze cokelat",
                price = 12000,
                formattedPrice = "Rp 12.000",
                iconResId = R.drawable.ic_food_donut,
            ),
            FoodItem(
                id = 3,
                name = "Sourdough Loaf",
                description = "Roti fermentasi alami",
                price = 35000,
                formattedPrice = "Rp 35.000",
                iconResId = R.drawable.ic_food_bread,
            ),
            FoodItem(
                id = 4,
                name = "Cappuccino",
                description = "Espresso, foam susu lembut",
                price = 25000,
                formattedPrice = "Rp 25.000",
                iconResId = R.drawable.ic_food_cappuccino,
            ),
            FoodItem(
                id = 5,
                name = "Matcha Latte",
                description = "Matcha jepang, susu segar",
                price = 27000,
                formattedPrice = "Rp 27.000",
                iconResId = R.drawable.ic_food_matcha,
            ),
            FoodItem(
                id = 6,
                name = "Butter Croissant",
                description = "Pastry renyah & wangi butter",
                price = 18000,
                formattedPrice = "Rp 18.000",
                iconResId = R.drawable.ic_food_croissant,
            ),
        )

        binding.rvFoodList.layoutManager = LinearLayoutManager(this)
        binding.rvFoodList.adapter = FoodAdapter(foodItems) { item ->
            UserSessionManager.setSelectedFood(this, "${item.name} (${item.formattedPrice})")
            val intent = Intent(this, OrderAddressActivity::class.java)
            startActivity(intent)
        }

        setupBottomNav()
    }

    private fun updateHeader() {
        val userName = UserSessionManager.getUserName(this)
        binding.tvHaloNama.text = getString(R.string.halo_nama, userName)
    }

    private fun setupBottomNav() {
        val navBinding = binding.layoutBottomNav

        // Active tab: Home
        navBinding.tvNavHome.setBackgroundResource(R.drawable.bg_nav_active)
        navBinding.tvNavHome.setTextColor(ContextCompat.getColor(this, R.color.white))

        navBinding.tvNavOrder.background = null
        navBinding.tvNavOrder.setTextColor(ContextCompat.getColor(this, R.color.text_dark))

        navBinding.tvNavProfile.background = null
        navBinding.tvNavProfile.setTextColor(ContextCompat.getColor(this, R.color.text_dark))

        navBinding.navOrderContainer.setOnClickListener {
            startActivity(Intent(this, OrderAddressActivity::class.java))
        }

        navBinding.navProfileContainer.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        updateHeader()
        setupBottomNav()
    }
}