package com.james.reviewer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionsAndAnswersFragment extends Fragment{



    private String ans = "";

    String question,choice1,choice2,choice3,choice4;




    TextView txtQuestion;
    RadioButton radio_a, radio_b, radio_c, radio_d;
    RadioGroup radioGroup;
    private OnFragmentInteractionListener mListener;

    public QuestionsAndAnswersFragment() {
        // Required empty public constructor
    }
    public void SetQuestion(String question, String choice1, String choice2,String choice3, String choice4 ){
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions_and_answers, container, false);

        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        radio_a =(RadioButton) view.findViewById(R.id.radio_a);
        radio_b =(RadioButton) view.findViewById(R.id.radio_b);
        radio_c = (RadioButton)view.findViewById(R.id.radio_c);
        radio_d =(RadioButton) view.findViewById(R.id.radio_d);
        txtQuestion = (TextView) view.findViewById(R.id.text_question);

        txtQuestion.setText(question);
        radio_a.setText(choice1);
        radio_b.setText(choice2);
        radio_c.setText(choice3);
        radio_d.setText(choice4);






        radio_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onFragmentInteraction("a");


                }
            }
        });
        radio_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onFragmentInteraction("b");


                }
            }
        });
        radio_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onFragmentInteraction("c");

                }
            }
        });
        radio_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onFragmentInteraction("d");

                }
            }
        });
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener != null) {
            mListener.onFragmentInteraction("");

        }

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String string);
    }
}
