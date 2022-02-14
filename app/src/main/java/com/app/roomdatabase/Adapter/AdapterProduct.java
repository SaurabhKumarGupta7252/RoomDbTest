package com.app.roomdatabase.Adapter;

import static java.lang.Integer.parseInt;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.roomdatabase.Model.Product;
import com.app.roomdatabase.RoomDatabase.AppDatabase;
import com.app.roomdatabase.RoomDatabase.ProductDao;
import com.app.roomdatabase.databinding.HolderProductBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    private HolderProductBinding binding;

    private Activity activity;

    private ProductDao dao;

    public AdapterProduct(Activity activity, List<Product> list) {

        this.activity = activity;

        this.list = list;

        dao = AppDatabase.getRoomDatabase().getDao();
    }

    private List<Product> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = HolderProductBinding.inflate(activity.getLayoutInflater(), parent, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product model = list.get(position);

        ((ViewHolder) holder).bind(model, position);

    }

    @Override
    public int getItemCount() {

        try {
            return list.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private HolderProductBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(AdapterProduct.this.binding.getRoot());

            binding = AdapterProduct.this.binding;
        }

        public void bind(Product model, int position) {

            binding.tvName.setText(model.getTitle());

            Picasso.get().load(model.getImage()).into(binding.image);

            if (dao.isExist(model.getId())) {

                binding.etCount.setText(dao.count(model.getId()) + "");
            }

            binding.btnAdd.setOnClickListener(v -> {

                if (!dao.isExist(model.getId())) {

                    model.setQty(parseInt(binding.etCount.getText().toString()));

                    dao.insert(model);

                    Toast.makeText(activity, "Data Saved", Toast.LENGTH_SHORT).show();

                } else {

                    dao.update(parseInt(binding.etCount.getText().toString()), model.getId());

                    Toast.makeText(activity, "Product Quantity Updated", Toast.LENGTH_SHORT).show();
                }
            });

            binding.ivDelete.setOnClickListener(v -> {
                if (dao.isExist(model.getId())) {
                    dao.delete(model.getId());
                    Toast.makeText(activity, "Product Delete", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
