package example.com.gcmsample;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by gunjansrivastava on 07-03-2016.
 */
public class RegistrationIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public RegistrationIntentService(){
        super("RegistrationService");
    }
    public RegistrationIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String token = null;
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            token = instanceID.getToken(getString(R.string.sender_id),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            editor.putString("GCMToken" , token);
            editor.commit();
            //sendTokenToServer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
