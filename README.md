#### Transition

> android系统API，由于只能在API 19（android 4.4）及以上运行，
> 因此引申出了一个向下兼容的库如下，同时这个库中也添加了一些新的效果实现类，
> 这里只介绍系统API相关内容

[Transitions-Everywhere](https://github.com/andkulikov/Transitions-Everywhere)

##### Transition使用局限

    1、如果将Transition应用在一个SurfaceView上，可能会造成不正常的显示。SurfaceView是在非UI线程更新的，因此在UI线程执行动画时，SurfaceView可能不会被实时同步。

    2、TextureView与一般的transition相容，但transition中依赖于viewOverlay（叠加层）的功能（如fade）与textureView不兼容，viewOverlay不适用于Textureview。
    注：TextureView可以用来显示内容流，内容流例如是视频或OpenGl场景，来自本应用程序以及其他进程。与SurfaceView相比，TextureView不会创建一个单独的窗口，这使得它可以像一般的View一样执行一些变换操作。

    // 以上两条是 Transition 类中注释中涉及的内容

    3、AdapterView的子类，比如ListView并不适应于Transition框架。

    4、如果尝试使用动画调整TextView大小，文本将在对象完成大小调整之前弹出到新的位置。为避免此问题，不要为包含文本的视图调整大小。



##### 基本使用方法

> Transition 框架相关类

    Scene
    Scene类存储着一个根view下的各种view的属性。
    通常由getSceneForLayout (ViewGroup sceneRoot,int layoutId,Context context)获取
    sceneRoot：scene发生改变和动画执行的位置
    layoutId：即上文所说的根view
    根据起始scene和结束scene来创建过渡动画，两个scene的ID应该一直，视为同一个view
    Transition框架可以实现在starting scene和ending scene之间执行动画。
    而且大多数情况下，我们不需要创建starting scene，因为starting scene通常由当前UI状态决定，我们只需要创建ending scene。


    Transition
    用来设置过渡动画效果用的，并且提供了一系列的实现效果


    子类：
    AutoTransition
    Transition实现效果，默认实现效果
    对应xml tag为autoTransition
    addTransition(new Fade(Fade.OUT)).
    addTransition(new ChangeBounds()).
    addTransition(new Fade(Fade.IN));


    ChangeBounds
    Transition实现效果
    对应xml tag为changeBounds
    检测view的位置边界创建移动和缩放动画


    ChangeClipBounds
    Transition实现效果
    对应xml tag为changeClipBounds
    检测view的剪切区域的位置边界，和ChangeBounds类似。
    不过ChangeBounds针对的是view而ChangeClipBounds针对的是view的剪切区域(setClipBound(Rect rect) 中的rect)。
    如果没有设置则没有动画效果。
    其rect必须表示一个矩形，如 new Rect(150, 150, 250, 250)
    如果矩形坐标点重合，则动画执行完会回到默认状态


    ChangeImageTransform
    Transition实现效果
    对应xml tag为changeImageTransform
    检测ImageView（这里是专指ImageView）的尺寸，
    位置以及ScaleType，并创建相应动画。


    ChangeScroll
    Transition实现效果
    对应xml tag为changeScroll
    作用对象：View的scroll属性值
    参考：TransitionOneActivity.initChangeScroll();


    ChangeTransform
    Transition实现效果
    对应xml tag 为changeTransform
    检测view的scale和rotation创建缩放和旋转动画


    Visibility
    Transition实现效果
        Explode
        对应xml tag为explode
        作用对象：View的Visibility
        爆炸动画

        Fade
        对应xml tag为fade
        作用对象：View的Visibility
        可以在初始化是指定IN或者OUT分别对应淡入和淡出，若不指定默认为淡入淡出效果

        Slide
        对应xml tag为slide
        作用对象：View的Visibility
        平移

    PathMition
    Transition实现效果

        ArcMotion
        对应xml tag为arcMotion
        PatternPathMotion
        对应xml tag为patternPathMotion
        以path的方式指定过渡效果，会形成一定的圆弧路径
        参考：TransitionOneActivity.initPathMotion();


    TransitionSet
    Transition实现效果
    对应xml tag为transitionSet
    通过
    new TransitionSet().addTransition(transition);
    来添加，组合多个效果


    TransitionManager
    Transition动画开始执行的manager
    两种方式开始执行动画
    1、TransitionManager.go()
        一直都是根据xml文件创造start scene和end scene来触发动画效果
        内部实际是先后调用了start scene的exit()方法，然后调用end scene的enter()方法
        然后执行动画播放效果

    2、TransitionManager.beginDelayedTransition()
        通过代码改变view的属性，然后通过ChangeBounds等类分析start scene和end Scene不同来创建动画


##### XML使用方式

    每一种Transition实现类都对应一个xml的tag值，如上
    在res下创建文件夹transition，将所有的transition的xml全部放入这个文件夹下
    参考：transition_set.xml
    另外其他属性如：
    duration
    startDelay
    interpolator
    transitionOrdering
    matchOrder

    使用
    TransitionInflater.from(this).inflateTransition(R.transition.transition_set);
    方式获取transition对象，然后再使用go或者beginDelayedTransition方法实现动画


##### Content Transition



##### Share Element Transition


    问题：退出时点击返回虚拟键会有回退的动画效果，调用finish()方法时，需要将此方法替换为finishAfterTransition()


ViewOverlay???

[Android Transition Framework详解---超炫的动画框架](https://www.jianshu.com/p/e497123652b5)

[Android 转场动画Transition 、Share Elements](https://www.jianshu.com/p/10a820329959)

[Material Designer的低版本兼容实现（五）—— ActivityOptionsCompat](https://www.cnblogs.com/tianzhijiexian/p/4087917.html)

