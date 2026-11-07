package lat.pam.uts_wawa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lat.pam.uts_wawa.databinding.ItemFoodBinding

class FoodAdapter(
    private val itemList: List<FoodItem>,
    private val onItemClick: (FoodItem) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = itemList[position]
        with(holder.binding) {
            tvFoodName.text = item.name
            tvFoodDescription.text = "• ${item.description}"
            tvFoodPrice.text = "• ${item.formattedPrice}"
            ivFoodIcon.setImageResource(item.iconResId)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}
