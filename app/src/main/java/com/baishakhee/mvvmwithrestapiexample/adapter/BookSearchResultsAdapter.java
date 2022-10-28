package com.baishakhee.mvvmwithrestapiexample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.baishakhee.mvvmwithrestapiexample.R;
import com.baishakhee.mvvmwithrestapiexample.models.Volume;
import com.baishakhee.mvvmwithrestapiexample.utill.Util;
import com.baishakhee.mvvmwithrestapiexample.views.BookDetailsActivity;
import com.baishakhee.mvvmwithrestapiexample.views.BookSearchFragment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsAdapter.BookSearchResultHolder> {
    private List<Volume> volumeArrayList = new ArrayList<>();
   static  String imageUrl;
    BookSearchFragment mContext;
    public BookSearchResultsAdapter(BookSearchFragment mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BookSearchResultsAdapter.BookSearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookSearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchResultsAdapter.BookSearchResultHolder holder, int position) {
        Volume volume = volumeArrayList.get(position);

        holder.titleTextView.setText(volume.getVolumeInfo().getTitle());
        holder.publishedDateTextView.setText(volume.getVolumeInfo().getPublishedDate());
        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(BookSearchFragment., BookDetailsActivity.class);
                intent.putExtra("title",volume.getVolumeInfo().getTitle());
                intent.putExtra("imageUrl",imageUrl);
                intent.putExtra("publishedDate",volume.getVolumeInfo().getPublishedDate());
                startActivity(intent);*/

            }
        });


        if (volume.getVolumeInfo().getImageLinks() != null) {
             imageUrl = volume.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.smallThumbnailImageView);
        }

        if (volume.getVolumeInfo().getAuthors() != null) {
            Util u = new Util();
            String authors = u.StringJoin(volume.getVolumeInfo().getAuthors(), ", ");
            holder.authorsTextView.setText(authors);
        }
    }

    @Override
    public int getItemCount() {
        return volumeArrayList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setResults(List<Volume> volumeList) {
        this.volumeArrayList = volumeList;
        notifyDataSetChanged();
    }
    public static class BookSearchResultHolder extends RecyclerView.ViewHolder {
        private  CardView cardViewItem;
        private final TextView titleTextView;
        private final TextView authorsTextView;
        private final TextView publishedDateTextView;
        private final ImageView smallThumbnailImageView;

        public BookSearchResultHolder(@NonNull View itemView) {
            super(itemView);

            cardViewItem = itemView.findViewById(R.id.cardViewItem);
            titleTextView = itemView.findViewById(R.id.book_item_title);
            authorsTextView = itemView.findViewById(R.id.book_item_authors);
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate);
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail);
        }
    }

}
