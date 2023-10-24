package com.garnier.garnier_tp_morse;

public class TraduireMorse {
    private ControllerMorse controllerMorse;
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
    public TraduireMorse(ControllerMorse controllerMorse) {
        this.controllerMorse = controllerMorse;
    }
    public void traduire (String texte){
        // Ici "/" corespond à une barre-morte et "\" corespond à un point-mort

        texte.toLowerCase();
        texte = texte.replace("","/");
        texte = texte.replace(" ","\\");
        texte = texte.substring(1,texte.length()-1);
        System.out.println(texte);

        char [] chars = texte.toCharArray();
        int length = chars.length;
        String morsetemp;
        String out = "";

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
             if (chars[i] == alphabet[j]) {
                 morsetemp = morse[j].replace("","\\");
                 morsetemp = morsetemp.substring(1,morsetemp.length()-1);
                 out = out + morsetemp;
             }
            }
            if (chars[i] == '/'){
            out = out + chars[i];
            }
            else if (chars[i] == '\\') {
            out = out + chars[i];
        }
        }
        System.out.println(out);

    }
}
