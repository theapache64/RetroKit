# RetroKit
An android project kit integrated mainly with Retrofit and other popular utility libraries.

### Libraries integrated
* [Retrofit](https://github.com/square/retrofit)
* [gson](https://github.com/google/gson)
* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [Iconify (fa-module)](https://github.com/JoanZapata/android-iconify)
* [AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView)
* [Calligraphy](https://github.com/chrisjenx/Calligraphy)
* and also material colors xml

### How to integrate this library ?

**Step one - Add to build.gradle**

```groovy
compile 'com.theah64.retrokit:retrokit:2.0.1'
```

**Step two - Initialize RetroKit in application instance**

```
public class App extends Application {
    private static final String BASE_URL = "http://www.mocky.io/v2/";
    
    @Override
    public void onCreate() {
        super.onCreate();

        RetroKit.getInstance()
                .setRetrofitBaseURL(BASE_URL)
                .setIconModule(new FontAwesomeModule())
                .setDefaultFontPathAsRobotoRegular()
                .setDefaultProgressIndicator(new BallPulseSyncIndicator())
                .setDefaultProgressIndicatorColor(R.color.colorPrimary);
    }
}
```
That's it!

### Components included
**Activities**

* BaseAppCompatActivity
* BaseDynamicActivity
* BaseRecyclerViewActivity

**Adapters**
* BaseRecyclerViewAdapter
* SimpleRecyclerViewAdapter

**Other misc. components**
* BaseAPIResponse
* RetrofitClient
* ProgressManager

See the example app to see live demonstrations
