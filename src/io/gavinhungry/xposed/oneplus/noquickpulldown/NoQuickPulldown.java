package io.gavinhungry.xposed.oneplus.noquickpulldown;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodHook;

import android.os.Build;
import android.view.MotionEvent;

public class NoQuickPulldown implements IXposedHookLoadPackage {

  private static final String qsExpandFieldName = Build.VERSION.SDK_INT == 21 ? "mTwoFingerQsExpand" : "mQsExpandImmediate";

  public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
    if (lpparam.packageName.equals("com.android.systemui")) {
      try {

        XposedHelpers.findAndHookMethod("com.android.systemui.statusbar.phone.NotificationPanelView", lpparam.classLoader,
          "onTouchEvent", MotionEvent.class, new XC_MethodHook() {

          @Override
          protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            XposedHelpers.setBooleanField(param.thisObject, qsExpandFieldName, false);
          }
        });

      } catch(Throwable t) {
        XposedBridge.log(t);
      }
    }
  }
}
