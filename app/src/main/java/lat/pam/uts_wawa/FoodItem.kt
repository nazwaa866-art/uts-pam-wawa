package lat.pam.uts_wawa

data class FoodItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val formattedPrice: String,
    val iconResId: Int
)