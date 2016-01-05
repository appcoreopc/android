package cam.appcore.com.intentmaster;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by jeremyw on 04/01/2016.
 */
public class IntentFactory {


    public static Intent getEmailIntent(String senderEmailAddress, String subject, String bodyText)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", senderEmailAddress, null));
        intent.setType("message/rfc822"); // this makes a big difference -> alert message seems to be better
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, bodyText);
        return intent;
    }

    public static Intent getSmsIntent()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"));
        return intent;
    }

    public static Intent getContactsBookIntent()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        return intent;
    }

    // Gets a specific contact by id.
    public static Intent getContactsItemIntent(int contactID)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
        intent.setData(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(contactID)));
        return intent;
    }
}
