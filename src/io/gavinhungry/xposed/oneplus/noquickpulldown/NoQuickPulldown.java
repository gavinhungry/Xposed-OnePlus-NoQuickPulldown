package io.gavinhungry.xposed.oneplus.noquickpulldown;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.view.MotionEvent;

public class NoQuickPulldown implements IXposedHookLoadPackage {
  public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {

    XposedBridge.log("OP2 handleLoadPackage: " + lpparam.packageName);

    if (lpparam.packageName.equals("com.android.systemui")) {
      try {
        findAndHookMethod("com.android.systemui.statusbar.phone.NotificationPanelView", lpparam.classLoader,
          "onTouchEvent", MotionEvent.class, XC_MethodReplacement.DO_NOTHING);
      } catch (Throwable t) {
        XposedBridge.log("OP2 Error!");
      }
    }

  }
}
