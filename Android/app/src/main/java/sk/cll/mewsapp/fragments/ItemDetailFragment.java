package sk.cll.mewsapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import sk.cll.mewsapp.R;
import sk.cll.mewsapp.activities.ItemListActivity;
import sk.cll.mewsapp.data.Photo;
import sk.cll.mewsapp.data.utils.MyViewModel;
import sk.cll.mewsapp.paging.ItemDetailActivity;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    private Photo mPhoto;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        MyViewModel model = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        if (model.getSelected() != null) {
            mPhoto = model.getSelected();
        } else {
            if (getArguments() != null) {
                if (getArguments().containsKey("photo")) {
                    Gson g = new Gson();
                    mPhoto = g.fromJson(getArguments().getString("photo"), Photo.class);

                    Activity activity = getActivity();


                    if (activity != null) {
                        CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
                        if (appBarLayout != null) {
                            appBarLayout.setTitle(mPhoto.getTitle());

                        }
                    }
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mPhoto != null) {
            ((TextView) rootView.findViewById(R.id.tv_id))
                    .setText(String.format("ID: %d", mPhoto.getId()));
            ((TextView) rootView.findViewById(R.id.tv_albumId))
                    .setText(String.format("Album ID: %d", mPhoto.getAlbumId()));
            Activity activity = this.getActivity();
            if (activity != null) {
                Picasso.get().load(mPhoto.getUrl())
                        .into(((ImageView) rootView.findViewById(R.id.img_photo)));
                //todo title
            }
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyViewModel model = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        model.setSelected(null);

    }
}