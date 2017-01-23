package com.android.busroutes.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.busroutes.sample.R;
import com.android.busroutes.sample.model.ImageInfo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImageListFragment extends Fragment {

    private static final String ARG_ROUTE_LIST = "routeList";

    private List<ImageInfo> imageInfos;

    public static ImageListFragment newInstance(List<ImageInfo> data) {

        Bundle args = new Bundle();
        if (data != null) {
            args.putParcelableArrayList(ARG_ROUTE_LIST, new ArrayList<Parcelable>(data));
        }
        ImageListFragment fragment = new ImageListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public interface onRouteSelectedListener {
        void onRouteSelected(ImageInfo imageInfo);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = savedInstanceState == null ? getArguments() : savedInstanceState;
        if (bundle != null && bundle.containsKey(ARG_ROUTE_LIST)) {
            imageInfos = bundle.getParcelableArrayList(ARG_ROUTE_LIST);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (imageInfos != null && !imageInfos.isEmpty()) {
            outState.putParcelableArrayList(ARG_ROUTE_LIST, new ArrayList<Parcelable>(imageInfos));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);

        RecyclerView routeListView = (RecyclerView) view.findViewById(R.id.list);

        routeListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RoutesAdapter adapter = new RoutesAdapter(getActivity());
        routeListView.setAdapter(adapter);
        adapter.setData(imageInfos);
        adapter.setOnItemClickedListener(new RoutesAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                onRouteSelected(position);
            }
        });

        getActivity().setTitle(getResources().getString(R.string.imageInfos));

        return view;
    }

    private void onRouteSelected(int position) {
        ImageInfo imageInfo = imageInfos.get(position);
        if (getActivity() instanceof onRouteSelectedListener) {
            ((onRouteSelectedListener) getActivity()).onRouteSelected(imageInfo);
        }
    }

    private static class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.RoutesViewHolder> {

        List<ImageInfo> imageInfos;
        Context context;

        public RoutesAdapter(Context context) {
            this.context = context;
            imageInfos = new ArrayList<>();
        }

        public class RoutesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ImageView route;

            public RoutesViewHolder(View itemView) {
                super(itemView);
                route = (ImageView) itemView.findViewById(R.id.route_image);
                itemView.setOnClickListener(this);
            }


            @Override
            public void onClick(View v) {
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClicked(getAdapterPosition());
                }
            }
        }

        public interface OnItemClickedListener {
            void onItemClicked (int position);
        }

        private OnItemClickedListener onItemClickedListener;

        private void setOnItemClickedListener (OnItemClickedListener listener) {
            this.onItemClickedListener = listener;
        }

        @Override
        public RoutesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.layout_image_list_item, parent, false);
            return new RoutesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RoutesViewHolder holder, int position) {

            ImageInfo imageInfo = imageInfos.get(position);
            Glide.with(context)
                    .load(imageInfo.getImage())
                    .into(holder.route);

        }

        @Override
        public int getItemCount() {
            return imageInfos.size();
        }

        public void setData(List<ImageInfo> data) {
            this.imageInfos.clear();
            if (data != null) {
                this.imageInfos.addAll(data);
            }
            this.notifyDataSetChanged();
        }
    }

}
