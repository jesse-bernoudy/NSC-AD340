package com.ad340.nsc.mynotes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnNoteDetailFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoteDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NOTE = "param1";

    // TODO: Rename and change types of parameters
    private MyNote note;

    private OnNoteDetailFragmentInteractionListener mListener;

    public NoteDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NoteDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static NoteDetail newInstance() {
        NoteDetail fragment = new NoteDetail();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onNoteDetailInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteDetailFragmentInteractionListener) {
            mListener = (OnNoteDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNoteDetailFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnNoteDetailFragmentInteractionListener {
        // TODO: Update argument type and name
        void onNoteDetailInteraction(Uri uri);
    }

    public void setNoteContent(MyNote newNote) {
        TextView noteTitle = (TextView) this.getView().findViewById(R.id.fragment_noteTitle);
        TextView noteBody = (TextView) this.getView().findViewById(R.id.fragment_noteBody);
        noteTitle.setText(newNote.getTitle());
        noteBody.setText(newNote.getBody());
    }
}
