package com.github.paicoding.forum.web.javabetter.top.copydown;

public class ImageUtil {
    public static String getImgExt(String url) {
        for (String extItem : Constants.imgExtension) {
            if (url.indexOf(extItem) != -1) {
                return extItem;
            }
        }
        return Constants.imgExtension[0];
    }
}
