package com.bing.http.annotation;

import androidx.annotation.IntDef;

import static com.bing.http.annotation.RequestMethod.GET;
import static com.bing.http.annotation.RequestMethod.POST;

@IntDef({GET,POST})
public @interface RequestMethod {
    int GET=1;
    int POST=2;
}
