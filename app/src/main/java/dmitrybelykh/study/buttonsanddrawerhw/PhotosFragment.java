package dmitrybelykh.study.buttonsanddrawerhw;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class PhotosFragment extends Fragment {

    private OnPhotosFragmentListener mListener;
    private FloatingActionButton fab;
    private AppCompatButton dialogButton;

    public PhotosFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_photos, container, false);
        fab = viewRoot.findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Photo added", Snackbar.LENGTH_SHORT)
                    .setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        });
        dialogButton = viewRoot.findViewById(R.id.dialog_button);
        dialogButton.setOnClickListener(click -> {
            new AlertDialog.Builder(click.getContext())
                    .setTitle("This is dialog")
                    .setCancelable(true)
                    .setPositiveButton("Ok", null)
                    .setMessage("Some information to acquaintance")
                    .create()
                    .show();
        });
        return viewRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
        fab.animate()
                .setDuration(500)
                .translationY(0f)
                .alpha(1f)
                .setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    public void onPause() {
        fab.animate()
                .translationY(-100f)
                .alpha(0f);
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPhotosFragmentListener) {
            mListener = (OnPhotosFragmentListener) context;
        } else {
            throw new ClassCastException("Must implement interface");
        }
    }

    @Override
    public void onDetach() {
        if (mListener != null) {
            mListener.onDetachFragment();
            mListener = null;
        }
        super.onDetach();
    }

    interface OnPhotosFragmentListener {
        // Some interactions
        void onDetachFragment();
    }
}
