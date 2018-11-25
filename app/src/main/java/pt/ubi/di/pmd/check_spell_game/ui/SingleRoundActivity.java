package pt.ubi.di.pmd.check_spell_game.ui;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pt.ubi.di.pmd.check_spell_game.R;



public class SingleRoundActivity extends Activity implements View.OnClickListener{

    private SingleRoundPresenter presenter;
    private EditText answerET;
    private TextView word1TV;
    private TextView word2TV;
    private TextView highScoreTV;
    private Button checkButton;
    private Button nextButton;
    private Button skipButton;
    private Button shareButton;
    private TextView pointsTV;
    private TextView levelTV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleround);
       initViews();

       presenter=new SingleRoundPresenter(this);

}

    private void initViews() {

        answerET = findViewById(R.id.answerEditText);
        word1TV=findViewById(R.id.word1TextView);
        word2TV=findViewById(R.id.word2TextView);
        checkButton=findViewById(R.id.checkButton);
        nextButton=findViewById(R.id.nextButton);
        skipButton=findViewById(R.id.skipButton);
        pointsTV=findViewById(R.id.playerPointsTextView);
        shareButton= findViewById(R.id.shareButton);
        levelTV=findViewById(R.id.playerLevelTextView);
        highScoreTV=findViewById(R.id.highScoreTextView);



        //init listeners
        nextButton.setOnClickListener(this);
        checkButton.setOnClickListener(this);
        skipButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);


    }

    public void setWord1TextView(String text){
        this.word1TV.setText(text);
    }

    public void setWord2TextView(String text){
        this.word2TV.setText(text);
    }

    public void setSkipButtonVisible(){
        skipButton.setVisibility(View.VISIBLE);
    }
    public void setSkipButtonInvisible(){skipButton.setVisibility(View.INVISIBLE);}
    public void setCheckButtonEnable(boolean enable){
        checkButton.setEnabled(enable);
    }
    public void setNextButtonEnable(boolean enable){
        nextButton.setEnabled(enable);
    }
    public void cleanAnswerET(){answerET.setText("");}
    public void setAnswerETenable(boolean enable){answerET.setEnabled(enable);}

    public void setPointsTV(String points){pointsTV.setText(points);}
    public void setLevelTV(String level){levelTV.setText(level);}


    public void shareHighScore(int score){
        String shareMessage = String.format("I have just beaten a new record in Check-spell! I earned %s points.", score);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(share, "Share your high score!"));
    }

    public void showHighScore(){
        // fade out view nicely after 2 seconds

        final float startSize = 50; // Size in pixels
        final float endSize = 0;
        long animationDuration = 2000; // Animation duration in ms

        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                highScoreTV.setText("New high score!");
                highScoreTV.setTextSize(animatedValue);
            }


        });
        animator.start();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkButton:
                this.presenter.checkAnswer(answerET.getText().toString());
                break;
            case R.id.nextButton:
                this.presenter.loadRound();
                break;
            case R.id.skipButton:
                this.presenter.loadRound();
                break;
            case R.id.shareButton:
                this.presenter.shareHighScore();
        }

    }
}
