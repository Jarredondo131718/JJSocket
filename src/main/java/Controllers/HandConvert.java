package Controllers;

import Models.Hand;
import javafx.util.StringConverter;

public class HandConvert extends StringConverter<Hand> {

    @Override
    public Hand fromString(String arg0) {

        return null;
    }

    @Override
    public String toString(Hand hand) {

        return hand == null ? null : hand.getNombreDedo();
    }
}
