package br.com.geodrone.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.dto.MenuMainDto;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private MainActivity activity;

    public MainAdapter(MainActivity activity){
        this.activity  = activity;
    }
    private List<MenuMainDto> mContent = new ArrayList<>();


    final class MainViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewMenu)
        TextView textViewMenu;

        @BindView(R.id.image_view_main_menu)
        ImageView imageView;

        MainViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
        @OnClick
        void onClick(View view) {
            MenuMainDto menuMainDto = mContent.get(getAdapterPosition());
            //activity.onClickCliente(menuMainDto);
        }
     }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View root = activity.getLayoutInflater().inflate(R.layout.list_main_menu, viewGroup, false);
        return new MainViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        MenuMainDto menuMainDto = mContent.get(position);
        if (menuMainDto != null) {
            holder.textViewMenu.setText(menuMainDto.getDescricao());
            holder.imageView.setId(menuMainDto.getImageId());
        }
    }

    public void clearData() {
        mContent.clear();
    }

    public void setData(List<MenuMainDto> datas) {
        mContent = datas;
    }

    public void addData(MenuMainDto data) {
        mContent.add(data);
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

}