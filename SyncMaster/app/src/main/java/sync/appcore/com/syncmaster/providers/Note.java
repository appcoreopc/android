package sync.appcore.com.syncmaster.providers;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jeremyw on 06/01/2016.
 */

public class Note {

    public Note() {
    }

    public static final class Notes implements BaseColumns {
        private Notes() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://"
                +  DataContentProvider.AUTHORITY + "/notes");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.jwei512.notes";

        public static final String NOTE_ID = "_id";

        public static final String TITLE = "title";

        public static final String TEXT = "text";
    }

}