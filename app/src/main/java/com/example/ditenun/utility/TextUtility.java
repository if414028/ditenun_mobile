package com.example.ditenun.utility;

import java.text.NumberFormat;
import java.util.Locale;

public class TextUtility {

    private static final TextUtility ourInstance = new TextUtility();

    public static TextUtility getInstance() {
        return ourInstance;
    }

    private TextUtility() {
    }

    public String formatToRp(Double nominal) {
        if (nominal == null) return "";
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(nominal).replaceAll("Rp", "Rp ");
    }

}
