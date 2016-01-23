package android.tasc.ch.mmolmgrechner;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by developer on 16.11.14.
 */
public class BzCalculator {

    public final static double MMOL_FACTOR = 0.0555;

    public final static double MG_FACTOR = 18.0182;

    public String calcMmol(String mgTxt) {

        try {
            int mg = Integer.valueOf(mgTxt).intValue();
            double mmol = mg * MMOL_FACTOR;
            return String.format("%.2f",mmol);

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.FINE, "Invalid BZ value " + mgTxt);
            return null;
        }
    }

    public String calcMg(String mmolText) {
        try {
            double mmol = Double.valueOf(mmolText).doubleValue();
            int mg = (int) (mmol * MG_FACTOR);
            return String.valueOf(mg);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.FINE, "Invalid BZ value " + mmolText);
            return null;
        }
    }
}
