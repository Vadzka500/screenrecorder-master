

package com.orpheusdroid.screenrecorder.interfaces;


//Interface for permission result callback
public interface PermissionResultListener {
    void onPermissionResult(int requestCode,
                            String permissions[], int[] grantResults);
}
