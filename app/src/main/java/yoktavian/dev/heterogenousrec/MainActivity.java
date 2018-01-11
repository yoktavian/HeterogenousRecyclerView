package yoktavian.dev.heterogenousrec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import yoktavian.dev.heterogenousrec.models.BarangModel;
import yoktavian.dev.heterogenousrec.models.BarangPopulerModel;
import yoktavian.dev.heterogenousrec.models.KategoriModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_vertical_kategori)
    RecyclerView mListKategori;

    List<KategoriModel> listKategori;
    List<BarangModel> listBarang;
    List<BarangPopulerModel> listBarangPop;
    private AdapterKategori adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setList();

        setRecyclerView();

        setData();

    }

    private void setList() {
        listKategori = new ArrayList<>();
        listBarang = new ArrayList<>();
        listBarangPop = new ArrayList<>();
    }

    private void setRecyclerView() {
        adapter = new AdapterKategori(listKategori);
        mListKategori.setHasFixedSize(true);
        mListKategori.setAdapter(adapter);
    }

    private void setData() {

        // List Kategori
        listKategori.add(new KategoriModel().setKategori("All"));
        listKategori.add(new KategoriModel().setKategori("Populer"));
        listKategori.add(new KategoriModel().setKategori("Editor Picks"));
        listKategori.add(new KategoriModel().setKategori("Editor 1"));
        listKategori.add(new KategoriModel().setKategori("Editor 2"));
        listKategori.add(new KategoriModel().setKategori("Editor 3"));
        listKategori.add(new KategoriModel().setKategori("Editor 4"));

        // List Barang Populer
        listBarangPop.add(new BarangPopulerModel().set_id("1")
                .setName("Nike Nih")
                .setDesc("Masih mantab"));
        listBarangPop.add(new BarangPopulerModel().set_id("2")
                .setName("Tas Gunung")
                .setDesc("80% masih oke, cacat pemakaian tok"));

        // List All
        listBarang.add(new BarangModel().set_id("1")
                .setName("coy").setDesc("check"));
        listBarang.add(new BarangModel().set_id("2")
                .setName("bray").setDesc("check123"));
        listBarang.add(new BarangModel().set_id("3")
                .setName("sepatu").setDesc("asli gan"));
        listBarang.add(new BarangModel().set_id("4")
                .setName("tv").setDesc("alus pisan"));
        listBarang.add(new BarangModel().set_id("5")
                .setName("sendal").setDesc("baru beli banget"));
        listBarang.add(new BarangModel().set_id("6")
                .setName("sendal jepit").setDesc("100% mantap gan"));
        listBarang.add(new BarangModel().set_id("7")
                .setName("jam dinding").setDesc("90% asih alus"));
        listBarang.add(new BarangModel().set_id("8")
                .setName("laptop").setDesc("hadiah dari orang tua, masih baru"));

        adapter.notifyDataSetChanged();

    }

    // Vertical Adapter
    class AdapterKategori extends RecyclerView.Adapter<KategoriHolder> {

        List<KategoriModel> listKategori;

        /**
         *
         * @param listKategori
         */
        public AdapterKategori(List<KategoriModel> listKategori) {
            this.listKategori = listKategori;
        }

        @Override
        public int getItemCount() {
            return listKategori.size();
        }

        @Override
        public KategoriHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new KategoriHolder(parent);
        }

        @Override
        public void onBindViewHolder(final KategoriHolder holder, final int position) {
            if (listKategori.get(holder.getAdapterPosition()).getKategori().equals("Populer")) {
                holder.setKategori(listKategori.get(holder.getAdapterPosition())
                        .getKategori())
                        .setListBarangPop();
            } else {
                holder.setKategori(listKategori.get(holder.getAdapterPosition())
                        .getKategori())
                        .setListBarang();
            }
        }
    }

    // Vertical Holder
    class KategoriHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.value_kategori)
        TextView mKategori;

        @BindView(R.id.list_horizontal_value)
        RecyclerView mListBarang;

        private AdapterHorizontal adapterHorizontal;

        public KategoriHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_horizontal_kategori, parent, false));
            ButterKnife.bind(this, itemView);
            mListBarang.setHasFixedSize(true);
        }

        public KategoriHolder setKategori(String kategori) {
            mKategori.setText(kategori);
            return this;
        }

        // Set Horizontal Recyclerview type 0
        public KategoriHolder setListBarang() {
            adapterHorizontal = new AdapterHorizontal(0);
            mListBarang.setAdapter(adapterHorizontal);
            return this;
        }

        // Set Horizontal Recyclerview type 1
        public KategoriHolder setListBarangPop() {
            adapterHorizontal = new AdapterHorizontal(1);
            mListBarang.setAdapter(adapterHorizontal);
            return this;
        }

    }

    enum Type {
        POPULAR, STANDARD
    }

    // Horizontal Holder
    class PopulerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id_item)
        TextView mId;

        @BindView(R.id.item_name)
        TextView mName;

        @BindView(R.id.item_desc)
        TextView mDesc;

        public PopulerHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_barang, parent, false));
            ButterKnife.bind(this, itemView);
        }

        public PopulerHolder setId(String id) {
            mId.setText(id);
            return this;
        }

        public PopulerHolder setName(String name) {
            mName.setText(name);
            return this;
        }

        public PopulerHolder setDesc(String desc) {
            mDesc.setText(desc);
            return this;
        }

    }

    // Horizontal Holder
    class BarangHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id_item)
        TextView mId;

        @BindView(R.id.item_name)
        TextView mName;

        @BindView(R.id.item_desc)
        TextView mDesc;

        public BarangHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_barang, parent, false));
            ButterKnife.bind(this, itemView);
        }

        public BarangHolder setId(String id) {
            mId.setText(id);
            return this;
        }

        public BarangHolder setName(String name) {
            mName.setText(name);
            return this;
        }

        public BarangHolder setDesc(String desc) {
            mDesc.setText(desc);
            return this;
        }
    }

    // Adapter Horizontal
    class AdapterHorizontal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private int type;

        /**
         *
         * @param type
         */
        public AdapterHorizontal(int type) {
            this.type = type;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (type == 0) {
                return new BarangHolder(parent);
            } else {
                return new PopulerHolder(parent);
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof BarangHolder) {
                final BarangModel item = listBarang.get(position);
                final BarangHolder barangHolder = (BarangHolder) holder;
                barangHolder
                        .setId(item.get_id())
                        .setName(item.getName())
                        .setDesc(item.getDesc());

            } else if (holder instanceof PopulerHolder) {
                final PopulerHolder populerHolder = (PopulerHolder) holder;
                populerHolder
                        .setId(listBarangPop.get(position).get_id())
                        .setName(listBarangPop.get(position).getName())
                        .setDesc(listBarangPop.get(position).getDesc());
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (type == 0) {
                        Toast.makeText(holder.itemView.getContext(),
                                String.valueOf(listBarang.get(holder.getAdapterPosition())
                                        .toString()), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(holder.itemView.getContext(),
                                String.valueOf(listBarangPop.get(holder.getAdapterPosition())
                                        .toString()), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            if (type == 0) {
                return listBarang.size();
            } else {
                return listBarangPop.size();
            }
        }
    }

}
