package example.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.support.graphics.drawable.ArgbEvaluator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private Button mButton_alpha, mButton_rotate, mButton_scale,
            mButton_translate, mButton_set, mButton_clear, mButton_finish;
    private ImageView mImageView;

    private int imageViewWidth, imageViewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mContext = this;
        mImageView = findViewById(R.id.image_view);
        mImageView.setImageResource(R.drawable.benq_logo_00000);
        imageViewWidth = mImageView.getLayoutParams().width;
        imageViewHeight = mImageView.getLayoutParams().height;
        mImageView.setClickable(true);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("luoah", "[MainActivity.java] -- mImageView onClick -- ");
            }
        });


        Glide.with(mContext)
                .load(R.drawable.bueaty)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                /*.override(480, 960)*/
                .into(mImageView);

        mButton_alpha = findViewById(R.id.btn_alpha);
        mButton_alpha.setOnClickListener(this);
        mButton_rotate = findViewById(R.id.btn_rotate);
        mButton_rotate.setOnClickListener(this);
        mButton_scale = findViewById(R.id.btn_scale);
        mButton_scale.setOnClickListener(this);
        mButton_translate = findViewById(R.id.btn_translate);
        mButton_translate.setOnClickListener(this);
        mButton_set = findViewById(R.id.btn_set);
        mButton_set.setOnClickListener(this);
        mButton_clear = findViewById(R.id.btn_clear);
        mButton_clear.setOnClickListener(this);
        mButton_finish = findViewById(R.id.btn_finish);
        mButton_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alpha:
                loadAlphaAnimation();
                break;
            case R.id.btn_rotate:
                loadRotateAnimation();
                break;
            case R.id.btn_scale:
                loadScaleAnimation();
                break;
            case R.id.btn_translate:
                loadTranslateAnimation();
                break;
            case R.id.btn_set:
                loadAnimationSet();
                break;
            case R.id.btn_clear:
                clearAnimation();
                break;
            case R.id.btn_finish:
                //loadFinishAnimation();
                loadObjectAnimator(7);
                break;
            default:
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    private void loadAlphaAnimation(){
        Animation alphaAnimation = AnimationUtils.loadAnimation(mContext, R.anim.alpha_animation);
        alphaAnimation.setFillAfter(true);
        mImageView.startAnimation(alphaAnimation);
    }

    private void loadScaleAnimation(){
//        Animation scaleAnimation = AnimationUtils.loadAnimation(mContext, R.anim.scale_animation);
//        scaleAnimation.setFillAfter(true);
//        mImageView.startAnimation(scaleAnimation);

        // mImageView放到最上层，防止被其他空间覆盖
        mImageView.bringToFront();

        ScaleAnimation scaleAnim = new ScaleAnimation(1, 1.5f, 1,1.5f);
        scaleAnim.setFillAfter(true);
        mImageView.startAnimation(scaleAnim);
    }

    private void loadTranslateAnimation(){
        Animation translateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.translate_animation);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mImageView.startAnimation(translateAnimation);
    }

    private void loadRotateAnimation(){
        Animation rotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_animation);
        rotateAnimation.setFillAfter(true);
        mImageView.startAnimation(rotateAnimation);
    }

    private void loadDrawableAnimation() {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.benq_logo_00000), 1000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.benq_logo_00001), 1000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.benq_logo_00002), 1000);
        animationDrawable.setOneShot(true);
        mImageView.setImageDrawable(animationDrawable);
        animationDrawable.start();
    }

    private void loadAnimationSet(){
        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(2000);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, mImageView.getWidth(), 0, 0);
        translateAnimation.setDuration(2000);

        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setStartOffset(300);
        animationSet.setFillAfter(true);
        mImageView.startAnimation(animationSet);
    }

    private void loadFinishAnimation(){
        finish();
        overridePendingTransition(0 ,R.anim.translate_animation);
    }

    private void clearAnimation(){
        mImageView.clearAnimation();
    }

    private void loadObjectAnimator(int index) {

        //获取屏幕宽高
        WindowManager wm1 = this.getWindowManager();
        final int windowWidth = wm1.getDefaultDisplay().getWidth();
        int windowHeight = wm1.getDefaultDisplay().getHeight();

        if (index == 0) {
            //利用ObjectAnimator实现透明度动画
            ObjectAnimator.ofFloat(mImageView, "alpha", 1, 0, 1)
                    .setDuration(2000).start();
        }

        if (index == 1) {
            //利用AnimatorSet和ObjectAnimator实现缩放动画
            final AnimatorSet animatorSet = new AnimatorSet();
            mImageView.setPivotX(mImageView.getWidth() / 2);
            mImageView.setPivotY(mImageView.getHeight() / 2);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(mImageView, "scaleX", 1, 1.5f).setDuration(5000),
                    ObjectAnimator.ofFloat(mImageView, "scaleY", 1, 1.5f).setDuration(5000));
            animatorSet.start();
        }

        if (index == 2) {
            //利用AnimatorSet和ObjectAnimator实现平移动画
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(mImageView, "translationX", 20, 100).setDuration(2000),
                    ObjectAnimator.ofFloat(mImageView, "translationY", 20, 100).setDuration(2000));
            animatorSet.start();
        }
        if (index == 3) {
            //利用ObjectAnimator实现旋转动画
            mImageView.setPivotX(mImageView.getWidth() / 2);
            mImageView.setPivotY(mImageView.getHeight() / 2);
            ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360)
                    .setDuration(1000).start();
        }

        if (index == 4) {
            ValueAnimator colorAnim = ObjectAnimator.ofInt(mButton_alpha, "backgroundColor",
                    /*Red*//*0xFFFF8080, *//*Blue*//*0xFF8080FF);*/
                    /*Red*/Color.RED, Color.BLUE);
            colorAnim.setDuration(3000);
            colorAnim.setEvaluator(new android.animation.ArgbEvaluator());
            colorAnim.setRepeatCount(ValueAnimator.INFINITE);
            colorAnim.setRepeatMode(ValueAnimator.REVERSE);
            colorAnim.start();
        }

        if (index == 5) {
            ObjectAnimator.ofFloat(mButton_alpha, "scaleX", 1, 0.5f).setDuration(2000).start();
        }

        if (index == 6) {
            ViewWrapper wrapper = new ViewWrapper(mButton_scale);
            ObjectAnimator.ofInt(wrapper, "width", mButton_scale.getWidth(), windowWidth)
                    .setDuration(5000)
                    .start();
        }

        if (index == 7) {
            ValueAnimator animator = ValueAnimator.ofInt(1, 1000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                private IntEvaluator mIntEvaluator = new IntEvaluator();

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    // 获得当前进度占整个动画过程的比例，浮点型，0-1之间
                    float fraction = animation.getAnimatedFraction();
//                    mImageView.getLayoutParams().height = mIntEvaluator.evaluate(fraction,
//                            mImageView.getWidth(), 1920);
//                    mImageView.requestLayout();

                    mButton_scale.getLayoutParams().width = mIntEvaluator.evaluate(fraction,
                            mButton_scale.getWidth(), windowWidth*7/10);
                    mButton_scale.requestLayout();
                }
            });
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.d("luoah", "[MainActivity.java] -- onAnimationEnd -- " +
                            "mButton_scale.width:" + mButton_scale.getLayoutParams().width);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.setDuration(5000).start();
        }
    }


    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
}
