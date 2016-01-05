package cam.appcore.com.intentmaster;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;

public class IntentFactory {

    public static PendingIntent createPendingIntent(Context context, Class claz)
    {
        Intent intent = new Intent(context, claz);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, 0);
        return pendingIntent;
    }

    public static Intent getEmailIntent(String senderEmailAddress, String subject, String bodyText)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", senderEmailAddress, null));
        intent.setType("message/rfc822"); // this makes a big difference -> alert message seems to be better
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, bodyText);
        return intent;
    }

    public static Intent getCustomIntent()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("text/plain");
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
