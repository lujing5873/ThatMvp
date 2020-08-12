package pers.nchz.thatmvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;

/**
 *
 */

public interface IThatBaseSubView  extends IThatBaseView{
     <P extends ThatBasePresenter>   void setPresenter(P presenter);
}
