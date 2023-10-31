package com.garnier.garnier_tp_morse;

import javafx.application.Platform;

public class TraduireMorse {
    private static  final char[] alphabet =
            {
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                    'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                    'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
            };
    private static final String[] morse =
            {
                    ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                    ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                    "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                    "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----"
            };
    private Thread led;
    private ControllerMorse controllerMorse;
    public TraduireMorse(ControllerMorse controllerMorse) {
        this.controllerMorse = controllerMorse;
    }
    public void traduire (String texte) throws InterruptedException {
        // pensser a tuer le thread sinon deux message peuvent s'executer ene même temps. C'est mieux que de bloque l'interface le temps que le précédent message ce traduise. là au moin l'utilisateur peut interompre une traduction qu'il éstime mauvaise.
        // mais avant de tuer le thread vaudrait mieux qu'il existe et qu'il tourne
        if (led != null){
            if  (led.isAlive()){
                led.stop();
            }
        }
        // Ici "/" corespond à une barre-morte et "\" corespond à un point-mort
        System.out.println(texte);

        texte.toLowerCase();
        texte = texte.replace("","/");
        texte = texte.replace(" ","\\");
        texte = texte.substring(1,texte.length()-1);

        System.out.println(texte);

        char [] chars = texte.toCharArray();
        int length = chars.length;
        String morsetemp;
        texte = "";

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
             if (chars[i] == alphabet[j]) {
                 morsetemp = morse[j].replace("","\\");
                 morsetemp = morsetemp.substring(1,morsetemp.length()-1);
                 texte = texte + morsetemp;
             }
            }
            if (chars[i] == '/'){
            texte = texte + chars[i];
            }
            else if (chars[i] == '\\') {
            texte = texte + chars[i];
            }
        }

        System.out.println(texte);

        String  [] message;
        message = texte.split("");

        led = new Thread(() -> {
            try {
                controllerMorse.morse(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        led.start();
    }
    public void stop (){
        if (led != null){
            if  (led.isAlive()){
                led.stop();
            }
        }
    }
}
