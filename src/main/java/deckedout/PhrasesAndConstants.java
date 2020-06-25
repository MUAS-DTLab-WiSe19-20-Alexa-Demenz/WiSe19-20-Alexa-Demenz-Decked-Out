package deckedout;

public class PhrasesAndConstants {

    private PhrasesAndConstants() {
        throw new IllegalStateException("Utility class");
    }
   
    public static final String CARD_TITLE = "DeckedOut";
    public static final String OBJECT_KEY = "Object"; //Slot name for objects
    public static final String LOCATION_KEY = "Location"; //Slot name for Location
    public static final String CURRENT_LOCATION_KEY = "CurrentLocation"; //Slot name for current Location (RelocateIntent)
    public static final String NEW_LOCATION_KEY = "NewLocation"; //Slot name for new Location (ReloacteIntent)
    
    public static final String HELP = "Du kannst mir sagen wo du deine Wertsachen aufbewahrst. Sage zum Beispiel: Ich habe meine Armbanduhr in die Bettschublade gelegt.";
    public static final String CANCEL_AND_STOP = "Auf Wiedersehen";
    public static final String FALLBACK = "Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.";

    public static final String OBJECT_DEPOSITED = "Du hast den Gegenstand %s am Ort %s hinterlegt. %s";
    public static final String CHANGE_LOCATION = "Wenn du den Gegenstand wo anders hinlegen willst, sage mir den neuen Ort.";
    public static final String HELP_REPROMPT = "Bitte sage mir wo deine Sachen liegen";
    public static final String WELCOME = "Hallo. Ich helfe dir beim Wiederfinden von Gegenständen. Bitte sage mir zum Beispiel: Mein Haustürschlüssel ist auf der Anrichte.";



}
