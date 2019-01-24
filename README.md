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



基本使用方法

Transition 框架相关类

    Scene

    Transition
    子类：
    AutoTransition
    ChangeBounds
    ChangeClipBounds
    ChangeImageTransform
    ChangeScroll
    ChangeTransform
    Visibility
        Explode
        Fade
        Slide
    PathMition
        ArcMotion
        PatternPathMotion
    TransitionSet


    TransitionManager


使用场景

XML使用方式

onPreDraw()方法调用的时机？？？

