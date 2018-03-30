package br.com.geodrone.presenter;

/**
 * Created by fernandes on 29/03/2018.
 */
public abstract class BasePresenter<T>{
    protected T view;

    public void dropView() {
        this.view = null;
    }

    public void onLoad() {
    }

    public void takeView(T view) {
        this.view = view;
        onLoad();
    }

    protected boolean hasView() {
        return view != null;
    }
}
