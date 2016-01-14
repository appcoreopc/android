package sync.appcore.com.syncmaster.security;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Authenticator extends AbstractAccountAuthenticator {

    private Context mContext;
    private static String TAG = "Authenticator";

    public Authenticator(Context ctx) {
        super(ctx);
        mContext = ctx;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {

        final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(CustomAuthenticationAccount.ACCOUNT_TYPE, accountType);
        intent.putExtra(CustomAuthenticationAccount.ACCOUNT_AUTH_TYPE, authTokenType);
        intent.putExtra(CustomAuthenticationAccount.ACCOUNT_RESPONSE, accountAuthenticatorResponse);

        final Bundle addAccountBundle = new Bundle();
        addAccountBundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return addAccountBundle;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strings) throws NetworkErrorException {
        final Bundle bundle = new Bundle();
        bundle.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false);
        return bundle;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        if (CustomAuthenticationAccount.AUTHTOKEN_TYPE_FULL_ACCESS.equals(authTokenType))
            return CustomAuthenticationAccount.AUTHTOKEN_TYPE_FULL_ACCESS_LABEL;
        else if (CustomAuthenticationAccount.AUTHTOKEN_TYPE_READ_ONLY.equals(authTokenType))
            return CustomAuthenticationAccount.AUTHTOKEN_TYPE_READ_ONLY_LABEL;
        else
            return authTokenType + " (Label)";
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }


    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String s, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String accountType) {

        return null;
    }

}
