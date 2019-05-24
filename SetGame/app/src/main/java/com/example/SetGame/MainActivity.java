package com.example.SetGame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.SetGame.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int cardCounter = 0; //counts the amount of cards drawn from the deck
    private int emptyButtonCounter = 0; //used in addThreeCards method
    private int score = 0; //used to track the score of the player
    private Cards[] deck = new Cards[81]; //the card deck
    private ImageButton[] buttons = new ImageButton[24]; //array with all the ImageButtons
    private ImageButton firstButton, secondButton, thirdButton; //used in savingPressedButtons method
    private Cards firstCard, secondCard, thirdCard; //used in savingPressedButtons method
    private boolean isSet; //used in compareCards method
    private int firstButtonID = 0, secondButtonID = 0, thirdButtonID = 0; //used in savingPressedButtons method
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDeck(); //creating the deck of cards
        shuffleCards(deck); //shuffling the deck
        createFirstCards(); //setting up the first 12 cards
    }

    public void touchCard(View view) {

        switch(view.getId()) { //this happens if you press a button

            case R.id.imageButton1: //passing a card and the associated button to the chooseCard method
                chooseCard((Cards) buttons[0].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton2:
                chooseCard((Cards) buttons[1].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton3:
                chooseCard((Cards) buttons[2].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton4:
                chooseCard((Cards) buttons[3].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton5:
                chooseCard((Cards) buttons[4].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton6:
                chooseCard((Cards) buttons[5].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton7:
                chooseCard((Cards) buttons[6].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton8:
                chooseCard((Cards) buttons[7].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton9:
                chooseCard((Cards) buttons[8].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton10:
                chooseCard((Cards) buttons[9].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton11:
                chooseCard((Cards) buttons[10].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton12:
                chooseCard((Cards) buttons[11].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton13:
                chooseCard((Cards) buttons[12].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton14:
                chooseCard((Cards) buttons[13].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton15:
                chooseCard((Cards) buttons[14].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton16:
                chooseCard((Cards) buttons[15].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton17:
                chooseCard((Cards) buttons[16].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton18:
                chooseCard((Cards) buttons[17].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton19:
                chooseCard((Cards) buttons[18].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton20:
                chooseCard((Cards) buttons[19].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton21:
                chooseCard((Cards) buttons[20].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton22:
                chooseCard((Cards) buttons[21].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton23:
                chooseCard((Cards) buttons[22].getTag(), (ImageButton) findViewById(view.getId()));
                break;
            case R.id.imageButton24:
                chooseCard((Cards) buttons[23].getTag(), (ImageButton) findViewById(view.getId()));
                break;
        }

        TextView tv = findViewById(R.id.scoreText);
        tv.setText("Score : " + score);
    }

    public void chooseCard(Cards card, ImageButton btn) {

        savingPressedButtons(card, btn); //save Buttons temporarily to compare them afterwards
        compareCards(); //compare cards to detect if it's a deck
    }

    public void savingPressedButtons(Cards card, ImageButton btn) {
        //saving three buttons to compare them and adding functionality to reselect cards
        if(firstButtonID == 0) {
            firstButton = btn;
            firstButtonID = btn.getId();
            if(firstButtonID != secondButtonID) { //pick first card
                firstCard = card;
                firstButton.setRotation(5);
            } else if(firstButtonID == secondButtonID) { //if first two buttons are the same after deselecting the first card if second card is already selected
                firstButtonID = 0;
                secondButtonID = 0;
                secondButton.setRotation(0);
            } else { //if first two buttons are the same
                firstButtonID = 0;
            }
        } else if(secondButtonID == 0) {
            secondButton = btn;
            secondButtonID = btn.getId();
            if(secondButtonID != firstButtonID) { //pick second card
                secondCard = card;
                secondButton.setRotation(355);
            } else {    //if second card is the same as first card
                firstButtonID = 0;
                firstButton.setRotation(0);
                secondButtonID = 0;
            }
        } else {
            thirdButton = btn;
            thirdButtonID = btn.getId();
            if(secondButtonID != thirdButtonID && firstButtonID != thirdButtonID) { //pick third card
                thirdCard = card;
                thirdButton.setRotation(5);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        firstButton.setRotation(0);
                        secondButton.setRotation(0);
                        thirdButton.setRotation(0);
                    }
                }, 200);
            } else if(secondButtonID == thirdButtonID) { //if second button is the same as third
                secondButtonID = 0;
                secondButton.setRotation(0);
                thirdButtonID = 0;
            } else { //if first button is the same as third
                firstButtonID = 0;
                firstButton.setRotation(0);
                thirdButtonID = 0;
            }
        }
    }

    public boolean isSet(Cards a, Cards b, Cards c) {
        //categories need to be either completely identical or different
        if (!((a.getNumber() == b.getNumber()) && (b.getNumber() == c.getNumber()) ||
                (a.getNumber() != b.getNumber()) && (a.getNumber() != c.getNumber())
                        && (b.getNumber() != c.getNumber()))) {
            return false;
        }
        if (!((a.getSymbol() == b.getSymbol()) && (b.getSymbol() == c.getSymbol()) ||
                (a.getSymbol() != b.getSymbol()) && (a.getSymbol() != c.getSymbol())
                        && (b.getSymbol() != c.getSymbol()))) {
            return false;
        }
        if (!((a.getShading() == b.getShading()) && (b.getShading() == c.getShading()) ||
                (a.getShading() != b.getShading()) && (a.getShading() != c.getShading())
                        && (b.getShading() != c.getShading()))) {
            return false;
        }
        if (!((a.getColor() == b.getColor()) && (b.getColor() == c.getColor()) ||
                (a.getColor() != b.getColor()) && (a.getColor() != c.getColor())
                        && (b.getColor() != c.getColor()))) {
            return false;
        }
        return true;
    }

    public void compareCards() {

        if(thirdButtonID != 0) {
            isSet = isSet(firstCard, secondCard, thirdCard); //isSet method checks if the three cards are a deck
            if(isSet == true && firstButtonID != secondButtonID && secondButtonID != thirdButtonID) { //if its a set
                    score += 3;
                    deleteCards();
                    resetButtonID();
            } else { //if its not a set
                score -= 5;
                resetButtonID();
            }
        }
    }

    public void deleteCards() {
        //making cards invisible if set is found
        firstButton.setVisibility(View.INVISIBLE);
        secondButton.setVisibility(View.INVISIBLE);
        thirdButton.setVisibility(View.INVISIBLE);
    }

    public void resetButtonID() {
        //resetting buttonIDs to select new three cards
        firstButtonID = 0;
        secondButtonID = 0;
        thirdButtonID = 0;
    }

    public void createFirstCards() {
        //declaring all 24 buttons and initialize the first 12 buttons
        int i = 0;
        for(int x = 1; x < 25; x++) {
            int resID = MainActivity.this.getResources().getIdentifier("imageButton" + Integer.toString(x),
            "id", getPackageName()); //iterates through all the ImageButtons
            ImageButton genButton = findViewById(resID);
            if(x <=12) { //first 12 cards that are shown on the field
                genButton.setBackground(getDrawable(deck[cardCounter].getPicture()));
                cardCounter++;
                genButton.setTag(deck[cardCounter -1]);
                i++;
                buttons[i-1] = genButton;
            } else { //invisible Buttons that are added by AddThreeCards function
                genButton.setVisibility(View.INVISIBLE);
                i++;
                buttons[i-1] = genButton;
            }
        }
    }

    public void addThreeCards(View view) { //adds three new cards if the button is clicked

        Button button = findViewById(R.id.addButton);
        TextView tv = findViewById(R.id.scoreText);
        tv.setText("Score : " + score);

        if(cardCounter < 81) {

            for(int x = 0; x <= 23; x++) {

                if(!(buttons[x].isShown())) { //check buttons array for invisible buttons
                    buttons[x].setVisibility(View.VISIBLE);
                    buttons[x].setBackground(getDrawable((deck[cardCounter].getPicture())));
                    buttons[x].setTag(deck[cardCounter]);
                    cardCounter++;
                    emptyButtonCounter++;
                    if(emptyButtonCounter == 3) { //stop after 3 invisible buttons are found
                        x = 25; //ends the for loop
                        emptyButtonCounter = 0;
                    }
                }
            }
        } else {
            button.setEnabled(false); //set Enabled false after maximum amount of cards is reached
        }
    }

    public void createDeck(){
        int picture = 0;
        int i = 0;
        String pictureName;

        //reverse engineering the picture names to use them for our Cards-object
        for(int number = 0; number < 3000; number+=1000) {
            for(int shading = 0; shading < 300; shading+=100) {
                for(int colour = 0; colour < 30; colour+=10) {
                    for(int symbol = 0; symbol < 3; symbol++) {
                        picture = number + shading + colour + symbol;
                        pictureName = "s" + getPictureName(picture); //getting the names of the pictures from the drawable folder for the resID
                        int resID = MainActivity.this.getResources().getIdentifier(pictureName ,
                                "drawable", getPackageName()); //using generated picture names to access the int value in drawable
                        Cards card = new Cards(getNumber(number), getShading(shading),
                                getColour(colour), getSymbol(symbol), resID); //creating the cards
                        deck[i] = card;
                        i++;
                    }
                }
            }
        }
    }

    public Cards[] shuffleCards(Cards[] arr) {

        Random shuffle = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randomPosition = shuffle.nextInt(arr.length);
            Cards temp = arr[i];
            arr[i] = arr[randomPosition];
            arr[randomPosition] = temp;
        }
        return arr;
    }

    public int getNumber(int num){

        if (num>0)
            return num/1000;
        else
            return num;
    }

    public int getShading(int num){

        if (num>0)
            return num/100;
        else
            return num;
    }

    public int getColour(int num){

        if (num>0)
            return num/10;
        else
            return num;
    }

    public int getSymbol(int num){

        return num;
    }

    public static String getPictureName(int picture){
        //checks if zeros are needed to get the right card name
        String str;

        if(picture < 3) {
            str = "000" + Integer.toString(picture);
        } else if(picture < 30) {
            str = "00" + Integer.toString(picture);
        } else if(picture < 300) {
            str = "0" + Integer.toString(picture);
        } else {
            str = Integer.toString(picture);
        }
        return str;
    }

    public void newGame(View view) { //starts a new game if the button is clicked

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}