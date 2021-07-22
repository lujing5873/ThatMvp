package com.nhcz500.base.base.view;

import com.nhcz500.base.network.throwable.HttpThrowable;

import pers.nchz.thatmvp.java.view.IThatBaseView;
import pers.nchz.thatmvp.kotlin.view.IThatView;


public interface IBaseView extends IThatView {
      /**
       * 获取数据失败
       * @param throwable
       */
      void dataFailed(HttpThrowable throwable);

      /**
       * 获取数据
       */
      void requestData();
      void showLoading();
      void dismissLoading();
}
