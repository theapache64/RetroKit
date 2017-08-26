# RetroKit
An android project kit integrated mainly with Retrofit and other popular utility libraries.

### Libraries integrated
* [Retrofit v2.3.0](https://github.com/square/retrofit)
* [gson v2.7](https://github.com/google/gson)
* [ButterKnife v8.8.1](https://github.com/JakeWharton/butterknife)
* [Iconify (fa-module v2.2.1)](https://github.com/JoanZapata/android-iconify)
* [AVLoadingIndicatorView v2.1.3](https://github.com/81813780/AVLoadingIndicatorView)
* [Calligraphy v2.3.0](https://github.com/chrisjenx/Calligraphy)
* and also material colors xml

### How to integrate this library ?

**Step one - Add to build.gradle**

`compile 'com.theah64.retrokit:retrokit:1.0.1'`

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

    


