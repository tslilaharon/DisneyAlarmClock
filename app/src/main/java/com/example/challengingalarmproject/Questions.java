package com.example.challengingalarmproject;

public class Questions {
//יצירת כל השאלות האפשריות והתשובות
    //שאלה
    private String mQuestions[]={
            "What's the name of the princess in the picture ?",
            "What is the name of the character in the picture ?",
            "What's the name of the princess in the picture ?",
            "What is the name of the character ?",
            "What's the name of the princess in the picture ?",
            "What's the name of the princess in the picture ?",
            "What is the name of the character ?",
            "What's the name of the princess in the picture ?",
            "From what movie the character in the picture",
            "From what movie the character in the picture",
            "What is the name of the character ?",
            "What's the name of the princess in the picture ?"

    };
    //אפשרויות
    private String mChoice[] []={
            {"Cinderella", "Ariel", "belle"},//Ariel
            {"Balu", "Dugi", "Simba"},//Balu
            {"Cinderella", "Elsa", "SnowWhite"},//Cinderella
            {"Simba", "TheBeast", "Dugi"},//Dugi
            {"Elsa", "Rapunzel", "belle"},//Elsa
            {"SnowWhite", "Tiana", "Mulan"},//Snow White
            {"Simba", "Aladdin", "Tarzan"},//Simba
            {"Moana", "Pokahontas", "Merida"},//Pokahontas
            {"LionKing", "Cinderella", "Pkahontas"},// Pkahontas
            {"Moana", "Hrculis", "Tiana"},// Hrculis
            {"TheBeast", "Ursula", "Captain Hook"},//TheBeast
            {"Tiana", "Yasmin", "Moana"}//Yasmin

    };
    //תמונה
    private String mImages[]={
            "q",
            "q1",
            "q2",
            "q3",
            "q4",
            "q5",
            "q6",
            "q7",
            "q8",
            "q9",
            "q10",
            "q11"

    };
    //סוג
    private String mQuestionsType[]={
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton"
    };
    //תשובה
    private String mCorrectAnswers[]={
            "Ariel",
            "Balu",
            "Cinderella",
            "Dugi",
            "Elsa",
            "SnowWhite",
            "Simba",
            "Pokahontas",
            "Hrculis",
            "Pkahontas",
            "TheBeast",
            "Yasmin"
    };
//יצירת Get
    public String getQuestions(int q) {
        String questions =mQuestions[q];
        return questions;
    }

    public String[] getChoice(int q) {
        String[] Choice = mChoice[q];
        return Choice;
    }

    public String getImages(int q) {
        String img = mImages[q];
        return img;
    }

    public String getType(int q){
        String type= mQuestionsType[q];
        return type;
    }
    public int getLength(){
        return mQuestions.length;
    }
    public String getCorrectAnswers(int q){
        String correct= mCorrectAnswers [q];
        return correct;
    }
}
