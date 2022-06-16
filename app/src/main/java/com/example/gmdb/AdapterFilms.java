package com.example.gmdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class AdapterFilms extends FirestoreRecyclerAdapter<ModelFilms, AdapterFilms.FilmsViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterFilms(@NonNull FirestoreRecyclerOptions<ModelFilms> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FilmsViewHolder filmViewHolder, int position, @NonNull ModelFilms model) {
        String affiche = model.getAffiche();
        String titre = model.getTitre();
        String synopsis = model.getSynopsis();

        filmViewHolder.tv_titre.setText(titre);
        filmViewHolder.tv_synopsis.setText(synopsis);
        // Ajout des options pour afficher les affiches
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_movies)
                .placeholder(R.drawable.ic_movies);

        Context context = filmViewHolder.iv_Affiche.getContext();
        Glide.with(context)
                .load(affiche)
                .apply(options)
                .fitCenter()
                .override(150,150)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(filmViewHolder.iv_Affiche);
    }

    @NonNull
    @Override
    public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_film, parent, false);

        return new FilmsViewHolder(view);
    }

    public class FilmsViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_Affiche;
        private TextView tv_titre, tv_synopsis;

        public FilmsViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_Affiche = itemView.findViewById(R.id.tv_affiche);
            tv_titre = itemView.findViewById(R.id.tv_titre);
            tv_synopsis = itemView.findViewById(R.id.tv_synopsis);

            // gestion du click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && filmClickListener != null){
                        DocumentSnapshot filmSnapshot = getSnapshots().getSnapshot(position);
                        filmClickListener.onItemClick(filmSnapshot, position);
                    }
                }
            });
        }
    }

    /** interface pour le click **/
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    private  OnItemClickListener filmClickListener;

    public void setOnItemClickListener(OnItemClickListener filmClickListener){
        this.filmClickListener = filmClickListener;
    }

}
