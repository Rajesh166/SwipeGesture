package com.android.busroutes.sample.rest.response;

import java.util.List;

import com.android.busroutes.sample.model.ImageInfo;

public class ImagesResponse extends BaseServiceResponse {

    private List<ImageInfo> images;

    public List<ImageInfo> getImages() {
        return images;
    }

    public void setRoutes(List<ImageInfo> imageInfos) {
        this.images = imageInfos;
    }
}
