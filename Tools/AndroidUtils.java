/**
 * the code comes from:
 * http://mp.weixin.qq.com/s?__biz=MzA4NDM2MjAwNw==&mid=205338069&idx=1&sn=32af2ad335e0ad59371782f5e5ebdcc3&scene=1&key=c468684b929d2be25028dfdc44ba279b0ae8ff2663110d6081d6d4e730c6b096dc58193e5727908fcf6e27d83b21a488&ascene=0&uin=MTU5NzI4MTg2MQ%3D%3D&devicetype=iMac+MacBookPro10%2C1+OSX+OSX+10.10.1+build(14B25)&version=11010011&pass_ticket=5DEjFplwA7XF5Hj%2BIrgeZFtYYgs6KnkPahAYNvdk1f19YuYPMpLBelFUVE3KhWS5
 */
class AndroidUtils {
    /**
     * 精确获取屏幕尺寸（例如：3.5、4.0、5.0寸屏幕）
     *
     * @param ctx
     * @return
     */
    public static double getScreenPhysicalSize(Activity ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double diagonalPixels = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
        return diagonalPixels / (160 * dm.density);
    }

    /**
     * 判断是否是平板（官方用法）
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 计算字宽
     * @param text
     * @param Size
     * @return
     */
    public static float GetTextWidth(String text, float Size) {
        TextPaint FontPaint = new TextPaint();
        FontPaint.setTextSize(Size);
        return FontPaint.measureText(text);
    }

    /**
     * 获取应用程序下所有Activity
     * @param ctx
     * @return
     */
    public static ArrayList<String> getActivities(Context ctx) {
        ArrayList<String> result = new ArrayList<String>();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.setPackage(ctx.getPackageName());
        for (ResolveInfo info : ctx.getPackageManager().queryIntentActivities(intent, 0)) {
            result.add(info.activityInfo.name);
        }
        return result;
    }

    /**
     * 启动APK的默认Activity
     * @param ctx
     * @param packageName
     */
    public static void startApkActivity(final Context ctx, String packageName) {
        PackageManager pm = ctx.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(packageName, 0);
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setPackage(pi.packageName);List<ResolveInfo> apps = pm.queryIntentActivities(intent, 0);

            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                String className = ri.activityInfo.name;
                intent.setComponent(new ComponentName(packageName, className));
                ctx.startActivity(intent);
            }
        } catch (NameNotFoundException e) {
            Log.e("startActivity", e);
        }
    }

    /**
     * 检测字符串中是否包含汉字
     * @param sequence
     * @return
     */
    public static boolean checkChinese(String sequence) {
        final String format = "[\\u4E00-\\u9FA5\\uF900-\\uFA2D]";
        boolean result = false;
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(sequence);
        result = matcher.find();
        return result;
    }

    /**
     * 检测字符串中只能包含：中文、数字、下划线(_)、横线(-)
     * @param sequence
     * @return
     */
    public static boolean checkNickname(String sequence) {
        final String format = "[^\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w-_]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(sequence);
        return !matcher.find();
    }


    /**
     * 检查有没有应用程序来接受处理你发出的intent
     * @param context
     * @param action
     * @return
     */
    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size();
    }

    /**
     * 使用TransitionDrawable实现图片显示的时候有渐变效果
     * @param imageView
     * @param bitmap
     */
    private void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        // Use TransitionDrawable to fade in.
        final TransitionDrawable td = new TransitionDrawable(new Drawable[] { new ColorDrawable(android.R.color.transparent), new BitmapDrawable(mContext.getResources(), bitmap) });
        //noinspection deprecation
        imageView.setBackgroundDrawable(imageView.getDrawable());
        imageView.setImageDrawable(td);
        td.startTransition(200);
    }

    /**
     * Dip转px
     * @param ctx
     * @param dip
     * @return
     */
    public static int dipToPX(final Context ctx, float dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, ctx.getResources().getDisplayMetrics());
    }


    /**
     * 获取已经安装APK（列表）的路径
     */
    PackageManager pm = getPackageManager();
    for (ApplicationInfo app : pm.getInstalledApplications(0)) {
        Log.d("PackageList", "package: " + app.packageName + ", sourceDir: " + app.sourceDir);
    }
}