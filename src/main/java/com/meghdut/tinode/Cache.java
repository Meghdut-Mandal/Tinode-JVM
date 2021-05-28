package com.meghdut.tinode;

import com.meghdut.tinode.model.VxCard;

import java.util.Locale;

import co.tinode.tinodesdk.Tinode;
import co.tinode.tinodesdk.model.PrivateType;

public class Cache
{

    private static final String API_KEY = "AQEAAAABAAD_rAp4DJh05a1HAwFT3A6K";

    private static Tinode sTinode;
    private static String version = "JVM-1";
    private static SqlStorage storage;
    private static String dbFileName = "tnode.db";

    public static Tinode getTinode()
    {
        if (sTinode == null) {
            storage = new SqlStorage(dbFileName);
            sTinode = new Tinode("Tinode/" + version, API_KEY,
                                 storage, null);
            sTinode.setOsString(System.getProperty("os.name"));

            // Default types for parsing Public, Private fields of messages
            sTinode.setDefaultTypeOfMetaPacket(VxCard.class, PrivateType.class);
            sTinode.setMeTypeOfMetaPacket(VxCard.class);
            sTinode.setFndTypeOfMetaPacket(VxCard.class);

            // Set device language
            sTinode.setLanguage(Locale.getDefault().toString());

            // Keep in app to prevent garbage collection.
//            TindroidApp.retainTinodeCache(sTinode);
        }


        return sTinode;
    }

}
