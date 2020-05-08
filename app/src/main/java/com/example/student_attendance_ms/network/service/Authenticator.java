package com.example.student_attendance_ms.network.service;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.student_attendance_ms.login.LoginActivity;


public class Authenticator extends AbstractAccountAuthenticator {

    private Context context;

    Authenticator(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Bundle addAccount(
            AccountAuthenticatorResponse response,
            String accountType, String authTokenType,
            String[] requiredFeatures, Bundle options
    ) throws NetworkErrorException {

        final Intent intent = new Intent(context, LoginActivity.class);
        final Bundle bundle = new Bundle();

        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType);
        intent.putExtra("KEY_AUTH_TOKEN_TYPE", authTokenType);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        if (options != null){
            bundle.putAll(options);
        }

        bundle.putParcelable(AccountManager.KEY_INTENT, intent);

        return bundle;
    }

    @Override
    public Bundle getAuthToken(
            AccountAuthenticatorResponse response,
            Account account,
            String authTokenType,
            Bundle options
    ) throws NetworkErrorException {

        final AccountManager accountManager = AccountManager.get(context);
        final Intent intent = new Intent(context, LoginActivity.class);
        final Bundle result = new Bundle();

        String authToken = accountManager.peekAuthToken(account, authTokenType);

//        if (TextUtils.isEmpty(authToken)){
//            final String password = accountManager.getPassword(account);
//            if (password != null){
//                authToken = Loader(
//                        context, account.name, password);
//            }
//        }

        if (!TextUtils.isEmpty(authToken)) {

            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken);

        } else {

            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
            intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, account.name);
            intent.putExtra("KEY_AUTH_TOKEN_TYPE", authTokenType);

            result.putParcelable(AccountManager.KEY_INTENT, intent);

        }

        return result;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }


}
